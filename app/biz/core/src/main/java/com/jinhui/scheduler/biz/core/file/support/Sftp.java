package com.jinhui.scheduler.biz.core.file.support;/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Sftp {

	private final static Logger LOGGER = LoggerFactory.getLogger(Sftp.class);

	private final String user;
	private final String password;
	private final String host;
	private final int port;
	private final boolean ssl;
	private final int timeout = 60000;

	private final static ExecutorService executor = Executors.newSingleThreadExecutor();

	public Sftp(String user, String password, String host, String port) {
		this.user = user;
		this.password = password;
		this.host = host;
		this.port = Integer.parseInt(port.trim());
		this.ssl = true;
	}

	public Sftp(String user, String password, String host, int port, boolean ssl) {
		this.user = user;
		this.password = password;
		this.host = host;
		this.port = port;
		this.ssl = ssl;
	}

	/**
	 * 列出server指定目录下的文件集
	 * @param ftpDst 目录
	 * @return
	 */
	public Set<String> ls(final String ftpDst){
		final String dst = ftpPath(ftpDst);
		final Set<String> files = new HashSet<>();
		execute(new Action() {
			@Override
			public void doAction(FtpAction c) throws Exception {
				try {
					files.addAll(c.ls(dst));
				}catch(SftpException | FtpProtocolException e){
					LOGGER.info("目录={}, 不存在", dst);
					throw new RuntimeException(e);
				}
			}
		});
		return files;
	}

	public void get(String ftpSrc, String ftpDst){
		final String src = ftpPath(ftpSrc);
		final String dst = ftpPath(ftpDst);
		execute(new Action() {
			@Override
			public void doAction(FtpAction c) throws Exception {
				try {
					//检查文件是否存在
					c.ls(src);
				}catch(SftpException | FtpProtocolException e){
					LOGGER.info("文件={}, 不存在", src);
					throw new RuntimeException(e);
				}
				c.get(src, dst);
			}
		});
	}

	/**
	 * 对于耗时网络IO，使用异步发送文件
	 * @param ftpSrc
	 * @param ftpDst 远程server目录
	 */
	public void asycPut(final String ftpSrc, final String ftpDst, final UploadCompletedAction action){
		final String src = ftpPath(ftpSrc);
		final String dst = ftpPath(ftpDst);
		Future future = executor.submit(new Runnable() {
			@Override
			public void run() {
				execute(new Action() {
					@Override
					public void doAction(FtpAction c) throws Exception {
						try {
							//检查目录是否存在
							c.ls(dst);
						}catch(SftpException | FtpProtocolException e){
							//目录不能存在，创建目录
							c.mkdir(dst);
						}
						//发送文件
						c.put(src, dst);
						action.action();
					}
				});
			}
		});
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			future.cancel(true);
		}
	}

	public interface UploadCompletedAction {
		void action();
	}

	private void execute(Action action) {
		if(ssl) {
			executeSftpAction(action);
		} else {
			executeFtpAction(action);
		}
	}

	private void executeFtpAction(Action action) {
		FtpClient ftpClient = null;
		try {
			ftpClient = FtpClient.create(new InetSocketAddress(host, port));
			ftpClient.login(user, password.toCharArray());
			action.doAction(new FtpAction(ftpClient));
		} catch (Exception e) {
			LOGGER.info("ftp异常",e);
			throw new RuntimeException(e);
		} finally {
			if(ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.close();
				} catch (IOException e) {
					LOGGER.info("ftp异常", e);
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * 每建立一次连接只发送一个文件
	 */
	private void executeSftpAction(Action action) {
		Session session = null;
		Channel channel = null;
		try{
			JSch jsch=new JSch();
			session = jsch.getSession(user, host, port);
			session.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			session.setConfig(sshConfig);
			session.connect(timeout);
			channel=session.openChannel("sftp");
			channel.connect(timeout);
			ChannelSftp c=(ChannelSftp)channel;
			action.doAction(new FtpAction(c));
		} catch(Exception e){
			LOGGER.info("sftp异常",e);
			throw new RuntimeException(e);
		} finally {
			if(channel != null){
				channel.disconnect();
			}
			if(session != null){
				session.disconnect();
			}
		}
	}

	private interface Action{
		void doAction(FtpAction ftp) throws Exception;
	}

	private class FtpAction {
		private ChannelSftp sftp;
		private FtpClient ftp;

		public FtpAction(ChannelSftp sftp) {
			this.sftp = sftp;
		}

		public FtpAction(FtpClient ftp) {
			this.ftp = ftp;
		}

		public Set<String> ls(String path) throws Exception {
			Set<String> lsRes = new HashSet<>();
			if(sftp != null) {
				Vector<ChannelSftp.LsEntry> dir = sftp.ls(path);
				for(ChannelSftp.LsEntry entry: dir){
					if(!entry.getFilename().equals(".") && !entry.getFilename().equals(".."))
						lsRes.add(entry.getFilename());
				}
			} else if (ftp != null){
				Iterator<FtpDirEntry> files = ftp.listFiles(path);
				while (files.hasNext()) {
					FtpDirEntry file = files.next();
					lsRes.add(file.getName());
				}
			}
			return lsRes;
		}

		public void mkdir(String path) throws Exception {
			if(sftp != null) {
				sftp.mkdir(path);
			} else  if(ftp != null) {
				ftp.makeDirectory(path);
			}
		}

		public void get(String src, String dst) throws Exception {
			if(sftp != null) {
				sftp.get(src, dst);
			} else if (ftp != null){
				File file = new File(dst+src.substring(src.lastIndexOf("/")));
				ftp.getFile(src, new FileOutputStream(file));
			}
		}

		public void put(String src, String dst) throws Exception {
			if(sftp != null) {
				sftp.put(src, dst);
			} else if (ftp != null) {
				dst = dst+src.substring(src.lastIndexOf("/"));
				ftp.putFile(dst, new FileInputStream(src));
			}
		}

	}

	private static String ftpPath(String srcPath){
		return srcPath.replaceAll("\\\\","/");
	}

	public static void main(String[] strs) {
		FtpClient ftpClient = null;
		try {
			ftpClient = FtpClient.create("219.143.244.227");
			ftpClient.login("B00000603","B00000603123".toCharArray());
			Iterator<FtpDirEntry> dirs = ftpClient.listFiles(".");
			while (dirs.hasNext()) {
				FtpDirEntry dir = dirs.next();
				System.out.println(dir.getName());
			}
			ftpClient.putFile("./REDEEM_SETTLE/redemption_B00000603_20170619.TXT", new FileInputStream("D:\\scheduler\\workspace\\upload\\zlrt\\redemption_B00000603_20170619.TXT"));
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ftpClient != null)
				try {
					ftpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}

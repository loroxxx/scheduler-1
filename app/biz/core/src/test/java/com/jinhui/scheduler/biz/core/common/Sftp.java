package com.jinhui.scheduler.biz.core.common;/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */

import com.jcraft.jsch.*;
import org.springframework.core.io.Resource;

import java.util.Properties;
import java.util.Vector;

public class Sftp{
	public static void main(String[] arg){

		try{
			  JSch jsch=new JSch();
              //jsch.setKnownHosts(Sftp.class.getClassLoader().getResourceAsStream("key"));
			  String host="163.53.88.26";
			  String user="jhjr";
			  int port=22000;
			  Session session=jsch.getSession(user, host, port);
              session.setPassword("jhjr@2016");
			  // username and password will be given via UserInfo interface.
			  /*UserInfo ui=new MyUserInfo();
			  session.setUserInfo(ui);*/
				Properties sshConfig = new Properties();
				sshConfig.put("StrictHostKeyChecking", "no");
				session.setConfig(sshConfig);

			  session.connect(10000);
			  Channel channel=session.openChannel("sftp");
			  channel.connect(10000);
			  ChannelSftp c=(ChannelSftp)channel;

              String p1 = "D:/scheduler/workspace/upload/cmbfae/20170605/test.txt";
			  String p2="./20160907";
			  try{
                  System.out.println(c.pwd());
                  System.out.println(c.lpwd());
				  //c.put(p1, p2);
				  try {
					  c.ls("./20170429");
				  }catch(SftpException e){
					  c.mkdir("./20170429");
				  }

				  Vector<ChannelSftp.LsEntry> dir = c.ls("./20170608");
				  for(ChannelSftp.LsEntry entry: dir){
					  System.out.println(entry.getFilename());
				  }
			  }
			  catch(SftpException e){
				  System.out.println(e.toString());
			  }
			  session.disconnect();
		} catch(Exception e){
	  		System.out.println(e);
		}
	}
}

package com.jinhui.scheduler.biz.cmbfae.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	public static String getLine(){
		return System.getProperty("line.separator");
	}
	
	
	/**
	 * 获取文件名
	 */
	public static String getJsonFileName(String fileType){
		Configuration config =getConfig();
		String instId = config.getString("$INSTID");
		String prefix = config.getString(fileType);
		String fileName = prefix.replace("$INSTID", instId);
		return fileName;
		
	}
	
	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig(){
		try {
			return new PropertiesConfiguration("common.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException("获取配置文件失败，", e);
		}
	}
	
	
	
	public static String toString(Object obj) {
		 
        if(obj == null) 
            return "null";
         
        StringBuffer sb = new StringBuffer();
 
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
         
        sb.append(clazz.getName() + "{");
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                sb.append("\n  " + field.getName() + ":" + field.get(obj));
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        sb.append("\n}");
         
        return sb.toString();
    }
	
	
	public static String certTypeConverter(String userFlag,String certType){
		
		String cert = getConfig().getString(userFlag+","+certType);
		if(cert==null||cert.equals("")){
			cert="A";
		}
		
		return cert;
		
	}
	


	/**
	 * 检查目录下的招银文件，记录每个文件错误信息，有错误返回true
	 *
	 * @throws IOException
	 */
	public static boolean checkFiles(List<InstitutionFile> files)  {
		boolean hasError = false;

		for (InstitutionFile institutionFile : files) {

			String filePath = institutionFile.getFileWorkspaceLocation() + File.separator + institutionFile.getFileName();
			try {
				if (checkLine(new File(filePath))) {
                    hasError = true;
                    continue;
                }
			} catch (Exception e) {
				log.error("文件检查错误",e);
				throw new RuntimeException("招银文件检查错误:"+filePath);
			}
			log.info("检查"+institutionFile.getFileName() + "文件返回正确");
		}
		return hasError;

	}

	private static boolean checkLine(File file) throws IOException {
		LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8");
		boolean hasError = false;
		try {
			while (lineIterator.hasNext()) {
				String line = lineIterator.next();
				Map<String, String> lineMap = JSON.parseObject(line, Map.class);
				String status = lineMap.get("status");

				// 行内有错误标志0
				if (status.equals("0")) {
					log.error(file.getName() + " 有错误信息：" + lineMap);
					hasError = true;
				}
			}
			return hasError;
		} finally {
			LineIterator.closeQuietly(lineIterator);
		}
	}


}

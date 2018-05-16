package com.jinhui.scheduler.biz.imiqian.utils;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by confirmExport on 2017/5/24.
 */
public class TemplateFileReader {
    //将读入的模版数据存放到内存
    private static Map<FileType, String> template = new HashMap<>(FileType.values().length);
    static {
        /**
         * 在class load时初始化模版文件数据
         */
        for(FileType fileType: FileType.values()){
            template.put(fileType, fileReader(fileType));
        }
    }

    enum FileType{
        AccountConfirmHeader("OFD_CF_301_02_header.txt","帐户确认文件头"),
        SubscribeConfirmHeader("OFD_CF_301_04_header.txt","认购确认文件头");
        private String filename;
        private String describe;
        FileType(String filename, String describe){
            this.filename = filename;
            this.describe = describe;
        }
        public String getFilename(){
            return this.filename;
        }
        public String getDesc(){
            return this.describe;
        }
    }

    public static String fileReader(TemplateFileReader.FileType fileType){
        try(BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:file"+
                File.separator + fileType.getFilename())))){
            StringBuilder appender = new StringBuilder();
            String str = reader.readLine();
            do{
                appender.append(str).append(System.lineSeparator());
            }while ((str = reader.readLine()) != null);
            return appender.toString();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取帐户确认文件文件头
     * @param date
     * @return
     */
    public static String getAccountConfirmHeader(String date){
        return template.get(FileType.AccountConfirmHeader).replace("#{date}", date);
    }

    /**
     * 获取认申购确认文件文件头
     * @param date
     * @return
     */
    public static String getSubscribeConfirmHeader(String date){
        return template.get(FileType.SubscribeConfirmHeader).replace("#{date}", date);
    }
}

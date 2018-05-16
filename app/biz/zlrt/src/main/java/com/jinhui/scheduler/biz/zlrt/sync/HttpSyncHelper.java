package com.jinhui.scheduler.biz.zlrt.sync;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 与交易平台http交互帮助类
 *
 * @autor wsc
 * @create 2018-03-06 10:13
 **/
@Component
public class HttpSyncHelper {
    private static Logger logger = LoggerFactory.getLogger(HttpSyncHelper.class);

    private static String uri;

    public HttpSyncHelper(String uri) {
        this.uri = uri;
    }

    private static boolean httpGet(String url) throws IOException {
        logger.info("请求URL： "+ url);
        // 创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建GET请求（在构造器中传入URL字符串即可）
        HttpGet get = new HttpGet(url);
        // 调用HttpClient对象的execute方法获得响应
        HttpResponse response = client.execute(get);
        // 调用HttpResponse对象的getEntity方法得到响应实体
        HttpEntity httpEntity = response.getEntity();
        logger.info(httpEntity.toString());
        // 使用EntityUtils工具类得到响应的字符串表示
        String result = EntityUtils.toString(httpEntity,"utf-8");
        logger.info(result);
        if(result != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
             return true;
        }
        return false;
    }

    //同步渠道产品
    public static boolean syncChnProduct() throws IOException {
        return HttpSyncHelper.httpGet(uri + "/syncData/syncChnProduct");
    }

    //同步用户和交易状态
    public static boolean syncStatus() throws IOException {
        return HttpSyncHelper.httpGet(uri + "/syncData/syncStatus");
    }

    //生成渠道申请文件
   /* public static boolean generateChnnalFiles() throws IOException {
        return HttpSyncHelper.httpGet(uri + "/generateFile/generateAllFiles");
    }*/


    public static void main(String[] args) throws IOException {
        HttpSyncHelper.syncChnProduct();
    }

}

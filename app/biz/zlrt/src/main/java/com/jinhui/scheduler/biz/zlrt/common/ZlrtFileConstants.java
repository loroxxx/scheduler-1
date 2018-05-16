package com.jinhui.scheduler.biz.zlrt.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wsc on 2017/05/21
 */
@Component
public class ZlrtFileConstants {

    public static final String HEADERFILEPATH_Redeem = "classpath:file/Redeem_B00000603_01_header.txt";  //报送给证联的赎回业务文件头


    public static final String HEADERFILEPATH_Purchase = "classpath:file/purchase_B00000603_01_header.txt";  //结算类业务_申购结算文件头

    public static final String HEADERFILEPATH_Redemption = "classpath:file/redemption_B00000603_01_header.txt";  //结算类业务_赎回结算文件头

    //报送证联文件前缀
    public static String[] sendFileArray = new String[]{"Redeem","purchase","redemption"};
    //文件类型，对应上面的文件 001赎回类   003申购结算   004赎回结算
    public static String[] fileTypeArray = new String[]{"001","003","004"};
    //文件ftp服务器路径，对应上面的文件
    public static String[] filePathArray = new String[]{"/REDEEM/","/PURCHASE_SETTLE/","/REDEEM_SETTLE/"};

    public static String xuhao;    //给证联的赎回文件和监管报送文件 最后两位序号

    @Value("${xuhao}")
    public void setXuhao(String xuHao) {
        xuhao = xuHao;
    }
}
package com.jinhui.scheduler.biz.zlrt.utils;


/*import com.zlink.zlpay.commonofs.common.ZlpayUtil;
import com.zlink.zlpay.commonofs.common.exception.MsgException;*/

/**
 * @autor wsc
 * @create 2017-11-20 10:00
 **/
public class TestZlrt {

    private static String cerPath = "D:/jks_cer/压力/压力测试环境-证联自己与华创平台商数字证书/zlzf.cer";
    private static String pfxPath = "D:/jks_cer/压力/压力测试环境-证联自己与华创平台商数字证书/B00000001.pfx";
    private static String keyPwd = "111111";

    /*public static void main(String[] args) throws MsgException {
        String data = "instuId=B00000001|fundDate=20160714|fundTime=155601|fundSeqId=983e4fe5e5a83061c3218b212cbcbdea|preFundSeqId=11|TradeType=01|resv=11|";
        System.out.println("明文：：：：：：：" + data);

        String encryptData = ZlpayUtil.encryptData(data, cerPath);
        System.out.println("加密结果：：：：：：：" + encryptData);

        String signData = ZlpayUtil.signData(data, pfxPath, keyPwd);
        System.out.println("加签结果：：：：：：：" + signData);
    }*/
}

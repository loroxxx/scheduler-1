package com.jinhui.scheduler.biz.zlrt.utils.zlrt;

import com.zlink.zlpay.commonofs.common.ZlpayUtil;
import com.zlink.zlpay.commonofs.common.exception.MsgException;
import com.zlink.zlpay.commonofs.common.util.Constants;
import demo.util.HttpRequestParam;
import demo.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 与证联http交互帮助类
 *
 * @autor wsc
 * @create 2018-01-31 14:28
 **/
@Component
public class ZlrtHttpUtils {
    private static Logger logger = LoggerFactory.getLogger(ZlrtHttpUtils.class);

    private static String root = RequestContext.class.getResource("/").getFile();
    private static String cerPath = root + "cert/zlzf_uat.cer";
    private static String pfxPath = root + "cert/通用商户私钥.pfx";
    private static String INSTU_ID;
    private static String keyPwd;
    private static String ZLRT_API_BACK_URI;

    @Value("${INSTU_ID}")
    public void setInstuId(String instuId) {
        INSTU_ID = instuId;
    }
    @Value("${keyPwd}")
    public void setKeyPwd(String keypwd) {
        keyPwd = keypwd;
    }
    //后台表单方式URL
    @Value("${ZLRT_API_BACK_URI}")
    public void setZlrtApiBackUri(String zlrtApiBackUri) {
        ZLRT_API_BACK_URI = zlrtApiBackUri;
    }

    public static String getInstuId() {
        return INSTU_ID;
    }

    public static String getKeyPwd() {
        return keyPwd;
    }

    public static String getZlrtApiBackUri() {
        return ZLRT_API_BACK_URI;
    }

    //通知类接口后缀
    private static String GET_MSG_BACK_URL = "/fundofs/bg/getmsg";


    private static String generateData(Object obj)  throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String data = "";
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field:fields){
            String value = "";
            String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            String type = field.getGenericType().toString();    //获取属性的类型
            if (type.equals("class java.lang.String")) {
                Method m = obj.getClass().getMethod("get" + name);
                value = (String) m.invoke(obj);
            }
            if(value != null && !"".equals(value)){
                data = data + field.getName() + "="+value + "|";
            }
        }
        logger.info(data);
        return data;
    }

    private static Object generateObj(String msg,Object obj) throws IllegalAccessException {
        String[] params = msg.split("\\|");
        Field[] fields = obj.getClass().getDeclaredFields();
        for(String param:params){
            for(Field field:fields) {
                String type = field.getGenericType().toString();    //获取属性的类型
                if(param.contains(field.getName())){
                    if (type.equals("class java.lang.String")) {
                        field.set(obj,param.substring(param.indexOf("=")+1,param.length()));
                    }else if(type.equals("class java.math.BigDecimal")){
                        field.set(obj,new BigDecimal(param.substring(param.indexOf("=")+1,param.length())));
                    }
                }
            }
        }
        return obj;
    }

    private static String sendMsg(String instuId, String transType,
                                  String encMsg, String checkValue, String sendUrl) throws MsgException {
        try {
            List<HttpRequestParam> params = bulidParam(instuId, transType,  URLEncoder.encode(encMsg,"UTF-8"), URLEncoder.encode(checkValue,"UTF-8"));
            HttpUtils http = new HttpUtils();
            http.openConnection();
            String responseMsg = http.executeHttpPost(sendUrl, params, Constants.ENCODING);
            http.closeConnection();
            if("".equals(responseMsg)||responseMsg == null){
                throw new MsgException(Constants.ERROR_SENDFAIL);
            }
            return responseMsg;
        } catch (Exception e){
            throw new MsgException(e.getMessage());
        }
    }
    /**
     * @description 组装发送参数
     * @param instuId
     * @param transType
     * @param encMsg
     * @param checkValue
     * @return
     * @author ZhangDM(Mingly)
     * @date 2012-10-21
     */
    private static List<HttpRequestParam> bulidParam(String instuId, String transType,
                                                     String encMsg, String checkValue){

        HttpRequestParam verNumParam = new HttpRequestParam();
        verNumParam.setParaName(Constants.VERNUM);
        verNumParam.setParaValue(Constants.VERNUM_VALUE);
        HttpRequestParam sysDateTimeParam = new HttpRequestParam();
        sysDateTimeParam.setParaName(Constants.SYSDATETIME);
        sysDateTimeParam.setParaValue(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        HttpRequestParam instuIdParam = new HttpRequestParam();
        instuIdParam.setParaName(Constants.INSTUID);
        instuIdParam.setParaValue(instuId);
        HttpRequestParam transTypeParam = new HttpRequestParam();
        transTypeParam.setParaName(Constants.TRANSTYPE);
        transTypeParam.setParaValue(transType);
        HttpRequestParam encMsgParam = new HttpRequestParam();
        encMsgParam.setParaName(Constants.ENCMSG);
        encMsgParam.setParaValue(encMsg);
        HttpRequestParam checkValueParam = new HttpRequestParam();
        checkValueParam.setParaName(Constants.CHECKVALUE);
        checkValueParam.setParaValue(checkValue);

        List<HttpRequestParam> params=new ArrayList<HttpRequestParam>();
        params.add(verNumParam);
        params.add(sysDateTimeParam);
        params.add(instuIdParam);
        params.add(transTypeParam);
        params.add(checkValueParam);
        params.add(encMsgParam);

        return params;
    }

    private static String responseMsg(Object obj,String transType,String url) throws Exception{
        logger.info(cerPath);
        logger.info(ZLRT_API_BACK_URI + url);
        String responseMsg = sendMsg(INSTU_ID, transType,
                ZlpayUtil.encryptData(generateData(obj), cerPath),
                ZlpayUtil.signData(generateData(obj), pfxPath, keyPwd),
                ZLRT_API_BACK_URI + url);
        logger.info("证联返回： " + responseMsg);
        return ZlpayUtil.parseResponse(responseMsg, cerPath, pfxPath, keyPwd);
    }

    //通知
    public static AnnounceResponse announce(AnnounceRequest announceRequest) throws Exception {
        String msg = responseMsg(announceRequest,"000", GET_MSG_BACK_URL);
        logger.info("明文： " + msg);
        return (AnnounceResponse) generateObj(msg,new AnnounceResponse());
    }


    public static void main(String[] args) throws IOException {
        /*AnnounceRequest announceRequest = new AnnounceRequest();
        announceRequest.setInstuId("B00000603");
        announceRequest.setFundDate(DateUtils.getCurrentDate());
        announceRequest.setFundTime(DateUtils.getCurrentTime());
        announceRequest.setFundSeqId("7013333330303356");
        announceRequest.setFileName("Redeem_B00000603_20180306_01.txt");
        announceRequest.setFilePath("/REDEEM");
        announceRequest.setBusiType("001");

        try {
            System.out.println(ZlrtHttpUtils.announce(announceRequest).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

}

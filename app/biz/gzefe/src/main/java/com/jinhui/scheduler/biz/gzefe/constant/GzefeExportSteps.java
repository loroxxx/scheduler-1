package com.jinhui.scheduler.biz.gzefe.constant;

/**
 * 记录
 * Created by Administrator on 2018/1/11 0011.
 */
public enum GzefeExportSteps {

    STEP_0("statisticsChildProduct", " 根据子产品生成贵股交业务汇总信息"),
    STEP_1("gzefeAccountApplyStep", "生成账户申请文件01"),
    STEP_2("gzefeTransApplyStep", "生成交易文件03"),
    STEP_3("gzefeIncomingStep", "生成分红文件06"),
    STEP_4("gzefeGatherStep", "生成业务汇总信息12"),
    STEP_5("uploadToFtp", "上传到ftp服务器"),
    STEP_6("rollback", "回滚操作");

    private String code;
    private String desc;

    GzefeExportSteps(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }







}

package com.jinhui.scheduler.biz.cmbfae.constans;

/**
 * 记录与招银的文件交互记录
 * Created by Administrator on 2018/1/11 0011.
 */
public enum CmbfaeExportSteps {

    STEP_START("", "未开始"),

    STEP_0("importProduct", "导入交易所的产品文件"),
    STEP_01("createChildProduct", "生成子产品文件"),
    STEP_02("uploadChildProduct", "上传子产品文件"),
    STEP_03("confirmChildProduct", "交易所确认子产品文件成功"),

    STEP_1("createTransFile", "生成交易文件"),
    STEP_2("createPositionFile", "生成持仓文件"),
    STEP_3("createProfitFile", "生成收益文件"),

    STEP_4("uploadTransFile", "上传交易文件"),
    STEP_5("confirmTransFile", "交易所确认交易文件成功"),

    STEP_6("uploadPositionFile", "上传持仓文件"),
    STEP_7("confirmPositionFile", "交易所确认持仓文件成功"),

    STEP_8("uploadProfitFile", "上传收益文件"),
    STEP_9("confirmProfitFile", "交易所确认收益文件成功");

    private String code;
    private String desc;

    CmbfaeExportSteps(String code, String desc) {
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

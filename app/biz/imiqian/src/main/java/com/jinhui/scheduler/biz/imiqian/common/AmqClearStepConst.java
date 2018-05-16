package com.jinhui.scheduler.biz.imiqian.common;

/**
 * 清算步骤常量
 *
 * create by wsc 2017-05-22 20:02
 *
 **/
public class AmqClearStepConst {

    public static enum STEPS{
        STEP_0,STEP_1,STEP_2,STEP_3,STEP_4,STEP_5,STEP_6,STEP_7,STEP_8,STEP_9,STEP_10,STEP_11,STEP_12,STEP_13,STEP_14
    }

    //清算步骤
    public static enum ClearSteps {
        STEP_0("amqCheckIndexFileStep", "检测索引文件"),
        STEP_1("amqAccountApplyStep", "将01账户申请文件入库"),  //将账户申请文件入库
        STEP_2("amqTradeApplyStep", "将03交易申请文件入库"),    //将交易申请文件入库
        STEP_3("transferDataToTemp", "将T日交易数据备份至临时表"),     //将T日交易数据备份至临时表
        STEP_4("backupToPositionHistory", "备份T-1日的持仓数据"),     //备份T-1日的持仓数据
        STEP_5("batchGenerateIncome", "生成T日客户当前持有金额的收益"),  //生成T日客户当前持有金额的收益
        STEP_6("caculateInvestorPosition", "统计持仓"),               //统计持仓
        STEP_7("gatherBusiness", "业务数据汇总"),                     //业务数据汇总
        STEP_8("upgrateBatchCode", "更新批次"),                     //更新批次
        STEP_9("amqAccountConfirmStep", "生成02账户确认文件"),
        STEP_10("amqTradeConfirmStep", "生成04交易确认文件"),
        STEP_11("amqReconciliateDataStep", "生成05对账数据文件"),
        STEP_12("amqIncomeStep", "生成06基金分红文件"),
        STEP_13("amqGatherDataStep", "生成12业务数据汇总文件"),
        STEP_14("rollBack", "回滚");   //回滚

        private String clearCode;
        private String clearDesc;

        ClearSteps(String clearCode, String clearDesc) {
            this.clearCode = clearCode;
            this.clearDesc = clearDesc;
        }

        public static String returnClearDesc(String code) {
            for (ClearSteps busCode : ClearSteps.values()) {
                if (busCode.getClearCode().equals(code)) {
                    return busCode.getClearDesc();
                }
            }
            return null;
        }

        public String getClearCode() {
            return clearCode;
        }

        public String getClearDesc() {
            return clearDesc;
        }
    }

}

package com.jinhui.scheduler.biz.zlrt.common;

/**
 * 证联对账步骤常量
 *
 * create by wsc 2017-11-22 20:02
 *
 **/
public class ZlrtClearStepConst {

    public static enum STEPS{
        STEP_15,STEP_16,STEP_17,STEP_18,STEP_19,STEP_20
    }

    //清算步骤
    public static enum ClearSteps {
        STEP_15("importBalanceAccStep", "导入证联对账单"), //导入证联对账单
        STEP_16("balanceAccountStep", "证联对账单对账"),  //证联对账单对账
        STEP_17("redeemStep", "赎回文件报送"),    //赎回文件报送
        STEP_18("purchaseStep", "监管申购文件报送"), //监管申购文件报送
        STEP_19("redemptionStep", "监管赎回文件报送"),  //监管赎回文件报送
        STEP_20("announceZlrtStep", "通知证联");  //通知证联

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

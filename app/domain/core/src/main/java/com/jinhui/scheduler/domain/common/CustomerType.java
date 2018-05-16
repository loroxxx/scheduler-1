package com.jinhui.scheduler.domain.common;

/**
 * Created by jinhui on 2017/5/25.
 */
public enum CustomerType {
    Personal("1","个人"), Institution("0","机构"), SelfSupport("2","自营");
    private String code, text;
    CustomerType(String code, String text){
        this.code = code;
        this.text = text;
    }
    public static CustomerType codeOf(String code){
        for(CustomerType type:CustomerType.values()){
            if(type.getCode().equals(code)){
                return type;
            }
        }
        throw  new IllegalArgumentException("未知客户类型");
    }

    public String getCode(){
        return this.code;
    }

    public String getText(){
        return this.text;
    }

}

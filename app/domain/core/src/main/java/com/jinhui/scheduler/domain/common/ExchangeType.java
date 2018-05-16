package com.jinhui.scheduler.domain.common;

/**
 * 记录交易所的映射,在数据库添加了新的交易所后，也要在这个类中配置对应的枚举
 * Created by Administrator on 2018/1/18 0018.
 */
public enum ExchangeType {

    Cmbfae("Cmbfae","ZHAOYIN","招银前海"),
    Gzefe("Gzefe","gzefe","贵股交"),
    HuNan("HuNan","9990","湖南金融交易所");


    private String exchangeCode;//交易所代码，在程序中使用
    private String exchangeNo;//交易所代码，记录在数据库中
    private String exchangeName;//交易所名称


    ExchangeType(String exchangeCode, String exchangeNo, String exchangeName) {
        this.exchangeCode = exchangeCode;
        this.exchangeNo = exchangeNo;
        this.exchangeName = exchangeName;
    }


    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getExchangeNo() {
        return exchangeNo;
    }

    public void setExchangeNo(String exchangeNo) {
        this.exchangeNo = exchangeNo;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }
}

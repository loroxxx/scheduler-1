package com.jinhui.scheduler.domain.zlrt;

import java.math.BigDecimal;

public class ExchangeInfo {
    private Integer id;

    private String zlInstuId;

    private String exchangeCode;

    private String exchangeName;

    private String idType;

    private String idNo;

    private String contacts;

    private String artificial;

    private String adress;

    private String phone;

    private BigDecimal currentProductLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZlInstuId() {
        return zlInstuId;
    }

    public void setZlInstuId(String zlInstuId) {
        this.zlInstuId = zlInstuId == null ? null : zlInstuId.trim();
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode == null ? null : exchangeCode.trim();
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName == null ? null : exchangeName.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getArtificial() {
        return artificial;
    }

    public void setArtificial(String artificial) {
        this.artificial = artificial == null ? null : artificial.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public BigDecimal getCurrentProductLimit() {
        return currentProductLimit;
    }

    public void setCurrentProductLimit(BigDecimal currentProductLimit) {
        this.currentProductLimit = currentProductLimit;
    }
}
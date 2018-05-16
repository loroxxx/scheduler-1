package com.jinhui.scheduler.domain.core;

import java.math.BigDecimal;

public class ExchangeInfo {
    /**
     * 表主键
     */
    private Integer id;

    /**
     * 证联商户机构代码
     */
    private String zlInstuId;

    /**
     * 交易所代码
     */
    private String exchangeCode;

    /**
     * 交易所名称
     */
    private String exchangeName;

    /**
     * 证件类型：0-组织机构代码证，1-营业执照，2-社会统一信用代码
     */
    private String idType;

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 法人代表
     */
    private String artificial;

    /**
     * 地址
     */
    private String adress;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 活期产品额度
     */
    private BigDecimal currentProductLimit;

    /**
     * 获取表主键
     *
     * @return id - 表主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置表主键
     *
     * @param id 表主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取证联商户机构代码
     *
     * @return zl_instu_id - 证联商户机构代码
     */
    public String getZlInstuId() {
        return zlInstuId;
    }

    /**
     * 设置证联商户机构代码
     *
     * @param zlInstuId 证联商户机构代码
     */
    public void setZlInstuId(String zlInstuId) {
        this.zlInstuId = zlInstuId == null ? null : zlInstuId.trim();
    }

    /**
     * 获取交易所代码
     *
     * @return exchange_code - 交易所代码
     */
    public String getExchangeCode() {
        return exchangeCode;
    }

    /**
     * 设置交易所代码
     *
     * @param exchangeCode 交易所代码
     */
    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode == null ? null : exchangeCode.trim();
    }

    /**
     * 获取交易所名称
     *
     * @return exchange_name - 交易所名称
     */
    public String getExchangeName() {
        return exchangeName;
    }

    /**
     * 设置交易所名称
     *
     * @param exchangeName 交易所名称
     */
    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName == null ? null : exchangeName.trim();
    }

    /**
     * 获取证件类型：0-组织机构代码证，1-营业执照，2-社会统一信用代码
     *
     * @return id_type - 证件类型：0-组织机构代码证，1-营业执照，2-社会统一信用代码
     */
    public String getIdType() {
        return idType;
    }

    /**
     * 设置证件类型：0-组织机构代码证，1-营业执照，2-社会统一信用代码
     *
     * @param idType 证件类型：0-组织机构代码证，1-营业执照，2-社会统一信用代码
     */
    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    /**
     * 获取证件号码
     *
     * @return id_no - 证件号码
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * 设置证件号码
     *
     * @param idNo 证件号码
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    /**
     * 获取联系人
     *
     * @return contacts - 联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置联系人
     *
     * @param contacts 联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    /**
     * 获取法人代表
     *
     * @return artificial - 法人代表
     */
    public String getArtificial() {
        return artificial;
    }

    /**
     * 设置法人代表
     *
     * @param artificial 法人代表
     */
    public void setArtificial(String artificial) {
        this.artificial = artificial == null ? null : artificial.trim();
    }

    /**
     * 获取地址
     *
     * @return adress - 地址
     */
    public String getAdress() {
        return adress;
    }

    /**
     * 设置地址
     *
     * @param adress 地址
     */
    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取活期产品额度
     *
     * @return current_product_limit - 活期产品额度
     */
    public BigDecimal getCurrentProductLimit() {
        return currentProductLimit;
    }

    /**
     * 设置活期产品额度
     *
     * @param currentProductLimit 活期产品额度
     */
    public void setCurrentProductLimit(BigDecimal currentProductLimit) {
        this.currentProductLimit = currentProductLimit;
    }
}
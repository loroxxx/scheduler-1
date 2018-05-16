package com.jinhui.scheduler.domain.core;

import java.math.BigDecimal;

public class ChnInfo {
    /**
     * 表主键
     */
    private Integer id;

    private String codeName;

    /**
     * 渠道代码，渠道在平台中的代码标识
     */
    private String chnCode;

    /**
     * 渠道名称
     */
    private String chnName;

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
     * 渠道总额度
     */
    private BigDecimal totalLimit;

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
     * @return code_name
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * @param codeName
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    /**
     * 获取渠道代码，渠道在平台中的代码标识
     *
     * @return chn_code - 渠道代码，渠道在平台中的代码标识
     */
    public String getChnCode() {
        return chnCode;
    }

    /**
     * 设置渠道代码，渠道在平台中的代码标识
     *
     * @param chnCode 渠道代码，渠道在平台中的代码标识
     */
    public void setChnCode(String chnCode) {
        this.chnCode = chnCode == null ? null : chnCode.trim();
    }

    /**
     * 获取渠道名称
     *
     * @return chn_name - 渠道名称
     */
    public String getChnName() {
        return chnName;
    }

    /**
     * 设置渠道名称
     *
     * @param chnName 渠道名称
     */
    public void setChnName(String chnName) {
        this.chnName = chnName == null ? null : chnName.trim();
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
     * 获取渠道总额度
     *
     * @return total_limit - 渠道总额度
     */
    public BigDecimal getTotalLimit() {
        return totalLimit;
    }

    /**
     * 设置渠道总额度
     *
     * @param totalLimit 渠道总额度
     */
    public void setTotalLimit(BigDecimal totalLimit) {
        this.totalLimit = totalLimit;
    }
}
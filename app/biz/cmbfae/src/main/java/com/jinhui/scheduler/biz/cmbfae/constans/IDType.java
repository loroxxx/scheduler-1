package com.jinhui.scheduler.biz.cmbfae.constans;

/**
 * 清算平台和招银前海的对应关系，证件类型枚举类
 * <p>
 * Created by Administrator on 2018/2/28 0028.
 */
public enum IDType {

    identity_card("身份证", "00", "0"),
    passport("护照", "01", "4"),
    officers_cert("军官证", "02", "5"),
    soldiers_cert("士兵证", "03", "6"),
    hk_pass("港澳通行证", "04", "9"),
    household_Register("户口本", "05", "8"),
    organization_code("组织机构代码证", "10", "2"),
    business_license("营业执照", "11", "1"),
    unified_social_credit_code("统一社会信用代码", "19", "3"),
    foundation_card("基金会", "17", "A"),
    foreign_passport("外国护照", "06", "A"),
    other("其他", "07", "A"),
    civilian_cadres_card("文职证", "08", "A"),
    police_card("警官证", "09", "A"),
    taiwan_card("台胞证", "0A", "A");

    private String name;
    private String code;//交易后台定义的证件类型
    private String cmbfaeCode;//对应招银前海的的证件类型


    /**
     * 根据交易后台定义的证件类型，得到对应招银前海的证件类型
     */
    public static String transform(String code) {
        IDType[] values = IDType.values();
        for (IDType value : values) {
            if (value.getCode().equals(code)) {
                return value.getCmbfaeCode();
            }
        }
        throw new RuntimeException("不支持的证件类型");
    }


    IDType(String name, String code, String cmbfaeCode) {
        this.name = name;
        this.code = code;
        this.cmbfaeCode = cmbfaeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCmbfaeCode() {
        return cmbfaeCode;
    }

    public void setCmbfaeCode(String cmbfaeCode) {
        this.cmbfaeCode = cmbfaeCode;
    }

    @Override
    public String toString() {
        return "IDType{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", cmbfaeCode='" + cmbfaeCode + '\'' +
                '}';
    }
}

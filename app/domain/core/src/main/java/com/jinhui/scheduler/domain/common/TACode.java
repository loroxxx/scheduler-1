package com.jinhui.scheduler.domain.common;

/**
 * Created by jinhui on 2017/5/25.
 */
public enum  TACode {

    Cmbfae("001", InstitutionType.Cmbfae);

    private String code;
    private InstitutionType institutionType;

    TACode(String code, InstitutionType institutionType){
        this.code= code;
        this.institutionType = institutionType;
    }

    public static TACode codeOf(String code){
        for(TACode ta:TACode.values()){
            if(ta.getCode().equals(code))
                return ta;
        }
        throw new IllegalArgumentException("未知渠道代码");
    }


    public String getCode() {
        return code;
    }

    public InstitutionType getInstitutionType() {
        return institutionType;
    }
}

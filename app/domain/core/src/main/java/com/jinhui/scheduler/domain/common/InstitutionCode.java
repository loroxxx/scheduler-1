package com.jinhui.scheduler.domain.common;

/**
 * Created by jinhui on 2017/6/13.
 */
public enum InstitutionCode {
    Imiqian("1018", InstitutionType.Imiqian), Cmbfae("001", InstitutionType.Cmbfae),
    ZLRT("", InstitutionType.ZLRT),
    Gzefe("", InstitutionType.Gzefe),
    XWBank("9910",InstitutionType.XWBank);

    private String code;
    private InstitutionType type;

    InstitutionCode(String code, InstitutionType type){
        this.code= code;
        this.type = type;
    }

    public static InstitutionCode codeOf(String code){
        for(InstitutionCode institutionCode:InstitutionCode.values()){
            if(institutionCode.code().equals(code))
                return institutionCode;
        }
        throw new IllegalArgumentException("未知渠道代码");
    }

    public static InstitutionCode typeOf(InstitutionType type){
        for(InstitutionCode code:InstitutionCode.values()){
            if(code.type() == type)
                return code;
        }
        throw new IllegalArgumentException("未知渠道代码");
    }

    public String code() {
        return code;
    }

    public InstitutionType type() {
        return type;
    }
}

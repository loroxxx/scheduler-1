package com.jinhui.scheduler.domain.common;

/**
 * Created by jinhui on 2017/5/25.
 */
public enum ChannelCode {
    Imiqian("1018", InstitutionType.Imiqian),
    XWBank("9910", InstitutionType.XWBank);

    private String code;
    private InstitutionType type;

    ChannelCode(String code, InstitutionType type){
        this.code= code;
        this.type = type;
    }

    public static ChannelCode codeOf(String code){
        for(ChannelCode channel:ChannelCode.values()){
            if(channel.getCode().equals(code))
                return channel;
        }
        throw new IllegalArgumentException("未知渠道代码");
    }

    public static ChannelCode typeOf(InstitutionType type){
        for(ChannelCode channel:ChannelCode.values()){
            if(channel.getType() == type)
                return channel;
        }
        throw new IllegalArgumentException("未知渠道代码");
    }

    public String getCode() {
        return code;
    }

    public InstitutionType getType() {
        return type;
    }
}

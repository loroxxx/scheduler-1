package com.jinhui.scheduler.domain.divided;

/**
 * Created by liyou on 17/3/14.
 */
public enum FileStatus {
    UNKNOWN("未知"), SENT("已发送"),
    RECEIVED("已收到文件"), ERROR("确认文件错误");
    String desc;
    FileStatus(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return desc;
    }
}

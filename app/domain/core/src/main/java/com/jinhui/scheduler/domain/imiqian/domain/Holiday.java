package com.jinhui.scheduler.domain.imiqian.domain;

import java.util.Date;

public class Holiday {

    private Integer id;

    private String date;

    private String flag;

    private String despict;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDespict() {
        return despict;
    }

    public void setDespict(String despict) {
        this.despict = despict;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", flag='" + flag + '\'' +
                ", despict='" + despict + '\'' +
                '}';
    }
}
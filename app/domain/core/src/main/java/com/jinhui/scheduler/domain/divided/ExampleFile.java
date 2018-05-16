package com.jinhui.scheduler.domain.divided;


import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by liyou on 17/3/14.
 */
public class ExampleFile {

    //我端父产品代码
    private String productNum;

    //对端给的父产品ID
    private String corpProductCode;

    //拆包文件状态
    private FileStatus status;

    //状态更新时间
    private Date statusUpdateTime;

    public ExampleFile(String productNum, String corpProductCode, FileStatus status) {
        if(StringUtils.isEmpty(productNum))
            throw new IllegalArgumentException("productNum不能为空");
        if(StringUtils.isEmpty(corpProductCode))
            throw new IllegalArgumentException("corpProductCode不能为空");
        if(status == null)
            throw new IllegalArgumentException("status不能为空");
        this.productNum = productNum;
        this.corpProductCode = corpProductCode;
        this.status = status;
    }

    public ExampleFile(String corpProductCode, FileStatus status) {
        if(StringUtils.isEmpty(corpProductCode))
            throw new IllegalArgumentException("corpProductCode不能为空");
        if(status == null)
            throw new IllegalArgumentException("status不能为空");
        this.corpProductCode = corpProductCode;
        this.status = status;
    }

    public void statusUpdateTime(Date statusUpdateTime){
        this.statusUpdateTime = statusUpdateTime;
    }
    public Date statusUpdateTime(){
        return statusUpdateTime;
    }

    public String productNum(){
        return productNum;
    }
    public void productNum(String productNum){
        this.productNum = productNum;
    }

    public String childProductNum(){
        return corpProductCode;
    }
    public void childProductNum(String childProductNum){
        this.corpProductCode = childProductNum;
    }

    public FileStatus status(){
        return status;
    }
    public void status(FileStatus status){
        this.status = status;
    }

    @Deprecated
    public ExampleFile() {
    }
}

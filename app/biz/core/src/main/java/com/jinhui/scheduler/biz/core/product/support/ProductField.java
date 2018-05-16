package com.jinhui.scheduler.biz.core.product.support;

import com.jinhui.scheduler.biz.core.util.excel.ExcelCell;

/**
 * 产品文件的字段
 * Created by Administrator on 2017/12/13 0013.
 */
public class ProductField {

    /**
     * 字段名
     */
    @ExcelCell(index = 0,name="字段名",allowNull = true)
    private String fieldName;

    /**
     * 字段内容
     */
    @ExcelCell(index = 1,name="字段内容",allowNull = true)
    private String value;

    /**
     * 转义
     */
    @ExcelCell(index = 2,name="转义",allowNull = true)
    private String meaning;


    /**
     * 说明
     */
    @ExcelCell(index = 3,name="说明",allowNull = true)
    private String describe;


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    @Override
    public String toString() {
        return "ExcelTemplate{" +
                "fieldName='" + fieldName + '\'' +
                ", value='" + value + '\'' +
                ", meaning='" + meaning + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}

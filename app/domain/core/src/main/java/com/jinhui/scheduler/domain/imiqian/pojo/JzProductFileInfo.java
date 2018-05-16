package com.jinhui.scheduler.domain.imiqian.pojo;

/**
 * Created by Administrator on 2017/3/5.
 */
public class JzProductFileInfo {
    //主键
    private int file_id;
    //文件名称
    private String file_name;
    //标题
    private String file_title;
    //类型
    private String file_type;
    //日期
    private String file_date;
    //产品名称
    private String product_name;
    //产品代码
    private String product_code;
    //机构号
    private String institution;
    //文件路径
    private String backup_location;

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_title() {
        return file_title;
    }

    public void setFile_title(String file_title) {
        this.file_title = file_title;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_date() {
        return file_date;
    }

    public void setFile_date(String file_date) {
        this.file_date = file_date;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getBackup_location() {
        return backup_location;
    }

    public void setBackup_location(String backup_location) {
        this.backup_location = backup_location;
    }

    @Override
    public String toString() {
        return "JzProductFileInfo{" +
                "file_id=" + file_id +
                ", file_name='" + file_name + '\'' +
                ", file_title='" + file_title + '\'' +
                ", file_type='" + file_type + '\'' +
                ", file_date='" + file_date + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_code='" + product_code + '\'' +
                ", institution='" + institution + '\'' +
                ", backup_location='" + backup_location + '\'' +
                '}';
    }
}

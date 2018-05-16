package com.jinhui.scheduler.biz.cmbfae.service.product.divided;

import java.io.IOException;

/**
 * Created by jinhui on 2017/6/30.
 */
public interface ProductDividedService {


    /**
     * 生成子产品文件并上传
     *
     * @throws IOException
     */
    void exportChildProductFile(String productNo) throws IOException;


    /**
     * 确认子产品文件
     */
    void confirmChildProductFile() throws Exception;

}

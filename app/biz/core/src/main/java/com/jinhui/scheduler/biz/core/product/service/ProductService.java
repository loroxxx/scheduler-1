package com.jinhui.scheduler.biz.core.product.service;

import com.jinhui.scheduler.domain.core.ChildProductVo;
import com.jinhui.scheduler.domain.core.ProductVo;
import com.jinhui.scheduler.domain.core.Product;
import com.jinhui.scheduler.domain.core.ProductQueryParam;

import java.io.InputStream;
import java.util.List;


/**
 * Created by Administrator on 2017/12/13 0013.
 */
public interface ProductService {


    /**
     * 解析产品的excel,返回产品信息
     * @param inputStream 文件流
     * @param productType 产品类型
     * @return
     */
    ProductVo resolveProductFile(InputStream inputStream, String productType);


    /**
     * 查询所有的产品信息
     */
    List<ProductVo> queryProducts(ProductQueryParam queryParam);



    /**
     * 保存产品信息
     */
    void saveProducts(Product product);


    /**
     * 导出，下发产品要素表
     */
    void issueProduct(String chnCode,Product product);

}

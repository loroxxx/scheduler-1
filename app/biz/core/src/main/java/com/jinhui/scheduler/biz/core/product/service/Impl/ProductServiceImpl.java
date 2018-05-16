package com.jinhui.scheduler.biz.core.product.service.Impl;

import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.core.product.service.ProductService;
import com.jinhui.scheduler.biz.core.product.service.TermPaymentService;
import com.jinhui.scheduler.biz.core.product.support.ProductBuilder;
import com.jinhui.scheduler.biz.core.product.support.ProductField;
import com.jinhui.scheduler.biz.core.util.excel.Excel2List;
import com.jinhui.scheduler.biz.core.util.excel.ExcelException;
import com.jinhui.scheduler.data.core.mapper.core.ProductDao;
import com.jinhui.scheduler.domain.core.ProductVo;
import com.jinhui.scheduler.domain.core.Product;
import com.jinhui.scheduler.domain.core.ProductQueryParam;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/12/13 0013.
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productMapper;

//    @Autowired
//    private ChnProductService chnProductService;

    @Autowired
    private TermPaymentService termPaymentService;

    @Autowired
    private BatchStateService batchStateService;

    @Autowired
    private IFileService iFileService;


    @Override
    public ProductVo resolveProductFile(InputStream inputStream, String productType) {

        //解析excel文件流
        Excel2List<ProductField> resolve = new Excel2List<>(ProductField.class, 0, 1);

        List<ProductField> fieldList = null;
        try {
            fieldList = resolve.resolve2List(inputStream);
            //把产品要素表转为map
            HashMap<String, ProductField> map = new HashMap<>();
            for (ProductField productField : fieldList) {
                map.put(productField.getFieldName().trim(), productField);
            }

            ProductVo product = null;
            if (productType.equals("0")) {//构造活期产品
                product = ProductBuilder.buildCurrentProduct(map);
            } else if (productType.equals("1")) {//构造定期产品
                product = ProductBuilder.buildTermProduct(map);
            }
            return product;

        } catch (ExcelException e) {
            throw new RuntimeException("解析产品失败");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }


    @Override
    public List<ProductVo> queryProducts(ProductQueryParam param) {


        return productMapper.selectByQueryParam(param);
    }


    @Override
    public void saveProducts(Product product) {

        productMapper.insertSelective(product);

    }

    @Override
    public void issueProduct(String chnCode, Product product) {

    }


    public static void main(String[] args) {
//            sd0001

        String a = "code%04d";
        String sd = a.replace("code", "sd");
        String asd = "%04d";
        String format = String.format(sd, 1);
        System.out.println(format);
    }

}

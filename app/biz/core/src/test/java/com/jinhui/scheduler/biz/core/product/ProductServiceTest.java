package com.jinhui.scheduler.biz.core.product;

import com.TestConfig;
import com.jinhui.scheduler.biz.core.product.service.ProductDividedService;
import com.jinhui.scheduler.biz.core.product.service.ProductService;
import com.jinhui.scheduler.domain.core.ChildProductVo;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class ProductServiceTest extends TestConfig {

    @Autowired
    private ProductService productService;


    @Autowired
    private ProductDividedService productDividedService;

    @Test
    public void test1() throws FileNotFoundException {

        //活期
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\20170612-爽活宝要素表v1.xlsx");

        productService.resolveProductFile(inputStream, "0");


        //定期
        FileInputStream inputStream2 = new FileInputStream("C:\\Users\\Administrator\\Desktop\\“惠水涟江河2017”1号A类.xlsx");

        productService.resolveProductFile(inputStream2, "1");
    }


    @Test
    public void test2(){


//        productDividedService.addChild(gz0001);
    }


}
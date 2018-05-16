package com.jinhui.scheduler.data.core.test.cmfae;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jinhui.scheduler.data.core.mapper.cmbfae.CmbfaeProductDao;
import com.jinhui.scheduler.domain.cmbfae.model.Product;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath*:META-INF/spring/*.xml" })
public class CommonTest {

    @Autowired
    private CmbfaeProductDao productDao;
    
    @Test
    public void ProductDaoTest(){
    	Product product = new Product();
    	product.setPlatProductNo("2201705270001");
    	Product queryProduct = productDao.queryProduct(product);
    	System.out.println(queryProduct);
    }
  
}

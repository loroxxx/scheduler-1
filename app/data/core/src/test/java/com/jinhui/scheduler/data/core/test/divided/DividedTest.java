package com.jinhui.scheduler.data.core.test.divided;



import com.jinhui.scheduler.data.core.mapper.divided.InvestorDividedMapper;
import com.jinhui.scheduler.data.core.mapper.divided.ProductDividedMapper;
import com.jinhui.scheduler.domain.divided.InvestorDivided;
import com.jinhui.scheduler.domain.divided.ProductDivided;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath*:META-INF/spring/*.xml" })
public class DividedTest {

    @Autowired
    private ProductDividedMapper productDivided;

    @Autowired
    private InvestorDividedMapper investorDivided;

    @Test
    public void findByCriteria(){
        ProductDivided divided = new ProductDivided("123456");
        divided = productDivided.findByCriteria(divided.dividedCriteria());
        System.out.println(divided);
    }

    @Test
    public void saveInvestorDivided(){
        InvestorDivided divided = new InvestorDivided("1234","土壕","666666");
        divided.childProductNo("6666660001");
        divided.index(1);
        investorDivided.save(divided);
    }

    @Test
    public void findInvestD(){
        InvestorDivided divided = new InvestorDivided("1234","土壕","666666");
        divided = investorDivided.find(divided);
        System.out.println();
    }

    @Test
    public void saveProductDivided(){
        ProductDivided divided = new ProductDivided();
        divided.productNo("123456");
        divided.productName("第一条");
        divided.childProductNo("1234560000");
        productDivided.save(divided);
    }
}

package com.jinhui.scheduler.data.core.test.preparing;



import com.jinhui.scheduler.data.core.mapper.preparing.PreparingMapper;
import com.jinhui.scheduler.data.core.query.preparing.PreparingQueryManagement;
import com.jinhui.scheduler.data.core.query.preparing.criteria.*;
import com.jinhui.scheduler.data.core.query.preparing.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath*:META-INF/spring/*.xml" })
public class PreparingTest {

    @Autowired
    private PreparingQueryManagement preparingQuery;

    @Autowired
    private PreparingMapper preparingMapper;
    @Test
    public void findTradingWithPage(){
        TradingCriteria criteria = new TradingCriteria();
        criteria.setBatchDate("2017-06-06");
        Page<TradingEntity> entityPage = preparingQuery.findTradingWithPage(criteria);
        System.out.println(entityPage);
    }

    @Test
    public void findPositionWithPage(){
        PositionCriteria criteria = new PositionCriteria();
        Page<PositionEntity> entityPage = preparingQuery.findPositionWithPage(criteria);
        System.out.println(entityPage);
    }

    @Test
    public void findProductHistoryScale(){
        ProductCriteria criteria = new ProductCriteria();
        criteria.setBatchDate("2017-06-14");
        List<ProductEntity> entities = preparingMapper.findProductHistoryScale(criteria);
        System.out.println(entities);
    }

    @Test
    public void findProduct(){
        ProductCriteria criteria = new ProductCriteria();
        criteria.setBatchDate("2017-06-02");
        List<ProductEntity> entities = preparingMapper.findProductTScale(criteria);
        System.out.println(entities);
    }

    @Test
    public void findTATissueScale(){
        TACriteria criteria = new TACriteria();
        criteria.setBatchDate("2017-06-02");
        List<TAEntity> entities = preparingMapper.findTATissueScale(criteria);
        System.out.println(entities);
    }

    @Test
    public void findTAHistIssueScale(){
        TACriteria criteria = new TACriteria();
        criteria.setBatchDate("2017-06-06");
        List<TAEntity> entities = preparingMapper.findTAHistIssueScale(criteria);
        System.out.println(entities);
    }

    @Test
    public void findTA(){
        TACriteria criteria = new TACriteria();
        criteria.setBatchDate("2017-06-13");
        List<TAEntity> entities = preparingQuery.findTA(criteria);
        System.out.println(entities);
    }

    @Test
    public void findChannel(){
        ChannelCriteria criteria = new ChannelCriteria();
        criteria.setBatchDate("2017-06-06");
        List<ChannelEntity> entities = preparingQuery.findChannel(criteria);
        System.out.println(entities);
    }

}

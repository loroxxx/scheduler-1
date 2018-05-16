package com.jinhui.scheduler.biz.core.divided.service.Impl;

import com.jinhui.scheduler.biz.core.divided.service.IDividedService;
import com.jinhui.scheduler.data.core.mapper.divided.InvestorDividedMapper;
import com.jinhui.scheduler.data.core.mapper.divided.ProductDividedMapper;
import com.jinhui.scheduler.domain.divided.InvestorDivided;
import com.jinhui.scheduler.domain.divided.ProductDivided;
import com.sun.xml.internal.rngom.ast.builder.Div;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jinhui on 2017/5/27.
 */
@Service
public class DividedServiceImpl implements IDividedService{
    private final static Logger logger = LoggerFactory.getLogger(DividedServiceImpl.class);

    @Autowired
    private InvestorDividedMapper investorDividedMapper;

    @Autowired
    private ProductDividedMapper productDividedMapper;

    @Override
    @Transactional
    public String getInvestorDivided(InvestorDivided investorDivided) {
        if(logger.isInfoEnabled()){
            logger.info("获取投资者分组信息, {}", investorDivided);
        }
        InvestorDivided oldDivided = investorDividedMapper.find(investorDivided);
        if(oldDivided != null)
            return oldDivided.childProductNo();
        ProductDivided productDivided = new ProductDivided(investorDivided.productNo());
        if(productDivided == null){
            throw new RuntimeException("没有可分配的子产品");
        }
        productDivided = productDividedMapper.findByCriteria(productDivided.dividedCriteria());
        int index = productDivided.countAddOne();
        //更行对应子产品当前投资人数
        productDividedMapper.updateCount(productDivided);
        investorDivided.index(index);
        investorDivided.childProductNo(productDivided.childProductNo());
        investorDividedMapper.save(investorDivided);
        if(logger.isInfoEnabled()){
            logger.info("返回投资者分组信息, {}", investorDivided);
        }
        return investorDivided.childProductNo();
    }

    @Override
    public List<InvestorDivided> getInvestorDivided(List<InvestorDivided> investorDivideds) {
        //// TODO: 2017/5/27
        return null;
    }
}

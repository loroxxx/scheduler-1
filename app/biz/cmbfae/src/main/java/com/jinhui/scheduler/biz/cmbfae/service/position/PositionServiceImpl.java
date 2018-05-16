package com.jinhui.scheduler.biz.cmbfae.service.position;


import java.util.List;

import com.jinhui.scheduler.domain.common.ExchangeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhui.scheduler.data.core.mapper.cmbfae.InvestorPositionDao;

import com.jinhui.scheduler.domain.cmbfae.template.PositionSuContent;

@Service("PositionService")
public class PositionServiceImpl implements PositionService {
    private Logger log = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Autowired
    private InvestorPositionDao investorPositionDao;


    @Override
    public List<PositionSuContent> querySummarize() {


        String exchangeNo = ExchangeType.Cmbfae.getExchangeNo();

        List<PositionSuContent> suContents = investorPositionDao.queryPositionCount(exchangeNo);

        return suContents;
    }



}

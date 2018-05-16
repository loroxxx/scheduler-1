package com.jinhui.scheduler.biz.imiqian.service.impl;

import com.jinhui.scheduler.biz.imiqian.service.BenifitService;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.TradeIdSequenceMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jinhui on 2017/5/25.
 */
@Service
public class BenifitServiceImpl implements BenifitService {

    @Autowired
    private TradeIdSequenceMapper tradeIdSequenceMapper;


    @Override
    public String generateBenifitNo() {

        long seq = getSeq("benifit");

        return DateUtils.getCurrentDatetime() + String.format("%04d", seq);//不足4位补0

    }


    /**
     * 根据key递增生成seq
     */
    private long getSeq(final String key) {

        tradeIdSequenceMapper.insertOrUpdateByKey(key);

        return tradeIdSequenceMapper.querySeqByKey(key);

    }


}

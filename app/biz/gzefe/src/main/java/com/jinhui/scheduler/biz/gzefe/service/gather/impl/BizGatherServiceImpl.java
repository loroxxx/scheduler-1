package com.jinhui.scheduler.biz.gzefe.service.gather.impl;

import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.biz.gzefe.constant.GzefeConst;
import com.jinhui.scheduler.biz.gzefe.constant.GzefeExportSteps;
import com.jinhui.scheduler.biz.gzefe.service.gather.BizGatherService;
import com.jinhui.scheduler.data.core.mapper.gzefe.ExchangeFileLogMapper;
import com.jinhui.scheduler.data.core.mapper.gzefe.GzefeBusinessGatherMapper;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.gzefe.ExchangeFileLog;
import com.jinhui.scheduler.domain.gzefe.GzefeBusinessGather;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
@Service("BizGatherService")
public class BizGatherServiceImpl implements BizGatherService {


    @Autowired
    private GzefeBusinessGatherMapper gzefeBusinessGatherMapper;

    @Autowired
    private BatchStateService batchStateService;

    @Autowired
    private ExchangeFileLogMapper exchangeFileLogMapper;

    private static Logger log = LoggerFactory.getLogger(BizGatherServiceImpl.class);

    @Override
    public void statisticsChildProduct() {


        //获取当前批次
        BatchState batchStateCurrent = batchStateService.getBatchStateCurrent();

        int batchCode = batchStateCurrent.getBatchCode();

        //只统计贵股交的产品
        String exchangeNo = ExchangeType.Gzefe.getExchangeNo();

        //根据子产品统计交易表的汇总信息,ps:只有在交易日才有交易信息,所以只统计当日批次即可
        List<GzefeBusinessGather> transGathers = gzefeBusinessGatherMapper.selectTransGather(batchCode, exchangeNo);

        for (GzefeBusinessGather transGather : transGathers) {
            transGather.setBatchCode(batchCode);
            transGather.setFailNum(0);
            transGather.setFailVol(new BigDecimal("0"));
            transGather.setFailAmount(new BigDecimal("0"));
            transGather.setGatherDate(batchStateCurrent.getDate());

            gzefeBusinessGatherMapper.insertSelective(transGather);
        }


        //根据子产品统计收益表的汇总信息（统计时间范围：交易日当天到下一个交易日期间）
        //ps：在节假日期间也会有收益记录，统计出来的数据放在当前批次日一起发送给交易所
        //在清算日到节假日期间,下个清算日之前，applyDate不变，改变的只有gatherDate
        List<GzefeBusinessGather> incomingGathers = gzefeBusinessGatherMapper.selectIncomingGather(batchStateCurrent.getDate(), exchangeNo);

        for (GzefeBusinessGather incomingGather : incomingGathers) {
            incomingGather.setBatchCode(batchCode);
            incomingGather.setTransType(GzefeConst.BUSINESS_CODE_FEN_HONG);//分红业务代码
            incomingGather.setFailNum(0);
            incomingGather.setFailVol(new BigDecimal("0"));
            incomingGather.setFailAmount(new BigDecimal("0"));
            incomingGather.setGatherDate(batchStateCurrent.getDate());
            gzefeBusinessGatherMapper.insertSelective(incomingGather);
        }


        //写入日志
        ExchangeFileLog fileLog = new ExchangeFileLog();
        GzefeExportSteps step0 = GzefeExportSteps.STEP_0;
        fileLog.setStep(step0.name());
        fileLog.setStepCode(step0.getCode());
        fileLog.setStepDesc(step0.getDesc());
        fileLog.setCreateTime(new Date());
        fileLog.setStatus("0");//成功
        fileLog.setBatchCode(Integer.valueOf(batchCode));
        fileLog.setBatchDate(batchStateCurrent.getDate());
        fileLog.setExchangeNo(InstitutionType.Gzefe.getAbbr());
        exchangeFileLogMapper.insert(fileLog);


    }
}

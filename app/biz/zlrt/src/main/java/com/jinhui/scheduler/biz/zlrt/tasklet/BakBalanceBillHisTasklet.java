package com.jinhui.scheduler.biz.zlrt.tasklet;

import com.jinhui.scheduler.data.core.mapper.zlrt.BatchStateMapperZlrt;
import com.jinhui.scheduler.data.core.mapper.zlrt.ZlBalanceAccBillMapper;
import com.jinhui.scheduler.domain.zlrt.BatchState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 备份对账单历史
 *
 * @autor wsc
 * @create 2017-11-24 14:17
 **/
public class BakBalanceBillHisTasklet implements Tasklet {
    private final static Logger logger = LoggerFactory.getLogger(BakBalanceBillHisTasklet.class);

    @Autowired
    private BatchStateMapperZlrt batchStateMapper;
    @Autowired
    ZlBalanceAccBillMapper zlBalanceAccBillMapper;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        //查询最新的批次信息
        BatchState batchState = batchStateMapper.findNewest();
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());

        int count = zlBalanceAccBillMapper.bakBalanceBillHis(batchState.getBatchCode());

        logger.info("备份T日对账单成功记录数： "+ count);
        return RepeatStatus.FINISHED;
    }
}

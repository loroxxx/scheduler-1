package com.jinhui.scheduler.biz.zlrt.tasklet;

import com.jinhui.scheduler.data.core.mapper.zlrt.InvestorTransMapperZlrt;
import com.jinhui.scheduler.data.core.mapper.zlrt.BatchStateMapperZlrt;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.zlrt.BatchState;
import com.jinhui.scheduler.domain.zlrt.InvestorTrans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 备份T日交易数据至临时表
 *
 * @autor wsc
 * @create 2017-11-19 13:48
 **/
public class BakTransDataTasklet implements Tasklet {
    private final static Logger logger = LoggerFactory.getLogger(BakTransDataTasklet.class);

    @Autowired
    private BatchStateMapperZlrt batchStateMapper;

    @Autowired
    InvestorTransMapperZlrt investorTransMapper;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        //查询最新的批次信息
        BatchState batchState = batchStateMapper.findNewest();
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());


        //清空交易临时表
        investorTransMapper.deleteTransTemp();

        //备份T日交易数据至临时表
        InvestorTrans investorTrans = new InvestorTrans();
        investorTrans.setBatchCode(batchState.getBatchCode());
        int count = investorTransMapper.transferDataToTemp(investorTrans);

        logger.info("T日交易数据转移至临时表记录数：" + count);
        return RepeatStatus.FINISHED;
    }
}

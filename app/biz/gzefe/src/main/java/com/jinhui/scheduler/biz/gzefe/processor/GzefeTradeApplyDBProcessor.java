package com.jinhui.scheduler.biz.gzefe.processor;


import com.jinhui.scheduler.biz.gzefe.pojo.TransApplyInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by Administrator on 2017/11/16 0016.
 */
public class GzefeTradeApplyDBProcessor implements ItemProcessor<TransApplyInfo, TransApplyInfo> {


    @Override
    public TransApplyInfo process(TransApplyInfo transApplyInfoIn) throws Exception {

        TransApplyInfo transApplyInfoOut = new TransApplyInfo();

        BeanUtils.copyProperties(transApplyInfoIn, transApplyInfoOut);

        return transApplyInfoOut;

    }
}

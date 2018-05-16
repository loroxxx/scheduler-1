package com.jinhui.scheduler.biz.zlrt.processor;

import com.jinhui.scheduler.biz.zlrt.dto.RedeemLineItem;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by wsc on 2017/5/21.
 */
public class ZlrtRedeemDBProcessor implements ItemProcessor<RedeemLineItem,RedeemLineItem> {

    @Override
    public RedeemLineItem process(RedeemLineItem redeemLineItemIn) throws Exception {
        RedeemLineItem redeemLineItemOut = new RedeemLineItem();
        BeanUtils.copyProperties(redeemLineItemIn,redeemLineItemOut);


        return redeemLineItemOut;
    }
}

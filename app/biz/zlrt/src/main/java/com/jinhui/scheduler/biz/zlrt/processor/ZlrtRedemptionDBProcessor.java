package com.jinhui.scheduler.biz.zlrt.processor;

import com.jinhui.scheduler.biz.zlrt.dto.PurchaseLineItem;
import com.jinhui.scheduler.biz.zlrt.dto.RedemptionLineItem;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by wsc on 2017/5/21.
 */
public class ZlrtRedemptionDBProcessor implements ItemProcessor<RedemptionLineItem,RedemptionLineItem> {

    @Override
    public RedemptionLineItem process(RedemptionLineItem redemptionLineItemIn) throws Exception {
        RedemptionLineItem redemptionLineItemOut = new RedemptionLineItem();
        BeanUtils.copyProperties(redemptionLineItemIn,redemptionLineItemOut);


        return redemptionLineItemOut;
    }
}

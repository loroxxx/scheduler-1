package com.jinhui.scheduler.biz.zlrt.processor;

import com.jinhui.scheduler.biz.zlrt.dto.PurchaseLineItem;
import com.jinhui.scheduler.biz.zlrt.dto.RedeemLineItem;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by wsc on 2017/5/21.
 */
public class ZlrtPurchaseDBProcessor implements ItemProcessor<PurchaseLineItem,PurchaseLineItem> {

    @Override
    public PurchaseLineItem process(PurchaseLineItem purchaseLineItemIn) throws Exception {
        PurchaseLineItem purchaseLineItemOut = new PurchaseLineItem();
        BeanUtils.copyProperties(purchaseLineItemIn,purchaseLineItemOut);


        return purchaseLineItemOut;
    }
}

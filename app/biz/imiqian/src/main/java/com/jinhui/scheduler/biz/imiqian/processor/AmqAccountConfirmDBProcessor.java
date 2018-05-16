package com.jinhui.scheduler.biz.imiqian.processor;

import com.jinhui.scheduler.domain.imiqian.pojo.ChnOpenInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by wsc on 2017/5/21.
 */
public class AmqAccountConfirmDBProcessor implements ItemProcessor<ChnOpenInfo,ChnOpenInfo> {

    @Override
    public ChnOpenInfo process(ChnOpenInfo chnOpenInfoIn) throws Exception {
        ChnOpenInfo chnOpenInfoOut = new ChnOpenInfo();
        BeanUtils.copyProperties(chnOpenInfoIn,chnOpenInfoOut);

        return chnOpenInfoOut;
    }
}

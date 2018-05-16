package com.jinhui.scheduler.biz.gzefe.processor;

import com.jinhui.scheduler.biz.gzefe.pojo.GatherInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class GzefeGatherDBProcessor implements ItemProcessor<GatherInfo, GatherInfo> {


    @Override
    public GatherInfo process(GatherInfo gatherInfoIn) throws Exception {

        GatherInfo gatherInfoOut = new GatherInfo();
        BeanUtils.copyProperties(gatherInfoIn, gatherInfoOut);
        return gatherInfoOut;

    }
}

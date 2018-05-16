package com.jinhui.scheduler.biz.core.test.aggregator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.LineAggregator;

import java.util.Map;

public class TestLineAggregator implements LineAggregator<Map<String,Object>> {

    private final static Logger logger = LoggerFactory.getLogger(TestLineAggregator.class);

    @Override
    public String aggregate(Map<String, Object> item) {

        if(logger.isInfoEnabled())
            logger.info("信息 : {}",item.toString());
        if(item == null || item.size() < 1 )
            throw new IllegalArgumentException("信息为空");

        for(Map.Entry<String,Object> entry : item.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        return "result";
    }
}

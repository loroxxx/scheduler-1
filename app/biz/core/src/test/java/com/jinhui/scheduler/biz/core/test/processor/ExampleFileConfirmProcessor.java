package com.jinhui.scheduler.biz.core.test.processor;

import com.jinhui.scheduler.biz.core.test.dto.ExampleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;


public class ExampleFileConfirmProcessor implements ItemProcessor<Map, ExampleDto> {

    private final static Logger logger = LoggerFactory.getLogger(ExampleFileConfirmProcessor.class);

    /**
     *
     * @param item
     * @return 对端给的父产品ID
     * @throws Exception
     */
    @Override
    public ExampleDto process(Map item) throws Exception {
        if(item == null || item.size() < 1) {
            /**
             * 如果读入记录为空结束处理
             */
            logger.warn("读入空记录, 文件结束，返回空结束处理");
            return null;
        }


        return new ExampleDto();
    }
}

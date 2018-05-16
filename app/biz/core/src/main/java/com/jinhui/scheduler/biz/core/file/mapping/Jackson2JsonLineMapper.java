package com.jinhui.scheduler.biz.core.file.mapping;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.LineMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

public class Jackson2JsonLineMapper implements LineMapper<Map<String, Object>> {

    private ObjectMapper mapper = new ObjectMapper();

    private final static Logger logger = LoggerFactory.getLogger(Jackson2JsonLineMapper.class);

    /**
     * Interpret the line as a Json object and create a Map from it.
     *
     * @see LineMapper#mapLine(String, int)
     */
    @Override
    public Map<String, Object> mapLine(String line, int lineNumber) throws Exception {
        if(StringUtils.isEmpty(line)) {
            logger.warn("行号 : {},读入空窜", lineNumber);
            /**
             * 如果读入的为空串则返回空对象
             */
            return new HashMap<>();
        }
        Map<String, Object> result = mapper.readValue(line,
            new TypeReference<Map<String, Object>>() {
            });
        return result;
    }
}

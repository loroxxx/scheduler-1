package com.jinhui.scheduler.biz.imiqian.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.ExtractorLineAggregator;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * Created by luozl on 2017/3/17.
 */
public class AccountFormatterLineAggregator<T> extends ExtractorLineAggregator<T> {
    private final static Logger logger = LoggerFactory.getLogger(AccountFormatterLineAggregator.class);
    private String format;

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    protected String doAggregate(Object[] fields) {
        Object [] temp = new Object[fields.length];
        for(int i=0;i<fields.length;i++) {
            String str = (String)fields[i];
            try {
                temp[i] = new String(str.getBytes("GBK"),"ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                logger.info("系统不支持编码GBK或ISO-8859-1");
            }
        }
        String value = String.format(Locale.getDefault(),format,temp);
        String returmVal = null;
        try {
            returmVal = new String(value.getBytes("ISO-8859-1"),"GBK");
        } catch (UnsupportedEncodingException e) {
            logger.info("系统不支持编码GBK或ISO-8859-1");
        }
        return returmVal;
    }
}

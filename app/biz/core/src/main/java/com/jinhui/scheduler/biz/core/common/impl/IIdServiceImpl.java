package com.jinhui.scheduler.biz.core.common.impl;

import com.jinhui.scheduler.biz.core.common.IIdService;
import com.jinhui.scheduler.biz.core.common.IdGenerator;
import com.jinhui.scheduler.data.core.dao.IdSequenceDAO;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.CustomerType;
import com.jinhui.scheduler.domain.common.SequenceKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jinhui on 2017/5/25.
 */
@Service
public class IIdServiceImpl implements IIdService {

    @Autowired
    private IdSequenceDAO idSequenceDAO;

    @Override
    public String generateSerialNumber(ChannelCode code) {
        final StringBuilder appender = new StringBuilder(20);
        appender.append(code.getCode());
        appender.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        long seq = getSeq(appender.toString());
        appender.append(String.format("%08d", seq));
        return appender.toString();
    }

    @Override
    public String generateInvestorID(CustomerType type) {
        final StringBuilder appender = new StringBuilder(12);
        appender.append("JFB").append(type.getCode());
        long seq = getSeq(appender.toString());
        appender.append(String.format("%08d", seq));
        return appender.toString();
    }

    private long getSeq(final String key){
        return idSequenceDAO.fetchSequence(new SequenceKey() {
            @Override
            public String getKey() {
                return key;
            }
        });
    }


}

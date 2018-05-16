package com.jinhui.scheduler.biz.core.common;

import com.jinhui.scheduler.domain.common.ChannelCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static AtomicInteger sequence = new AtomicInteger(10000000);

    private static class Timestamp {

        private static ThreadLocal<DateFormat> DATA_FORMAT = new ThreadLocal<DateFormat>() {
            @Override
            public DateFormat get() {
                return super.get();
            }

            @Override
            protected DateFormat initialValue() {
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                df.setLenient(false);
                return df;
            }

            @Override
            public void remove() {
                super.remove();
            }

            @Override
            public void set(DateFormat value) {
                super.set(value);
            }

        };

        private static synchronized String currentTime() {
            return DATA_FORMAT.get().format(System.currentTimeMillis());
        }
    }

    public static String fetch20LSerialNo(ChannelCode channelCode) {

        StringBuilder appender = new StringBuilder(20);
        String timestamp = Timestamp.currentTime();
        int sequence = nextSeq();
        appender.append(channelCode.getCode()).append(timestamp).append(sequence);
        return appender.toString();
    }

    private static int nextSeq() {
        for (;;) {
            int current = sequence.get();
            int next = (current > 99999998) ? 10000000 : current + 1;
            if (sequence.compareAndSet(current, next))
                return next;
        }
    }


}

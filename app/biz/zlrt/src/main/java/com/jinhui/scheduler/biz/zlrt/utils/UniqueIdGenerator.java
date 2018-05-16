package com.jinhui.scheduler.biz.zlrt.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueIdGenerator {
    private static AtomicInteger sequence = new AtomicInteger(1000);

    private static class Timestamp {

        private static ThreadLocal<DateFormat> DATA_FORMAT = new ThreadLocal<DateFormat>() {
            @Override
            public DateFormat get() {
                return super.get();
            }

            @Override
            protected DateFormat initialValue() {
                DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
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

    public static String generate(String date, String idType) {
        if (date.length() != 8)
            throw new IllegalArgumentException("日期长度必须为8");
        if (idType.length() != 2)
            throw new IllegalArgumentException("ID类型长度必须为2");
        StringBuilder stringBuilder = new StringBuilder(20);
        String timestamp = Timestamp.currentTime();
        int sequence = nextSeq();
        stringBuilder.append(idType).append(date).append(timestamp.substring(8)).append(sequence);
        return stringBuilder.toString();
    }

    private static int nextSeq() {
        for (;;) {
            int current = sequence.get();
            int next = (current > 9000) ? 1000 : current + 1;
            if (sequence.compareAndSet(current, next))
                return next;
        }
    }

}

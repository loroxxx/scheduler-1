package com.jinhui.scheduler.biz.imiqian.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by luozl on 2017/3/6.
 */
public class TAAccountGenerator {
    private static int counter = 0;
    private String serialNumber;

    public TAAccountGenerator() {
        this.serialNumber = TAAccountGenerator.generate();
    }

    public String getSerialNumber(){
        return this.serialNumber;
    }

    /**
     *  生成一个TA账号
     * @return
     */
    public static synchronized String generate() {
        if(counter == 10) {
            counter = 0;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        Calendar dayDate = new GregorianCalendar(calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                .get(Calendar.DATE), 0, 0, 0);

        String currentSecondStr = (calendar.getTimeInMillis()-dayDate.getTimeInMillis())/1000+"";
        int len = currentSecondStr.length();
        if(len<5) {
            for(int i=0;i<(5-len);i++) {
                currentSecondStr = "0" + currentSecondStr;
            }
        }
        String currentDateStr = simpleDateFormat.format(currentDate);
        try {
            //保证生成的账号唯一
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return currentDateStr+currentSecondStr + counter++;
    }
}

package com.jinhui.scheduler.biz.imiqian.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luozl on 2017/3/6.
 */
public class DateUtils {

    /**
     *  获取自然日
     * @param dateStr
     * @param days 倒推天数
     * @return
     */
    public static String getNatureDateStr(String dateStr,int days) {
        if(EmptyUtils.isEmpty(dateStr)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String natureDateStr = "";
        try {
            Date date = simpleDateFormat.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,days);
            natureDateStr = simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return natureDateStr;
    }

    /**
     * 格式化日期
     * @param yyyyMMdd
     * @return
     */
    public static String formatDate(String yyyyMMdd){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 获取上一个日期
     * @param yyyyMMdd
     * @return
     */
    public static String getPreDate(String yyyyMMdd){
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 获取下一个日期
     * @param yyyyMMdd
     * @return
     */
    public static String getNextDate(String yyyyMMdd){
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 获取当前时间的年月日时分秒
     * @return
     */
    public static String getCurrentDatetime(){
        //new日期对象
        Date date = new Date();
        //转换时间输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    /**
     * 获取当前时间的年月日
     * @return
     */
    public static String getCurrentDate(){
        //new日期对象
        Date date = new Date();
        //转换时间输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }

    /**
     * 获取当前时间的时分秒
     * @return
     */
    public static String getCurrentTime(){
        //new日期对象
        Date date = new Date();
        //转换时间输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
        return dateFormat.format(date);
    }

}

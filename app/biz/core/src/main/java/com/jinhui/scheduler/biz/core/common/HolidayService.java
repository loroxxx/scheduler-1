package com.jinhui.scheduler.biz.core.common;

import java.util.List;

public interface HolidayService {


    void initHolidayTable(String start, String end, String excelPath);

    Boolean isWorkDay(String date); //是否工作日

    String getNextWorkDay(String date);   //获取下一个工作日

    String getPreWorkDay(String date);  //获取上一个工作日

    List<String> getPreWorkDayBetween(String date);//获取前一个工作日之间的日期列表

    List<String> getNextWorkDayBetween(String date);//获取下一个工作日之间的日期列表

}

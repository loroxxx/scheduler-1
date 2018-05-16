package com.jinhui.scheduler.biz.core.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



public class HolidayTest extends TestConfig {

    @Autowired
    private HolidayService service;
	
  //@Test
    public void isWorkDayTest(){
    	assertThat(service.isWorkDay("20170605")).isEqualTo(true);
    	assertThat(service.isWorkDay("20170606")).isEqualTo(true);
    	assertThat(service.isWorkDay("20170607")).isEqualTo(true);
    	assertThat(service.isWorkDay("20170608")).isEqualTo(true);
    	assertThat(service.isWorkDay("20170609")).isEqualTo(true);
    	assertThat(service.isWorkDay("20170610")).isEqualTo(false);
    	assertThat(service.isWorkDay("20170611")).isEqualTo(false);
    }
    
 // @Test
    public void getNextWorkDayTest(){
    	
    	assertThat(service.getNextWorkDay("20170603")).isEqualTo("20170605");
    	assertThat(service.getNextWorkDay("20170605")).isEqualTo("20170606");
    	assertThat(service.getNextWorkDay("20170609")).isEqualTo("20170612");
    	assertThat(service.getNextWorkDay("20170610")).isEqualTo("20170612");
    }
    
//  @Test
    public void getPreWorkDayTest(){
	 	assertThat(service.getPreWorkDay("20170505")).isEqualTo("20170504");
	   	assertThat(service.getPreWorkDay("20170605")).isEqualTo("20170602");
    	 assertThat(service.getPreWorkDay("20170606")).isEqualTo("20170605");
    	 assertThat(service.getPreWorkDay("20170612")).isEqualTo("20170609");
    	 assertThat(service.getPreWorkDay("20170611")).isEqualTo("20170609");
    }
    
  // @Test
    public void initHolidayTest(){
    	
    	service.initHolidayTable("20180101","20181231","C:\\Users\\Administrator\\Desktop\\节假日.xlsx");
    	
    }

    @Test
    public void test(){
    	List<String> list = service.getPreWorkDayBetween("20170531");
    	for (String string : list) {
			System.out.println(string);
		}
    	
    }
	
}

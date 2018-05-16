package com.jinhui.scheduler.biz.cmbfae.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TypeConverter {
	private static final Log log = LogFactory.getLog(TypeConverter.class);
	
	public static Date String2Date(String in,String format) throws ParseException{
	
		return new SimpleDateFormat(format).parse(in);
		
	}
	
	public static BigDecimal String2BigDecimal(String in) throws Exception{

		return new BigDecimal(in);
		
	}
	
	public static String Date2String(Date in,String format) {
		
		return new SimpleDateFormat(format).format(in);
		
	}

}

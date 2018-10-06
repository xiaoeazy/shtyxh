package com.shtyxh.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class DateUtil {
	public static final String TIME14 = "yyyyMMddHHmmss";
	public static final String TIME10 = "yyyy-MM-dd HH:mm:ss";
	public static final String STARTTIME = " 00:00:00";
	public static final String ENDTIME = " 23:59:59";
	public static String loadNowTime14() {
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat(TIME14);
		return (df.format(day));
	}

	public static Date stringTodate10(String time) {
		SimpleDateFormat df = new SimpleDateFormat(TIME10);
		try {
			return (df.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}

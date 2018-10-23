package com.shtyxh.manager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgOffers;

public class CommonFuncUtil {
    private static Logger logger = LoggerFactory.getLogger(CommonFuncUtil.class);
	/**
	 * 文件下载默认编码.
	 */
	private static final String ENC = "UTF-8";
	/**
	 * buffer 大小.
	 */

	public static final String SUSPENSION_POINTS = "...";
	public static final String TIME14 = "yyyyMMddHHmmss";
	public static final String TIME10 = "yyyy-MM-dd HH:mm:ss";
	public static final String STARTTIME = " 00:00:00";
	public static final String ENDTIME = " 23:59:59";
	
	
	public static List listToPage(List list ,int page ,int limit){
		if(list.size()!=0){
			int fromIndex = (page-1)*limit;
			int toIndex = Math.min(list.size(), page * limit);
			return list.subList(fromIndex	,toIndex);
		}
		 return list;
	}
	
	public static List listToList(List list ,int size){
		if(list.size()!=0)
			return list.subList(0, list.size()>size?size:list.size());
		 return list;
	}
	

	public static void judgeNewsTitleLength(List<KgNews> news, int size) {
		for (KgNews kg : news) {
			String title = kg.getNewstitle();
			if (title.length() > size) {
				title = kg.getNewstitle().substring(0, size) + SUSPENSION_POINTS;
				kg.setNewsSimpleTitle(title);
			} else {
				kg.setNewsSimpleTitle(title);
			}
		}
	}
	
	public static void judgeAssessmentActivityTitleLength(List<KgAssessmentActivity> assessmentActivityList, int size) {
    	for(KgAssessmentActivity kg :assessmentActivityList) {
    		String title =kg.getAssessmentActivityName();
    		if(title.length()>size) {
    			title=kg.getAssessmentActivityName().substring(0, size)+SUSPENSION_POINTS;
    			kg.setAssessmentActivityName(title);
    		}else {
    			kg.setAssessmentActivityName(title);
    		}
    	}
    }
	
	public static void judgeOffersTitleLength(List<KgOffers> news, int size) {
		for (KgOffers kg : news) {
			String title = kg.getOffertitle();
			if (title.length() > size) {
				title = kg.getOffertitle().substring(0, size) + SUSPENSION_POINTS;
				kg.setNewsSimpleTitle(title);
			} else {
				kg.setNewsSimpleTitle(title);
			}
		}
	}


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

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static Map<String, String> getSort(String sort) {
//		Gson gson = new Gson();
//		Map<String, String> map = new HashMap<String, String>();
//		if (sort == null || "".equals(sort)) {
//		} else {
//			List<SortBean> sortData = gson.fromJson(sort, new TypeToken<List<SortBean>>() {
//			}.getType());
//			map.put("direction", sortData.get(0).getDirection());
//			map.put("property", sortData.get(0).getProperty());
//		}
//		return map;
		return null;
	}


	
	
	
	

}

package com.shtyxh.manager.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
	
	public static void judgeOffersTitleLength(List<KgOffers> news, int size) {
		for (KgOffers kg : news) {
			String title = kg.getTitle();
			if (title.length() > size) {
				title = kg.getTitle().substring(0, size) + SUSPENSION_POINTS;
				kg.setNewsSimpleTitle(title);
			} else {
				kg.setNewsSimpleTitle(title);
			}
		}
	}

	public static void judgeAssessmentActivityTitleLength(List<KgAssessmentActivity> assessmentActivityList,
			int length) {
		for (KgAssessmentActivity kg : assessmentActivityList) {
			String title = kg.getAssessmentActivityName();
			if (title.length() > length) {
				title = kg.getAssessmentActivityName().substring(0, length) + SUSPENSION_POINTS;
				kg.setNewsSimpleAssessmentActivityName(title);
			} else {
				kg.setNewsSimpleAssessmentActivityName(title);
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


	
	
	
	public static void sendFileInputStream (String webUrl ,File file,String fileNameWithPath) throws Exception{
		 OutputStream oStream =null;
		 String BOUNDARY = "letv"; // 边界标识 随机生成
		 String PREFIX = "--", LINE_END = "\r\n";
		 String CONTENT_TYPE = "multipart/form-data"; // 内容类型
		 BufferedReader reader = null;
		 DataOutputStream dos = null;
		 HttpURLConnection conn = null;
		 BufferedInputStream bis  = null;
		 try{ 
				bis = new BufferedInputStream(new FileInputStream(file));
			 	int TIME_OUT = 600 * 1000; // 超时时间
			 	String CHARSET = "utf-8"; // 设置编码
			    String urlStr  =webUrl+"/fileUpload";
			 	URL  url = new URL(urlStr);
			 	conn = (HttpURLConnection) url.openConnection();
	            conn.setReadTimeout(TIME_OUT);
	            conn.setConnectTimeout(TIME_OUT);
	            conn.setDoInput(true); // 允许输入流
	            conn.setDoOutput(true); // 允许输出流
	            conn.setUseCaches(false); // 不允许使用缓存
	            conn.setRequestMethod("POST"); // 请求方式
	            conn.setRequestProperty("Charset", CHARSET); // 设置编码
	            conn.setRequestProperty("connection", "keep-alive");
	            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
	                    + BOUNDARY);

				
	            dos = new DataOutputStream(
                      conn.getOutputStream());
	            
	            StringBuffer sb1 = new StringBuffer();
	            sb1.append(LINE_END);
              sb1.append(PREFIX);
              sb1.append(BOUNDARY);
              sb1.append(LINE_END);
              sb1.append("Content-Disposition: form-data; name=\"theRealName\" "+ LINE_END);
              sb1.append(LINE_END);
              sb1.append(fileNameWithPath);
              dos.write(sb1.toString().getBytes("utf-8"));
	            //===============里面是文件=================
              StringBuffer sb = new StringBuffer();
              sb.append(LINE_END);
              sb.append(PREFIX);
              sb.append(BOUNDARY);
              sb.append(LINE_END);
              /**
               * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
               * filename是文件的名字，包含后缀名的 比如:abc.png
               */
              sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
                      + fileNameWithPath + "\"" + LINE_END);
              sb.append("Content-Type: application/ctet-stream" + LINE_END);
              sb.append(LINE_END);
              dos.write(sb.toString().getBytes("utf-8"));
              
              byte[] bytes = new byte[1024 * 1024];
              int len = 0;
              while ((len = bis.read(bytes)) != -1) {
                  dos.write(bytes, 0, len);
                  logger.error("发送长度:"+len);
              }
              dos.write(LINE_END.getBytes());
              
              byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
              dos.write(end_data);
              dos.flush();
              
              int res = conn.getResponseCode();
              logger.info("response code:" + res);
              logger.info( "request success");
              
		         StringBuffer resultBuffer = new StringBuffer();
		         String tempLine = null;
		
		         reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		         while ((tempLine = reader.readLine()) != null) {
		             resultBuffer.append(tempLine);
		         }
		         String resultStr = resultBuffer.toString();
		         System.out.println(resultStr);
		         Gson gson = new Gson();
		         JsonObject json = new JsonParser().parse(resultStr).getAsJsonObject();
		         Boolean success = json.get("success").getAsBoolean();
		        if(!success){
		        	throw new Exception("同步上传文件失败："+json.get("msg").getAsString());
		        }
	     } catch (Exception e) {
	    	 logger.error("error in sendFileInputStream:",e);
	         throw new Exception(e);
	     }finally{
	    	 try {
		    	 if(oStream!=null)
					oStream.close();
		    	 if(bis!=null)
		    		 bis.close();
		    	 if(reader!=null)
		    		 reader.close();
		    	 if(dos!=null)
		    		 dos.close();
		    	 if(conn!=null)
		    		 conn.disconnect();
	    	 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			 }
	     }
	}

}

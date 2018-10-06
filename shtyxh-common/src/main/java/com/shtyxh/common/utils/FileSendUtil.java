package com.shtyxh.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FileSendUtil {

	/**
	 * 
	 * @param webUrl
	 * @param funcName
	 *            "/filename"
	 * @param file
	 * @param fileNameWithPath
	 * @throws Exception
	 */
	public static String sendFileInputStream(String webUrl, String funcName, InputStream in, String filename,
			String type) throws Exception {
		OutputStream oStream = null;
		String BOUNDARY = "letv"; // 边界标识 随机生成
		String PREFIX = "--", LINE_END = "\r\n";
		String CONTENT_TYPE = "multipart/form-data"; // 内容类型
		BufferedReader reader = null;
		DataOutputStream dos = null;
		HttpURLConnection conn = null;
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(in);
			int TIME_OUT = 600 * 1000; // 超时时间
			String CHARSET = "utf-8"; // 设置编码
			String urlStr = webUrl + funcName;
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", CHARSET); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

			dos = new DataOutputStream(conn.getOutputStream());

			StringBuffer sb1 = new StringBuffer();
			sb1.append(LINE_END);
			sb1.append(PREFIX);
			sb1.append(BOUNDARY);
			sb1.append(LINE_END);
			sb1.append("Content-Disposition: form-data; name=\"type\" " + LINE_END);
			sb1.append(LINE_END);
			sb1.append(type);
			dos.write(sb1.toString().getBytes("utf-8"));
			// ===============里面是文件=================
			StringBuffer sb = new StringBuffer();
			sb.append(LINE_END);
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINE_END);
			/**
			 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
			 * filename是文件的名字，包含后缀名的 比如:abc.png
			 */
			sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + filename + "\"" + LINE_END);
			sb.append("Content-Type: application/ctet-stream" + LINE_END);
			sb.append(LINE_END);
			dos.write(sb.toString().getBytes("utf-8"));

			byte[] bytes = new byte[1024 * 1024];
			int len = 0;
			while ((len = bis.read(bytes)) != -1) {
				dos.write(bytes, 0, len);
			}
			dos.write(LINE_END.getBytes());

			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
			dos.write(end_data);
			dos.flush();

			int res = conn.getResponseCode();

			StringBuffer resultBuffer = new StringBuffer();
			String tempLine = null;

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
			String resultStr = resultBuffer.toString();
			System.out.println(resultStr);
			JSONObject json = JSON.parseObject(resultStr);
			Boolean success = json.getBoolean("success");
			if (!success) {
				throw new Exception("同步到文件服务器失败：" + json.getString("msg"));
			}
			return json.getString("message");
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				if (oStream != null)
					oStream.close();
				if (bis != null)
					bis.close();
				if (reader != null)
					reader.close();
				if (dos != null)
					dos.close();
				if (conn != null)
					conn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String sendFileInputStream(String webUrl, String funcName, InputStream in, String filename,
			Long userid, Long assessmentActivityId) throws Exception {
		OutputStream oStream = null;
		String BOUNDARY = "letv"; // 边界标识 随机生成
		String PREFIX = "--", LINE_END = "\r\n";
		String CONTENT_TYPE = "multipart/form-data"; // 内容类型
		BufferedReader reader = null;
		DataOutputStream dos = null;
		HttpURLConnection conn = null;
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(in);
			int TIME_OUT = 600 * 1000; // 超时时间
			String CHARSET = "utf-8"; // 设置编码
			String urlStr = webUrl + funcName;
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", CHARSET); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

			dos = new DataOutputStream(conn.getOutputStream());

			StringBuffer sb1 = new StringBuffer();
			sb1.append(LINE_END);
			sb1.append(PREFIX);
			sb1.append(BOUNDARY);
			sb1.append(LINE_END);
			sb1.append("Content-Disposition: form-data; name=\"assessmentActivityId\" " + LINE_END);
			sb1.append(LINE_END);
			sb1.append(assessmentActivityId);
			dos.write(sb1.toString().getBytes("utf-8"));

			StringBuffer sb2 = new StringBuffer();
			sb2.append(LINE_END);
			sb2.append(PREFIX);
			sb2.append(BOUNDARY);
			sb2.append(LINE_END);
			sb2.append("Content-Disposition: form-data; name=\"userid\" " + LINE_END);
			sb2.append(LINE_END);
			sb2.append(userid);
			dos.write(sb2.toString().getBytes("utf-8"));

			// ===============里面是文件=================
			StringBuffer sb = new StringBuffer();
			sb.append(LINE_END);
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINE_END);
			/**
			 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
			 * filename是文件的名字，包含后缀名的 比如:abc.png
			 */
			sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + filename + "\"" + LINE_END);
			sb.append("Content-Type: application/ctet-stream" + LINE_END);
			sb.append(LINE_END);
			dos.write(sb.toString().getBytes("utf-8"));

			byte[] bytes = new byte[1024 * 1024];
			int len = 0;
			while ((len = bis.read(bytes)) != -1) {
				dos.write(bytes, 0, len);
			}
			dos.write(LINE_END.getBytes());

			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
			dos.write(end_data);
			dos.flush();

			int res = conn.getResponseCode();

			StringBuffer resultBuffer = new StringBuffer();
			String tempLine = null;

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
			String resultStr = resultBuffer.toString();
			System.out.println(resultStr);

			JSONObject json = JSON.parseObject(resultStr);
			Boolean success = json.getBoolean("success");
			if (!success) {
				throw new Exception("同步到文件服务器失败：" + json.getString("msg"));
			}
			return json.getString("message");
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				if (oStream != null)
					oStream.close();
				if (bis != null)
					bis.close();
				if (reader != null)
					reader.close();
				if (dos != null)
					dos.close();
				if (conn != null)
					conn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

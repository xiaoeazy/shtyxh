package com.shtyxh.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.shtyxh.common.bean.ZipFile;
import com.shtyxh.common.exception.FileException;

public class FileUtil {
	// ================================文件下载======================================================================
	
	public static void downloadFile(File file, HttpServletResponse response, boolean isDelete)
			throws FileException, FileNotFoundException, IOException {
		try {
			// 以流的形式下载文件。
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			if (isDelete) {
				file.delete(); // 是否将生成的服务器端文件删除
			}
		} catch (IOException ex) {
			throw new FileException(ex.getMessage());
		}
	}

	public static void zipFile(String webPath ,List<ZipFile> list, ZipOutputStream outputStream) throws IOException, ServletException {
		try {
			// 压缩列表中的文件
			for (int i = 0; i < list.size(); i++) {
				ZipFile zipFile = (ZipFile) list.get(i);
				zipFile(webPath,zipFile, outputStream);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	public static void zipFile(String webPath,ZipFile zipFile, ZipOutputStream outputstream) throws IOException, ServletException {
		FileInputStream inStream = null;
		BufferedInputStream bInStream = null;
		String filePath = zipFile.getFilePath();
		File inputFile = new File(webPath+filePath);
		try {
			if (inputFile.exists()) {
				if (inputFile.isFile()) {
					inStream = new FileInputStream(inputFile);
					bInStream = new BufferedInputStream(inStream);
					ZipEntry entry = new ZipEntry(zipFile.getFileName());
					outputstream.putNextEntry(entry);

					// final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
					// long streamTotal = 0; // 接受流的容量
					// int streamNum = 0; // 流需要分开的数量
					// int leaveByte = 0; // 文件剩下的字符数
					// byte[] inOutbyte; // byte数组接受文件的数据
					//
					// streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
					// streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
					// leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量
					//
					// if (streamNum > 0) {
					// for (int j = 0; j < streamNum; ++j) {
					// inOutbyte = new byte[MAX_BYTE];
					// // 读入流,保存在byte数组
					// bInStream.read(inOutbyte, 0, MAX_BYTE);
					// outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
					// }
					// }
					// // 写出剩下的流数据
					// inOutbyte = new byte[leaveByte];
					// bInStream.read(inOutbyte, 0, leaveByte);
					// outputstream.write(inOutbyte);

					int len = 0;
					byte[] buffer = new byte[1024];
					while ((len = bInStream.read(buffer)) > 0) {
						outputstream.write(buffer, 0, len);
						outputstream.flush();
					}

				}
			} else {
				throw new ServletException("文件不存在！");
			}
		} catch (IOException e) {
			throw e;
		} finally {
			outputstream.closeEntry(); // Closes the current ZIP entry
			// and positions the stream for
			// writing the next entry
			if (bInStream != null)
				bInStream.close(); // 关闭
			if (inStream != null)
				inStream.close();
		}
	}

	/**
	 * 将文件对象的流写入Responsne对象.
	 *
	 * @param response
	 *            HttpServletResponse
	 * @param file
	 *            File
	 * @throws FileNotFoundException
	 *             找不到文件异常
	 * @throws IOException
	 *             IO异常
	 */
	public static void writeFileToResp(HttpServletResponse response, File file)
			throws FileNotFoundException, IOException {
		InputStream inStream = null;
		ServletOutputStream outputStream = null;
		try {
			byte[] buf = new byte[1024];
			inStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			int readLength;
			while (((readLength = inStream.read(buf)) != -1)) {
				outputStream.write(buf, 0, readLength);
			}
			outputStream.flush();
		} catch (Exception e) {
			if (inStream != null)
				inStream.close();
			if (outputStream != null)
				outputStream.close();
			;
		}

	}
}

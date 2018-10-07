package com.shtyxh.fileserver.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shtyxh.common.bean.AjaxFile;
import com.shtyxh.common.bean.ExtAjax;
import com.shtyxh.common.bean.FormUploadAjax;
import com.shtyxh.common.bean.UploadFileInfo;
import com.shtyxh.common.bean.ZipFile;
import com.shtyxh.common.exception.FileException;
import com.shtyxh.common.exception.GlobalException;
import com.shtyxh.common.utils.DateUtil;
import com.shtyxh.common.utils.ExceptionUtil;
import com.shtyxh.common.utils.FileUtil;
import com.shtyxh.common.utils.JsonUtils;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileController {
	/** 日志记录. **/
	private static Logger logger = LoggerFactory.getLogger(FileController.class);
	/**
	 * 文件下载默认编码.
	 */
	private static final String ENC = "UTF-8";

	private final String CAROUSEL_PAGE = "carousel";
	private final String DOWNLOAD_PAGE = "download";
	private final String NEWS_PAGE = "news";
	private final String LOGO = "logo";
	private final String ICO = "ico";
	private final String WX = "wx";
	private final String WB = "wb";
	private final String ENTRANCEIMAGE = "entranceimage";
	private final String UPLOAD_PATH = "/uploadFile";// 未知路径的文件全部传到这个文件夹下

	@RequestMapping(value = "/uploadImage", produces = "text/html")
	@ResponseBody
	public FormUploadAjax uploadImage(HttpServletRequest request, @RequestParam("photo") MultipartFile multfile) {
		String returnPath = "";
		try {
			String type = request.getParameter("type");

			String randomName = DateUtil.loadNowTime14();
			// 获取文件名
			String fileName = multfile.getOriginalFilename();
			// 获取文件后缀
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

			ext = ext.toLowerCase();
			if (!ext.equals("jpg") && !ext.equals("png") && !ext.equals("jpeg") && !ext.equals("gif")) {
				throw new FileException("上传文件非图片格式");
			}

			String fileResourcesPath = UPLOAD_PATH;
			if (type.equals(CAROUSEL_PAGE)) {
				fileResourcesPath += "/carousel";
			} else if (type.equals(NEWS_PAGE)) {
				fileResourcesPath += "/news";
			}
			String file_path = request.getServletContext().getRealPath("/") + fileResourcesPath;
			File dir = new File(file_path);
			if (!dir.exists())
				dir.mkdirs();
			String file_all_path = file_path + '/' + randomName + "." + ext;

			multfile.transferTo(new File(file_all_path));

			if (type.equals(CAROUSEL_PAGE)) {
				returnPath = genCompressImg(fileResourcesPath, file_path + '/' + randomName, ext, 820, 475);
			} else if (type.equals(NEWS_PAGE)) {
				returnPath = genCompressImg(fileResourcesPath, file_path + '/' + randomName, ext, 470, 315);
			} else {
				returnPath = fileResourcesPath + '/' + randomName + "." + ext;
			}

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new FormUploadAjax(false, ExceptionUtil.getStackTrace(e), null);
		} catch (IOException e) {
			e.printStackTrace();
			return new FormUploadAjax(false, ExceptionUtil.getStackTrace(e), null);
		} catch (FileException e) {
			e.printStackTrace();
			return new FormUploadAjax(false, ExceptionUtil.getStackTrace(e), null);
		}
		return new FormUploadAjax(true, null, returnPath);

	}

	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public FormUploadAjax uploadFile(HttpServletRequest request, @RequestParam("photo") MultipartFile multfile) {
		String returnPath = "";
		try {
			String type = request.getParameter("type");
			// 获取文件名
			String fileName = multfile.getOriginalFilename();

			String fileResourcesPath = UPLOAD_PATH;
			if (type.equals(DOWNLOAD_PAGE)) {
				fileResourcesPath += "/download";
			}

			String file_path = request.getServletContext().getRealPath("/") + fileResourcesPath;
			File dir = new File(file_path);
			if (!dir.exists())
				dir.mkdirs();
			String file_all_path = file_path + '/' + fileName;

			multfile.transferTo(new File(file_all_path));
			returnPath = fileResourcesPath + "/" + fileName;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new FormUploadAjax(false, ExceptionUtil.getStackTrace(e), null);
		} catch (IOException e) {
			e.printStackTrace();
			return new FormUploadAjax(false, ExceptionUtil.getStackTrace(e), null);
		}
		return new FormUploadAjax(true, null, returnPath);
	}

	@RequestMapping(value = "/uploadOther")
	@ResponseBody
	public FormUploadAjax uploadOther(HttpServletRequest request, @RequestParam("photo") MultipartFile multfile) {
		String returnPath = "";
		try {
			String type = request.getParameter("type");

			// 获取文件名
			String fileName = multfile.getOriginalFilename();
			// 获取文件后缀
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			ext = ext.toLowerCase();
			if (!ext.equals("jpg") && !ext.equals("png") && !ext.equals("jpeg") && !ext.equals("gif")) {
				throw new FileException("上传文件非图片格式");
			}

			int width = 500;
			int height = 100;
			String fileResourcesPath = "";
			String imageName = "";
			if (type.equals(LOGO)) {
				imageName = "logo.png";
				// fileResourcesPath= "/resources/upload/logo/";
				// width =380;
				// height = 80;
				fileResourcesPath = UPLOAD_PATH + "/logo/";
				width = 100;
				height = 100;
			}
			if (type.equals(ICO)) {
				imageName = "favicon.ico";
				fileResourcesPath = UPLOAD_PATH + "/ico";
				width = 16;
				height = 16;
			}
			if (type.equals(WX)) {
				imageName = "wx.png";
				fileResourcesPath = UPLOAD_PATH + "/wx";
				width = 100;
				height = 100;
			}
			if (type.equals(WB)) {
				imageName = "wb.png";
				fileResourcesPath = UPLOAD_PATH + "/wb";
				width = 100;
				height = 100;
			}
			if (type.equals(ENTRANCEIMAGE)) {
				imageName = "entranceimage.png";
				fileResourcesPath = UPLOAD_PATH + "/entranceimage";
				width = 170;
				height = 120;
			}

			if (type.equals(DOWNLOAD_PAGE)) {
				fileResourcesPath += "/download";
			}

			String file_path = request.getServletContext().getRealPath("/") + fileResourcesPath;
			File dir = new File(file_path);
			if (!dir.exists())
				dir.mkdirs();
			String file_all_path = file_path + '/' + imageName;

			multfile.transferTo(new File(file_all_path));

			File tempFile = new File(file_all_path);
			InputStream is = multfile.getInputStream();
			OutputStream os = new FileOutputStream(tempFile);
			Thumbnails.of(is).forceSize(width, height).toOutputStream(os);
			is.close();
			os.close();

			returnPath = fileResourcesPath + "/" + imageName;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new FormUploadAjax(false, ExceptionUtil.getStackTrace(e), null);
		} catch (IOException e) {
			e.printStackTrace();
			return new FormUploadAjax(false, ExceptionUtil.getStackTrace(e), null);
		} catch (FileException e) {
			e.printStackTrace();
			return new FormUploadAjax(false, ExceptionUtil.getStackTrace(e), null);
		}
		return new FormUploadAjax(true, null, returnPath);
	}

	@RequestMapping(value = "/uploadAssessment")
	@ResponseBody
	public AjaxFile uploadAssessment(HttpServletRequest request, @RequestParam("txt_file") MultipartFile[] mfs) {
		AjaxFile af = new AjaxFile(true);
		try {

			String assessmentActivityId = request.getParameter("assessmentActivityId");
			String userid = request.getParameter("userid");

			String fileResourcesPath = UPLOAD_PATH + "/assessment" + "/" + assessmentActivityId + "/" + userid;
			String file_path = request.getServletContext().getRealPath("/") + fileResourcesPath;
			File dir = new File(file_path);
			if (!dir.exists())
				dir.mkdirs();
			// 获取文件名
			for (MultipartFile multfile : mfs) {
				String oriFileName = multfile.getOriginalFilename();
				int idx = oriFileName.lastIndexOf(".");
				String ext = oriFileName.substring(idx).toUpperCase();
				UUID randomFileName = UUID.randomUUID();
				String newFileName = randomFileName + ext;

				String file_all_path = file_path + '/' + newFileName;
				multfile.transferTo(new File(file_all_path));
				String returnPath = fileResourcesPath + "/" + newFileName;

				UploadFileInfo nfi = new UploadFileInfo();
				nfi.setFileName(oriFileName);
				nfi.setFilePath(returnPath);
				af.getList().add(nfi);
			}
			return af;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			af.setSuccess(false);
			af.setMessage(e.getMessage());
			return af;
		} catch (IOException e) {
			e.printStackTrace();
			af.setSuccess(false);
			af.setMessage(e.getMessage());
			return af;
		}
	}

	@RequestMapping(value = "/fileDelete", method = RequestMethod.POST)
	@ResponseBody
	public ExtAjax fileDelete(HttpServletRequest request, @RequestBody List<String> filePath) {
		File file = null;
		for (String dl : filePath) {
			if (!StringUtils.isEmpty(dl)) {
				file = new File(request.getServletContext().getRealPath("/") + dl);
				if (file.exists())
					file.delete();
			}
		}
		return new ExtAjax(true, "成功");
	}

	@RequestMapping(value = { "/fileDownload" })
	public void detailinfo(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName)
			throws GlobalException, FileNotFoundException, IOException {
		filePath = java.net.URLDecoder.decode(filePath, "UTF-8");
		File file = new File(request.getServletContext().getRealPath("/") + filePath);
		if (file.exists()) {
			String name = fileName == null ? file.getName() : java.net.URLDecoder.decode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(name, ENC) + "\"");
			response.setHeader("Accept-Ranges", "bytes");
			int fileLength = (int) file.length();
			response.setContentLength(fileLength);
			if (fileLength > 0) {
				FileUtil.writeFileToResp(response, file);
			}
		} else {
			throw new GlobalException("文件不存在");
		}
	}

	@RequestMapping("/fileDownloadZip")
	public String downloadFiles(String json,  HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, FileException {
		List<ZipFile> files = JsonUtils.jsonToList(json, ZipFile.class);
		File fileZip;
		// 文件输出流
		FileOutputStream outStream = null;
		// 压缩流
		ZipOutputStream toClient = null;
		String fileName = UUID.randomUUID().toString() + ".zip";
		// 在服务器端创建打包下载的临时文件
		String webPath = request.getServletContext().getRealPath("/");
		String outFilePath =webPath  + "/zipFile/";

		fileZip = new File(outFilePath + fileName);
		outStream = new FileOutputStream(fileZip);
		toClient = new ZipOutputStream(outStream);
		// toClient.setEncoding("gbk");
		FileUtil.zipFile(webPath,files, toClient);
		toClient.close();
		outStream.close();
		FileUtil.downloadFile(fileZip, response, true);
		return null;
	}

	/**
	 * 压缩图片
	 * 
	 * @param f
	 *            图片文件
	 * @param wigth
	 *            宽
	 * @param height
	 *            高
	 * @throws FileReadIOException
	 *             IOException
	 */
	private String genCompressImg(String fileResourcesPath, String fileName, String ext, int wigth, int height)
			throws FileException {
		String filePath = fileName + "_" + wigth + "_" + height + "." + ext;
		String name = filePath.substring(filePath.lastIndexOf("/") + 1);
		File newFile = new File(filePath);
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(newFile);
			Thumbnails.of(fileName + "." + ext).forceSize(wigth, height).toOutputStream(os);
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			throw new FileException(e.getMessage());
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				if (logger.isErrorEnabled()) {
					logger.error(e.getMessage(), e);
				}
				throw new FileException(e.getMessage());
			}
		}
		return fileResourcesPath + "/" + name;
	}

}

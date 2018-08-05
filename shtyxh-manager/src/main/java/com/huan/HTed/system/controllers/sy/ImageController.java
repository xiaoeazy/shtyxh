package com.huan.HTed.system.controllers.sy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huan.HTed.attachment.exception.StoragePathNotExsitException;
import com.huan.HTed.attachment.exception.UniqueFileMutiException;
import com.huan.HTed.bean.UploadImgAjax;

import cn.huan.kindergarten.bean.SysConfig;
import cn.huan.kindergarten.exception.FileReadIOException;
import cn.huan.kindergarten.utils.CommonUtil;
import net.coobird.thumbnailator.Thumbnails;

@Controller
public class ImageController {
	  /** 日志记录. **/
    private static Logger logger = LoggerFactory.getLogger(ImageController.class);
    
	private final String CAROUSEL_PAGE="carousel";
	private final String DOWNLOAD_PAGE="download";
	private final String NEWS_PAGE="news";
	private final String LOGO="logo";
	private final String ICO = "ico";
	private final String WX ="wx";
	private final String WB ="wb";
	private final String ENTRANCEIMAGE="entranceimage";
	/**
     * 图片上传提交页面.
     * 
     * @param request
     *            HttpServletRequest
     * @return Map 返回结果对象
     * @throws StoragePathNotExsitException
     *             存储路径不存在异常
     * @throws UniqueFileMutiException
     *             附件不唯一异常
     * @throws IOException
     * @throws FileUploadException
	 * @throws FileReadIOException 
     */
    @RequestMapping(value = "/sys/config/upload", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public UploadImgAjax upload(HttpServletRequest request)
            throws StoragePathNotExsitException, UniqueFileMutiException, IOException, FileUploadException, FileReadIOException {
    	String type = request.getParameter("type");
    	String fileResourcesPath=SysConfig.uploadpath;
    	if(type.equals(CAROUSEL_PAGE)) {
    		fileResourcesPath += "/carousel";
    	}else if(type.equals(NEWS_PAGE))  {
    		fileResourcesPath += "/news";
    	}
    	String file_path=request.getServletContext().getRealPath("/")+fileResourcesPath;
        File dir=new File(file_path);
        if(!dir.exists())
            dir.mkdirs();
      
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
        String imgName ="";
        String ext = "";
        String returnPath ="";
        for (FileItem fi : items) {
            if (fi.isFormField()) {
                fi.getFieldName();
                fi.getString();
            } else {
//                String imgName = fi.getName();//
            	imgName = fi.getName();
                if (imgName == null) {
//                    return "<script>window.parent.showUploadError('NO_FILE')</script>";
                	return new UploadImgAjax(false, "NO_FILE",null);
                } else {
                    int idx = imgName.lastIndexOf(".");
                    if (idx != -1) {
                    	ext= imgName.substring(idx + 1).toUpperCase();
                        ext = ext.toLowerCase();
                        if (!ext.equals("jpg") && !ext.equals("png") && !ext.equals("jpeg") && !ext.equals("gif")) {
                            // 错误信息
//                            return "<script>window.parent.showUploadError('FILE_TYPE_ERROR')</script>";
                        	return new UploadImgAjax(false, "FILE_TYPE_ERROR",null);
                        }
                    } else {
                        // 文件类型错误
//                        return "<script>window.parent.showUploadError('FILE_NO_SUFFIX')</script>";
                    	return new UploadImgAjax(false, "FILE_NO_SUFFIX",null);
                    }
                }
                String randomName = CommonUtil.loadNowTime14();
                File tempFile = new File(file_path+'/'+randomName+"."+ext);
                try (InputStream is = fi.getInputStream(); OutputStream os = new FileOutputStream(tempFile)) {
                    IOUtils.copyLarge(is, os);
                }
                
                if(type.equals(CAROUSEL_PAGE)) {
//                	returnPath=genCompressImg(fileResourcesPath,file_path+'/'+randomName,ext, 1920, 765);
                	returnPath=genCompressImg(fileResourcesPath,file_path+'/'+randomName,ext, 1000, 475);
                }else  if(type.equals(NEWS_PAGE)) {
                	returnPath=genCompressImg(fileResourcesPath,file_path+'/'+randomName,ext, 390, 285);
                }else {
                	returnPath=fileResourcesPath+'/'+randomName+"."+ext;
                }
                	
            }
        }
//        return "<script>window.parent.showUploadSucessLogo()</script>";
    	return new UploadImgAjax(true,null, returnPath);
    }
    
    @RequestMapping(value = "/sys/config/uploadImagePath", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public UploadImgAjax uploadImagePath(HttpServletRequest request)
            throws StoragePathNotExsitException, UniqueFileMutiException, IOException, FileUploadException, FileReadIOException {
    	String type = request.getParameter("type");
    	String fileResourcesPath="";
    	String imageName = "";
    	int width =500;
    	int height = 100;
    	if(type.equals(LOGO)) {
    		imageName= "logo.png";
//    		fileResourcesPath= "/resources/upload/logo/";
//    		width =380;
//    		height = 80;
    		fileResourcesPath= "/resources/upload/logo/";
    		width =100;
    		height = 100;
    	}
    	if(type.equals(ICO)) {
    		imageName= "favicon.ico";
    		fileResourcesPath= "/";
    		width =16;
    		height=16;
    	}
    	if(type.equals(WX)) {
    		imageName= "wx.png";
    		fileResourcesPath= SysConfig.uploadpath+"/wx";
    		width =100;
    		height=100;
    	}
    	if(type.equals(WB)) {
    		imageName= "wb.png";
    		fileResourcesPath= SysConfig.uploadpath+"/wb";
    		width =100;
    		height=100;
    	}
    	if(type.equals(ENTRANCEIMAGE)) {
    		imageName= "entranceimage.png";
    		fileResourcesPath= SysConfig.uploadpath+"/entranceimage";
    		width =170;
    		height=120;
    	}
    	
    	String file_path=request.getServletContext().getRealPath("/")+fileResourcesPath;
        File dir=new File(file_path);
        if(!dir.exists())
            dir.mkdirs();
      
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
        String ext = "";
        String returnPath ="";
        for (FileItem fi : items) {
            if (fi.isFormField()) {
                fi.getFieldName();
                fi.getString();
            } else {
            	 String imgName = fi.getName();
                if (imgName == null) {
                	return new UploadImgAjax(false, "NO_FILE",null);
                } else {
                    int idx = imgName.lastIndexOf(".");
                    if (idx != -1) {
                    	ext= imgName.substring(idx + 1).toUpperCase();
                        ext = ext.toLowerCase();
                        if (!ext.equals("jpg") && !ext.equals("png") && !ext.equals("jpeg") && !ext.equals("gif") && !ext.equals("ico")) {
                        	return new UploadImgAjax(false, "FILE_TYPE_ERROR",null);
                        }
                    } else {
                    	return new UploadImgAjax(false, "FILE_NO_SUFFIX",null);
                    }
                }
                File tempFile = new File(file_path+'/'+imageName);
                InputStream is = fi.getInputStream();
                OutputStream os = new FileOutputStream(tempFile);
                
                Thumbnails.of(is).forceSize(width, height).toOutputStream(os);
               
                is.close();
                os.close();
                returnPath=fileResourcesPath+'/'+imageName;
            }
        }
    	return new UploadImgAjax(true,null, returnPath);
    }
    
    
    @RequestMapping(value = "/sys/config/file/upload", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public UploadImgAjax fileupload(HttpServletRequest request)
            throws StoragePathNotExsitException, UniqueFileMutiException, IOException, FileUploadException {
    	String type = request.getParameter("type");
    	String fileResourcesPath=SysConfig.uploadpath;;
    	 if(type.equals(DOWNLOAD_PAGE)){
    		 fileResourcesPath+="/download";
    	}
    	String file_path=request.getServletContext().getRealPath("/") + fileResourcesPath;
        File dir=new File(file_path);
        if(!dir.exists())
            dir.mkdirs();
      
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
        String imgName ="";
        for (FileItem fi : items) {
            if (fi.isFormField()) {
                fi.getFieldName();
                fi.getString();
            } else {
            	
//                String imgName = fi.getName();//
            	imgName = fi.getName();
                File tempFile = new File(file_path+'/'+imgName);
                if (imgName == null) {
//                    return "<script>window.parent.showUploadError('NO_FILE')</script>";
                	return new UploadImgAjax(false, "NO_FILE",null);
                } else {
                    int idx = imgName.lastIndexOf(".");
                    if (idx != -1) {
                    } else {
                        // 文件类型错误
//                        return "<script>window.parent.showUploadError('FILE_NO_SUFFIX')</script>";
                    	return new UploadImgAjax(false, "FILE_NO_SUFFIX",null);
                    }
                }
                try (InputStream is = fi.getInputStream(); OutputStream os = new FileOutputStream(tempFile)) {
                    IOUtils.copyLarge(is, os);
                }

            }
        }
//        return "<script>window.parent.showUploadSucessLogo()</script>";
    	return new UploadImgAjax(true,null, fileResourcesPath+"/"+imgName);
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
    private String genCompressImg(String fileResourcesPath,String fileName,String ext, int wigth, int height) throws FileReadIOException {
    	String filePath = fileName + "_" + wigth + "_" + height+"."+ext;
    	String name = filePath.substring(filePath.lastIndexOf("/")+1);
        File newFile = new File(filePath);
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(newFile);
            Thumbnails.of(fileName+"."+ext).forceSize(wigth, height).toOutputStream(os);
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw new FileReadIOException();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage(), e);
                }
                throw new FileReadIOException();
            }
        }
        return fileResourcesPath+"/"+name;
    }
}

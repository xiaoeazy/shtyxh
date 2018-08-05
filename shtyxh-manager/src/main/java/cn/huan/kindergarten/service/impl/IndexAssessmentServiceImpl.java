package cn.huan.kindergarten.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.attachment.exception.StoragePathNotExsitException;
import com.huan.HTed.attachment.exception.UniqueFileMutiException;
import com.huan.HTed.core.IRequest;

import cn.huan.kindergarten.bean.FileInfo;
import cn.huan.kindergarten.bean.SysConfig;
import cn.huan.kindergarten.dto.KgAssessmentActivityUserProgress;
import cn.huan.kindergarten.dto.KgAssessmentActivityUserUpload;
import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.service.IIndexAssessmentService;
import cn.huan.kindergarten.service.IKgAssessmentActivityUserProgressService;
import cn.huan.kindergarten.service.IKgAssessmentActivityUserUploadService;

@Service
@Transactional(rollbackFor = Exception.class)
public class IndexAssessmentServiceImpl  implements IIndexAssessmentService{
	 @Autowired
	 private IKgAssessmentActivityUserProgressService iKgAssessmentActivityUserProgressService;
	 @Autowired
	 private IKgAssessmentActivityUserUploadService iKgAssessmentActivityUserUploadService;

	 
	 public List<FileInfo> assessmentUpload(IRequest requestContext,HttpServletRequest request,Long assessmentActivityId) 
			 throws StoragePathNotExsitException, UniqueFileMutiException, IOException, FileUploadException  {
		    Long userid = (Long)request.getSession().getAttribute(IRequest.FIELD_USER_ID);
			String fileResourcesPath=SysConfig.uploadpath+"/assessment"+"/"+assessmentActivityId+"/"+userid+"/";
	    	String file_path=request.getServletContext().getRealPath("/") + fileResourcesPath;
	        File dir=new File(file_path);
	        if(!dir.exists())
	            dir.mkdirs();
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        List<FileItem> items = upload.parseRequest(request);
	        
	        List<FileInfo>  allFilePath = new ArrayList<FileInfo>();
	        for (FileItem fi : items) {
	            if (fi.isFormField()) {
	                fi.getFieldName();
	                fi.getString();
	            } else {
	            	
	            	String oriFileName = fi.getName();
	                if (oriFileName == null) {
	                	throw new RuntimeException("无文件");
	                } 
	                int idx = oriFileName.lastIndexOf(".");
	                String ext= oriFileName.substring(idx ).toUpperCase();
	                UUID randomFileName = UUID.randomUUID();
	                String newFileName = randomFileName+ext;
	                File tempFile = new File(file_path+'/'+newFileName);
	                try (InputStream is = fi.getInputStream(); OutputStream os = new FileOutputStream(tempFile)) {
	                    IOUtils.copyLarge(is, os);
	                }
	                allFilePath.add(new FileInfo(oriFileName,fileResourcesPath+newFileName));
	            }
	        }
	        //更新
	       
	        int lockTable = iKgAssessmentActivityUserProgressService.countLockTable(requestContext);
	        KgAssessmentActivityUserProgress isExistHaveUploadQuery = new KgAssessmentActivityUserProgress();
	        isExistHaveUploadQuery.setUploadUserId(userid);
	        isExistHaveUploadQuery.setAssessmentActivityId(assessmentActivityId);
	        List<KgAssessmentActivityUserProgress> isExistHaveUploadResult = iKgAssessmentActivityUserProgressService.select(requestContext, isExistHaveUploadQuery);
	        KgAssessmentActivityUserProgress insertBean = null;
	        System.out.println("长度:"+isExistHaveUploadResult.size());
	        if(isExistHaveUploadResult.size()==0) {
	        	    KgAssessmentActivityUserProgress kaup = new KgAssessmentActivityUserProgress();
		   	        kaup.setAssessmentActivityId(assessmentActivityId);
		   	        kaup.setState(10);
		   	        kaup.setUploadUserId(userid);
		   	        insertBean=iKgAssessmentActivityUserProgressService.insertSelective(requestContext, kaup);
	        }else {
	        		insertBean = isExistHaveUploadResult.get(0);
	        }
	        
	     
	        for(FileInfo fi :allFilePath) {
        	    KgAssessmentActivityUserUpload kauu = new KgAssessmentActivityUserUpload();
  	        	kauu.setUploadUserId(userid);
  	        	kauu.setProgressId(insertBean.getId());
  	        	kauu.setFileName(fi.getFileName());
  	        	kauu.setFilePath(fi.getFilePath());
  	        	iKgAssessmentActivityUserUploadService.insertSelective(requestContext, kauu);
	        }
	        return allFilePath;
	      
	 }
	 
	 public void indexFileDelete (IRequest request, String webPath , List<KgAssessmentActivityUserUpload> dto) {
		 iKgAssessmentActivityUserUploadService.batchDelete(dto);
		 File file = null;
		 for(KgAssessmentActivityUserUpload dl :dto) {
			 if(!StringUtils.isEmpty(dl.getFilePath())) {
				 file = new File(webPath+dl.getFilePath());
				 if(file.exists())
					 file.delete();
			 }
		 }
	 }
	 
	 public void userJoinAssessmentDelete (IRequest request, String webPath , List<KgAssessmentActivityUserProgress> dto) {
		 for(KgAssessmentActivityUserProgress kp :dto) {
			 KgAssessmentActivityUserProgress nowProgressState =iKgAssessmentActivityUserProgressService.selectByPrimaryKey(request, kp);
			 int state = nowProgressState.getState();
			 if(state!=10) {
				 throw new RuntimeException(kp.getAssessmentActivityName()+" ，后台人员已经审批不能删除!");
			 }
			 KgAssessmentActivityUserUpload ku = new KgAssessmentActivityUserUpload();
			 ku.setProgressId(kp.getId());
			 List<KgAssessmentActivityUserUpload> userUploadInfo = iKgAssessmentActivityUserUploadService.select(request, ku) ;
			 File file = null;
			 for(KgAssessmentActivityUserUpload kk:userUploadInfo) {
				 if(!StringUtils.isEmpty(kk.getFilePath())) {
					 file = new File(webPath+kk.getFilePath());
					 if(file.exists())
						 file.delete();
				 }
			 }
			 iKgAssessmentActivityUserUploadService.batchDelete(userUploadInfo);
			 iKgAssessmentActivityUserProgressService.deleteByPrimaryKey(kp);
		 }
	 }
}
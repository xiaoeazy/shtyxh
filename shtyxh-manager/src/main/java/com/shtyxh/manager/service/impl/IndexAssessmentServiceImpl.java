package com.shtyxh.manager.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.shtyxh.common.bean.UploadFileInfo;
import com.shtyxh.common.exception.GlobalException;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;
import com.shtyxh.manager.dto.KgAssessmentActivityUserUpload;
import com.shtyxh.manager.service.IIndexAssessmentService;
import com.shtyxh.manager.service.IKgAssessmentActivityService;
import com.shtyxh.manager.service.IKgAssessmentActivityUserProgressService;
import com.shtyxh.manager.service.IKgAssessmentActivityUserUploadService;

@Service
@Transactional(rollbackFor = Exception.class)
public class IndexAssessmentServiceImpl  implements IIndexAssessmentService{
	 @Autowired
	 private IKgAssessmentActivityUserProgressService iKgAssessmentActivityUserProgressService;
	 @Autowired
	 private IKgAssessmentActivityUserUploadService iKgAssessmentActivityUserUploadService;
	 @Autowired
	 private IKgAssessmentActivityService iKgAssessmentActivityService;
	 
	 public void assessmentUpload(IRequest requestContext,HttpServletRequest request,List<UploadFileInfo> list,Long assessmentActivityId) {
//		    Long userid = (Long)request.getSession().getAttribute(IRequest.FIELD_USER_ID);
//			String fileResourcesPath=SysConfig.uploadpath+"/assessment"+"/"+assessmentActivityId+"/"+userid+"/";
//	    	String file_path=request.getServletContext().getRealPath("/") + fileResourcesPath;
//	        File dir=new File(file_path);
//	        if(!dir.exists())
//	            dir.mkdirs();
//	        DiskFileItemFactory factory = new DiskFileItemFactory();
//	        ServletFileUpload upload = new ServletFileUpload(factory);
//	        List<FileItem> items = upload.parseRequest(request);
//	        
//	        List<FileInfo>  allFilePath = new ArrayList<FileInfo>();
//	        for (FileItem fi : items) {
//	            if (fi.isFormField()) {
//	                fi.getFieldName();
//	                fi.getString();
//	            } else {
//	            	String oriFileName = fi.getName();
//	                if (oriFileName == null) {
//	                	throw new RuntimeException("无文件");
//	                } 
//	                int idx = oriFileName.lastIndexOf(".");
//	                String ext= oriFileName.substring(idx ).toUpperCase();
//	                UUID randomFileName = UUID.randomUUID();
//	                String newFileName = randomFileName+ext;
//	                File tempFile = new File(file_path+'/'+newFileName);
//	                try (InputStream is = fi.getInputStream(); OutputStream os = new FileOutputStream(tempFile)) {
//	                    IOUtils.copyLarge(is, os);
//	                }
//	                allFilePath.add(new FileInfo(oriFileName,fileResourcesPath+newFileName));
//	            }
//	        }
		 
		 	//	        更新数据
		 	User user = (User)request.getAttribute("user");
		 	Long userid = user.getUserId();
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
	        
	     
	        for(UploadFileInfo fi :list) {
        	    KgAssessmentActivityUserUpload kauu = new KgAssessmentActivityUserUpload();
  	        	kauu.setUploadUserId(userid);
  	        	kauu.setProgressId(insertBean.getId());
  	        	kauu.setFileName(fi.getFileName());
  	        	kauu.setFilePath(fi.getFilePath());
  	        	iKgAssessmentActivityUserUploadService.insertSelective(requestContext, kauu);
	        }
	 }
	 
	 public void indexFileDelete (IRequest request,	User user ,Long assessmentActivityId, List<KgAssessmentActivityUserUpload> dto) {
		 iKgAssessmentActivityUserUploadService.batchDelete(dto);
		 
		 KgAssessmentActivityUserProgress kup = new KgAssessmentActivityUserProgress();
	     kup.setAssessmentActivityId(assessmentActivityId);
	     kup.setUploadUserId(user.getUserId());
	     List<KgAssessmentActivityUserProgress> kupDataList = iKgAssessmentActivityUserProgressService.select(request, kup);
	     if(kupDataList.size()!=0){
	    	 KgAssessmentActivityUserProgress selectBean = kupDataList.get(0);
	    	 
			 KgAssessmentActivityUserUpload ku = new KgAssessmentActivityUserUpload();
			 ku.setProgressId(selectBean.getId());
			 List<KgAssessmentActivityUserUpload> userUploadInfo = iKgAssessmentActivityUserUploadService.select(request, ku) ;
			 if(userUploadInfo.size()==0)
				 iKgAssessmentActivityUserProgressService.deleteByPrimaryKey(selectBean);
	     }
	 }
	 @Override
	 public void validateAssessmentCanUpload(IRequest request,	User user ,Long assessmentActivityId){
		 	KgAssessmentActivity ka = new KgAssessmentActivity();
			ka.setId(assessmentActivityId);
			KgAssessmentActivity kaa = iKgAssessmentActivityService.selectByPrimaryKey(request, ka);
			Boolean flag = kaa.getFinished();
			if(flag ==false) {
				throw new GlobalException("该活动已经结束");
			}else {
			     KgAssessmentActivityUserProgress kup = new KgAssessmentActivityUserProgress();
			     kup.setAssessmentActivityId(assessmentActivityId);
			     kup.setUploadUserId(user.getUserId());
			     List<KgAssessmentActivityUserProgress> kupDataList = iKgAssessmentActivityUserProgressService.select(request, kup);
			     if(kupDataList.size()==1) {
			    	 Integer state = kupDataList.get(0).getState();
			    	 if(state==10||state==30||state==60) {
			    		 
			    	 }else {
			    		 throw new GlobalException("后台人员审批中，不能修改！");
			    	 }
			     }
			}
	 }
	 
	 public void userJoinAssessmentDelete (IRequest request,  List<KgAssessmentActivityUserProgress> dto) {
		 for(KgAssessmentActivityUserProgress kp :dto) {
			 KgAssessmentActivityUserProgress nowProgressState =iKgAssessmentActivityUserProgressService.selectByPrimaryKey(request, kp);
			 int state = nowProgressState.getState();
			 if(state!=10) {
				 throw new GlobalException(kp.getAssessmentActivityName()+" ，后台人员已经审批不能删除!");
			 }
			 KgAssessmentActivityUserUpload ku = new KgAssessmentActivityUserUpload();
			 ku.setProgressId(kp.getId());
			 List<KgAssessmentActivityUserUpload> userUploadInfo = iKgAssessmentActivityUserUploadService.select(request, ku) ;
			 iKgAssessmentActivityUserUploadService.batchDelete(userUploadInfo);
			 iKgAssessmentActivityUserProgressService.deleteByPrimaryKey(kp);
		 }
	 }
}
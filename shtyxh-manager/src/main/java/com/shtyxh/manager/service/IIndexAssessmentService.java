package com.shtyxh.manager.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.shtyxh.common.bean.UploadFileInfo;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;
import com.shtyxh.manager.dto.KgAssessmentActivityUserUpload;

public interface IIndexAssessmentService extends  ProxySelf<IIndexAssessmentService>{
	public void assessmentUpload(IRequest requestContext,HttpServletRequest request,List<UploadFileInfo> list,Long assessmentActivityId);

	 public void indexFileDelete (IRequest request,	User user ,Long assessmentActivityId,List<KgAssessmentActivityUserUpload> dto);
	 
	 public void userJoinAssessmentDelete (IRequest request,  List<KgAssessmentActivityUserProgress> dto) ;

	public void validateAssessmentCanUpload(IRequest request, User user, Long assessmentActivityId);
}
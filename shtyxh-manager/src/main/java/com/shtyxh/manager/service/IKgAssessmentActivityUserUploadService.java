package com.shtyxh.manager.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;
import com.shtyxh.manager.dto.KgAssessmentActivityUserUpload;

public interface IKgAssessmentActivityUserUploadService extends IBaseService<KgAssessmentActivityUserUpload>, ProxySelf<IKgAssessmentActivityUserUploadService>{
			public List<KgAssessmentActivityUserUpload>  loadUserUploadList(IRequest request,KgAssessmentActivityUserProgress kaup,int page,int limit);
			
			public int  countUserUploadList(IRequest request,KgAssessmentActivityUserProgress kaup);
}
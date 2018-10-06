package com.shtyxh.manager.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgAssessmentType;
import com.shtyxh.manager.dto.KgQuestionsurvey;

public interface IKgQuestionsurveyService extends IBaseService<KgQuestionsurvey>, ProxySelf<IKgQuestionsurveyService>{
	public int adminQueryCount(IRequest request,KgQuestionsurvey record);
	
	public void adminDelete(IRequest requestCtx,List<KgQuestionsurvey> dto) ;
	
	public void addKgQuestionsurvey(IRequest request,KgQuestionsurvey record);
}
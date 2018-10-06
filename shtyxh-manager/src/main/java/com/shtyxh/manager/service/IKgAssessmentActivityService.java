package com.shtyxh.manager.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;

public interface IKgAssessmentActivityService extends IBaseService<KgAssessmentActivity>, ProxySelf<IKgAssessmentActivityService>{

	public List<KgAssessmentActivity> selectWithOtherInfo(IRequest request, KgAssessmentActivity condition, Integer pageNum,Integer pageSize);
	
	public List<KgAssessmentActivity> selectWithOtherInfo(IRequest request,  KgAssessmentActivityUserProgress condition ,Integer pageNum, Integer pageSize);

	public int adminQueryCount(IRequest request, KgAssessmentActivity record);
	
	public void deleteActivity(IRequest request, String webPath, List<KgAssessmentActivity> dlist);

}
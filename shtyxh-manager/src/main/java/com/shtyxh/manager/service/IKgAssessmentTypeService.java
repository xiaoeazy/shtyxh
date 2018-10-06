package com.shtyxh.manager.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgAssessmentType;

public interface IKgAssessmentTypeService extends IBaseService<KgAssessmentType>, ProxySelf<IKgAssessmentTypeService>{
	public int adminQueryCount(IRequest request,KgAssessmentType record);
}
package com.shtyxh.manager.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgAssessmentType;
import com.shtyxh.manager.service.IKgAssessmentTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgAssessmentTypeServiceImpl extends BaseServiceImpl<KgAssessmentType> implements IKgAssessmentTypeService{
	public int adminQueryCount(IRequest request,KgAssessmentType record) {
		return  mapper.selectCount(record);
	}
}
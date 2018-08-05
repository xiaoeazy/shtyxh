package cn.huan.kindergarten.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgAssessmentType;
import cn.huan.kindergarten.service.IKgAssessmentTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgAssessmentTypeServiceImpl extends BaseServiceImpl<KgAssessmentType> implements IKgAssessmentTypeService{
	public int adminQueryCount(IRequest request,KgAssessmentType record) {
		return  mapper.selectCount(record);
	}
}
package cn.huan.kindergarten.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import cn.huan.kindergarten.dto.KgAssessmentType;

public interface IKgAssessmentTypeService extends IBaseService<KgAssessmentType>, ProxySelf<IKgAssessmentTypeService>{
	public int adminQueryCount(IRequest request,KgAssessmentType record);
}
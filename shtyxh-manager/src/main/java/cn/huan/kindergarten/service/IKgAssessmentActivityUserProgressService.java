package cn.huan.kindergarten.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;

import cn.huan.kindergarten.dto.KgAssessmentActivity;
import cn.huan.kindergarten.dto.KgAssessmentActivityUserProgress;

public interface IKgAssessmentActivityUserProgressService extends IBaseService<KgAssessmentActivityUserProgress>, ProxySelf<IKgAssessmentActivityUserProgressService>{
	
	public List<KgAssessmentActivityUserProgress> selectWithOtherInfo(IRequest request, KgAssessmentActivityUserProgress condition, int pageNum,int pageSize);

	public int adminQueryCount(IRequest request, KgAssessmentActivityUserProgress record);
	
	public int countLockTable(IRequest request);

}
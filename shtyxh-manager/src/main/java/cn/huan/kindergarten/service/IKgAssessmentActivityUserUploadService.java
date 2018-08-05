package cn.huan.kindergarten.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;

import cn.huan.kindergarten.dto.KgAssessmentActivityUserProgress;
import cn.huan.kindergarten.dto.KgAssessmentActivityUserUpload;

public interface IKgAssessmentActivityUserUploadService extends IBaseService<KgAssessmentActivityUserUpload>, ProxySelf<IKgAssessmentActivityUserUploadService>{
			public List<KgAssessmentActivityUserUpload>  loadUserUploadList(IRequest request,KgAssessmentActivityUserProgress kaup,int page,int limit);
			
			public int  countUserUploadList(IRequest request,KgAssessmentActivityUserProgress kaup);
}
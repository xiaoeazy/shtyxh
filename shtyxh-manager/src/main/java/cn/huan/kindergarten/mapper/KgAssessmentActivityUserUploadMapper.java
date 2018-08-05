package cn.huan.kindergarten.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;

import cn.huan.kindergarten.dto.KgAssessmentActivityUserProgress;
import cn.huan.kindergarten.dto.KgAssessmentActivityUserUpload;

public interface KgAssessmentActivityUserUploadMapper extends Mapper<KgAssessmentActivityUserUpload>{
	public List<KgAssessmentActivityUserUpload>  loadUserUploadList(KgAssessmentActivityUserProgress kaup);
	
	public int  countUserUploadList(KgAssessmentActivityUserProgress kaup);
	
}
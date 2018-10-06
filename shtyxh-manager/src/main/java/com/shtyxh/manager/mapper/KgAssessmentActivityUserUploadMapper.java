package com.shtyxh.manager.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;
import com.shtyxh.manager.dto.KgAssessmentActivityUserUpload;

public interface KgAssessmentActivityUserUploadMapper extends Mapper<KgAssessmentActivityUserUpload>{
	public List<KgAssessmentActivityUserUpload>  loadUserUploadList(KgAssessmentActivityUserProgress kaup);
	
	public int  countUserUploadList(KgAssessmentActivityUserProgress kaup);
	
}
package com.shtyxh.manager.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;

public interface KgAssessmentActivityMapper extends Mapper<KgAssessmentActivity>{
	public List<KgAssessmentActivity> selectWithOtherInfo( KgAssessmentActivity condition);
	
	public List<KgAssessmentActivity> selectWithOtherInfo( KgAssessmentActivityUserProgress condition);
	
	public Integer adminQueryCount( KgAssessmentActivity condition);
}
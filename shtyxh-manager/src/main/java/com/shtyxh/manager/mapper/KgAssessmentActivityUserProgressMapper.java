package com.shtyxh.manager.mapper;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;

public interface KgAssessmentActivityUserProgressMapper extends Mapper<KgAssessmentActivityUserProgress>{
	
	 public List<KgAssessmentActivityUserProgress> selectWithOtherInfo(KgAssessmentActivityUserProgress condition);
	 
	 public int adminQueryCount(KgAssessmentActivityUserProgress record);
	 
	 public int countLockTable();
	 
	 
}
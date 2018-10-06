package com.shtyxh.manager.mapper;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgQuestionsurvey;

public interface KgQuestionsurveyMapper extends Mapper<KgQuestionsurvey>{
		public int updateJonCount(Long id);

}
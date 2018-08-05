package cn.huan.kindergarten.mapper;

import com.huan.HTed.mybatis.common.Mapper;
import cn.huan.kindergarten.dto.KgQuestionsurvey;

public interface KgQuestionsurveyMapper extends Mapper<KgQuestionsurvey>{
		public int updateJonCount(Long id);

}
package cn.huan.kindergarten.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;

import cn.huan.kindergarten.dto.KgQuestionmainitem;

public interface KgQuestionmainitemMapper extends Mapper<KgQuestionmainitem>{
	public List<KgQuestionmainitem> questionmainitem(KgQuestionmainitem kgQuestionmainitem);
}
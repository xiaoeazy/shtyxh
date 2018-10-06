package com.shtyxh.manager.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgQuestionmainitem;

public interface KgQuestionmainitemMapper extends Mapper<KgQuestionmainitem>{
	public List<KgQuestionmainitem> questionmainitem(KgQuestionmainitem kgQuestionmainitem);
}
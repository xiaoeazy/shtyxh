package com.shtyxh.manager.mapper;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgUserQAnswer;

public interface KgUserQAnswerMapper extends Mapper<KgUserQAnswer>{
	public int queryCount(KgUserQAnswer record) ;
}
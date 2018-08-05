package cn.huan.kindergarten.mapper;

import com.huan.HTed.mybatis.common.Mapper;

import cn.huan.kindergarten.dto.KgUserQAnswer;

public interface KgUserQAnswerMapper extends Mapper<KgUserQAnswer>{
	public int queryCount(KgUserQAnswer record) ;
}
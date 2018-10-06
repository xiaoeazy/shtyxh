package com.shtyxh.manager.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgMemberUnits;
import com.shtyxh.manager.dto.KgNews;

public interface KgMemberUnitsMapper extends Mapper<KgMemberUnits>{
	public List<KgMemberUnits> selectWithOtherInfo( KgMemberUnits condition);
	
	public Integer adminQueryCount( KgMemberUnits condition);
}
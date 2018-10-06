package com.shtyxh.manager.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgType;

public interface KgTypeMapper extends Mapper<KgType>{
	List<KgType>   findChildType(Long typeid);
	KgType   findParentType (Long typeid);
}
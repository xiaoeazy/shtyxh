package cn.huan.kindergarten.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;

import cn.huan.kindergarten.dto.KgType;

public interface KgTypeMapper extends Mapper<KgType>{
	List<KgType>   findChildType(Long typeid);
}
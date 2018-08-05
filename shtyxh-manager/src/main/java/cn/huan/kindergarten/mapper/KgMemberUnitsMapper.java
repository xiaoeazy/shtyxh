package cn.huan.kindergarten.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;
import cn.huan.kindergarten.dto.KgMemberUnits;
import cn.huan.kindergarten.dto.KgNews;

public interface KgMemberUnitsMapper extends Mapper<KgMemberUnits>{
	public List<KgMemberUnits> selectWithOtherInfo( KgMemberUnits condition);
	
	public Integer adminQueryCount( KgMemberUnits condition);
}
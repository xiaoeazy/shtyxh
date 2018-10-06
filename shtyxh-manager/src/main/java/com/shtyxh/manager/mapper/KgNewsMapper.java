package com.shtyxh.manager.mapper;

import java.util.List;
import java.util.Map;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgNews;

public interface KgNewsMapper extends Mapper<KgNews>{
		public List<KgNews> selectWithOtherInfo( KgNews condition);
		
		public List<KgNews> selectByMap(   Map<String ,Object>  typeids);
		
		public Integer adminQueryCount( KgNews condition);
		
		public Integer adminQueryCountByMap( Map<String ,Object> map);
		
		public List<KgNews> selectLinkNews(  Map<String ,Object>  typeids);
		
		public Integer selectCountByMap(  Map<String ,Object>  typeids);
}
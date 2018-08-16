package cn.huan.kindergarten.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;

import cn.huan.kindergarten.dto.KgNews;

public interface KgNewsMapper extends Mapper<KgNews>{
		public List<KgNews> selectWithOtherInfo( KgNews condition);
		
		public List<KgNews> selectByTypeId(  List<Long> typeids);
		
		public Integer adminQueryCount( KgNews condition);
}
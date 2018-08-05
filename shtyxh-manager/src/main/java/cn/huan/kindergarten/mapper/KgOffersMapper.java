package cn.huan.kindergarten.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;

import cn.huan.kindergarten.dto.KgOffers;

public interface KgOffersMapper extends Mapper<KgOffers>{
	public List<KgOffers> selectWithOtherInfo( KgOffers condition);
	
	public Integer adminQueryCount( KgOffers condition);
}
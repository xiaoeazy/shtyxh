package com.shtyxh.manager.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.dto.KgOffers;

public interface KgOffersMapper extends Mapper<KgOffers>{
	public List<KgOffers> selectWithOtherInfo( KgOffers condition);
	
	public Integer adminQueryCount( KgOffers condition);
}
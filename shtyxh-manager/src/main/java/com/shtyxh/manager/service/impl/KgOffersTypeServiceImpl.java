package com.shtyxh.manager.service.impl;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.shtyxh.manager.dto.KgOffersType;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IKgOffersTypeService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgOffersTypeServiceImpl extends BaseServiceImpl<KgOffersType> implements IKgOffersTypeService{
	@Override
	public int adminQueryCount(IRequest request,KgOffersType record) {
		return  mapper.selectCount(record);
	}
}
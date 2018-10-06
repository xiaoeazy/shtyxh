package com.shtyxh.manager.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgLink;
import com.shtyxh.manager.service.IKgLinkService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgLinkServiceImpl extends BaseServiceImpl<KgLink> implements IKgLinkService{
	public int adminQueryCount(IRequest request,KgLink record) {
		return  mapper.selectCount(record);
	}
}
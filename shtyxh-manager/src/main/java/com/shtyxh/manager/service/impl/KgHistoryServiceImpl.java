package com.shtyxh.manager.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgHistory;
import com.shtyxh.manager.service.IKgHistoryService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgHistoryServiceImpl extends BaseServiceImpl<KgHistory> implements IKgHistoryService{
	@Override
	public int adminQueryCount(IRequest request,KgHistory record) {
		return  mapper.selectCount(record);
	}
}
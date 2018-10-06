package com.shtyxh.manager.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgNewsSource;
import com.shtyxh.manager.service.IKgNewsSourceService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgNewsSourceServiceImpl extends BaseServiceImpl<KgNewsSource> implements IKgNewsSourceService{
	public int adminQueryCount(IRequest request,KgNewsSource record) {
		return  mapper.selectCount(record);
	}
}
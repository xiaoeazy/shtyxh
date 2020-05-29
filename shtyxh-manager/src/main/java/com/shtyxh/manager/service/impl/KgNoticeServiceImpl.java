package com.shtyxh.manager.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgCarousel;
import com.shtyxh.manager.dto.KgNotice;
import com.shtyxh.manager.service.IKgNoticeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgNoticeServiceImpl extends BaseServiceImpl<KgNotice> implements IKgNoticeService{
	@Override
	public int adminQueryCount(IRequest request,KgNotice record) {
		return  mapper.selectCount(record);
	}
	
}
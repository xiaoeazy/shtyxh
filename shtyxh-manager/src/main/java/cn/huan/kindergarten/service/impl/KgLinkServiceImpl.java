package cn.huan.kindergarten.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgLink;
import cn.huan.kindergarten.service.IKgLinkService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgLinkServiceImpl extends BaseServiceImpl<KgLink> implements IKgLinkService{
	public int adminQueryCount(IRequest request,KgLink record) {
		return  mapper.selectCount(record);
	}
}
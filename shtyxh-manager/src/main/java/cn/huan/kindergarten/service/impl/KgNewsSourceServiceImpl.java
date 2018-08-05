package cn.huan.kindergarten.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgNewsSource;
import cn.huan.kindergarten.service.IKgNewsSourceService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgNewsSourceServiceImpl extends BaseServiceImpl<KgNewsSource> implements IKgNewsSourceService{
	public int adminQueryCount(IRequest request,KgNewsSource record) {
		return  mapper.selectCount(record);
	}
}
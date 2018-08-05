package cn.huan.kindergarten.service.impl;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.KgHistory;
import cn.huan.kindergarten.dto.KgNewstype;
import cn.huan.kindergarten.service.IKgHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgHistoryServiceImpl extends BaseServiceImpl<KgHistory> implements IKgHistoryService{
	@Override
	public int adminQueryCount(IRequest request,KgHistory record) {
		return  mapper.selectCount(record);
	}
}
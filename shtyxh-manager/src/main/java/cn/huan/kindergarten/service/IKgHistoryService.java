package cn.huan.kindergarten.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import cn.huan.kindergarten.dto.KgHistory;

public interface IKgHistoryService extends IBaseService<KgHistory>, ProxySelf<IKgHistoryService>{

	int adminQueryCount(IRequest request, KgHistory record);

}
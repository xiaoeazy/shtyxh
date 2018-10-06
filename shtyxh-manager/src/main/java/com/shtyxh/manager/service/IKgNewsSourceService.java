package com.shtyxh.manager.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgNewsSource;

public interface IKgNewsSourceService extends IBaseService<KgNewsSource>, ProxySelf<IKgNewsSourceService>{
	public int adminQueryCount(IRequest request,KgNewsSource record) ;
}
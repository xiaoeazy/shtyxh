package com.shtyxh.manager.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgLink;

public interface IKgLinkService extends IBaseService<KgLink>, ProxySelf<IKgLinkService>{
	public int adminQueryCount(IRequest request,KgLink record);
}
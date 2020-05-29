package com.shtyxh.manager.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgNotice;

public interface IKgNoticeService extends IBaseService<KgNotice>, ProxySelf<IKgNoticeService>{

	int adminQueryCount(IRequest request, KgNotice record);



}
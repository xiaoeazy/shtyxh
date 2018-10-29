package com.shtyxh.manager.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgOffersType;

public interface IKgOffersTypeService extends IBaseService<KgOffersType>, ProxySelf<IKgOffersTypeService>{

	int adminQueryCount(IRequest request, KgOffersType record);

}
package com.shtyxh.manager.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgBaywindow;

public interface IKgBaywindowService extends IBaseService<KgBaywindow>, ProxySelf<IKgBaywindowService>{

	int adminQueryCount(IRequest request, KgBaywindow record);

	void adminDelete(IRequest request, String webPath, List<KgBaywindow> dto);

}
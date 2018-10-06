package com.shtyxh.manager.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgUserQAnswer;

public interface IKgUserQAnswerService extends IBaseService<KgUserQAnswer>, ProxySelf<IKgUserQAnswerService>{
	public int queryCount(IRequest request,KgUserQAnswer record);
	public int adminQueryCount(IRequest request,KgUserQAnswer record);
	public void adminUpdate(IRequest request,List<KgUserQAnswer> dto,String ip,Long sid );
}
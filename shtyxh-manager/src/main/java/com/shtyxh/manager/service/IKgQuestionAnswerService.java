package com.shtyxh.manager.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgQuestionAnswer;

public interface IKgQuestionAnswerService extends IBaseService<KgQuestionAnswer>, ProxySelf<IKgQuestionAnswerService>{
	public int adminQueryCount(IRequest request,KgQuestionAnswer record);
}
package com.shtyxh.manager.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgQuestionmainitem;

public interface IKgQuestionmainitemService extends IBaseService<KgQuestionmainitem>, ProxySelf<IKgQuestionmainitemService>{
	public List<KgQuestionmainitem> questionmainitem(IRequest requestCtx,KgQuestionmainitem kgQuestionmainitem);
}
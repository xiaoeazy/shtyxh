package cn.huan.kindergarten.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import cn.huan.kindergarten.dto.KgQuestionAnswer;

public interface IKgQuestionAnswerService extends IBaseService<KgQuestionAnswer>, ProxySelf<IKgQuestionAnswerService>{
	public int adminQueryCount(IRequest request,KgQuestionAnswer record);
}
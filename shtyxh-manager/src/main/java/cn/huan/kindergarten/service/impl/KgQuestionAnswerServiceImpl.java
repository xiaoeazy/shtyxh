package cn.huan.kindergarten.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgQuestionAnswer;
import cn.huan.kindergarten.service.IKgQuestionAnswerService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgQuestionAnswerServiceImpl extends BaseServiceImpl<KgQuestionAnswer> implements IKgQuestionAnswerService{
	public int adminQueryCount(IRequest request,KgQuestionAnswer record) {
		return  mapper.selectCount(record);
	}
}
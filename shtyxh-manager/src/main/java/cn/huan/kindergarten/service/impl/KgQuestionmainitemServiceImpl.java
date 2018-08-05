package cn.huan.kindergarten.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgQuestionmainitem;
import cn.huan.kindergarten.mapper.KgQuestionmainitemMapper;
import cn.huan.kindergarten.service.IKgQuestionmainitemService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgQuestionmainitemServiceImpl extends BaseServiceImpl<KgQuestionmainitem> implements IKgQuestionmainitemService{
	@Autowired
	private KgQuestionmainitemMapper questionmainitemMapper;
	public List<KgQuestionmainitem> questionmainitem(IRequest requestCtx,KgQuestionmainitem kgQuestionmainitem){
		return questionmainitemMapper.questionmainitem(kgQuestionmainitem);
	}
}
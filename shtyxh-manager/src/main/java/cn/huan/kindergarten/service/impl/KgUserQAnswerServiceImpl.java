package cn.huan.kindergarten.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgUserQAnswer;
import cn.huan.kindergarten.mapper.KgQuestionsurveyMapper;
import cn.huan.kindergarten.mapper.KgUserQAnswerMapper;
import cn.huan.kindergarten.service.IKgUserQAnswerService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgUserQAnswerServiceImpl extends BaseServiceImpl<KgUserQAnswer> implements IKgUserQAnswerService{
	@Autowired
	private  KgUserQAnswerMapper kgUserQAnswerMapper;
	@Autowired
	private  KgQuestionsurveyMapper kgQuestionsurveyMapper;
	@Override
	public int queryCount(IRequest request,KgUserQAnswer record) {
		return  kgUserQAnswerMapper.queryCount(record);
	}
	
	public int adminQueryCount(IRequest request,KgUserQAnswer record) {
		return  mapper.selectCount(record);
	}
	
	public void adminUpdate(IRequest request,List<KgUserQAnswer> dto,String ip,Long sid ) {
		 int i = kgQuestionsurveyMapper.updateJonCount(sid);
		 
		 for(KgUserQAnswer kq:dto) {
    		 kq.setIp(ip);
    		 kq.set__status("add");
    	 }
         List<KgUserQAnswer> list = batchUpdate(request, dto);
	}
}
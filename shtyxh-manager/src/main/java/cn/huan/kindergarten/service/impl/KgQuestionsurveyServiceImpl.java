package cn.huan.kindergarten.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgQuestionAnswer;
import cn.huan.kindergarten.dto.KgQuestionmainitem;
import cn.huan.kindergarten.dto.KgQuestionsurvey;
import cn.huan.kindergarten.dto.KgUserQAnswer;
import cn.huan.kindergarten.service.IKgQuestionAnswerService;
import cn.huan.kindergarten.service.IKgQuestionmainitemService;
import cn.huan.kindergarten.service.IKgQuestionsurveyService;
import cn.huan.kindergarten.service.IKgUserQAnswerService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgQuestionsurveyServiceImpl extends BaseServiceImpl<KgQuestionsurvey> implements IKgQuestionsurveyService{
	@Autowired
	private IKgQuestionmainitemService  iKgQuestionmainitemService;
	@Autowired
	private IKgQuestionAnswerService iKgQuestionAnswerService;
	@Autowired 
	private IKgUserQAnswerService iKgUserQAnswerService;
	
	public int adminQueryCount(IRequest request,KgQuestionsurvey record) {
		return  mapper.selectCount(record);
	}
	
	public void adminDelete(IRequest requestCtx,List<KgQuestionsurvey> dto) {
		
		for(KgQuestionsurvey a:dto) {
			KgQuestionmainitem k = new KgQuestionmainitem();
			k.setSid(a.getId());
			List<KgQuestionmainitem> bList = iKgQuestionmainitemService.select(requestCtx, k);
			for(KgQuestionmainitem b :bList) {
				KgQuestionAnswer l = new KgQuestionAnswer();
				l.setQid(b.getId());
				List<KgQuestionAnswer> cList = iKgQuestionAnswerService.select(requestCtx, l);	
				for(KgQuestionAnswer c :cList) {
					KgUserQAnswer m = new KgUserQAnswer();
					m.setAid(c.getId());
					List<KgUserQAnswer> dList = iKgUserQAnswerService.select(requestCtx, m);	
					iKgUserQAnswerService.batchDelete(dList);
				}
				iKgQuestionAnswerService.batchDelete(cList);
			}
			
			iKgQuestionmainitemService.batchDelete(bList);
		}
		self().batchDelete(dto);
	}
	
	public void addKgQuestionsurvey(IRequest request,KgQuestionsurvey record) {
		record.set__status("add");
		KgQuestionsurvey sqs = self().insertSelective(request, record);
		for(KgQuestionmainitem ki :sqs.getQuestionItems()) {
			ki.setSid(sqs.getId());
			ki.set__status("add");
			KgQuestionmainitem kii = iKgQuestionmainitemService.insertSelective(request, ki);
			if(ki.getqListItems()!=null&&ki.getqListItems().size()!=0) {
				for(KgQuestionAnswer kqa :ki.getqListItems()) {
					kqa.setQid(kii.getId());
					kqa.set__status("add");
				}
				
			}else {
				List<KgQuestionAnswer> alist = new ArrayList<KgQuestionAnswer>();
				KgQuestionAnswer kqa = new KgQuestionAnswer();
				kqa.setQid(kii.getId());
				kqa.set__status("add");
				alist.add(kqa);
				ki.setqListItems(alist);
			}
			
			iKgQuestionAnswerService.batchUpdate(request, ki.getqListItems());
		}
	}
}
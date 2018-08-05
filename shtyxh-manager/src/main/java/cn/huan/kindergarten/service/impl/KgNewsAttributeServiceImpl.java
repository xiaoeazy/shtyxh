package cn.huan.kindergarten.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.dto.KgNewsAttribute;
import cn.huan.kindergarten.service.IKgNewsAttributeService;
import cn.huan.kindergarten.service.IKgNewsService;
import cn.huan.kindergarten.utils.CommonUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgNewsAttributeServiceImpl extends BaseServiceImpl<KgNewsAttribute> implements IKgNewsAttributeService{
	@Autowired
	private IKgNewsService iKgNewsService;
	@Override
	public int adminQueryCount(IRequest request,KgNewsAttribute record) {
		return  mapper.selectCount(record);
	}
	@Override
	 public void loadAttriteNews(ModelAndView mv,IRequest requestContext,int attributeSize) {
			List<KgNewsAttribute> rightAttributeList =  self().select(requestContext, null, 1, attributeSize);
	        for(KgNewsAttribute ka :rightAttributeList){
	        	KgNews kn = new KgNews();
	        	kn.setAttributeid(ka.getId()+"");
//	        	List<KgNews> newsList=iKgNewsService.selectWithOtherInfo(requestContext, kn, 1, 5);
	        	kn.setSortname("sequence");
	        	kn.setSortorder("desc");
	        	List<KgNews> newsList=iKgNewsService.select(requestContext, kn, 1, 5);
	        	CommonUtil.judgeNewsTitleLength(newsList,17);
	        	ka.setNewsList(newsList);
	        	
	        }
	        mv.addObject("rightAttributeList",rightAttributeList);
	 }
}
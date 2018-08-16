package cn.huan.kindergarten.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.dto.KgNewsAttribute;
import cn.huan.kindergarten.dto.KgType;
import cn.huan.kindergarten.service.IKgNewsAttributeService;
import cn.huan.kindergarten.service.IKgNewsService;
import cn.huan.kindergarten.service.IKgTypeService;
import cn.huan.kindergarten.utils.CommonUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgNewsAttributeServiceImpl extends BaseServiceImpl<KgNewsAttribute> implements IKgNewsAttributeService{
	public static final Long  TYPEID= 3L; //默认取的是咨讯中心的头条;
	@Autowired
	private IKgNewsService iKgNewsService;
	@Autowired
	private IKgTypeService iKgTypeService;
	@Override
	public int adminQueryCount(IRequest request,KgNewsAttribute record) {
		return  mapper.selectCount(record);
	}
	@Override
	 public void loadAttriteNews(ModelAndView mv,IRequest requestContext,Long typeid ,int attributeSize) {
			List<KgNewsAttribute> rightAttributeList =  self().select(requestContext, null, 1, attributeSize);
			KgType kgType = new KgType();
			kgType.setParentid(typeid);
			List<KgType> types = iKgTypeService.select(requestContext, kgType);
			List<Long> typeids = new ArrayList<Long>();
			for(KgType kt :types) {
				typeids.add(kt.getId());
			}
	        for(KgNewsAttribute ka :rightAttributeList){
	        	KgNews kn = new KgNews();
	        	kn.setAttributeid(ka.getId()+"");
	        	
	        	List<KgNews> newsList=iKgNewsService.selectByMap(requestContext, kn,typeids, 1, 5);
//	        	kn.setSortname("sequence");
//	        	kn.setSortorder("desc");
//	        	List<KgNews> newsList=iKgNewsService.select(requestContext, kn, 1, 5);
	        	CommonUtil.judgeNewsTitleLength(newsList,17);
	        	ka.setNewsList(newsList);
	        	
	        }
	        mv.addObject("rightAttributeList",rightAttributeList);
	 }
	
	@Override
	 public void loadAttriteNews(ModelAndView mv,IRequest requestContext,int attributeSize) {
		self().loadAttriteNews(mv, requestContext,TYPEID, attributeSize);
	 }
}
package com.shtyxh.manager.index.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.common.exception.E404Excetion;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.dto.KgOffers;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgOffersService;
import com.shtyxh.manager.utils.CommonFuncUtil;

@Controller
public class IndexOffersController extends IndexBaseController{
	
	@Autowired
	private IJedisService iJedisService;
	 @Autowired
	  private IKgOffersService service;

	@RequestMapping(value = "/index/offers")
	@ResponseBody
	public ModelAndView newsTypeList(@RequestParam(defaultValue = DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/index/offers/offers");
		KgType kt = iJedisService.loadType(new KgType(OFFER_ID));
		mv.addObject("kgType", kt);
		loadNavigation(mv, IndexController.CH_ZXZX);
//		loadAttriteNews(mv, kt.getParentid(), 3);
		loadSysConfig(mv);
		return mv;
	}

	@RequestMapping(value = "/index/offersList")
	@ResponseBody
	public ExtStore offersList( HttpServletRequest request,KgOffers KgOffers,
			@RequestParam(defaultValue = DEFAULT_PAGE) int offset,
			@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
			String sort,String sortOrder) {

//		int page = offset / pageSize + 1;
//		List<KgOffers> list = iJedisService.loadOffers();
//		int size = list.size();
//		
//		list=CommonFuncUtil.listToPage(list, page, pageSize);
		if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(sortOrder)) {
			KgOffers.setSortname(sort);
			KgOffers.setSortorder(sortOrder);
		}
		
		int page = offset / pageSize + 1;
		 IRequest requestContext = createRequestContext(request);
    	 List<KgOffers> list = service.selectWithOtherInfo(requestContext, KgOffers, page, pageSize);
    	 int count = service.adminQueryCount(requestContext, KgOffers);
    	 return new ExtStore(page, pageSize, count, list);
	}

	@RequestMapping(value = "/index/offers/Detail")
	@ResponseBody
	public ModelAndView offersDetail(Long id, HttpServletRequest request) throws E404Excetion {
		if (id == null)
			throw new E404Excetion("查看的网页不存在!");
		KgOffers offersInfo = iJedisService.loadOffer(new KgOffers(id));
		if (offersInfo == null)
			throw new E404Excetion("查看的网页不存在!");

		ModelAndView mv = new ModelAndView("/index/offers/offersDetail");
		mv.addObject("offersInfo", offersInfo);
		loadNavigation(mv, IndexController.CH_ZXZX);
		loadSysConfig(mv);
		return mv;
	}


    
 
   
}
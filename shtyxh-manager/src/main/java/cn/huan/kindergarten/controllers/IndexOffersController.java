package cn.huan.kindergarten.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;

import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.dto.KgOffers;
import cn.huan.kindergarten.dto.KgType;
import cn.huan.kindergarten.exception.E404Excetion;
import cn.huan.kindergarten.service.IKgNewsAttributeService;
import cn.huan.kindergarten.service.IKgOffersService;
import cn.huan.kindergarten.service.IKgTypeService;
import cn.huan.kindergarten.utils.CommonUtil;
import cn.huan.shtyxh.common.bean.ExtStore;

@Controller
public class IndexOffersController extends IndexBaseController{
	
	@Autowired
	private IKgTypeService iKgTypeService;
	
	@Autowired
	private IKgOffersService iKgOffersService;
	@Autowired
	private IKgNewsAttributeService iKgNewsAttributeService;
	
	 @RequestMapping(value = "/index/offers")
	    @ResponseBody
	    public ModelAndView newsTypeList(@RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/offers/offers");
	        IRequest requestContext = createRequestContext(request);
	        KgNews news = new KgNews();
	        news.setTypeid(OFFER_ID);

	        KgType  kt = iKgTypeService.selectByPrimaryKey(requestContext, new KgType(OFFER_ID));
	        mv.addObject("kgType", kt);
	        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,kt.getParentid(),3);
	        loadSysConfig(mv);
	        return mv;
	    }
	 
	
	    @RequestMapping(value = "/index/offersList")
	    @ResponseBody
	    public ExtStore loadHYDW(  KgOffers offers,HttpServletRequest request,
	    		@RequestParam(defaultValue = DEFAULT_PAGE) int offset,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
	    	
	        IRequest requestContext = createRequestContext(request);
	        int page = offset /pageSize+1;
	        List<KgOffers> list = iKgOffersService.selectWithOtherInfo(requestContext, offers, page, pageSize);
	        int size = iKgOffersService.adminQueryCount(requestContext, offers);
	        return new ExtStore(page, pageSize, size, list);
	    }

    @RequestMapping(value = "/index/offers/Detail")
    @ResponseBody
    public ModelAndView offersDetail(Long id,HttpServletRequest request) throws E404Excetion {
    	IRequest requestContext = createRequestContext(request);
    	if(id==null)
    		throw new E404Excetion("请查看的网页不存在!"); 
       KgOffers offersInfo = iKgOffersService.selectByPrimaryKey(requestContext, new KgOffers(id));
       if(offersInfo==null)
   			throw new E404Excetion("请查看的网页不存在!"); 
       
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/offers/offersDetail");
        mv.addObject("offersInfo", offersInfo);
        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
        KgType  kt = iKgTypeService.selectByPrimaryKey(requestContext, new KgType(OFFER_ID));
        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,kt.getId(),3);
        loadSysConfig(mv);
        return mv;
    }
    


    
 
   
}
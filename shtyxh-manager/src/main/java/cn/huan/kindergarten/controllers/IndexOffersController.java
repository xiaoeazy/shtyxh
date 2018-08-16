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
import cn.huan.kindergarten.service.IKgNewsAttributeService;
import cn.huan.kindergarten.service.IKgOffersService;
import cn.huan.kindergarten.service.IKgTypeService;
import cn.huan.kindergarten.utils.CommonUtil;
import cn.huan.shtyxh.common.bean.ExtStore;

@Controller
public class IndexOffersController extends IndexBaseController{
	
	public static final Long offerId = 20L;
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
	        news.setTypeid(offerId);

	        KgType  kt = iKgTypeService.selectByPrimaryKey(requestContext, new KgType(offerId));
	        mv.addObject("kgType", kt);
	        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,3);
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

    @RequestMapping(value = "/index/offersDetail")
    @ResponseBody
    public ModelAndView newsDetail(Long id,HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/offers/offersDetail");
        IRequest requestContext = createRequestContext(request);
        
        KgNews news = new KgNews();
        news.setId(id);
        KgOffers offersInfo = iKgOffersService.selectByPrimaryKey(requestContext, new KgOffers(id));
        mv.addObject("offersInfo", offersInfo);
        
        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,3);
        loadSysConfig(mv);
        return mv;
    }
    


    
 
   
}
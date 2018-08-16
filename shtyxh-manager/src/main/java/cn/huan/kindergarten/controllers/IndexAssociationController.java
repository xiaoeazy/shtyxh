package cn.huan.kindergarten.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;

import cn.huan.kindergarten.bean.SysConfig;
import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.dto.KgNewsSource;
import cn.huan.kindergarten.dto.KgType;
import cn.huan.kindergarten.exception.E404Excetion;
import cn.huan.kindergarten.service.IKgNewsAttributeService;
import cn.huan.kindergarten.service.IKgNewsService;
import cn.huan.kindergarten.service.IKgNewsSourceService;
import cn.huan.kindergarten.service.IKgTypeService;
import cn.huan.kindergarten.utils.CommonUtil;
/**
 * 协会动态
 * @author huan
 *
 */
@Controller
public class IndexAssociationController extends IndexBaseController{
	public static final Long xhdtId = 6L ;//协会动态id;
	
	@Autowired
	private IKgNewsService iKgNewsService;
	@Autowired
	private IKgTypeService iKgTypeService;
	@Autowired
	private IKgNewsAttributeService iKgNewsAttributeService;
	@Autowired
	private IKgNewsSourceService iKgNewsSourceService;
	
	@RequestMapping(value = {"/index/xhdt"})
    @ResponseBody
    public ModelAndView associationDynamics(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/associationDynamics/associationDynamics");
        IRequest requestContext = createRequestContext(request);
        KgType kt = new KgType();
        kt.setParentid(xhdtId);
        kt.setRelatetype(2);
        List<KgType> typeList = iKgTypeService.select(requestContext, kt);
        List<Long> typeidList = new ArrayList<Long>();
        for(KgType kn:typeList) {
        	typeidList.add(kn.getId());
        	KgNews news = new KgNews();
        	news.setTypeid(kn.getId());
        	int count = iKgNewsService.adminQueryCount(requestContext, news);
        	kn.setCount(count);
        	List<KgNews> newsList = iKgNewsService.selectWithOtherInfo(requestContext, news, 1, 6);
        	kn.setNewsList(newsList);
        }
        
         //查询头部9条文章
	   	 List<KgNews> topNewsList =iKgNewsService.selectByTypeId(requestContext, typeidList, 1, 9);
    	 CommonUtil.judgeNewsTitleLength(topNewsList,17);
    	 
         List<KgNews> newsThumbNailList =iKgNewsService.select(requestContext, null, 1, 5);
	   	 for(KgNews kn:newsThumbNailList) {
	   		 if(("").equals(kn.getThumbnail())) {
	   			 kn.setThumbnail(SysConfig.nonePic);
	   		 }
	   	 }
	   	 CommonUtil.judgeNewsTitleLength(newsThumbNailList,17);
        
        mv.addObject("typeList", typeList);
        mv.addObject("newsThumbNailList",newsThumbNailList);
        
        mv.addObject("topNewsList",topNewsList);
        loadNavigation(mv, requestContext,IndexController.CH_XHDT);
        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,2);
        loadSysConfig(mv);
        return mv;
    }
	
	 @RequestMapping(value = {"/index/xhdt/typeList"})
	    @ResponseBody
	    public ModelAndView associationDynamicsTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/associationDynamics/associationDynamicsTypeList");
	    	IRequest requestContext = createRequestContext(request);
	    	if(typeid==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
	    	KgType kgtype = iKgTypeService.selectByPrimaryKey(requestContext, new KgType(typeid));
	    	if(kgtype==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
	        KgNews news = new KgNews();
	        news.setTypeid(typeid);
	        int count = iKgNewsService.adminQueryCount(requestContext, news);
	        int allPageNum = count%limit==0?count/limit:count/limit+1;
	        if(count==0) allPageNum=1;
	        List<KgNews> list = iKgNewsService.selectWithOtherInfo(requestContext, news, page, limit);
	        
	        CommonUtil.judgeNewsTitleLength(list,33);
	        mv.addObject("newsList", list);
	        mv.addObject("page", page);
	        mv.addObject("allPageNum",allPageNum);
	        mv.addObject("kgtype", kgtype);
	        
	        loadNavigation(mv, requestContext,IndexController.CH_XHDT);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,3);
	        loadSysConfig(mv);
	        return mv;
	    }
	 
	
	    @RequestMapping(value = "/index/xhdt/newsDetail")
	    @ResponseBody
	    public ModelAndView associationDynamicsDetail(Long id,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/associationDynamics/associationDynamicsNewsDetail");
	        IRequest requestContext = createRequestContext(request);
	        
	        if(id==null)
	        	throw new E404Excetion("请查看的网页不存在!"); 
	        KgNews newsInfo = iKgNewsService.selectByPrimaryKey(requestContext, new KgNews(id));
	        if(newsInfo==null)
	        	throw new E404Excetion("请查看的网页不存在!"); 
	        
	        mv.addObject("newsInfo", newsInfo);
	        KgType kgNewstype = iKgTypeService.selectByPrimaryKey(requestContext, new KgType(newsInfo.getTypeid()));
	        
	        KgNewsSource kgNewsSource = iKgNewsSourceService.selectByPrimaryKey(requestContext, new KgNewsSource(newsInfo.getSourceid()));
	        
	      
	        KgNews linkNews = new KgNews();
	        linkNews.setTypeid(newsInfo.getTypeid());
	        List<KgNews> linkNewsList = iKgNewsService.selectWithOtherInfo(requestContext, linkNews, 1, 2);
	        CommonUtil.judgeNewsTitleLength(linkNewsList,45);
	        
	        mv.addObject("kgNewstype", kgNewstype);
	        mv.addObject("kgNewsSource", kgNewsSource);
	        mv.addObject("linkNewsList", linkNewsList);
	        
	        
	        loadNavigation(mv, requestContext,IndexController.CH_XHDT);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,3);
	        loadSysConfig(mv);
	        return mv;
	    }
	    
	
 
   
}
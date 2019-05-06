package com.shtyxh.manager.index.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.shtyxh.common.exception.E404Excetion;
import com.shtyxh.common.utils.DateUtil;
import com.shtyxh.manager.bean.NewsSearchVo;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgNewsSource;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgNewsService;
import com.shtyxh.manager.service.IKgTypeService;
import com.shtyxh.manager.utils.CommonFuncUtil;

@Controller
public class IndexNewsController extends IndexBaseController{
	@Autowired
	private IKgNewsService iKgNewsService;
	@Autowired
	private IKgTypeService iKgTypeService;
	
	@Autowired
	private IJedisService iJedisService;
	
	@RequestMapping(value = "/index/news")
    @ResponseBody
    public ModelAndView news(HttpServletRequest request) {
		IRequest requestContext = createRequestContext(request);
    	ModelAndView mv = new ModelAndView( "/index/news/news");
        
        KgType kt = new KgType();
        kt.setParentid(ZXZX_ID);
        kt.setRelatetype(2);
        //查询列表
        List<Long> typeidList = new ArrayList<Long>();
        
        List<KgType> typeList = iKgTypeService.select(requestContext, kt);
        for(KgType kn:typeList) {
        	typeidList.add(kn.getId());
        	KgNews news = new KgNews();
        	news.setTypeid(kn.getId());
        	List<KgNews> newsList = iJedisService.loadTypeNews(news);
        	kn.setCount(newsList.size());
        	newsList=CommonFuncUtil.listToList(newsList, 6);
        	
        	kn.setNewsList(newsList);
        }
        
        List<KgNewsSource> sourceList = iJedisService.loadNewsSource();
        for(KgNewsSource kn:sourceList) {
        	KgNews news = new KgNews();
        	news.setSourceid(kn.getId());
        	int count = iKgNewsService.adminQueryCount(requestContext, news,typeidList);
        	kn.setCount(count);
        }
        
        KgType offerType = iJedisService.loadType(new KgType(OFFER_ID));
        KgNews kn = new KgNews();
    	kn.setAttributeid("4");
    	KgNews newsTop = iJedisService.loadNewsPage_AttributeId4_News(kn, typeidList, 1, 1);
    	mv.addObject("newsTop",newsTop);
        mv.addObject("typeList", typeList);
        mv.addObject("sourceList",sourceList);
        mv.addObject("offerType",offerType);
        loadNavigation(mv, IndexController.CH_ZXZX);
        loadSysConfig(request,mv);
        return mv;
    }
	
	@RequestMapping(value = "/index/news/search")
    @ResponseBody
    public ModelAndView newsSearch(String searchparam,String dateissuestart,String dateissueend , Long newstype,Long newssource,
    		 @RequestParam(defaultValue = DEFAULT_PAGE) int page, @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,
    		HttpServletRequest request) {
		IRequest requestContext = createRequestContext(request);
		ModelAndView mv = new ModelAndView( "/index/news/newsSearch");
        KgType kt = new KgType();
        kt.setRelatetype(2);
        List<KgType> typeList = iKgTypeService.select(requestContext, kt);
        mv.addObject("typeList", typeList);
        mv.addObject("searchparam",searchparam);
        mv.addObject("dateissuestart",dateissuestart);
        mv.addObject("dateissueend",dateissueend);
        mv.addObject("newstype",newstype==null?"":newstype);
        mv.addObject("newssource",newssource==null?"":newssource);
        
        loadNavigation(mv, IndexController.CH_NULL);
        loadSysConfig(request,mv);
       
        return mv;
    }
	
	
	@RequestMapping(value = "/index/news/newsSearchDetail")
    @ResponseBody
    public ModelAndView searchInfo(@RequestBody NewsSearchVo newsSearchVo,
    		 @RequestParam(defaultValue = DEFAULT_PAGE) int page, @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,
    		HttpServletRequest request) {
		IRequest requestContext = createRequestContext(request);
    	ModelAndView mv = new ModelAndView( "/index/news/searchDetail/searchDetail");
        KgNews news = new KgNews();
        if(!StringUtils.isEmpty(newsSearchVo.getSearchparam())) news.setNewstitle(newsSearchVo.getSearchparam());
        if(newsSearchVo.getNewstype()!=null)   news.setTypeid(newsSearchVo.getNewstype());
        if(newsSearchVo.getNewssource()!=null)   news.setSourceid(newsSearchVo.getNewssource());
        if(StringUtils.isNotEmpty(newsSearchVo.getDateissuestart())) news.setStartDate(DateUtil.stringTodate10(newsSearchVo.getDateissuestart()+CommonFuncUtil.STARTTIME));
        if(StringUtils.isNotEmpty(newsSearchVo.getDateissueend())) news.setEndDate(DateUtil.stringTodate10(newsSearchVo.getDateissueend()+CommonFuncUtil.ENDTIME));
        List<KgNews> list = iKgNewsService.selectWithOtherInfo(requestContext,news, page, limit);
        int count = iKgNewsService.adminQueryCount(requestContext, news);
        int allPageNum = count%limit==0?count/limit:count/limit+1;
        if(count==0) allPageNum=1;
        mv.addObject("newstitle",newsSearchVo.getSearchparam());
        mv.addObject("newsList", list);
        mv.addObject("newsListSize", count);
        mv.addObject("page", page);
        mv.addObject("allPageNum",allPageNum);
        return mv;
    }
	
	
	 @RequestMapping(value = "/index/news/typeList")
	    @ResponseBody
	    public ModelAndView newsTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_20) int limit,HttpServletRequest request) throws E404Excetion {
		 	IRequest requestContext = createRequestContext(request);
	    	ModelAndView mv = new ModelAndView( "/index/news/newsTypeList");
	        
	        if(typeid==null)
	    		throw new E404Excetion("查看的网页不存在!"); 
	        KgType kgNewstype = iJedisService.loadType( new KgType(typeid));
	        if(kgNewstype==null)
	    		throw new E404Excetion("查看的网页不存在!"); 
	        KgNews news = new KgNews();
	        news.setTypeid(typeid);
	        
	        List<KgNews> list = iJedisService.loadTypeNews(news);
	        int count = list.size();
	        list=CommonFuncUtil.listToPage(list, page, limit);
	        CommonFuncUtil.judgeNewsTitleLength(list,33);
	        
	        int allPageNum = count%limit==0?count/limit:count/limit+1;
	        if(count==0) allPageNum=1;
	        
	        mv.addObject("newsList", list);
	        mv.addObject("page", page);
	        mv.addObject("allPageNum",allPageNum);
	        mv.addObject("kgNewstype", kgNewstype);
	        mv.addObject("typeid", typeid);
	        loadNavigation(mv, IndexController.CH_ZXZX);
	        loadAttriteNews(mv, kgNewstype.getParentid(),3);
	        loadSysConfig(request,mv);
	        return mv;
	    }
	 
	 @RequestMapping(value = "/index/news/sourceList")  //这边固定死加载父类是ZXZX_ID
	    @ResponseBody
	    public ModelAndView newsSourceList(Long sourceid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_20) int limit,HttpServletRequest request) throws E404Excetion {
		  IRequest requestContext = createRequestContext(request);
	    	ModelAndView mv = new ModelAndView( "/index/news/newsSourceList");
	    	
    	   if(sourceid==null)
		    		throw new E404Excetion("查看的网页不存在!"); 
    	   KgNewsSource kgNewsSource = iJedisService.loadKgNewsSource( new KgNewsSource(sourceid));
    	   if(kgNewsSource==null)
	    		throw new E404Excetion("查看的网页不存在!"); 
    	   
	      
	        KgNews news = new KgNews();
	        news.setSourceid(sourceid);
	        
	        KgType kt = new KgType();
	        kt.setParentid(ZXZX_ID);
	        List<KgType> typeList = iJedisService.loadChildType(kt);
	        List<Long> typeidList = new ArrayList<Long>();
	        for(KgType kn:typeList) {
	        	typeidList.add(kn.getId());
	        }
	        
	        int count = iKgNewsService.selectCountByMap( requestContext,news, typeidList);
	        int allPageNum = count%limit==0?count/limit:count/limit+1;
	        if(count==0) allPageNum=1;
	        List<KgNews> list = iKgNewsService.selectByMap(requestContext,news, typeidList, page, limit);
//	        list=CommonFuncUtil.listToPage(list, page, limit);
	        CommonFuncUtil.judgeNewsTitleLength(list,33);
	       
	        mv.addObject("newsList", list);
	        mv.addObject("page", page);
	        mv.addObject("allPageNum",allPageNum);
	        mv.addObject("kgNewsSource", kgNewsSource);
	        mv.addObject("sourceid", sourceid);
	        
	        loadNavigation(mv, IndexController.CH_ZXZX);
	        loadAttriteNews(mv, ZXZX_ID,3);
	        loadSysConfig(request,mv);
	        return mv;
	    }


    @RequestMapping(value = "/index/news/newsDetail")
    @ResponseBody
    public ModelAndView newsDetail(Long id,HttpServletRequest request) throws E404Excetion {
    	IRequest requestContext = createRequestContext(request);
    	ModelAndView mv = new ModelAndView( "/index/news/newsDetail");
        if(id==null)
    		throw new E404Excetion("查看的网页不存在!"); 
        KgNews newsInfo = iJedisService.loadNews( new KgNews(id));
        if(newsInfo==null)
    		throw new E404Excetion("查看的网页不存在!"); 
        
        mv.addObject("newsInfo", newsInfo);
        KgType newsType = new KgType();
        newsType.setId(newsInfo.getTypeid());
        KgType kgNewstype = iJedisService.loadType(newsType);
        
        KgNewsSource newsSource = new KgNewsSource();
        newsSource.setId(newsInfo.getSourceid());
        KgNewsSource kgNewsSource = iJedisService.loadKgNewsSource( newsSource);
        
      
        KgNews linkNews = new KgNews();
        linkNews.setTypeid(newsInfo.getTypeid());
        linkNews.setId(id);
        List<KgNews> linkNewsList = iKgNewsService.selectLinkNews(requestContext,linkNews, 1);
        CommonFuncUtil.judgeNewsTitleLength(linkNewsList,45);
        
        mv.addObject("kgNewstype", kgNewstype);
        mv.addObject("kgNewsSource", kgNewsSource);
        mv.addObject("linkNewsList", linkNewsList);
        
        
        loadNavigation(mv, IndexController.CH_ZXZX);
        loadAttriteNews(mv, kgNewstype.getParentid(),3);
        loadSysConfig(request,mv);
        return mv;
    }
    
	

    
 
   
}
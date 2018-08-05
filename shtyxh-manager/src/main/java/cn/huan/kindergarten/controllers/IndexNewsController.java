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
import cn.huan.kindergarten.dto.KgNewsSource;
import cn.huan.kindergarten.dto.KgNewstype;
import cn.huan.kindergarten.service.IKgConfigService;
import cn.huan.kindergarten.service.IKgNewsAttributeService;
import cn.huan.kindergarten.service.IKgNewsService;
import cn.huan.kindergarten.service.IKgNewsSourceService;
import cn.huan.kindergarten.service.IKgNewstypeService;
import cn.huan.kindergarten.utils.CommonUtil;

@Controller
public class IndexNewsController extends IndexBaseController{
	
	
	@Autowired
	private IKgNewsService iKgNewsService;
	@Autowired
	private IKgNewstypeService iKgNewstypeService;
	@Autowired 
	private IKgNewsSourceService iKgNewsSourceService;
	@Autowired
	private IKgNewsAttributeService iKgNewsAttributeService;
	
	@RequestMapping(value = "/index/news")
    @ResponseBody
    public ModelAndView news(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/news/news");
        IRequest requestContext = createRequestContext(request);
        List<KgNewstype> typeList = iKgNewstypeService.selectAll(requestContext);
        for(KgNewstype kn:typeList) {
        	KgNews news = new KgNews();
        	news.setTypeid(kn.getId());
        	int count = iKgNewsService.adminQueryCount(requestContext, news);
        	kn.setCount(count);
        	List<KgNews> newsList = iKgNewsService.selectWithOtherInfo(requestContext, news, 1, 6);
        	kn.setNewsList(newsList);
        }
        
        List<KgNewsSource> sourceList = iKgNewsSourceService.selectAll(requestContext);
        for(KgNewsSource kn:sourceList) {
        	KgNews news = new KgNews();
        	news.setSourceid(kn.getId());
        	int count = iKgNewsService.adminQueryCount(requestContext, news);
        	kn.setCount(count);
        }
        
        
        KgNews kn = new KgNews();
    	kn.setAttributeid("4");
    	List<KgNews> newsTop=iKgNewsService.selectWithOtherInfo(requestContext, kn, 1, 1);
    	if(newsTop.size()!=0)
    		mv.addObject("newsTop", newsTop.get(0));
    	else
    		mv.addObject("newsTop", null);
        mv.addObject("typeList", typeList);
        mv.addObject("sourceList",sourceList);
        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,2);
        loadSysConfig(mv);
        return mv;
    }
	
	@RequestMapping(value = "/index/newsSearch")
    @ResponseBody
    public ModelAndView newsSearch(String searchparam,String dateissuestart,String dateissueend , Long newstype,Long newssource,
    		 @RequestParam(defaultValue = DEFAULT_PAGE) int page, @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,
    		HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/news/newsSearch");
        IRequest requestContext = createRequestContext(request);
        
        KgNews news = new KgNews();
        if(searchparam!=null&&!("").equals(searchparam)) news.setNewstitle(searchparam);
        if(newstype!=null)   news.setTypeid(newstype);
        if(newssource!=null)   news.setSourceid(newssource);
        if(dateissuestart!=null) news.setStartDate(CommonUtil.stringTodate10(dateissuestart+CommonUtil.STARTTIME));
        if(dateissueend!=null) news.setEndDate(CommonUtil.stringTodate10(dateissueend+CommonUtil.ENDTIME));
        List<KgNews> list = iKgNewsService.selectWithOtherInfo(requestContext, news, page, limit);
        int count = iKgNewsService.adminQueryCount(requestContext, news);
        int allPageNum = count%limit==0?count/limit:count/limit+1;
        if(count==0) allPageNum=1;
        
        mv.addObject("newstitle",searchparam);
        mv.addObject("newsList", list);
        mv.addObject("newsListSize", count);
        mv.addObject("page", page);
        mv.addObject("allPageNum",allPageNum);
        
        mv.addObject("searchparam",searchparam);
        mv.addObject("dateissuestart",dateissuestart);
        mv.addObject("dateissueend",dateissueend);
        mv.addObject("newstype",newstype==null?"":newstype);
        mv.addObject("newssource",newssource==null?"":newssource);
        
        
        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
        loadSysConfig(mv);
       
        return mv;
    }
	
	 @RequestMapping(value = "/index/newsTypeList")
	    @ResponseBody
	    public ModelAndView newsTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/news/newsTypeList");
	        IRequest requestContext = createRequestContext(request);
	        KgNews news = new KgNews();
	        news.setTypeid(typeid);
	        int count = iKgNewsService.adminQueryCount(requestContext, news);
	        int allPageNum = count%limit==0?count/limit:count/limit+1;
	        if(count==0) allPageNum=1;
	        List<KgNews> list = iKgNewsService.selectWithOtherInfo(requestContext, news, page, limit);
	        KgNewstype newsType = new KgNewstype();
	        newsType.setId(typeid);
	        KgNewstype kgNewstype = iKgNewstypeService.selectByPrimaryKey(requestContext, newsType);
	        CommonUtil.judgeNewsTitleLength(list,33);
	        mv.addObject("newsList", list);
	        mv.addObject("page", page);
	        mv.addObject("allPageNum",allPageNum);
	        mv.addObject("kgNewstype", kgNewstype);
	        mv.addObject("typeid", typeid);
	        
	        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,3);
	        loadSysConfig(mv);
	        return mv;
	    }
	 
	 @RequestMapping(value = "/index/newsSourceList")
	    @ResponseBody
	    public ModelAndView newsSourceList(Long sourceid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/news/newsSourceList");
	        IRequest requestContext = createRequestContext(request);
	        KgNews news = new KgNews();
	        news.setSourceid(sourceid);
	        int count = iKgNewsService.adminQueryCount(requestContext, news);
	        int allPageNum = count%limit==0?count/limit:count/limit+1;
	        if(count==0) allPageNum=1;
	        List<KgNews> list = iKgNewsService.selectWithOtherInfo(requestContext, news, page, limit);
	        CommonUtil.judgeNewsTitleLength(list,33);
	        KgNewsSource newsSource = new KgNewsSource();
	        newsSource.setId(sourceid);
	        KgNewsSource kgNewsSource = iKgNewsSourceService.selectByPrimaryKey(requestContext, newsSource);
	        mv.addObject("newsList", list);
	        mv.addObject("page", page);
	        mv.addObject("allPageNum",allPageNum);
	        mv.addObject("kgNewsSource", kgNewsSource);
	        mv.addObject("sourceid", sourceid);
	        
	        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,3);
	        loadSysConfig(mv);
	        return mv;
	    }


    @RequestMapping(value = "/index/newsDetail")
    @ResponseBody
    public ModelAndView newsDetail(Long id,HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/news/newsDetail");
        IRequest requestContext = createRequestContext(request);
        
        KgNews news = new KgNews();
        news.setId(id);
        KgNews newsInfo = iKgNewsService.selectByPrimaryKey(requestContext, news);
        mv.addObject("newsInfo", newsInfo);
        
        KgNewstype newsType = new KgNewstype();
        newsType.setId(newsInfo.getTypeid());
        KgNewstype kgNewstype = iKgNewstypeService.selectByPrimaryKey(requestContext, newsType);
        
        KgNewsSource newsSource = new KgNewsSource();
        newsSource.setId(newsInfo.getSourceid());
        KgNewsSource kgNewsSource = iKgNewsSourceService.selectByPrimaryKey(requestContext, newsSource);
        
      
        KgNews linkNews = new KgNews();
        linkNews.setTypeid(newsInfo.getTypeid());
        List<KgNews> linkNewsList = iKgNewsService.select(requestContext, linkNews, 1, 2);
        CommonUtil.judgeNewsTitleLength(linkNewsList,17);
        
        mv.addObject("kgNewstype", kgNewstype);
        mv.addObject("kgNewsSource", kgNewsSource);
        mv.addObject("linkNewsList", linkNewsList);
        
        
        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,3);
        loadSysConfig(mv);
        return mv;
    }
    


    
 
   
}
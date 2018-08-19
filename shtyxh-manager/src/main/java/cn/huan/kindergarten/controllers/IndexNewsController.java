package cn.huan.kindergarten.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

@Controller
public class IndexNewsController extends IndexBaseController{
	
	
	@Autowired
	private IKgNewsService iKgNewsService;
	@Autowired
	private IKgTypeService iKgTypeService;
	@Autowired 
	private IKgNewsSourceService iKgNewsSourceService;
	@Autowired
	private IKgNewsAttributeService iKgNewsAttributeService;
	
	@RequestMapping(value = "/index/news")
    @ResponseBody
    public ModelAndView news(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/news/news");
        IRequest requestContext = createRequestContext(request);
        KgType kt = new KgType();
        kt.setParentid(3L);
        kt.setRelatetype(2);
        //查询列表
        List<Long> typeidList = new ArrayList<Long>();
        
        List<KgType> typeList = iKgTypeService.select(requestContext, kt);
        for(KgType kn:typeList) {
        	typeidList.add(kn.getId());
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
        	int count = iKgNewsService.adminQueryCount(requestContext, news,typeidList);
        	kn.setCount(count);
        }
        
        KgType offerType = iKgTypeService.selectByPrimaryKey(requestContext, new KgType(OFFER_ID));
        
        KgNews kn = new KgNews();
    	kn.setAttributeid("4");
    	List<KgNews> newsTop=iKgNewsService.selectByMap(requestContext, kn,typeidList, 1, 1);
    	if(newsTop.size()!=0) {
   	   		 if(("").equals(newsTop.get(0).getThumbnail())) {
   	   			 kn.setThumbnail(SysConfig.nonePic);
   	   		 }
    		mv.addObject("newsTop", newsTop.get(0));
    	}else
    		mv.addObject("newsTop", null);
        mv.addObject("typeList", typeList);
        mv.addObject("sourceList",sourceList);
        mv.addObject("offerType",offerType);
        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
        loadSysConfig(mv);
        return mv;
    }
	
	@RequestMapping(value = "/index/news/search")
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
        if(StringUtils.isNotEmpty(dateissuestart)) news.setStartDate(CommonUtil.stringTodate10(dateissuestart+CommonUtil.STARTTIME));
        if(StringUtils.isNotEmpty(dateissueend)) news.setEndDate(CommonUtil.stringTodate10(dateissueend+CommonUtil.ENDTIME));
        List<KgNews> list = iKgNewsService.selectWithOtherInfo(requestContext, news, page, limit);
        int count = iKgNewsService.adminQueryCount(requestContext, news);
        int allPageNum = count%limit==0?count/limit:count/limit+1;
        if(count==0) allPageNum=1;
        
        mv.addObject("newstitle",searchparam);
        mv.addObject("newsList", list);
        mv.addObject("newsListSize", count);
        mv.addObject("page", page);
        mv.addObject("allPageNum",allPageNum);
        
        KgType kt = new KgType();
        kt.setRelatetype(2);
        List<KgType> typeList = iKgTypeService.select(requestContext, kt);
        
        mv.addObject("typeList", typeList);
        mv.addObject("searchparam",searchparam);
        mv.addObject("dateissuestart",dateissuestart);
        mv.addObject("dateissueend",dateissueend);
        mv.addObject("newstype",newstype==null?"":newstype);
        mv.addObject("newssource",newssource==null?"":newssource);
        
        
        loadNavigation(mv, requestContext,IndexController.CH_NULL);
        loadSysConfig(mv);
       
        return mv;
    }
	
	 @RequestMapping(value = "/index/news/typeList")
	    @ResponseBody
	    public ModelAndView newsTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_20) int limit,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/news/newsTypeList");
	        IRequest requestContext = createRequestContext(request);
	        if(typeid==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
	        KgType kgNewstype = iKgTypeService.selectByPrimaryKey(requestContext, new KgType(typeid));
	        if(kgNewstype==null)
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
	        mv.addObject("kgNewstype", kgNewstype);
	        mv.addObject("typeid", typeid);
	        
	        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,kgNewstype.getParentid(),3);
	        loadSysConfig(mv);
	        return mv;
	    }
	 
	 @RequestMapping(value = "/index/news/sourceList")  //这边固定死加载父类是ZXZX_ID
	    @ResponseBody
	    public ModelAndView newsSourceList(Long sourceid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_20) int limit,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/news/newsSourceList");
	    	IRequest requestContext = createRequestContext(request);
    	   if(sourceid==null)
		    		throw new E404Excetion("请查看的网页不存在!"); 
    	   KgNewsSource kgNewsSource = iKgNewsSourceService.selectByPrimaryKey(requestContext, new KgNewsSource(sourceid));
    	   if(kgNewsSource==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
    	   
	      
	        KgNews news = new KgNews();
	        news.setSourceid(sourceid);
	        int count = iKgNewsService.adminQueryCount(requestContext, news);
	        int allPageNum = count%limit==0?count/limit:count/limit+1;
	        if(count==0) allPageNum=1;
	        List<KgNews> list = iKgNewsService.selectWithOtherInfo(requestContext, news, page, limit);
	        CommonUtil.judgeNewsTitleLength(list,33);
	       
	        mv.addObject("newsList", list);
	        mv.addObject("page", page);
	        mv.addObject("allPageNum",allPageNum);
	        mv.addObject("kgNewsSource", kgNewsSource);
	        mv.addObject("sourceid", sourceid);
	        
	        loadNavigation(mv, requestContext,IndexController.CH_ZXZX);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,ZXZX_ID,3);
	        loadSysConfig(mv);
	        return mv;
	    }


    @RequestMapping(value = "/index/news/newsDetail")
    @ResponseBody
    public ModelAndView newsDetail(Long id,HttpServletRequest request) throws E404Excetion {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/news/newsDetail");
        IRequest requestContext = createRequestContext(request);
        if(id==null)
    		throw new E404Excetion("请查看的网页不存在!"); 
        KgNews newsInfo = iKgNewsService.selectByPrimaryKey(requestContext, new KgNews(id));
        if(newsInfo==null)
    		throw new E404Excetion("请查看的网页不存在!"); 
        
        mv.addObject("newsInfo", newsInfo);
        
        KgType newsType = new KgType();
        newsType.setId(newsInfo.getTypeid());
        KgType kgNewstype = iKgTypeService.selectByPrimaryKey(requestContext, newsType);
        
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
        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,kgNewstype.getParentid(),3);
        loadSysConfig(mv);
        return mv;
    }
    


    
 
   
}
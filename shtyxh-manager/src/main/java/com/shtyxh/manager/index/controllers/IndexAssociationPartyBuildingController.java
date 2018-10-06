package com.shtyxh.manager.index.controllers;

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
import com.shtyxh.common.exception.E404Excetion;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgNewsSource;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgNewsService;
import com.shtyxh.manager.service.IKgTypeService;
import com.shtyxh.manager.utils.CommonFuncUtil;
/**
 * 党建活动
 * @author huan
 *
 */
@Controller
public class IndexAssociationPartyBuildingController extends IndexBaseController{
	

	
	@Autowired
	private IKgNewsService iKgNewsService;
	@Autowired
	private IKgTypeService iKgTypeService;
	@Autowired
	private IJedisService iJedisService;
	
	@RequestMapping(value = {"/index/djhd"})
    @ResponseBody
    public ModelAndView associationPartyBuilding(HttpServletRequest request) {
		IRequest requestContext = createRequestContext(request);
    	ModelAndView mv = new ModelAndView( "/index/associationPartyBuilding/associationPartyBuilding");
        KgType kt = new KgType();
        kt.setParentid(DJHD_ID);
        kt.setRelatetype(2);
        List<KgType> typeList = iKgTypeService.select( requestContext,kt);
        //查询列表
        List<Long> typeidList = new ArrayList<Long>();
        for(KgType kn:typeList) {
        	typeidList.add(kn.getId());
        	KgNews news = new KgNews();
        	news.setTypeid(kn.getId());
        	List<KgNews> newsList = iJedisService.loadTypeNews(news);
        	int count =newsList.size();
        	kn.setCount(count);
        	newsList=CommonFuncUtil.listToList(newsList, 6);
        	kn.setNewsList(newsList);
        }
        
        mv.addObject("typeList", typeList);
        loadNavigation(mv, IndexController.CH_DJHD);
        loadSysConfig(mv);
        return mv;
    }
	
	 @RequestMapping(value = {"/index/djhd/typeList"})
	    @ResponseBody
	    public ModelAndView associationPartyBuildingTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_20) int limit,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView( "/index/associationPartyBuilding/associationPartyBuildingTypeList");
	    	
	    	if(typeid==null)
	    		throw new E404Excetion("查看的网页不存在!"); 
	    	KgType kgtype = iJedisService.loadType(new KgType(typeid));
	    	if(kgtype==null)
	    		throw new E404Excetion("查看的网页不存在!"); 
	        KgNews news = new KgNews();
	        news.setTypeid(typeid);
	        
	        List<KgNews> list = iJedisService.loadTypeNews(news);
	        int count = list.size();
	        int allPageNum = count%limit==0?count/limit:count/limit+1;
	        if(count==0) allPageNum=1;
	      
	        list=CommonFuncUtil.listToPage(list, page, limit);
	        CommonFuncUtil.judgeNewsTitleLength(list,33);
	        mv.addObject("newsList", list);
	        mv.addObject("page", page);
	        mv.addObject("allPageNum",allPageNum);
	        mv.addObject("kgtype", kgtype);
	        
	        loadNavigation(mv, IndexController.CH_DJHD);
	        loadAttriteNews(mv, kgtype.getParentid(),3);
	        loadSysConfig(mv);
	        return mv;
	    }
	 
	
	    @RequestMapping(value = "/index/djhd/newsDetail")
	    @ResponseBody
	    public ModelAndView associationDynamicsDetail(Long id,HttpServletRequest request) throws E404Excetion {
	    	 IRequest requestContext = createRequestContext(request);
	    	ModelAndView mv = new ModelAndView( "/index/associationPartyBuilding/associationPartyBuildingNewsDetail");
	        if(id==null)
	        	throw new E404Excetion("查看的网页不存在!"); 
	        KgNews newsInfo = iJedisService.loadNews( new KgNews(id));
	        if(newsInfo==null)
	        	throw new E404Excetion("查看的网页不存在!"); 
	        
	        mv.addObject("newsInfo", newsInfo);
	        KgType kgNewstype = iJedisService.loadType(new KgType(newsInfo.getTypeid()));
	        KgNewsSource kgNewsSource = iJedisService.loadKgNewsSource( new KgNewsSource(newsInfo.getSourceid()));
	        
	      
	        KgNews linkNews = new KgNews();
	        linkNews.setTypeid(newsInfo.getTypeid());
	        linkNews.setId(newsInfo.getId());
	        List<KgNews> linkNewsList = iKgNewsService.selectLinkNews(requestContext,linkNews, 1);
	        CommonFuncUtil.judgeNewsTitleLength(linkNewsList,45);
	        
	        mv.addObject("kgNewstype", kgNewstype);
	        mv.addObject("kgNewsSource", kgNewsSource);
	        mv.addObject("linkNewsList", linkNewsList);
	        
	        
	        loadNavigation(mv, IndexController.CH_DJHD);
	        loadAttriteNews(mv, kgNewstype.getParentid(),3);
	        loadSysConfig(mv);
	        return mv;
	    }
	    
	
	
 
   
}
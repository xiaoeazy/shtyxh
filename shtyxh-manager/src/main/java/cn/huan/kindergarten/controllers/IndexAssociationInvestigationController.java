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
 * 调查与研究
 * @author huan
 *
 */
@Controller
public class IndexAssociationInvestigationController extends IndexBaseController{
	
	@Autowired
	private IKgNewsService iKgNewsService;
	@Autowired
	private IKgTypeService iKgTypeService;
	@Autowired
	private IKgNewsAttributeService iKgNewsAttributeService;
	@Autowired
	private IKgNewsSourceService iKgNewsSourceService;
	
	@RequestMapping(value = {"/index/dcyyj"})
    @ResponseBody
    public ModelAndView associationPartyBuilding(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/associationInvestigation/associationInvestigation");
        IRequest requestContext = createRequestContext(request);
        KgType kt = new KgType();
        kt.setParentid(DCYYJ_ID);
        kt.setRelatetype(2);
        List<KgType> typeList = iKgTypeService.select(requestContext, kt);
        //查询列表
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
        
        
        
        mv.addObject("typeList", typeList);
        
        loadNavigation(mv, requestContext,IndexController.CH_DCYYJ);
        loadSysConfig(mv);
        return mv;
    }
	
	 @RequestMapping(value = {"/index/dcyyj/typeList"})
	    @ResponseBody
	    public ModelAndView associationPartyBuildingTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_20) int limit,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/associationInvestigation/associationInvestigationTypeList");
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
	        
	        loadNavigation(mv, requestContext,IndexController.CH_DCYYJ);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,kgtype.getParentid(),3);
	        loadSysConfig(mv);
	        return mv;
	    }
	 
	
	    @RequestMapping(value = "/index/dcyyj/newsDetail")
	    @ResponseBody
	    public ModelAndView associationDynamicsDetail(Long id,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/associationInvestigation/associationInvestigationNewsDetail");
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
	        
	        
	        loadNavigation(mv, requestContext,IndexController.CH_DCYYJ);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,kgNewstype.getParentid(),3);
	        loadSysConfig(mv);
	        return mv;
	    }
	    
	
 
   
}
package com.shtyxh.manager.index.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.common.exception.E404Excetion;
import com.shtyxh.manager.dto.KgAllonetext;
import com.shtyxh.manager.dto.KgContact;
import com.shtyxh.manager.dto.KgHistory;
import com.shtyxh.manager.dto.KgMemberUnits;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgAllonetextService;
import com.shtyxh.manager.service.IKgContactService;
import com.shtyxh.manager.service.IKgHistoryService;
import com.shtyxh.manager.service.IKgMemberUnitsService;
import com.shtyxh.manager.service.IKgNewsService;
import com.shtyxh.manager.service.IKgTypeService;
import com.shtyxh.manager.utils.CommonFuncUtil;

/**
 * 协会概况
 * @author huanghuan
 *
 */
@Controller
public class IndexAssociationSurveyController extends IndexBaseController{
	
	@Autowired
	private IJedisService iJedisService;
	@Autowired
	private IKgMemberUnitsService iKgMemberUnitsService;
	
	@RequestMapping(value = {"/index/xhgk"})
    @ResponseBody
    public ModelAndView associationSurveyTypeList(Long typeid,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 ModelAndView mv = new ModelAndView( "/index/associationSurvey/associationSurvey");
    	 
    	 KgType kt = new KgType();
    	 kt.setParentid(XHGK_ID);
    	 List<KgType> leftNavList = iJedisService.loadChildType(kt);
    	 KgType nowType = null;
    	 for(KgType type :leftNavList) {
    		 if(typeid==type.getId()) {
    			 nowType =type;
    		 }
    	 }
    	 if(nowType==null) {
    		 nowType = leftNavList.get(0);
    	 }
    	 loadNavigation(mv,  CH_XHGK);
    	 mv.addObject("leftList",leftNavList);
    	 mv.addObject("nowType",nowType);
    	 loadSysConfig(request,mv);
         return mv;
    }
	
	@RequestMapping(value = {"/index/xhgk/typeList"})
    @ResponseBody
    public ModelAndView typeList(Long typeid,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
		 ModelAndView mv = new ModelAndView( "/index/associationSurvey/rightDetail");
		 
		 KgType nowType = iJedisService.loadType(new KgType(typeid));
		 
		 if(typeid==LJLS_ID||typeid==LSYG_ID||typeid==HYDW_ID){ //这些板块内容开发中
			 mv = new ModelAndView("/index/associationSurvey/pageDesign");
			 mv.addObject("nowType",nowType);
			 return mv;
		 }
		 
	   	 if(typeid==LSYG_ID) {
    		 Map<String,List<KgHistory>> map =new HashMap<String,List<KgHistory>>();
    		 mv = new ModelAndView("/index/associationSurvey/historyDetail");
    		 List<KgHistory> kgHistoryList =  iJedisService.loadHistory();
    		 for(KgHistory history:kgHistoryList) {
    			 List<KgHistory> value = map.get(history.getHistoryyear());
    			 if(value==null) {
    				 List<KgHistory> list = new ArrayList<KgHistory>();
    				 list.add(history);
    				 map.put(history.getHistoryyear(), list);
    			 }else {
    				 value.add(history);
    			 }
    		 }
         	 mv.addObject("nowType",nowType);
    		 mv.addObject("rightMap",map);
             return mv;
    	 }
    	 
    	 if(typeid==HYDW_ID) {
    		 mv = new ModelAndView( "/index/associationSurvey/hydwDetail");
        	 mv.addObject("nowType",nowType);
             return mv;
    	 }
    	 
    	 if(typeid==LXFS_ID) {
    		mv = new ModelAndView( "/index/associationSurvey/contactDetail");
    		loadNavigation(mv,  CH_XHGK);
        	mv.addObject("nowType",nowType);
        	 
         	List<KgContact> contactList = iJedisService.loadContact();
         	Map<String ,String > map = new HashMap<String ,String >();
         	for(KgContact ct :contactList) {
         		map.put(ct.getSyskey(), ct.getSysvalue());
         	}
         	mv.addObject("contactMap",map);
             return mv;
    	 }
    		
    	
    	 if(nowType.getRelatetype()==1) {
    		 KgAllonetext a = new KgAllonetext();
    		 a.setId(nowType.getRelatetypeid());
    		 KgAllonetext aot   = iJedisService.loadAllonetext(a);
    		 mv.addObject("content",aot.getContent());
    	 }
    	 if(nowType.getRelatetype()==2) {
    		 KgNews a = new KgNews();
    		 a.setTypeid(nowType.getId());
    		 List<KgNews> newList = iJedisService.loadTypeNews(a);
    		 int count = newList.size();
    		 newList=CommonFuncUtil.listToPage(newList, page, limit);
    		
    	     int allPageNum = count%limit==0?count/limit:count/limit+1;
    	     CommonFuncUtil.judgeNewsTitleLength(newList,45);
    	     if(count==0) allPageNum=1;
    	     mv.addObject("page", page);
    	     mv.addObject("allPageNum",allPageNum);
    	     mv.addObject("rightList",newList);
    	 }
    	 mv.addObject("nowType",nowType);
		 return mv;
	}
	
	
	
	@RequestMapping(value = "/index/xhgk/newsDetail")
    @ResponseBody
    public ModelAndView associationSurveyTypeDetail(Long typeid,Long newsid,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) throws E404Excetion {
    	 ModelAndView mv = new ModelAndView( "/index/associationSurvey/newsDetail");
    	 
    	 KgType nowType = iJedisService.loadType(new KgType(typeid));
    	 if(nowType==null)
    		 throw new E404Excetion("该类型不存在！");
    	 KgNews selectBean = new KgNews();
    	 selectBean.setId(newsid);
    	 KgNews newsInfo   = iJedisService.loadNews(selectBean);
    	 mv.addObject("nowType",nowType);
    	 mv.addObject("newsInfo",newsInfo);
         return mv;
    }
	
	
	   //==获取会员单位
    @RequestMapping(value = "/index/xhgk/loadHYDW")
    @ResponseBody
    public ExtStore loadHYDW(  KgMemberUnits memberUnits,HttpServletRequest request,
    		@RequestParam(defaultValue = DEFAULT_PAGE) int offset,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
    	
        IRequest requestContext = createRequestContext(request);
        int page = offset /pageSize+1;
        List<KgMemberUnits> list = iKgMemberUnitsService.selectWithOtherInfo(requestContext, memberUnits, page, pageSize);
        int size = iKgMemberUnitsService.adminQueryCount(requestContext, memberUnits);
        return new ExtStore(page, pageSize, size, list);
    }
}
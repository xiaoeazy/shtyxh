package cn.huan.kindergarten.controllers;

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

import cn.huan.kindergarten.dto.KgAllonetext;
import cn.huan.kindergarten.dto.KgContact;
import cn.huan.kindergarten.dto.KgHistory;
import cn.huan.kindergarten.dto.KgMemberUnits;
import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.dto.KgType;
import cn.huan.kindergarten.service.IKgAllonetextService;
import cn.huan.kindergarten.service.IKgContactService;
import cn.huan.kindergarten.service.IKgHistoryService;
import cn.huan.kindergarten.service.IKgMemberUnitsService;
import cn.huan.kindergarten.service.IKgNewsService;
import cn.huan.kindergarten.service.IKgTypeService;
import cn.huan.kindergarten.utils.CommonUtil;
import cn.huan.shtyxh.common.bean.ExtStore;

/**
 * 协会概况
 * @author huanghuan
 *
 */
@Controller
public class IndexAssociationSurveyController extends IndexBaseController{
	
	@Autowired
	private IKgTypeService kgTypeService;
	@Autowired
	private IKgAllonetextService kgAllonetextService;
	@Autowired
	private IKgMemberUnitsService kgMemberUnitsService;
	@Autowired
	private IKgNewsService kgNewsService;
	@Autowired
	private IKgHistoryService kgHistoryService;
	@Autowired
	private IKgContactService kgContactService;
	
	@RequestMapping(value = {"/index/xhgk","/index/xhgk/typeList"})
    @ResponseBody
    public ModelAndView associationSurveyTypeList(Long typeid,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 ModelAndView mv = new ModelAndView(getViewPath() + "/index/associationSurvey/associationSurveyTypeList");
    	 IRequest requestContext = createRequestContext(request);
    	 Long parentId = XHGK_ID;
    	 List<KgType> leftNavList = kgTypeService.findChildType(parentId);
    	 KgType nowType = null;
    	 for(KgType type :leftNavList) {
    		 if(typeid==type.getId()) {
    			 nowType =type;
    		 }
    	 }
    	 if(nowType==null) {
    		 nowType = leftNavList.get(0);
    	 }
    	 
    	 if(typeid==LSYG_ID) {
    		 Map<String,List<KgHistory>> map =new HashMap<String,List<KgHistory>>();
    		 mv = new ModelAndView(getViewPath() + "/index/associationSurvey/associationSurveyTypeListHistory");
    		 List<KgHistory> kgHistoryList =  kgHistoryService.selectAll(requestContext);
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
    		 loadNavigation(mv, requestContext, CH_XHGK);
    		 mv.addObject("leftList",leftNavList);
    		 mv.addObject("rightMap",map);
        	 mv.addObject("nowType",nowType);
        	 loadSysConfig(mv);
             return mv;
    	 }
    	 
    	 if(typeid==HYDW_ID) {
    		 mv = new ModelAndView(getViewPath() + "/index/associationSurvey/associationSurveyTypeListHYDW");
    		 loadNavigation(mv, requestContext, CH_XHGK);
    		 mv.addObject("leftList",leftNavList);
        	 mv.addObject("nowType",nowType);
        	 loadSysConfig(mv);
             return mv;
    	 }
    	 
    	 if(typeid==LXFS_ID) {
    		mv = new ModelAndView(getViewPath() + "/index/associationSurvey/associationSurveyTypeListContact");
    		loadNavigation(mv, requestContext, CH_XHGK);
    		mv.addObject("leftList",leftNavList);
        	mv.addObject("nowType",nowType);
        	 
         	List<KgContact> contactList = kgContactService.selectAll(requestContext);
         	Map<String ,String > map = new HashMap<String ,String >();
         	for(KgContact ct :contactList) {
         		map.put(ct.getSyskey(), ct.getSysvalue());
         	}
         	mv.addObject("contactMap",map);
         	loadSysConfig(mv);
             return mv;
    	 }
    		
    	
    	 if(nowType.getRelatetype()==1) {
    		 KgAllonetext a = new KgAllonetext();
    		 a.setId(nowType.getRelatetypeid());
    		 KgAllonetext aot   = kgAllonetextService.selectByPrimaryKey(requestContext, a);
    		 mv.addObject("content",aot.getContent());
    	 }
    	 if(nowType.getRelatetype()==2) {
    		 KgNews a = new KgNews();
    		 a.setTypeid(nowType.getId());
    		 List<KgNews> newList   = kgNewsService.select(requestContext, a, page, limit);
    		 int count = kgNewsService.adminQueryCount(requestContext, a);
    	     int allPageNum = count%limit==0?count/limit:count/limit+1;
    	     CommonUtil.judgeNewsTitleLength(newList,45);
    	     if(count==0) allPageNum=1;
    	     mv.addObject("page", page);
    	     mv.addObject("allPageNum",allPageNum);
    	     mv.addObject("rightList",newList);
    	 }
    	 loadNavigation(mv, requestContext, CH_XHGK);
    	 mv.addObject("leftList",leftNavList);
    	 mv.addObject("nowType",nowType);
    	
    	 loadSysConfig(mv);
         return mv;
    }

	@RequestMapping(value = "/index/xhgk/newsDetail")
    @ResponseBody
    public ModelAndView associationSurveyTypeDetail(Long typeid,Long newsid,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 ModelAndView mv = new ModelAndView(getViewPath() + "/index/associationSurvey/associationSurveyTypeDetail");
    	 IRequest requestContext = createRequestContext(request);
    	 Long parentId = 2L;
    	 List<KgType> leftNavList = kgTypeService.findChildType(parentId);
    	 KgType nowType = null;
    	 for(KgType type :leftNavList) {
    		 if(typeid==type.getId()) {
    			 nowType =type;
    		 }
    	 }
    	 if(nowType==null) {
    		throw new RuntimeException("不存在！");
    	 }
    	 
    	 KgNews selectBean = new KgNews();
    	 selectBean.setId(newsid);
    	 KgNews newsInfo   = kgNewsService.selectByPrimaryKey(requestContext, selectBean);
    	 
    	 
    	 loadNavigation(mv, requestContext, CH_XHGK);
    	 mv.addObject("leftList",leftNavList);
    	 mv.addObject("nowType",nowType);
    	 mv.addObject("newsInfo",newsInfo);
    	
    	 loadSysConfig(mv);
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
        List<KgMemberUnits> list = kgMemberUnitsService.selectWithOtherInfo(requestContext, memberUnits, page, pageSize);
        int size = kgMemberUnitsService.adminQueryCount(requestContext, memberUnits);
        return new ExtStore(page, pageSize, size, list);
    }
}
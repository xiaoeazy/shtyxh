package com.shtyxh.manager.index.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.ResponseData;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.common.bean.UploadFileInfo;
import com.shtyxh.common.exception.E404Excetion;
import com.shtyxh.common.exception.GlobalException;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.bean.SysConfig;
import com.shtyxh.manager.dto.KgAllonetext;
import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;
import com.shtyxh.manager.dto.KgAssessmentActivityUserUpload;
import com.shtyxh.manager.dto.KgAssessmentType;
import com.shtyxh.manager.dto.KgContact;
import com.shtyxh.manager.dto.KgHistory;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgNewsAttribute;
import com.shtyxh.manager.dto.KgNewsSource;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IIndexAssessmentService;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgAssessmentActivityService;
import com.shtyxh.manager.service.IKgAssessmentActivityUserUploadService;
import com.shtyxh.manager.service.IKgAssessmentTypeService;
import com.shtyxh.manager.service.IKgNewsAttributeService;
import com.shtyxh.manager.service.IKgNewsService;
import com.shtyxh.manager.service.IKgNewsSourceService;
import com.shtyxh.manager.service.IKgTypeService;
import com.shtyxh.manager.utils.CommonFuncUtil;

@Controller
public class IndexAssessmentController extends IndexBaseController{
	public static final String  SUSPENSION_POINTS = "...";
	
    @Autowired
    private IKgAssessmentTypeService iKgAssessmentTypeService;
    @Autowired
    private IKgAssessmentActivityService iKgAssessmentActivityService;
    @Autowired
    private IKgNewsAttributeService iKgNewsAttributeService;
    @Autowired
    private IIndexAssessmentService iIndexAssessmentService;
    @Autowired
    private IKgAssessmentActivityUserUploadService iKgAssessmentActivityUserUploadService;
    @Autowired
    private IKgNewsService iKgNewsService;
    @Autowired 
    private IKgTypeService iKgTypeService;
    @Autowired
    private IKgNewsSourceService iKgNewsSourceService;
    @Autowired
    private IJedisService iJedisService;
    //======================================评估========================================
    @RequestMapping(value = "/index/assessmentTypeList")
    public ModelAndView assessmentTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) throws E404Excetion {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/assessmentTypeList");
        IRequest requestContext = createRequestContext(request);
        
       
        List<KgAssessmentType> typeList = iKgAssessmentTypeService.selectAll(requestContext);
        if(typeid==null) {
        	if(typeList.size()!=0)
        		typeid=typeList.get(0).getId();
        }
        if(typeid==null)
    		throw new E404Excetion("请查看的网页不存在!"); 
        	
        KgAssessmentActivity kaa = new KgAssessmentActivity();
        kaa.setAssessmentTypeId(typeid);
        
        KgAssessmentType kat = new KgAssessmentType();
        kat.setId(typeid);
        KgAssessmentType assessmentType = iKgAssessmentTypeService.selectByPrimaryKey(requestContext, kat);
        List<KgAssessmentActivity> activityList = iKgAssessmentActivityService.select(requestContext, kaa);
      
        int count = iKgAssessmentActivityService.adminQueryCount(requestContext, kaa);
        int allPageNum = count%limit==0?count/limit:count/limit+1;
        if(count==0) allPageNum=1;
       
        mv.addObject("typeList", typeList);
        mv.addObject("assessmentType",assessmentType);
        mv.addObject("activityList", activityList);
        mv.addObject("page", page);
        mv.addObject("allPageNum",allPageNum);
        mv.addObject("typeid", typeid);
        
        
        loadNavigation(mv,CH_PXYJD);
        loadSysConfig(mv);
        return mv;
    }

    
    @RequestMapping(value = "/index/assessmentDetail")
    public ModelAndView assessmentDetail(Long id, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) throws E404Excetion {
    	 IRequest requestContext = createRequestContext(request);
    	 KgAssessmentActivity kaa = iKgAssessmentActivityService.selectByPrimaryKey(requestContext, new KgAssessmentActivity(id));
	    if(id==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
	    if(kaa==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/assessmentDetail");
        mv.addObject("assessmentInfo", kaa);
        KgAssessmentType requestKT = new KgAssessmentType();
        requestKT.setId(kaa.getAssessmentTypeId());
        KgAssessmentType kgNewstype = iKgAssessmentTypeService.selectByPrimaryKey(requestContext, requestKT);
        
        HttpSession session = request.getSession(false);
        if(session!=null) {
        	  Long userid = (Long)session.getAttribute(IRequest.FIELD_USER_ID);
              KgAssessmentActivityUserProgress userProgress = new KgAssessmentActivityUserProgress();
              userProgress.setUploadUserId(userid);
              userProgress.setAssessmentActivityId(id);
        }
      
        mv.addObject("assessmentType", kgNewstype);
        User user = (User) request.getAttribute("user");
        mv.addObject("userid", user==null?"":user.getUserId());
        loadNavigation(mv, CH_PXYJD);
        loadAttriteAssessment(mv, requestContext,3);
        loadSysConfig(mv);
        return mv;
    }
    
    //===加载评估页面获取上传文件
    @RequestMapping(value = "/index/admin/assessmentDetail/uploadList")
    @ResponseBody
    public ExtStore uploadList(Long activityId,HttpServletRequest request,
    		@RequestParam(defaultValue = DEFAULT_PAGE) int offset,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
    	
        IRequest requestContext = createRequestContext(request);
        User user = (User) request.getAttribute("user");
        KgAssessmentActivityUserProgress userProgress = new KgAssessmentActivityUserProgress();
        userProgress.setUploadUserId(user.getUserId());
        userProgress.setAssessmentActivityId(activityId);
        int page = offset /pageSize+1;
        List<KgAssessmentActivityUserUpload> list = iKgAssessmentActivityUserUploadService.loadUserUploadList(requestContext,userProgress,page,pageSize );
        int size = iKgAssessmentActivityUserUploadService.countUserUploadList(requestContext, userProgress);
        
        return new ExtStore(page, pageSize, size, list);
    }
    

    
    //===获取活动状态是否允许上传
    @RequestMapping(value = "/index/admin/assessment/updateState")
    @ResponseBody
    public ResponseData updateState(Long  id,HttpServletRequest request){
    	ResponseData ua = new ResponseData(true);
    	try {
			IRequest requestContext = createRequestContext(request);
			 User user = (User) request.getAttribute("user");
			 iIndexAssessmentService.validateAssessmentCanUpload(requestContext, user, id);
		} catch(Exception e) {
			ua = new ResponseData(false);
			ua.setMessage(e.getMessage());
		}
    	return ua;
    }
    
    //===上传文件数据
    @RequestMapping(value = "/index/admin/assessment/upload", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public ResponseData fileupload(@RequestBody List<UploadFileInfo> list ,HttpServletRequest request){
    	ResponseData ua = null;
    	try {
			IRequest requestContext = createRequestContext(request);
			Long assessmentActivityId = Long.parseLong(request.getParameter("assessmentActivityId"));
			if(assessmentActivityId==null)
				throw new  GlobalException("参数错误");
			iIndexAssessmentService.assessmentUpload(requestContext, request, list, assessmentActivityId);
			ua = new ResponseData(true);
		} catch (Exception e) {
			ua = new ResponseData(false);
			ua.setMessage(e.getMessage());
		} 
    	return ua;
    }
    
    @RequestMapping(value = "/index/admin/assessment/delete")
    @ResponseBody
    public ResponseData fileDelete(HttpServletRequest request,@RequestBody List<KgAssessmentActivityUserUpload> dto) {
    	ResponseData rd = null;
    	try {
			IRequest requestCtx = createRequestContext(request);
			Long assessmentActivityId = Long.parseLong(request.getParameter("assessmentActivityId"));
			User user = (User) request.getAttribute("user");
			iIndexAssessmentService.validateAssessmentCanUpload(requestCtx, user, assessmentActivityId);
			iIndexAssessmentService.indexFileDelete(requestCtx,user, assessmentActivityId, dto);
			rd = new ResponseData(true);
		} catch (Exception e) {
			rd = new ResponseData(false);
			rd.setMessage(e.getMessage());
		}
    	return rd;
    }

    
    //===================================other======================================
   

 
    private void judgeTitleLength(List<KgAssessmentActivity> assessmentActivityList) {
    	for(KgAssessmentActivity kg :assessmentActivityList) {
    		String title =kg.getAssessmentActivityName();
    		if(title.length()>17) {
    			title=kg.getAssessmentActivityName().substring(0, 17)+SUSPENSION_POINTS;
    			kg.setAssessmentActivityName(title);
    		}else {
    			kg.setAssessmentActivityName(title);
    		}
    	}
    }
    
    private void loadAttriteAssessment(ModelAndView mv,IRequest requestContext,int attributeSize) {
	 	List<KgNewsAttribute> rightAttributeList =  iKgNewsAttributeService.select(requestContext, null, 1, attributeSize);
        for(KgNewsAttribute ka :rightAttributeList){
        	KgAssessmentActivity kn = new KgAssessmentActivity();
        	kn.setAttributeid(ka.getId()+"");
        	kn.setSortname("createdate");
        	kn.setSortorder("desc");
        	List<KgAssessmentActivity> assessmentActivityList=iKgAssessmentActivityService.select(requestContext, kn, 1, 5);
        	judgeTitleLength(assessmentActivityList);
        	ka.setAssessmentActivityList(assessmentActivityList);
        	
        }
        mv.addObject("rightAttributeList",rightAttributeList);
}
    
    //=====================岗位培训====================================================
    
	@RequestMapping(value = "/index/jdypx")
    @ResponseBody
    public ModelAndView news(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/trainAndAssessment");
        IRequest requestContext = createRequestContext(request);
        KgType kt = new KgType();
        kt.setParentid(PXYJD_ID);
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
        
        
      //===================================================================================================
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
    	//===================================================================================================
    	List<KgAssessmentType> typeAssessmentList = iKgAssessmentTypeService.selectAll(requestContext);
    	for(KgAssessmentType kat:typeAssessmentList) {
    		KgAssessmentActivity activitys = new KgAssessmentActivity();
    		activitys.setAssessmentTypeId(kat.getId());
         	int count = iKgAssessmentActivityService.adminQueryCount(requestContext, activitys);
         	kat.setCount(count);
         	List<KgAssessmentActivity> activityList = iKgAssessmentActivityService.selectWithOtherInfo(requestContext, activitys, 1, 6);
         	kat.setAssessmentActivityList(activityList);
         }
    	
    	 mv.addObject("typeList", typeList);
    	 mv.addObject("typeAssessmentList", typeAssessmentList);
    	
        
        loadNavigation(mv, IndexController.CH_PXYJD);
        loadSysConfig(mv);
        return mv;
    }
    
    
    @RequestMapping(value = "/index/jdypx/gwpx")
    public ModelAndView gwpx(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) throws E404Excetion {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/gwpx");
        IRequest requestContext = createRequestContext(request);
        KgType currentType = null;
        if(typeid!=null) {
        	currentType = iKgTypeService.selectByPrimaryKey(requestContext, new KgType(typeid));
        	if(currentType==null)
        		throw new E404Excetion("请查看的网页不存在!"); 
        }
        	
        KgType kt = new KgType();
        kt.setParentid(GWPX_ID);
        List<KgType> typeList = iKgTypeService.select(requestContext, kt);
        mv.addObject("typeList", typeList);
        
        if(typeid==null) {
        	if(typeList.size()!=0)
        		currentType=typeList.get(0);
        }
        
        mv.addObject("nowType",currentType);
        loadNavigation(mv, CH_PXYJD);
        loadSysConfig(mv);
        return mv;
    }
    
	@RequestMapping(value = {"/index/jdypx/gwpx/typeList"})
    @ResponseBody
    public ModelAndView typeList(Long typeid,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
		 ModelAndView mv = new ModelAndView( "/index/associationSurvey/rightDetail");
		 
		 KgType nowType = iJedisService.loadType(new KgType(typeid));
		 
		 if(typeid==YJSGZ_ID||typeid==ZLPX_ID){ //这些板块内容开发中
			 mv = new ModelAndView("/index/assessment/include/pageDesign");
			 mv.addObject("nowType",nowType);
			 return mv;
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
	
    
	 @RequestMapping(value = "/index/jdypx/typeList")
	    @ResponseBody
	    public ModelAndView newsTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
	            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_20) int limit,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/trainList");
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
	      
	        CommonFuncUtil.judgeNewsTitleLength(list,33);
	        mv.addObject("newsList", list);
	        mv.addObject("page", page);
	        mv.addObject("allPageNum",allPageNum);
	        mv.addObject("kgNewstype", kgNewstype);
	        mv.addObject("typeid", typeid);
	        
	        loadNavigation(mv,IndexController.CH_PXYJD);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,kgNewstype.getParentid(),3);
	        loadSysConfig(mv);
	        return mv;
	    }
	 
	   @RequestMapping(value = "/index/jdypx/newsDetail")
	    @ResponseBody
	    public ModelAndView newsDetail(Long id,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/trainNewsDetail");
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
	        CommonFuncUtil.judgeNewsTitleLength(linkNewsList,17);
	        
	        mv.addObject("kgNewstype", kgNewstype);
	        mv.addObject("kgNewsSource", kgNewsSource);
	        mv.addObject("linkNewsList", linkNewsList);
	        
	        
	        loadNavigation(mv, IndexController.CH_PXYJD);
	        iKgNewsAttributeService.loadAttriteNews(mv, requestContext,kgNewstype.getParentid(),3);
	        loadSysConfig(mv);
	        return mv;
	    }
   
}
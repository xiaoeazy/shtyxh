package com.shtyxh.manager.index.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;
import com.shtyxh.manager.dto.KgAssessmentActivityUserUpload;
import com.shtyxh.manager.dto.KgAssessmentType;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgNewsSource;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IIndexAssessmentService;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgAssessmentActivityUserUploadService;
import com.shtyxh.manager.service.IKgNewsService;
import com.shtyxh.manager.service.IKgTypeService;
import com.shtyxh.manager.utils.CommonFuncUtil;

@Controller
public class IndexAssessmentController extends IndexBaseController{
	public static final String  SUSPENSION_POINTS = "...";
	
    @Autowired
    private IIndexAssessmentService iIndexAssessmentService;
    @Autowired
    private IKgAssessmentActivityUserUploadService iKgAssessmentActivityUserUploadService;
    @Autowired
    private IKgNewsService iKgNewsService;
    @Autowired 
    private IKgTypeService iKgTypeService;
    @Autowired
    private IJedisService iJedisService;
    //======================================评估========================================
    @RequestMapping(value = "/index/assessmentTypeList")
    public ModelAndView assessmentTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) throws E404Excetion {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/assessmentTypeList");
        List<KgAssessmentType> typeList = iJedisService.loadAssessmentTypeAll();
        if(typeid==null) {
        	if(typeList.size()!=0)
        		typeid=typeList.get(0).getId();
        }
        if(typeid==null)
    		throw new E404Excetion("请查看的网页不存在!"); 
        
        KgAssessmentType kat = new KgAssessmentType();
        kat.setId(typeid);
        KgAssessmentType assessmentType = iJedisService.loadAssessmentType(kat);
        
        
        KgAssessmentActivity kaa = new KgAssessmentActivity();
        kaa.setAssessmentTypeId(typeid);
        
        List<KgAssessmentActivity> activityList = iJedisService.loadAssessmentActivityOfType(kaa);
        int count = activityList.size();
        int allPageNum = count%limit==0?count/limit:count/limit+1;
        if(count==0) allPageNum=1;
        activityList=CommonFuncUtil.listToPage(activityList, page, limit);
       
        mv.addObject("typeList", typeList);
        mv.addObject("assessmentType",assessmentType);
        mv.addObject("activityList", activityList);
        mv.addObject("page", page);
        mv.addObject("allPageNum",allPageNum);
        
        
        loadNavigation(mv,CH_PXYJD);
        loadSysConfig(request,mv);
        return mv;
    }

    
    @RequestMapping(value = "/index/assessmentDetail")
    public ModelAndView assessmentDetail(Long id, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) throws E404Excetion {
    	 IRequest requestContext = createRequestContext(request);
    	 User user = (User)	request.getAttribute("user");
    	 KgAssessmentActivity kaa = iJedisService.loadAssessmentActivity(new KgAssessmentActivity(id));
	    if(id==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
	    if(kaa==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/assessmentDetail");
        mv.addObject("assessmentInfo", kaa);
        KgAssessmentType requestKT = new KgAssessmentType();
        requestKT.setId(kaa.getAssessmentTypeId());
        KgAssessmentType kgNewstype = iJedisService.loadAssessmentType(requestKT);
        
//        HttpSession session = request.getSession(false);
//        if(session!=null) {
//        	  Long userid = (Long)session.getAttribute(IRequest.FIELD_USER_ID);
//              KgAssessmentActivityUserProgress userProgress = new KgAssessmentActivityUserProgress();
//              userProgress.setUploadUserId(userid);
//              userProgress.setAssessmentActivityId(id);
//        }
      
        mv.addObject("assessmentType", kgNewstype);
        mv.addObject("userid", user==null?"":user.getUserId());
        loadNavigation(mv, CH_PXYJD);
        loadAttriteAssessmentActivity(mv, requestContext,3);
        loadSysConfig(request,mv);
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
        	List<KgNews> newsList = iJedisService.loadTypeNews(news);
        	int count = newsList.size();
        	kn.setCount(count);
        	newsList= CommonFuncUtil.listToList(newsList, 6);
        	kn.setNewsList(newsList);
        }
        
        
      //===================================================================================================
        KgNews kn = new KgNews();
    	kn.setAttributeid("4");
    	
     	KgNews newsTop = iJedisService.loadJDYPXPage_AttributeId4_News(kn, typeidList, 1, 1);
    	mv.addObject("newsTop", newsTop);
    	
    	//===================================================================================================
    	List<KgAssessmentType> typeAssessmentList = iJedisService.loadAssessmentTypeAll();
    	for(KgAssessmentType kat:typeAssessmentList) {
    		KgAssessmentActivity activitys = new KgAssessmentActivity();
    		activitys.setAssessmentTypeId(kat.getId());
    		
    	 	List<KgAssessmentActivity> activityList = iJedisService.loadAssessmentActivityOfType(activitys);
         	int count =activityList.size();
         	kat.setCount(count);
         	activityList=CommonFuncUtil.listToList(activityList, 6);
         	kat.setAssessmentActivityList(activityList);
         }
    	
    	 mv.addObject("typeList", typeList);
    	 mv.addObject("typeAssessmentList", typeAssessmentList);
        loadNavigation(mv, IndexController.CH_PXYJD);
        loadSysConfig(request,mv);
        return mv;
    }
    
    
    @RequestMapping(value = "/index/jdypx/gwpx")
    public ModelAndView gwpx(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) throws E404Excetion {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/gwpx");
        KgType currentType = null;
        if(typeid!=null) {
        	currentType = iJedisService.loadType( new KgType(typeid));
        	if(currentType==null)
        		throw new E404Excetion("请查看的网页不存在!"); 
        }
        	
        KgType kt = new KgType();
        kt.setParentid(GWPX_ID);
        List<KgType> typeList = iJedisService.loadChildType(kt);
        mv.addObject("typeList", typeList);
        
        if(typeid==null) {
        	if(typeList.size()!=0)
        		currentType=typeList.get(0);
        }
        
        mv.addObject("nowType",currentType);
        loadNavigation(mv, CH_PXYJD);
        loadSysConfig(request,mv);
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
	        if(typeid==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
	        KgType kgNewstype = iJedisService.loadType(new KgType(typeid));
	        if(kgNewstype==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
	        KgNews news = new KgNews();
	        news.setTypeid(typeid);
	    	List<KgNews> list = iJedisService.loadTypeNews(news);
	        int count =list.size();
	        int allPageNum = count%limit==0?count/limit:count/limit+1;
	        if(count==0) allPageNum=1;
	        list=CommonFuncUtil.listToPage(list, page, limit);
	        CommonFuncUtil.judgeNewsTitleLength(list,33);
	        mv.addObject("newsList", list);
	        mv.addObject("page", page);
	        mv.addObject("allPageNum",allPageNum);
	        mv.addObject("kgNewstype", kgNewstype);
	        mv.addObject("typeid", typeid);
	        
	        loadNavigation(mv,IndexController.CH_PXYJD);
	        loadAttriteNews(mv, kgNewstype.getParentid(),3);
	        loadSysConfig(request,mv);
	        return mv;
	    }
	 
	   @RequestMapping(value = "/index/jdypx/newsDetail")
	    @ResponseBody
	    public ModelAndView newsDetail(Long id,HttpServletRequest request) throws E404Excetion {
	    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/trainNewsDetail");
	        IRequest requestContext = createRequestContext(request);
	        if(id==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
	        KgNews newsInfo = iJedisService.loadNews(new KgNews(id));
	        if(newsInfo==null)
	    		throw new E404Excetion("请查看的网页不存在!"); 
	        
	        mv.addObject("newsInfo", newsInfo);
	        
	        KgType newsType = new KgType();
	        newsType.setId(newsInfo.getTypeid());
	        KgType kgNewstype = iJedisService.loadType(newsType);
	        
	        KgNewsSource newsSource = new KgNewsSource();
	        newsSource.setId(newsInfo.getSourceid());
	        KgNewsSource kgNewsSource = iJedisService.loadKgNewsSource(newsSource);
	        
	        KgNews linkNews = new KgNews();
	        linkNews.setTypeid(newsInfo.getTypeid());
	        linkNews.setId(id);
	        List<KgNews> linkNewsList = iKgNewsService.selectLinkNews(requestContext,linkNews, 1);
	        CommonFuncUtil.judgeNewsTitleLength(linkNewsList,45);
	        
	        
	        mv.addObject("kgNewstype", kgNewstype);
	        mv.addObject("kgNewsSource", kgNewsSource);
	        mv.addObject("linkNewsList", linkNewsList);
	        
	        
	        loadNavigation(mv, IndexController.CH_PXYJD);
	        loadAttriteNews(mv, kgNewstype.getParentid(),3);
	        loadSysConfig(request,mv);
	        return mv;
	    }
   
}
package cn.huan.kindergarten.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.attachment.exception.StoragePathNotExsitException;
import com.huan.HTed.attachment.exception.UniqueFileMutiException;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.ResponseData;

import cn.huan.kindergarten.bean.FileInfo;
import cn.huan.kindergarten.dto.KgAssessmentActivity;
import cn.huan.kindergarten.dto.KgAssessmentActivityUserProgress;
import cn.huan.kindergarten.dto.KgAssessmentActivityUserUpload;
import cn.huan.kindergarten.dto.KgAssessmentType;
import cn.huan.kindergarten.dto.KgNewsAttribute;
import cn.huan.kindergarten.service.IIndexAssessmentService;
import cn.huan.kindergarten.service.IKgAssessmentActivityService;
import cn.huan.kindergarten.service.IKgAssessmentActivityUserProgressService;
import cn.huan.kindergarten.service.IKgAssessmentActivityUserUploadService;
import cn.huan.kindergarten.service.IKgAssessmentTypeService;
import cn.huan.kindergarten.service.IKgNewsAttributeService;
import cn.huan.shtyxh.common.bean.ExtStore;

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
    private IKgAssessmentActivityUserProgressService iKgAssessmentActivityUserProgressService;
    @Autowired
    private IKgAssessmentActivityUserUploadService iKgAssessmentActivityUserUploadService;
    //======================================评估========================================
    @RequestMapping(value = "/index/assessmentTypeList")
    public ModelAndView assessmentTypeList(Long typeid, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/assessmentTypeList");
        IRequest requestContext = createRequestContext(request);
        
       
        List<KgAssessmentType> typeList = iKgAssessmentTypeService.selectAll(requestContext);
        if(typeid==null) {
        	if(typeList.size()!=0)
        		typeid=typeList.get(0).getId();
        }
        	
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
        
        
        loadNavigation(mv, requestContext,CH_XHGZ);
        loadSysConfig(mv);
        return mv;
    }

    
    @RequestMapping(value = "/index/assessmentDetail")
    public ModelAndView assessmentDetail(Long id, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/assessment/assessmentDetail");
        IRequest requestContext = createRequestContext(request);
        KgAssessmentActivity k = new KgAssessmentActivity();
        k.setId(id);
        KgAssessmentActivity kaa = iKgAssessmentActivityService.selectByPrimaryKey(requestContext, k);
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
        loadNavigation(mv, requestContext,CH_XHGZ);
        loadAttriteAssessment(mv, requestContext,3);
        loadSysConfig(mv);
        return mv;
    }
    
    //===评估页面获取上传文件
    @RequestMapping(value = "/index/admin/assessmentDetail/uploadList")
    @ResponseBody
    public ExtStore uploadList(Long activityId,HttpServletRequest request,
    		@RequestParam(defaultValue = DEFAULT_PAGE) int offset,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
    	
        IRequest requestContext = createRequestContext(request);
        HttpSession session = request.getSession(false);
        Long userid = (Long)session.getAttribute(IRequest.FIELD_USER_ID);
        KgAssessmentActivityUserProgress userProgress = new KgAssessmentActivityUserProgress();
        userProgress.setUploadUserId(userid);
        userProgress.setAssessmentActivityId(activityId);
        int page = offset /pageSize+1;
        List<KgAssessmentActivityUserUpload> list = iKgAssessmentActivityUserUploadService.loadUserUploadList(requestContext,userProgress,page,pageSize );
        int size = iKgAssessmentActivityUserUploadService.countUserUploadList(requestContext, userProgress);
        
        return new ExtStore(page, pageSize, size, list);
    }
    
    //===个人 参与评估页面获取参与活动
    @RequestMapping(value = "/index/admin/user/joinAssessment")
    @ResponseBody
    public ExtStore joinAssessment(HttpServletRequest request,
    		@RequestParam(defaultValue = DEFAULT_PAGE) int offset,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
    	
        IRequest requestContext = createRequestContext(request);
        HttpSession session = request.getSession(false);
        Long userid = (Long)session.getAttribute(IRequest.FIELD_USER_ID);
        KgAssessmentActivityUserProgress userProgress = new KgAssessmentActivityUserProgress();
        userProgress.setUploadUserId(userid);
        int page = offset /pageSize+1;
        List<KgAssessmentActivityUserProgress> list = iKgAssessmentActivityUserProgressService.selectWithOtherInfo(requestContext, userProgress, page, pageSize);
        int size = iKgAssessmentActivityUserProgressService.adminQueryCount(requestContext, userProgress);
        return new ExtStore(page, pageSize, size, list);
    }
    
    
    @RequestMapping(value = "/index/admin/assessment/updateState")
    @ResponseBody
    public ResponseData updateState(Long  id,HttpServletRequest request){
    	ResponseData ua = new ResponseData(true);
    	try {
			IRequest requestContext = createRequestContext(request);
			KgAssessmentActivity ka = new KgAssessmentActivity();
			ka.setId(id);
			KgAssessmentActivity kaa = iKgAssessmentActivityService.selectByPrimaryKey(requestContext, ka);
			Boolean flag = kaa.getFinished();
			if(flag ==false) {
				ua = new ResponseData(false);
				ua.setMessage("该活动已经结束");
			}else {
				 HttpSession session = request.getSession(false);
			     Long userid = (Long)session.getAttribute(IRequest.FIELD_USER_ID);
			     KgAssessmentActivityUserProgress kup = new KgAssessmentActivityUserProgress();
			     kup.setAssessmentActivityId(id);
			     kup.setUploadUserId(userid);
			     List<KgAssessmentActivityUserProgress> kupDataList = iKgAssessmentActivityUserProgressService.select(requestContext, kup);
			     if(kupDataList.size()==1) {
			    	 Integer state = kupDataList.get(0).getState();
			    	 if(state==10||state==30||state==60) {
			    		 
			    	 }else {
			    		 ua = new ResponseData(false);
						 ua.setMessage("后台人员审批中，不能修改！");
			    	 }
			     }
			}
		} catch(Exception e) {
			ua = new ResponseData(false);
			ua.setMessage(e.getMessage());
		}
    	return ua;
    }
    
    
    @RequestMapping(value = "/index/admin/assessment/upload", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    public ResponseData fileupload(HttpServletRequest request){
    	ResponseData ua = null;
    	try {
			IRequest requestContext = createRequestContext(request);
			Long assessmentActivityId = Long.parseLong(request.getParameter("assessmentActivityId"));
			if(assessmentActivityId==null)
				throw new  FileUploadException("参数错误");
			
			List<FileInfo> list = iIndexAssessmentService.assessmentUpload(requestContext, request, assessmentActivityId);
			ua = new ResponseData(true);
//        return "<script>window.parent.showUploadSucessLogo()</script>";
		} catch (NumberFormatException e) {
			ua = new ResponseData(false);
			ua.setMessage(e.getMessage());
		} catch (StoragePathNotExsitException e) {
			ua = new ResponseData(false);
			ua.setMessage(e.getMessage());
		} catch (UniqueFileMutiException e) {
			ua = new ResponseData(false);
			ua.setMessage(e.getMessage());
		} catch (IOException e) {
			ua = new ResponseData(false);
			ua.setMessage(e.getMessage());
		} catch (FileUploadException e) {
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
			String webPath = request.getServletContext().getRealPath("/");
			iIndexAssessmentService.indexFileDelete(requestCtx, webPath, dto);
			rd = new ResponseData(true);
		} catch (Exception e) {
			rd = new ResponseData(false);
			rd.setMessage(e.getMessage());
		}
    	return rd;
    }
    
    @RequestMapping(value = "/index/admin/user/join/assessment/delete")
    @ResponseBody
    public ResponseData userJoinAssessmentDelete(HttpServletRequest request,@RequestBody List<KgAssessmentActivityUserProgress> dto) {
    	ResponseData rd = null;
    	try {
			IRequest requestCtx = createRequestContext(request);
			String webPath = request.getServletContext().getRealPath("/");
			iIndexAssessmentService.userJoinAssessmentDelete(requestCtx, webPath, dto);
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
   
}
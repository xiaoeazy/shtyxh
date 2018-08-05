package cn.huan.kindergarten.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.account.dto.User;
import com.huan.HTed.account.service.IUserService;
import com.huan.HTed.core.IRequest;

import cn.huan.kindergarten.dto.KgAssessmentActivity;
import cn.huan.kindergarten.exception.KgFileException;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

@Controller
public class IndexUserController extends IndexBaseController{
	@Autowired
	private IUserService iUserService;
	
	
	@RequestMapping(value = "/index/admin/user")
    @ResponseBody
    public ModelAndView user(HttpServletRequest request) throws KgFileException {
		 IRequest requestContext = createRequestContext(request);
		 
		 HttpSession session = request.getSession(false);
    	 User s = new User();
    	 s.setUserId((Long)session.getAttribute(IRequest.FIELD_USER_ID));
    	 User user = iUserService.selectByPrimaryKey(requestContext, s);
    	 ModelAndView mv = new ModelAndView(getViewPath() + "/index/user/user");
    	 loadNavigation(mv, requestContext, CH_NULL);
    	 
    	 mv.addObject("user",user);
    	 loadSysConfig(mv);
         return mv;
    }
	
	   @RequestMapping(value = "/index/admin/user/submit")
	    @ResponseBody
		public ExtAjax userUpdate(@RequestBody User user,  BindingResult result, HttpServletRequest request) throws KgFileException{
	    	 IRequest requestCtx = createRequestContext(request);
	    	 user.setUserId(requestCtx.getUserId());
	    	 user.set__status("update");
	    
			 if(!StringUtils.isEmpty(user.getPasswordEncrypted())) {
				 String passwordEncrypted = DigestUtils.md5Hex(user.getPasswordEncrypted());
				 user.setPasswordEncrypted(passwordEncrypted);
			 }else {
				 user.setPasswordEncrypted(null); 
			 }
			 iUserService.adminUpdate(requestCtx, user, null);
	         return new ExtAjax(true, null);
	    }
	   
	   @RequestMapping(value = "/index/admin/userAssessment")
	    @ResponseBody
	    public ModelAndView userAssessment(HttpServletRequest request) throws KgFileException {
	    	 ModelAndView mv = new ModelAndView(getViewPath() + "/index/user/userAssessment");
	    	 IRequest requestContext = createRequestContext(request);
	    	 loadNavigation(mv, requestContext, CH_NULL);
	    	 loadSysConfig(mv);
	         return mv;
	    }
	   
	   @RequestMapping(value = "/index/admin/userAssessment/userAssessmentInfo")
	    @ResponseBody
	    public ExtAjax  userAssessmentInfo(HttpServletRequest request) throws KgFileException {
//			 HttpSession session = request.getSession(false);
//	         if(session==null) {
//	        	 throw new KgFileException(null, "请先登陆！", null);
//	         }
//	    	 IRequest requestContext = createRequestContext(request);
//	    	 
//	    	 
//	         return mv;
		   return null;
	    }
	   
		@RequestMapping(value = "/index/logout")
		@ResponseBody
		public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response) {

			HttpSession session = request.getSession(false);
			if(session!=null)
				session.invalidate();
			ModelAndView view = new ModelAndView(REDIRECT + VIEW_LOGIN);
			return view;
		}
	   
	    @RequestMapping(value = "/index/admin/userAssessment/assessmentInfo")
	    @ResponseBody
	    public ExtStore assessmentInfo(KgAssessmentActivity dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,int start,
	        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
//	    	 HttpSession session = request.getSession(false);
//	         if(session==null) {
//	        	 throw new KgFileException(null, "请先登陆！", null);
//	         }
//	    	 IRequest requestContext = createRequestContext(request);
//	    	 
//	    	 List<KgAssessmentActivity> list = service.selectWithOtherInfo(requestContext,dto,page,limit);
//	    	 int count = service.adminQueryCount(requestContext, dto);
//	    	 return new ExtStore(start, limit, count, list);
	    	return null;
	    }


}
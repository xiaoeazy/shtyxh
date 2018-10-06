package com.shtyxh.manager.index.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.ResponseData;
import com.shtyxh.common.bean.ExtAjax;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.common.exception.GlobalException;
import com.shtyxh.common.utils.CookieUtils;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.account.service.ISSOService;
import com.shtyxh.manager.account.service.IUserService;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;
import com.shtyxh.manager.service.IIndexAssessmentService;
import com.shtyxh.manager.service.IKgAssessmentActivityUserProgressService;

@Controller
public class IndexUserController extends IndexBaseController{
	
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IKgAssessmentActivityUserProgressService iKgAssessmentActivityUserProgressService;
	@Autowired
	private IIndexAssessmentService iIndexAssessmentService;

	@Autowired
	private ISSOService iSSOService;
	
	@RequestMapping(value = "/index/admin/user")
    @ResponseBody
    public ModelAndView user(HttpServletRequest request) {
    	 User user = (User)	request.getAttribute("user");
    	 ModelAndView mv = new ModelAndView("/index/user/user");
    	 loadNavigation(mv, CH_NULL);
    	 mv.addObject("userInfo",user);
    	 mv.addObject("typeid",1);
    	 loadSysConfig(mv);
         return mv;
    }
	
	
	@RequestMapping(value = "/index/admin/user/typeList")
    @ResponseBody
    public ModelAndView typeList(HttpServletRequest request,Long typeid) {
    	 User user = (User)	request.getAttribute("user");
    	 ModelAndView mv = null;
    	 if(typeid==1)
    		 mv=new ModelAndView("/index/user/userInfo");
    	 if(typeid==2)
    		 mv = new ModelAndView( "/index/user/userAssessmentInfo");
    	 if(mv==null)
    		 throw new GlobalException("查看的页面不存在");
    	 mv.addObject("userInfo",user);
         return mv;
    }
	
	   @RequestMapping(value = "/index/admin/user/submit")
	    @ResponseBody
		public ExtAjax userUpdate(@RequestBody User user,  BindingResult result, HttpServletRequest request){
		   	 try {
		   		 IRequest requestCtx = createRequestContext(request);
		   		 User userInfo = (User)	request.getAttribute("user");
		   		 user.setUserId(userInfo.getUserId());
		    	 user.set__status("update");
		 	    
	 			 if(!StringUtils.isEmpty(user.getPasswordEncrypted())) {
	 				 String passwordEncrypted = DigestUtils.md5Hex(user.getPasswordEncrypted());
	 				 user.setPasswordEncrypted(passwordEncrypted);
	 			 }else {
	 				 user.setPasswordEncrypted(null); 
	 			 }
	 			 iUserService.adminUpdate(requestCtx, user, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				  return new ExtAjax(false, e.getMessage());
			}
	         return new ExtAjax(true, null);
	    }
	   
		// ===个人 参与评估页面获取参与活动
		@RequestMapping(value = "/index/admin/user/joinAssessment")
		@ResponseBody
		public ExtStore joinAssessment(HttpServletRequest request, @RequestParam(defaultValue = DEFAULT_PAGE) int offset,
				@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
			IRequest requestCtx = createRequestContext(request);
			User user = (User) request.getAttribute("user");
			Long userid = -1l;
			if (user != null)
				userid = user.getUserId();
			KgAssessmentActivityUserProgress userProgress = new KgAssessmentActivityUserProgress();
			userProgress.setUploadUserId(userid);
			int page = offset / pageSize + 1;
			List<KgAssessmentActivityUserProgress> list = iKgAssessmentActivityUserProgressService
					.selectWithOtherInfo(requestCtx,userProgress, page, pageSize);
			int size = iKgAssessmentActivityUserProgressService.adminQueryCount(requestCtx,userProgress);
			return new ExtStore(page, pageSize, size, list);
		}
		
	    
	    @RequestMapping(value = "/index/admin/user/join/assessment/delete")
	    @ResponseBody
	    public ResponseData userJoinAssessmentDelete(HttpServletRequest request,@RequestBody List<KgAssessmentActivityUserProgress> dto) {
	    	ResponseData rd = null;
	    	try {
				IRequest requestCtx = createRequestContext(request);
				iIndexAssessmentService.userJoinAssessmentDelete(requestCtx, dto);
				rd = new ResponseData(true);
			} catch (Exception e) {
				rd = new ResponseData(false);
				rd.setMessage(e.getMessage());
			}
	    	return rd;
	    }
	   


	   //==========================登录===================================
		@RequestMapping(value = "/index/logout")
		@ResponseBody
		public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView view = new ModelAndView(REDIRECT + VIEW_MAIN);
			String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
			iSSOService.logout(token, request, response);
			return view;
		}
		
		
		@RequestMapping("/index/loginPage")
		public ModelAndView showLogin(HttpServletRequest request,String redirect, Model model) {
			 ModelAndView view = new ModelAndView("/index/loginPage/login");
			 User loginUser = (User) request.getAttribute("user");
			 if(loginUser!=null)
				 view = new ModelAndView(REDIRECT + VIEW_MAIN);
			 model.addAttribute("redirect", redirect);
			return view;
		}

		@RequestMapping(value = "/index/login")
		@ResponseBody
		public ExtAjax login(String username, String password,HttpServletRequest request, HttpServletResponse response) {
			try {
				User loginUser = (User) request.getAttribute("user");
				if(loginUser==null){
					String token = iSSOService.userLogin(username, password, request, response);
				}
//				setRoleInfo
			} catch (Exception e) {
				e.printStackTrace();
				return new ExtAjax(false, e.getMessage());
			}
			return new ExtAjax(true, "登录成功");
		}
		
		
		

//		private void setRoleInfo(HttpServletRequest request, HttpSession session, User user) throws Exception {
//			UserRole ur = new UserRole();
//			ur.setUserId(user.getUserId());
//			List<Role> roles = roleService.adminQueryHave(RequestHelper.createServiceRequest(request), ur);
//			if (roles.isEmpty()) {
//				request.setAttribute("code", "NO_ROLE");
//				throw new Exception("该用户不存在角色");
//			}
//			List<Long> roleIds = new ArrayList<Long>();
//			for (Role role : roles) {
//				roleIds.add(role.getRoleId());
//			}
//			Long[] ids = roleIds.toArray(new Long[roleIds.size()]);
	//
//			session.setAttribute(IRequest.FIELD_ALL_ROLE_ID, ids);
//			session.setAttribute(IRequest.FIELD_ROLE_ID, roles.get(0).getRoleId());
//		}

	
	
//	@Autowired
//	private IUserService iUserService;
//	
//	
//	@RequestMapping(value = "/index/admin/user")
//    @ResponseBody
//    public ModelAndView user(HttpServletRequest request) throws KgFileException {
//		 IRequest requestContext = createRequestContext(request);
//		 
//		 HttpSession session = request.getSession(false);
//    	 User s = new User();
//    	 s.setUserId((Long)session.getAttribute(IRequest.FIELD_USER_ID));
//    	 User user = iUserService.selectByPrimaryKey(requestContext, s);
//    	 ModelAndView mv = new ModelAndView(getViewPath() + "/index/user/user");
//    	 loadNavigation(mv,  CH_NULL);
//    	 
//    	 mv.addObject("user",user);
//    	 loadSysConfig(mv);
//         return mv;
//    }
//	
//	   @RequestMapping(value = "/index/admin/user/submit")
//	    @ResponseBody
//		public ExtAjax userUpdate(@RequestBody User user,  BindingResult result, HttpServletRequest request) throws KgFileException{
//	    	 IRequest requestCtx = createRequestContext(request);
//	    	 user.setUserId(requestCtx.getUserId());
//	    	 user.set__status("update");
//	    
//			 if(!StringUtils.isEmpty(user.getPasswordEncrypted())) {
//				 String passwordEncrypted = DigestUtils.md5Hex(user.getPasswordEncrypted());
//				 user.setPasswordEncrypted(passwordEncrypted);
//			 }else {
//				 user.setPasswordEncrypted(null); 
//			 }
//			 iUserService.adminUpdate(requestCtx, user, null);
//	         return new ExtAjax(true, null);
//	    }
//	   
//	   @RequestMapping(value = "/index/admin/userAssessment")
//	    @ResponseBody
//	    public ModelAndView userAssessment(HttpServletRequest request) throws KgFileException {
//	    	 ModelAndView mv = new ModelAndView(getViewPath() + "/index/user/userAssessment");
//	    	 IRequest requestContext = createRequestContext(request);
//	    	 loadNavigation(mv, CH_NULL);
//	    	 loadSysConfig(mv);
//	         return mv;
//	    }
//	   
//	   @RequestMapping(value = "/index/admin/userAssessment/userAssessmentInfo")
//	    @ResponseBody
//	    public ExtAjax  userAssessmentInfo(HttpServletRequest request) throws KgFileException {
////			 HttpSession session = request.getSession(false);
////	         if(session==null) {
////	        	 throw new KgFileException(null, "请先登陆！", null);
////	         }
////	    	 IRequest requestContext = createRequestContext(request);
////	    	 
////	    	 
////	         return mv;
//		   return null;
//	    }
//	   
//		@RequestMapping(value = "/index/logout")
//		@ResponseBody
//		public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response) {
//
//			HttpSession session = request.getSession(false);
//			if(session!=null)
//				session.invalidate();
//			ModelAndView view = new ModelAndView(REDIRECT + VIEW_LOGIN);
//			return view;
//		}
//	   
//	    @RequestMapping(value = "/index/admin/userAssessment/assessmentInfo")
//	    @ResponseBody
//	    public ExtStore assessmentInfo(KgAssessmentActivity dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,int start,
//	        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
////	    	 HttpSession session = request.getSession(false);
////	         if(session==null) {
////	        	 throw new KgFileException(null, "请先登陆！", null);
////	         }
////	    	 IRequest requestContext = createRequestContext(request);
////	    	 
////	    	 List<KgAssessmentActivity> list = service.selectWithOtherInfo(requestContext,dto,page,limit);
////	    	 int count = service.adminQueryCount(requestContext, dto);
////	    	 return new ExtStore(start, limit, count, list);
//	    	return null;
//	    }


}
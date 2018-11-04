package com.shtyxh.manager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.impl.RequestHelper;
import com.huan.HTed.system.controllers.BaseController;
import com.shtyxh.common.bean.CommonPath;
import com.shtyxh.common.bean.ExtAjax;
import com.shtyxh.common.utils.CookieUtils;
import com.shtyxh.manager.account.dto.Role;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.account.dto.UserRole;
import com.shtyxh.manager.account.service.IRoleService;
import com.shtyxh.manager.account.service.ISSOService;
import com.shtyxh.manager.account.service.IUserService;
import com.shtyxh.manager.dto.Func;
import com.shtyxh.manager.service.IFuncService;

@Controller
public class AdminController extends BaseController {
	
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IFuncService funcService;
	
	@Value("${FILE_FILESERVER}")
	private String FILE_FILESERVER;
	
	@Autowired
	private ISSOService iSSOService;

//	@RequestMapping(value = { "/admin/login","/admin"})
//	@ResponseBody
//	public ModelAndView login(User dto, HttpServletRequest request, HttpServletResponse response) {
//
	
//		ModelAndView view=null;
//		try {
//			HttpSession session = request.getSession(false);
//			// 有session 则不再登录
//			if (session != null && session.getAttribute(User.FIELD_USER_ID) != null) {
//				ModelAndView mv = indexView(request, response);
//				return mv;
//			}
//			
//			view = new ModelAndView(getViewPath() + VIEW_MAIN);
//			view.addObject("fileserver",FILE_FILESERVER);
//			IRequest requestContext = createRequestContext(request);
//			if(dto.getPasswordEncrypted()!=null)
//				dto.setPasswordEncrypted( DigestUtils.md5Hex(dto.getPasswordEncrypted()));
//			User user = userService.selectOne(requestContext, dto);
//			if (user == null) {
//				view.setViewName(VIEW_LOGIN);
//				if(dto.getUserName()!=null) {
//					throw new Exception( "用户名或密码错误！");
//				}
//			} else {
//				session = request.getSession();
//				setRoleInfo(request, session, user);
//				session.setAttribute(IRequest.FIELD_USER_ID, user.getUserId());
//				session.setAttribute("userRealName", user.getRealName());
//			}
//		} catch (Exception e) {
//			view.setViewName(VIEW_LOGIN);
//			view.addObject("message", e.getMessage());
//		}
//		return view;
//	}
	
	
	@RequestMapping(value = {"/admin/loginPage","/admin"})
	public ModelAndView showLogin(HttpServletRequest request,String redirect, Model model) {
		 ModelAndView view = new ModelAndView("/admin/login");
		 User loginUser = (User) request.getAttribute("user");
		 if(loginUser!=null)
			 view = new ModelAndView(REDIRECT + CommonPath.ADMIN_VIEW_MAIN);
		 model.addAttribute("redirect", "");
		 view.addObject("fileserver",FILE_FILESERVER);
		return view;
	}
	
	@RequestMapping(value = "/admin/login")
	@ResponseBody
	public ExtAjax login(String username, String password,HttpServletRequest request, HttpServletResponse response) {
		try {
			User loginUser = (User) request.getAttribute("user");
			if(loginUser==null){
				String token = iSSOService.userLogin(username, password, request, response);
				System.out.println(token);
			}
//			setRoleInfo(request, session, loginUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtAjax(false, e.getMessage());
		}
		return new ExtAjax(true, "登录成功");
	}
	
//	private ModelAndView indexView(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession(false);
//		if (session != null) {
//			// 获取user
//			Long userId = (Long) session.getAttribute(User.FIELD_USER_ID);
//			if (userId != null) {
//					
//			} else {
//				return new ModelAndView(REDIRECT + VIEW_LOGIN);
//			}
//		}
//
////		String sysTitle = sysConfigManager.getSysTitle();
//		ModelAndView mav = new ModelAndView(REDIRECT + VIEW_MAIN);
////		mav.addObject("SYS_TITLE", sysTitle);
//		return mav;
//	}
	
	@RequestMapping(value = "/admin/main")
	@ResponseBody
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView(getViewPath() + CommonPath.ADMIN_VIEW_MAIN);
		view.addObject("fileserver",FILE_FILESERVER);
		User loginUser = (User) request.getAttribute("user");
		view.addObject("theLoginUserId",loginUser.getUserId());
		view.addObject("userRealName",loginUser.getUserName());
		
		UserRole ur = new UserRole();
		ur.setUserId(loginUser.getUserId());
		List<Role> roles = roleService.adminQueryHave(RequestHelper.createServiceRequest(request), ur);
		String isAdmin = "0";
		for(Role role :roles) {
			if(("admin").equals(role.getRoleCode())) {
				isAdmin ="1";
				break;
			}
		}
		view.addObject("isAdmin",isAdmin);
		return view;
	}

	private void setRoleInfo(HttpServletRequest request, HttpSession session, User user)  throws Exception {
		UserRole ur = new UserRole();
		ur.setUserId(user.getUserId());
		List<Role> roles = roleService.adminQueryHave(RequestHelper.createServiceRequest(request), ur);
		if (roles.isEmpty()) {
			request.setAttribute("code", "NO_ROLE");
			throw new Exception("该用户不存在角色");
		}
		List<Long> roleIds = new ArrayList<Long>();
		for (Role role : roles) {
			roleIds.add(role.getRoleId());
		}
		Long[] ids = roleIds.toArray(new Long[roleIds.size()]);

		session.setAttribute(IRequest.FIELD_ALL_ROLE_ID, ids);
		session.setAttribute(IRequest.FIELD_ROLE_ID, roles.get(0).getRoleId());
	}
	
	@RequestMapping(value = "/admin/logout")
	@ResponseBody
	public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView(REDIRECT + CommonPath.ADMIN_VIEW_MAIN);
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		iSSOService.logout(token, request, response);
		return view;
	}
	
	
    @RequestMapping(value = "/admin/updateCurrentUser")
    @ResponseBody
	public ExtAjax updateCurrentUser(User dto ,  BindingResult result, HttpServletRequest request){
    	 IRequest requestCtx = createRequestContext(request);
    	 String message = "";
		 if(dto.getPasswordEncrypted()!=null) {
			 String passwordEncrypted = DigestUtils.md5Hex(dto.getPasswordEncrypted());
			 dto.setPasswordEncrypted(passwordEncrypted);
			 User user = userService.selectOne(requestCtx, dto);
			 if(user!=null) {
				 String newPasswordEncrypted = DigestUtils.md5Hex(dto.getNewPasswordEncrypted());
				 dto.setPasswordEncrypted(newPasswordEncrypted);
				 userService.updateByPrimaryKey(requestCtx, user);
			 }else {
				 message = "原密码不正确";
			 }
		 }
         return new ExtAjax(false, message);
    }
    
    
    @RequestMapping(value = "/admin/getUserFunc", produces = "application/javascript;charset=utf8")
    @ResponseBody
    public String getUserFunc( HttpServletRequest request) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        IRequest iRequest = createRequestContext(request);
//		UserRole ur = new UserRole();
//		ur.setUserId(iRequest.getUserId());
    	
    	User loginUser = (User) request.getAttribute("user");
		UserRole ur = new UserRole();
		ur.setUserId(loginUser.getUserId());
        List<Func> list = funcService.adminUserFuncQueryHave(iRequest, ur);
        toJson(sb, "userFunc", list);
        return sb.toString();
    }

    
    /**
     * 基础数据转json格式字符串
     * @param sb
     * @param var
     * @param data
     * @throws JsonProcessingException
     */
    private void toJson(StringBuilder sb, String var, Object data) throws JsonProcessingException {
    	ObjectMapper objectMapper = new ObjectMapper();
        boolean hasVar = var != null && var.length() > 0;
        if (hasVar) {
            sb.append("var ").append(var).append('=');
        }
        sb.append(objectMapper.writeValueAsString(data));
        if (hasVar) {
            sb.append(';');
        }
    }
	


}
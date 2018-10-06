package com.shtyxh.manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shtyxh.common.utils.CookieUtils;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.account.service.ISSOService;

public class UserInfoInterceptor implements HandlerInterceptor {
	
	@Autowired
	private ISSOService iSSOService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url=request.getRequestURL().toString();    
	 	System.out.println("UserInfoInterceptor:"+url);
		//在Handler执行之前处理
		//判断用户是否登录
		//从cookie中取token
		System.out.println(request.getRequestURI());
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		if(token==null){
			request.setAttribute("user", null);
			return true;
		}
		try {
			//根据token换取用户信息，调用sso系统的接口。
			User user = iSSOService.getUserByToken(token,"index");
			//取不到用户信息
			//取到用户信息，放行
			//把用户信息放入Request
			request.setAttribute("user", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("user", null);
		}
		//返回值决定handler是否执行。true：执行，false：不执行。
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// handler执行之后，返回ModelAndView之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 返回ModelAndView之后。
		//响应用户之后。

	}

}

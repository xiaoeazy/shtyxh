package com.shtyxh.manager.interceptor;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huan.HTed.core.IRequest;
import com.shtyxh.common.bean.CommonPath;
import com.shtyxh.common.utils.CookieUtils;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.account.service.ISSOService;
import com.shtyxh.manager.controllers.AdminController;
import com.shtyxh.manager.index.controllers.IndexBaseController;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private ISSOService iSSOService;
	 @Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
		 	String url=request.getRequestURL().toString();    
		 	System.out.println("SessionInterceptor:"+url);
//		 	HttpSession session = request.getSession();
//		    if (session.getAttribute(IRequest.FIELD_USER_ID) != null) {
//		        return true;
//		    }
		 	try {
				String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
				User user = iSSOService.getUserByToken(token,"admin");
				if (user!=null) {
					return true;
				}
				jumpFunc(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jumpFunc(request, response);
			}
		    return false;
	    }
	private void jumpFunc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 如果是ajax请求，请求头会有x-requested-with
		String requestWith = request.getHeader("x-requested-with");
		if (requestWith != null && requestWith.equalsIgnoreCase("XMLHttpRequest")){
		    ServletOutputStream out = response.getOutputStream();
		    response.addHeader("sessionstatus", "timeout");   
		    out.close();
		} else {
			String contextName =  request.getContextPath();
			response.sendRedirect(contextName+CommonPath.ADMIN_VIEW_LOGIN);
		}
	}
}

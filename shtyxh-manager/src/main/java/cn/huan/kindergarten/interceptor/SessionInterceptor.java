package cn.huan.kindergarten.interceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huan.HTed.core.IRequest;

import cn.huan.kindergarten.controllers.AdminController;
import cn.huan.kindergarten.controllers.IndexBaseController;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	 @Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
		 	String url=request.getRequestURL().toString();    
		 	System.out.println(url);
		 	HttpSession session = request.getSession();
		    if (session.getAttribute(IRequest.FIELD_USER_ID) != null) {
		        return true;
		    }
		    // 如果是ajax请求，请求头会有x-requested-with
		    String requestWith = request.getHeader("x-requested-with");
		    if (requestWith != null && requestWith.equalsIgnoreCase("XMLHttpRequest")){
		        ServletOutputStream out = response.getOutputStream();
		        response.addHeader("sessionstatus", "timeout");   
		        out.close();
		    } else {
		    	String contextName =  request.getContextPath();
		    	if(url.contains("/index/admin")) {
		    		response.sendRedirect(contextName+IndexBaseController.VIEW_LOGIN);
		    	}else {
		    		 response.sendRedirect(contextName+AdminController.VIEW_LOGIN);
		    	}
		    }
		    return false;
	    }
}

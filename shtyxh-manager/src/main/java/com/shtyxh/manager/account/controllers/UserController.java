package com.shtyxh.manager.account.controllers;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.system.dto.ResponseData;
import com.shtyxh.common.bean.ExtAjax;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.manager.account.bean.UserAndRoleContext;
import com.shtyxh.manager.account.dto.Role;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.account.dto.UserRole;
import com.shtyxh.manager.account.service.IUserService;

    @Controller
    public class UserController extends BaseController{

    @Autowired
    private IUserService service;


    @RequestMapping(value = "/sys/user/query")
    @ResponseBody
    public ResponseData query(User dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sys/user/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<User> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sys/user/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<User> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    
 // ========================================后台===================================
    @RequestMapping(value = "/admin/user/query")
    @ResponseBody
    public ExtStore adminQuery(User dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<User> list = service.select(requestContext,dto,page,limit);
         int count  =  service.adminQueryCount(requestContext, null);
         Iterator<User> it = list.iterator();
         while(it.hasNext()){
        	  User user = it.next();
        	   if(("admin").equals(user.getUserName())) {
        		   it.remove();
         	  }
        	}
         return new ExtStore(start, limit, count-1, list);
    }
    
    @RequestMapping(value = "/admin/user/queryAll")
    @ResponseBody
    public ExtStore adminqueryAll(User dto, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<User> list = service.select(requestContext,dto);
         Iterator<User> it = list.iterator();
         while(it.hasNext()){
        	  User user = it.next();
        	   if(("admin").equals(user.getUserName())) {
        		   it.remove();
         	  }
        	}
         return new ExtStore(null, null, list.size(), list);
    }
    
    @RequestMapping(value = "/admin/user/queryAllWithRole")
    @ResponseBody
    public ExtStore adminqueryWithRole(Role dto, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<User> list = service.selectWithRole(requestContext,dto);
         Iterator<User> it = list.iterator();
         while(it.hasNext()){
        	  User user = it.next();
        	   if(("admin").equals(user.getUserName())) {
        		   it.remove();
         	  }
        	}
         return new ExtStore(null, null, list.size(), list);
    }
    

//    @RequestMapping(value = "/admin/user/submit")
//    @ResponseBody
//	public ExtAjax adminUpdate(@RequestBody List<User> dto, BindingResult result, HttpServletRequest request){
//    	 IRequest requestCtx = createRequestContext(request);
//    	 for(User u:dto) {
//    		 if(u.getPasswordEncrypted()!=null) {
//    			 String passwordEncrypted = DigestUtils.md5Hex(u.getPasswordEncrypted());
//    			 u.setPasswordEncrypted(passwordEncrypted);
//    		 }
//    		
//    	 }
//         List<User> list = service.batchUpdate(requestCtx, dto);
//         return new ExtAjax(true, null);
//    }
    
    @RequestMapping(value = "/admin/user/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody UserAndRoleContext userAndRoleContext,  BindingResult result, HttpServletRequest request){
    	 IRequest requestCtx = createRequestContext(request);
    	 User user = userAndRoleContext.getUser();
    	 List<UserRole>  userRoleList = userAndRoleContext.getUserRoleList();
		 if(!StringUtils.isEmpty(user.getPasswordEncrypted())) {
			 String passwordEncrypted = DigestUtils.md5Hex(user.getPasswordEncrypted());
			 user.setPasswordEncrypted(passwordEncrypted);
		 }else {
			 user.setPasswordEncrypted(null); 
		 }
//         List<User> list = service.batchUpdate(requestCtx, dto);
		 service.adminUpdate(requestCtx, user, userRoleList);
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/user/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<User> list){
    	 IRequest requestCtx = createRequestContext(request);
    	 service.adminDelete(requestCtx, list);
         return new ExtAjax(true, null);
    }
    
    
    }
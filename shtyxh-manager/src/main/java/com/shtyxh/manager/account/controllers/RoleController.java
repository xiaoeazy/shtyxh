package com.shtyxh.manager.account.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.shtyxh.manager.account.dto.UserRole;
import com.shtyxh.manager.account.service.IRoleService;
import com.shtyxh.manager.dto.RoleFunc;

    @Controller
    public class RoleController extends BaseController{

    @Autowired
    private IRoleService service;


    @RequestMapping(value = "/sys/role/query")
    @ResponseBody
    public ResponseData query(Role dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sys/role/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<Role> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sys/role/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Role> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    
    // ========================================后台===================================
    
    @RequestMapping(value = "/admin/role/queryNotHave")
    @ResponseBody
    public List<Role> adminqueryNotHave(Long userId,HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 List<Role> list =  new ArrayList<Role>();
    	 if(userId!=null) {
    		 UserRole ur = new UserRole();
    		 ur.setUserId(userId);
    		 list=service.adminqueryNotHave(requestContext, ur);
    	 }else
    		 list=service.selectAll(requestContext);
         return list;
    }
    
    @RequestMapping(value = "/admin/role/queryHave")
    @ResponseBody
    public List<Role> adminQueryHave(Long userId,HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 List<Role> list =  new ArrayList<Role>();
    	 if(userId!=null) {
    		 UserRole ur = new UserRole();
    		 ur.setUserId(userId);
    		 list=service.adminQueryHave(requestContext, ur);
    	 }
    		 
         return list;
    }
    
    
    @RequestMapping(value = "/admin/role/query")
    @ResponseBody
    public ExtStore adminQuery(Role dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<Role> list = service.select(requestContext,dto,page,limit);
         int count = service.adminQueryCount(requestContext, null);
         return new ExtStore(start, limit, count, list);
    }

    @RequestMapping(value = "/admin/role/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody UserAndRoleContext userAndRoleContext, BindingResult result, HttpServletRequest request){
    	 IRequest requestContext = createRequestContext(request);
         Role role = userAndRoleContext.getRole();
    	 List<RoleFunc>  roleFuncList = userAndRoleContext.getRoleFuncList();
		 service.adminUpdate(requestContext, role, roleFuncList);
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/role/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<Role> dto){
    	IRequest requestContext = createRequestContext(request);
        service.adminDelete(requestContext, dto);
        return new ExtAjax(true, null);
    }
    
    
    }
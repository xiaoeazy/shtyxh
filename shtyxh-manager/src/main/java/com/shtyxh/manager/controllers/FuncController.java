package com.shtyxh.manager.controllers;

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
import com.shtyxh.manager.dto.Func;
import com.shtyxh.manager.dto.RoleFunc;
import com.shtyxh.manager.service.IFuncService;

    @Controller
    public class FuncController extends BaseController{

    @Autowired
    private IFuncService service;


    @RequestMapping(value = "/sys/func/query")
    @ResponseBody
    public ResponseData query(Func dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sys/func/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<Func> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sys/func/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Func> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
 // ========================================后台===================================
    
    @RequestMapping(value = "/admin/sys/func/queryNotHave")
    @ResponseBody
    public List<Func> adminqueryNotHave(Long roleId,HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 List<Func> list =  new ArrayList<Func>();
    	 if(roleId!=null) {
    		 RoleFunc ur = new RoleFunc();
    		 ur.setRoleId(roleId);
    		 list=service.adminqueryNotHave(requestContext, ur);
    	 }else
    		 list=service.selectAll(requestContext);
         return list;
    }
    
    @RequestMapping(value = "/admin/sys/func/queryHave")
    @ResponseBody
    public List<Func> adminQueryHave(Long roleId,HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 List<Func> list =  new ArrayList<Func>();
    	 if(roleId!=null) {
    		 RoleFunc ur = new RoleFunc();
    		 ur.setRoleId(roleId);
    		 list=service.adminQueryHave(requestContext, ur);
    	 }
    		 
         return list;
    }
    
    }
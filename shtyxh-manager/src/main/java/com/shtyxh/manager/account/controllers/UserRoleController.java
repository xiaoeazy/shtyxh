package com.shtyxh.manager.account.controllers;

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
import com.shtyxh.manager.account.dto.UserRole;
import com.shtyxh.manager.account.service.IUserRoleService;

    @Controller
    public class UserRoleController extends BaseController{

    @Autowired
    private IUserRoleService service;


    @RequestMapping(value = "/sys/user/role/query")
    @ResponseBody
    public ResponseData query(UserRole dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sys/user/role/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<UserRole> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sys/user/role/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<UserRole> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    // ========================================后台===================================
    
    @RequestMapping(value = "/admin/user/role/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<UserRole> dto, BindingResult result, HttpServletRequest request){
    	  IRequest requestCtx = createRequestContext(request);
          List<UserRole> list = service.batchUpdate(requestCtx, dto);
          return new ExtAjax(true, null);
    }
    
    }
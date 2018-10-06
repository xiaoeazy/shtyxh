package com.shtyxh.manager.controllers;

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
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.manager.dto.KgCanton;
import com.shtyxh.manager.service.IKgCantonService;

    @Controller
    public class KgCantonController extends BaseController{

    @Autowired
    private IKgCantonService service;


    @RequestMapping(value = "/kg/canton/query")
    @ResponseBody
    public ResponseData query(KgCanton dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/canton/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgCanton> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/canton/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgCanton> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    //========================================后台===================================
    @RequestMapping(value = "/admin/canton/queryAll")
    @ResponseBody
    public ExtStore adminQueryAll(KgCanton dto, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         
         List<KgCanton> list =service.selectAll(requestContext);
         int count = list.size();
         return new ExtStore(null, null, count, list);
    }

    }
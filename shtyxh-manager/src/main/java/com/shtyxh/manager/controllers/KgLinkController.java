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
import com.shtyxh.common.bean.ExtAjax;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.manager.dto.KgLink;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgLinkService;

    @Controller
    public class KgLinkController extends BaseController{

    @Autowired
    private IKgLinkService service;
    @Autowired
   	private IJedisService iJedisService;

    @RequestMapping(value = "/kg/link/query")
    @ResponseBody
    public ResponseData query(KgLink dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/link/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgLink> dto, BindingResult result, HttpServletRequest request){
//		getValidator().validate(dto, result);
//		if (result.hasErrors()) {
//			ResponseData responseData = new ResponseData(false);
//			responseData.setMessage(getErrorMessage(result, request));
//			return responseData;
//		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/link/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgLink> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    
    
    //========================================后台===================================
    @RequestMapping(value = "/admin/link/query")
    @ResponseBody
    public ExtStore adminQuery(KgLink dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        int count = service.adminQueryCount(requestContext, null);
        List<KgLink> list = service.select(requestContext,dto,page,limit);
        return new ExtStore(start, limit, count, list);
    }
    
    
    @RequestMapping(value = "/admin/link/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgLink> dto, BindingResult result, HttpServletRequest request){
        IRequest requestCtx = createRequestContext(request);
        List<KgLink> list = service.batchUpdate(requestCtx, dto);
        iJedisService.delStringOfLink();
        return new ExtAjax(true, null);
    }
    
    @RequestMapping(value = "/admin/link/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgLink> dto){
        service.batchDelete(dto);
        iJedisService.delStringOfLink();
        return new ExtAjax(true, null);
    }
    
    }
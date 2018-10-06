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
import com.shtyxh.manager.bean.SysConfig;
import com.shtyxh.manager.dto.KgConfig;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgConfigService;

    @Controller
    public class KgConfigController extends BaseController{

    @Autowired
    private IKgConfigService service;
    @Autowired
  	private IJedisService iJedisService;

    @RequestMapping(value = "/kg/config/query")
    @ResponseBody
    public ResponseData query(KgConfig dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/config/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgConfig> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/config/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgConfig> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    //========================================后台===================================
    @RequestMapping(value = "/admin/config/query")
    @ResponseBody
    public ExtStore adminQuery(KgConfig dto, @RequestParam(defaultValue = DEFAULT_PAGE) int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgConfig> list = service.select(requestContext,dto,1,limit);
         return new ExtStore(start, limit, list.size(), list);
    }

    @RequestMapping(value = "/admin/config/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgConfig> dto, BindingResult result, HttpServletRequest request){
        IRequest requestCtx = createRequestContext(request);
        List<KgConfig> list = service.batchUpdate(requestCtx, dto);
        iJedisService.delStringOfConfig();
        return new ExtAjax(true, null);
    }

    }
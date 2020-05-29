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
import com.shtyxh.manager.dto.KgNotice;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgNoticeService;

    @Controller
    public class KgNoticeController extends BaseController{

    @Autowired
    private IKgNoticeService service;
    @Autowired
  	private IJedisService iJedisService;

    @RequestMapping(value = "/kg/notice/query")
    @ResponseBody
    public ResponseData query(KgNotice dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/notice/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgNotice> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/notice/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgNotice> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    //========================================后台===================================
    @RequestMapping(value = "/admin/notice/query")
    @ResponseBody
    public ExtStore adminQuery(KgNotice dto, @RequestParam(defaultValue = DEFAULT_PAGE)int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 dto.setSortname("sequence");
    	 dto.setSortorder("desc");
         List<KgNotice> list = service.select(requestContext,dto,page,limit);
         int count = service.adminQueryCount(requestContext, null);
         return new ExtStore(start, limit, count, list);
    }
    
  
    @RequestMapping(value = "/admin/notice/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgNotice> dto, BindingResult result, HttpServletRequest request){
    	 IRequest requestCtx = createRequestContext(request);
         List<KgNotice> list = service.batchUpdate(requestCtx, dto);
         iJedisService.delStringOfNotice();
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/notice/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgNotice> dto){
    	  service.batchDelete(dto);
    	  iJedisService.delStringOfNotice();
          return new ExtAjax(true, null);
    }
    }
package com.shtyxh.manager.controllers;

import org.springframework.stereotype.Controller;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.ResponseData;
import com.shtyxh.common.bean.ExtAjax;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.manager.dto.KgBaywindow;
import com.shtyxh.manager.dto.KgCarousel;
import com.shtyxh.manager.service.IKgBaywindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class KgBaywindowController extends BaseController{

    @Autowired
    private IKgBaywindowService service;


    @RequestMapping(value = "/kg/baywindow/query")
    @ResponseBody
    public ResponseData query(KgBaywindow dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/baywindow/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgBaywindow> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/baywindow/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgBaywindow> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    
  //========================================后台===================================
    @RequestMapping(value = "/admin/baywindow/query")
    @ResponseBody
    public ExtStore adminQuery(KgBaywindow dto, @RequestParam(defaultValue = DEFAULT_PAGE)int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgBaywindow> list = service.select(requestContext,dto,page,limit);
         int count = service.adminQueryCount(requestContext, null);
         return new ExtStore(start, limit, count, list);
    }
    
  
    @RequestMapping(value = "/admin/baywindow/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgBaywindow> dto, BindingResult result, HttpServletRequest request){
    	 IRequest requestCtx = createRequestContext(request);
         List<KgBaywindow> list = service.batchUpdate(requestCtx, dto);
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/baywindow/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgBaywindow> dto){
          IRequest requestCtx = createRequestContext(request);
    	  String webPath = request.getServletContext().getRealPath("/");
    	  service.adminDelete(requestCtx, webPath, dto);
          return new ExtAjax(true, null);
    }
    }
package cn.huan.kindergarten.controllers;

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

import cn.huan.kindergarten.dto.KgNewsAttribute;
import cn.huan.kindergarten.service.IKgNewsAttributeService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

    @Controller
    public class KgNewsAttributeController extends BaseController{

    @Autowired
    private IKgNewsAttributeService service;


    @RequestMapping(value = "/kg/newsattribute/query")
    @ResponseBody
    public ResponseData query(KgNewsAttribute dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/newsattribute/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgNewsAttribute> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/newsattribute/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgNewsAttribute> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    
    
  //========================================后台===================================
    @RequestMapping(value = "/admin/newsattribute/query")
    @ResponseBody
    public ExtStore adminQuery(KgNewsAttribute dto, @RequestParam(defaultValue = DEFAULT_PAGE)int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgNewsAttribute> list = service.select(requestContext,dto,page,limit);
         int count = service.adminQueryCount(requestContext, null);
        return new ExtStore(start, limit, count, list);
    }
    
    @RequestMapping(value = "/admin/newsattribute/queryAll")
    @ResponseBody
    public ExtStore adminQuery(KgNewsAttribute dto,HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgNewsAttribute> list = service.select(requestContext,dto);
         int count = service.adminQueryCount(requestContext, null);
        return new ExtStore(null, null, count, list);
    }

    @RequestMapping(value = "/admin/newsattribute/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgNewsAttribute> dto, BindingResult result, HttpServletRequest request){
    	 IRequest requestCtx = createRequestContext(request);
         List<KgNewsAttribute> list = service.batchUpdate(requestCtx, dto);
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/newsattribute/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgNewsAttribute> dto){
    	 service.batchDelete(dto);
         return new ExtAjax(true, null);
    }
    
    
    
    
    }
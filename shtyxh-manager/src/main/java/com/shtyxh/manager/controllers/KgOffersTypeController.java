package com.shtyxh.manager.controllers;

import org.springframework.stereotype.Controller;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.ResponseData;
import com.shtyxh.common.bean.ExtAjax;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.manager.dto.KgOffers;
import com.shtyxh.manager.dto.KgOffersType;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IKgOffersTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class KgOffersTypeController extends BaseController{

    @Autowired
    private IKgOffersTypeService service;


    @RequestMapping(value = "/kg/offers/type/query")
    @ResponseBody
    public ResponseData query(KgOffersType dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/offers/type/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgOffersType> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/offers/type/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgOffersType> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    //========================================后台===================================
    @RequestMapping(value = "/admin/offers/type/query")
    @ResponseBody
    public ExtStore adminQuery(KgOffersType dto, @RequestParam(defaultValue = DEFAULT_PAGE)int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgOffersType> list = service.select(requestContext,dto,page,limit);
         int count = service.adminQueryCount(requestContext, dto);
         return new ExtStore(start, limit, count, list);
    }
    
    @RequestMapping(value = "/admin/offers/offers/queryAll")
    @ResponseBody
    public ExtStore adminQueryAll(KgOffersType dto,HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 List<KgOffersType> list = service.selectAll(requestContext);
    	 int count = list.size();
    	 return new ExtStore(null, null, count, list);
    }

    
    
    @RequestMapping(value = "/admin/offers/type/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgOffersType> dto, BindingResult result, HttpServletRequest request){
    	 IRequest requestCtx = createRequestContext(request);
         service.batchUpdate(requestCtx, dto);
//         iJedisService.delHSetOfType();
         return new ExtAjax(true, null);
    }
    
    @RequestMapping(value = "/admin/offers/type/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgOffersType> dto){
    	 IRequest requestCtx = createRequestContext(request);
    	 service.batchDelete(dto);
//    	  iJedisService.delHSetOfType();
          return new ExtAjax(true, null);
    }
    
    }
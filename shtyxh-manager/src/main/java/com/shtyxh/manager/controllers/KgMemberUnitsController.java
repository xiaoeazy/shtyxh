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
import com.shtyxh.manager.dto.KgMemberUnits;
import com.shtyxh.manager.service.IKgMemberUnitsService;

    @Controller
    public class KgMemberUnitsController extends BaseController{

    @Autowired
    private IKgMemberUnitsService service;


    @RequestMapping(value = "/kg/member/units/query")
    @ResponseBody
    public ResponseData query(KgMemberUnits dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/member/units/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgMemberUnits> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/member/units/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgMemberUnits> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
 // ========================================后台===================================
    @RequestMapping(value = "/admin/member/query")
    @ResponseBody
    public ExtStore adminQuery(KgMemberUnits dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,String sort, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
//         List<KgNews> list = service.select(requestContext,dto,1,limit);
//    	 Map<String,String> map = CommonUtil.getSort(sort);
//    	 if(map.size()!=0) {
//    		 dto.setSortname(map.get("property"));
//    		 dto.setSortorder(map.get("direction"));
//    	 }
    	 List<KgMemberUnits> list = service.selectWithOtherInfo(requestContext,dto,page,limit);
    	 int count = service.adminQueryCount(requestContext, dto);
    	 return new ExtStore(start, limit, count, list);
    }
    
    @RequestMapping(value = "/admin/member/queryAll")
    @ResponseBody
    public ExtStore adminQueryAll(KgMemberUnits dto,HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 List<KgMemberUnits> list = service.selectAll(requestContext);
    	 int count = list.size();
    	 return new ExtStore(null, null, count, list);
    }

    @RequestMapping(value = "/admin/member/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgMemberUnits> dto, BindingResult result, HttpServletRequest request){
		
    	 IRequest requestCtx = createRequestContext(request);
         List<KgMemberUnits> list = service.batchUpdate(requestCtx, dto);
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/member/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgMemberUnits> dto){
    	 service.batchDelete(dto);
         return new ExtAjax(true, null);
    }
    }
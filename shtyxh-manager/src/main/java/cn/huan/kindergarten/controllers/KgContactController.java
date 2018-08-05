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

import cn.huan.kindergarten.bean.SysConfig;
import cn.huan.kindergarten.dto.KgContact;
import cn.huan.kindergarten.service.IKgContactService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

    @Controller
    public class KgContactController extends BaseController{

    @Autowired
    private IKgContactService service;


    @RequestMapping(value = "/kg/contact/query")
    @ResponseBody
    public ResponseData query(KgContact dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/contact/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgContact> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/contact/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgContact> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    //========================================后台===================================
    @RequestMapping(value = "/admin/contact/query")
    @ResponseBody
    public ExtStore adminQuery(KgContact dto, @RequestParam(defaultValue = DEFAULT_PAGE) int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgContact> list = service.select(requestContext,dto,1,limit);
         return new ExtStore(start, limit, list.size(), list);
    }

    @RequestMapping(value = "/admin/contact/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgContact> dto, BindingResult result, HttpServletRequest request){
        IRequest requestCtx = createRequestContext(request);
        List<KgContact> list = service.batchUpdate(requestCtx, dto);
        for(KgContact kg :list) {
     			switch(kg.getSyskey()) {
     				case "tel":{
     					SysConfig.tel=kg.getSysvalue();
     					break;
     				}
     			}
     		}
        return new ExtAjax(true, null);
    }
    }
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

import cn.huan.kindergarten.dto.KgIntroduction;
import cn.huan.kindergarten.service.IKgIntroductionService;
import cn.huan.shtyxh.common.bean.ExtAjax;

    @Controller
    public class KgIntroductionController extends BaseController{

    @Autowired
    private IKgIntroductionService service;


    @RequestMapping(value = "/kg/introduction/query")
    @ResponseBody
    public ResponseData query(KgIntroduction dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/introduction/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgIntroduction> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/introduction/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgIntroduction> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
  //========================================后台===================================
    @RequestMapping(value = "/admin/introduction/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgIntroduction> dto, BindingResult result, HttpServletRequest request){
    	IRequest requestCtx = createRequestContext(request);
        List<KgIntroduction> list = service.batchUpdate(requestCtx, dto);
        return new ExtAjax(true, null);
    }
    
    @RequestMapping(value = "/admin/introduction/query")
    @ResponseBody
    public ExtAjax adminQuery(KgIntroduction dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        List<KgIntroduction> list = service.select(requestContext,dto,page,pageSize);
        return new ExtAjax(true, list.get(0).getIntroduction());
    }
    }
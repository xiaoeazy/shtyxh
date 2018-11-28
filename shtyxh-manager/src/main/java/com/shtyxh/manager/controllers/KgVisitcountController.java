package com.shtyxh.manager.controllers;

import org.springframework.stereotype.Controller;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.ResponseData;
import com.shtyxh.manager.dto.KgVisitcount;
import com.shtyxh.manager.service.IKgVisitcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class KgVisitcountController extends BaseController{

    @Autowired
    private IKgVisitcountService service;


    @RequestMapping(value = "/kg/visitcount/query")
    @ResponseBody
    public ResponseData query(KgVisitcount dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/visitcount/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgVisitcount> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/visitcount/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgVisitcount> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }
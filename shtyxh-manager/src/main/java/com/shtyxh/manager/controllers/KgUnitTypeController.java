package com.shtyxh.manager.controllers;

import org.springframework.stereotype.Controller;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.ResponseData;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.manager.dto.KgCanton;
import com.shtyxh.manager.dto.KgUnitType;
import com.shtyxh.manager.service.IKgUnitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class KgUnitTypeController extends BaseController{

    @Autowired
    private IKgUnitTypeService service;


    @RequestMapping(value = "/kg/unittype/query")
    @ResponseBody
    public ResponseData query(KgUnitType dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/unittype/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgUnitType> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/unittype/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgUnitType> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    
    //========================================后台===================================
    @RequestMapping(value = "/admin/unittype/queryAll")
    @ResponseBody
    public ExtStore adminQueryAll(KgCanton dto, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         
         List<KgUnitType> list =service.selectAll(requestContext);
         int count = list.size();
         return new ExtStore(null, null, count, list);
    }
    
    }
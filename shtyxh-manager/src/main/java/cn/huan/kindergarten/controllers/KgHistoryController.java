package cn.huan.kindergarten.controllers;

import org.springframework.stereotype.Controller;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.ResponseData;
import cn.huan.kindergarten.dto.KgHistory;
import cn.huan.kindergarten.dto.KgMemberUnits;
import cn.huan.kindergarten.dto.KgNewstype;
import cn.huan.kindergarten.service.IKgHistoryService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class KgHistoryController extends BaseController{

    @Autowired
    private IKgHistoryService service;


    @RequestMapping(value = "/kg/history/query")
    @ResponseBody
    public ResponseData query(KgHistory dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/history/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgHistory> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/history/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgHistory> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    //========================================后台===================================
    @RequestMapping(value = "/admin/history/query")
    @ResponseBody
    public ExtStore adminQuery(KgHistory dto, @RequestParam(defaultValue = DEFAULT_PAGE)int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgHistory> list = service.select(requestContext,dto,page,limit);
         int count = service.adminQueryCount(requestContext, dto);
         return new ExtStore(start, limit, count, list);
    }
    
    @RequestMapping(value = "/admin/history/queryAll")
    @ResponseBody
    public ExtStore adminQueryAll(KgHistory dto, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgHistory> list =service.selectAll(requestContext);
         int count = list.size();
         return new ExtStore(null, null, count, list);
    }

    @RequestMapping(value = "/admin/history/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgHistory> dto, BindingResult result, HttpServletRequest request){
	   	 IRequest requestCtx = createRequestContext(request);
	     List<KgHistory> list = service.batchUpdate(requestCtx, dto);
	     return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/history/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgHistory> dto){
    	  service.batchDelete(dto);
          return new ExtAjax(true, null);
    }
    }
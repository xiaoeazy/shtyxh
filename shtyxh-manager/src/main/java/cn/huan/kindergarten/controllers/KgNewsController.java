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

import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.service.IKgNewsService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

    @Controller
    public class KgNewsController extends BaseController{

    @Autowired
    private IKgNewsService service;


    @RequestMapping(value = "/kg/news/query")
    @ResponseBody
    public ResponseData query(KgNews dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/news/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgNews> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/news/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgNews> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
   // ========================================后台===================================
    @RequestMapping(value = "/admin/news/query")
    @ResponseBody
    public ExtStore adminQuery(KgNews dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,String sort, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
//         List<KgNews> list = service.select(requestContext,dto,1,limit);
//    	 Map<String,String> map = CommonUtil.getSort(sort);
//    	 if(map.size()!=0) {
//    		 dto.setSortname(map.get("property"));
//    		 dto.setSortorder(map.get("direction"));
//    	 }
    	 List<KgNews> list = service.selectWithOtherInfo(requestContext,dto,page,limit);
    	 int count = service.adminQueryCount(requestContext, dto);
    	 return new ExtStore(start, limit, count, list);
    }
    
    @RequestMapping(value = "/admin/news/queryAll")
    @ResponseBody
    public ExtStore adminQueryAll(KgNews dto,HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 List<KgNews> list = service.selectAll(requestContext);
    	 int count = list.size();
    	 return new ExtStore(null, null, count, list);
    }

    @RequestMapping(value = "/admin/news/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgNews> dto, BindingResult result, HttpServletRequest request){
		
    	 IRequest requestCtx = createRequestContext(request);
         List<KgNews> list = service.batchUpdate(requestCtx, dto);
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/news/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgNews> dto){
        IRequest requestCtx = createRequestContext(request);
  	    String webPath = request.getServletContext().getRealPath("/");
  	    service.adminDelete(requestCtx, webPath, dto);
        return new ExtAjax(true, null);
    }
    }
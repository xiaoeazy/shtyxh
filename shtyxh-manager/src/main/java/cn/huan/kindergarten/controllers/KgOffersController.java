package cn.huan.kindergarten.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.mybatis.entity.Example;
import com.huan.HTed.mybatis.entity.Example.Criteria;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.system.dto.ResponseData;

import cn.huan.kindergarten.dto.KgOffers;
import cn.huan.kindergarten.service.IKgOffersService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

    @Controller
    public class KgOffersController extends BaseController{

    @Autowired
    private IKgOffersService service;


    @RequestMapping(value = "/kg/offers/query")
    @ResponseBody
    public ResponseData query(KgOffers dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/offers/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgOffers> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/offers/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgOffers> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    // ========================================后台===================================
    @RequestMapping(value = "/admin/offers/query")
    @ResponseBody
    public ExtStore adminQuery(KgOffers dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,String sort, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
//         List<KgNews> list = service.select(requestContext,dto,1,limit);
//    	 Map<String,String> map = CommonUtil.getSort(sort);
//    	 if(map.size()!=0) {
//    		 dto.setSortname(map.get("property"));
//    		 dto.setSortorder(map.get("direction"));
//    	 }
    	 List<KgOffers> list = service.selectWithOtherInfo(requestContext, dto, page, limit);
    	 int count = service.adminQueryCount(requestContext, dto);
    	 return new ExtStore(start, limit, count, list);
    }
    
    @RequestMapping(value = "/admin/offers/queryAll")
    @ResponseBody
    public ExtStore adminQueryAll(KgOffers dto,HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 List<KgOffers> list = service.selectAll(requestContext);
    	 int count = list.size();
    	 return new ExtStore(null, null, count, list);
    }

    @RequestMapping(value = "/admin/offers/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgOffers> dto, BindingResult result, HttpServletRequest request){
		
    	 IRequest requestCtx = createRequestContext(request);
         List<KgOffers> list = service.batchUpdate(requestCtx, dto);
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/offers/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgOffers> dto){
    	 service.batchDelete(dto);
         return new ExtAjax(true, null);
    }
    }
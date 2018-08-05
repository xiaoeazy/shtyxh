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

import cn.huan.kindergarten.dto.KgCarousel;
import cn.huan.kindergarten.service.IKgCarouselService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

    @Controller
    public class KgCarouselController extends BaseController{

    @Autowired
    private IKgCarouselService service;


    @RequestMapping(value = "/kg/carousel/query")
    @ResponseBody
    public ResponseData query(KgCarousel dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/carousel/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgCarousel> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/carousel/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgCarousel> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    //========================================后台===================================
    @RequestMapping(value = "/admin/carousel/query")
    @ResponseBody
    public ExtStore adminQuery(KgCarousel dto, @RequestParam(defaultValue = DEFAULT_PAGE)int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
    	 dto.setSortname("sequence");
    	 dto.setSortorder("desc");
         List<KgCarousel> list = service.select(requestContext,dto,page,limit);
         int count = service.adminQueryCount(requestContext, null);
         return new ExtStore(start, limit, count, list);
    }
    
  
    @RequestMapping(value = "/admin/carousel/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgCarousel> dto, BindingResult result, HttpServletRequest request){
    	 IRequest requestCtx = createRequestContext(request);
         List<KgCarousel> list = service.batchUpdate(requestCtx, dto);
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/carousel/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgCarousel> dto){
          IRequest requestCtx = createRequestContext(request);
    	  String webPath = request.getServletContext().getRealPath("/");
    	  service.adminDelete(requestCtx, webPath, dto);
          return new ExtAjax(true, null);
    }
    }
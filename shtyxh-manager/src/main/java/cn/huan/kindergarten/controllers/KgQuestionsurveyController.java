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

import cn.huan.kindergarten.dto.KgQuestionsurvey;
import cn.huan.kindergarten.service.IKgQuestionsurveyService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

    @Controller
    public class KgQuestionsurveyController extends BaseController{

    @Autowired
    private IKgQuestionsurveyService service;


    @RequestMapping(value = "/kg/questionsurvey/query")
    @ResponseBody
    public ResponseData query(KgQuestionsurvey dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/questionsurvey/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgQuestionsurvey> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/questionsurvey/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgQuestionsurvey> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    //========================================后台===================================
	
    @RequestMapping(value = "/admin/questionsurvey/query")
    @ResponseBody
    public ExtStore adminQuery(KgQuestionsurvey dto, @RequestParam(defaultValue = DEFAULT_PAGE)int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgQuestionsurvey> list = service.select(requestContext,dto,page,limit);
         int count = service.adminQueryCount(requestContext, null);
         return new ExtStore(start, limit, count, list);
    }
    
    @RequestMapping(value = "/admin/questionsurvey/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody KgQuestionsurvey  dto, BindingResult result, HttpServletRequest request){
        IRequest requestCtx = createRequestContext(request);
        service.addKgQuestionsurvey(requestCtx, dto);
    	return new ExtAjax(true, null);	
    }
    
    @RequestMapping(value = "/admin/questionsurvey/update")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgQuestionsurvey> dto, BindingResult result, HttpServletRequest request){
    	 IRequest requestCtx = createRequestContext(request);
         List<KgQuestionsurvey> list = service.batchUpdate(requestCtx, dto);
         return new ExtAjax(true, null);
    }
    
    @RequestMapping(value = "/admin/QuestionSurvey/remove")
    @ResponseBody
	public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgQuestionsurvey> dto ){
    	IRequest requestCtx = createRequestContext(request);
    	service.adminDelete(requestCtx, dto);
         return new ExtAjax(true, null);
    }
    
    
    }
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
import com.shtyxh.manager.dto.KgQuestionsurvey;
import com.shtyxh.manager.dto.KgUserQAnswer;
import com.shtyxh.manager.service.IKgQuestionsurveyService;
import com.shtyxh.manager.service.IKgUserQAnswerService;
import com.shtyxh.manager.utils.CommonFuncUtil;

    @Controller
    public class KgUserQAnswerController extends BaseController{

    @Autowired
    private IKgUserQAnswerService service;
    @Autowired
    private IKgQuestionsurveyService iKgQuestionsurveyService;


    @RequestMapping(value = "/kg/user/q/answer/query")
    @ResponseBody
    public ResponseData query(KgUserQAnswer dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/user/q/answer/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgUserQAnswer> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/user/q/answer/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgUserQAnswer> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    //=========================================================================
    @RequestMapping(value = "/index/kg/user/q/answer/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgUserQAnswer> dto,Long sid, BindingResult result, HttpServletRequest request){
    	 IRequest requestCtx = createRequestContext(request);
    	 
		 KgQuestionsurvey qs = new KgQuestionsurvey();
		 qs.setId(sid);
		 KgQuestionsurvey questionState = iKgQuestionsurveyService.selectByPrimaryKey(requestCtx, qs);
    	 if(questionState.getFinished()==null||questionState.getFinished()==false) {
    		 return new ExtAjax(false, "问卷活动已经结束");
    	 }
    	 String ip = CommonFuncUtil.getIpAddress(request);
    	 KgUserQAnswer qa = new KgUserQAnswer();
    	 qa.setIp(ip);
    	 qa.setSid(sid);
    	 int num = service.queryCount(requestCtx,qa);
    	 if(num!=0) {
    		 return new ExtAjax(false, "对不起，该ip已经投过票了");
    	 }
    	 service.adminUpdate(requestCtx, dto, ip, sid);
         return new ExtAjax(true, null);
    }
    }
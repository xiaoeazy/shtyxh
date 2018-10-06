package com.shtyxh.manager.controllers;

import java.util.ArrayList;
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
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.manager.bean.ChartItemA;
import com.shtyxh.manager.bean.ChartItemB;
import com.shtyxh.manager.bean.ChartItemC;
import com.shtyxh.manager.dto.KgQuestionAnswer;
import com.shtyxh.manager.dto.KgUserQAnswer;
import com.shtyxh.manager.service.IKgQuestionAnswerService;
import com.shtyxh.manager.service.IKgUserQAnswerService;

    @Controller
    public class KgQuestionAnswerController extends BaseController{

    @Autowired
    private IKgQuestionAnswerService service;
    
    @Autowired
    private IKgUserQAnswerService qService;


    @RequestMapping(value = "/kg/question/answer/query")
    @ResponseBody
    public ResponseData query(KgQuestionAnswer dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/question/answer/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgQuestionAnswer> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/question/answer/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgQuestionAnswer> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
//========================================后台===================================
    @RequestMapping(value = "/admin/question/answer/queryA")
    @ResponseBody
    public ExtStore adminQueryA(KgQuestionAnswer dto, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgQuestionAnswer> list = service.select(requestContext, dto);
         List<ChartItemA> tlist = new ArrayList<ChartItemA>();
         double allSize = 0;
         for(KgQuestionAnswer ka :list) {
        	 ChartItemA tb = new ChartItemA();
        	 tb.setName(ka.getName());
        	 tb.setValue(ka.getValue());
        	 tb.setId(ka.getId());
        	 
        	 KgUserQAnswer qa = new KgUserQAnswer();
        	 qa.setAid(ka.getId());
        	 List<KgUserQAnswer> uqa = qService.select(requestContext, qa);
        	 if(uqa.size()>0) {
        		 tb.setSize(uqa.size());
        		 allSize+=uqa.size();
        	 }
        	 tlist.add(tb);
         }
         for(ChartItemA ka:tlist) {
        	 double percent = ka.getSize()/allSize*100;
        	 ka.setPercent(percent);
         }
         return new ExtStore(null, null, tlist.size(), tlist);
    }
    
    @RequestMapping(value = "/admin/question/answer/queryB")
    @ResponseBody
    public ExtStore adminQueryB(KgQuestionAnswer dto, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgQuestionAnswer> list = service.select(requestContext, dto);
         List<ChartItemB> tlist = new ArrayList<ChartItemB>();
         double allSize = 0;
         for(KgQuestionAnswer ka :list) {
        	 ChartItemB tb = new ChartItemB();
        	 tb.setName(ka.getName());
        	 tb.setValue(ka.getValue());
        	 tb.setId(ka.getId());
        	 
        	 KgUserQAnswer qa = new KgUserQAnswer();
        	 qa.setAid(ka.getId());
        	 List<KgUserQAnswer> uqa = qService.select(requestContext, qa);
        	 if(uqa.size()>0) {
        		 tb.setSize(uqa.size());
        		 allSize+=uqa.size();
        	 }
        	 tlist.add(tb);
         }
         for(ChartItemB ka:tlist) {
        	 double percent = ka.getSize()/allSize*100;
        	 ka.setPercent(percent);
         }
         return new ExtStore(null, null, tlist.size(), tlist);
    }
    
    
    @RequestMapping(value = "/admin/question/answer/queryC")
    @ResponseBody
    public ExtStore adminQueryC(KgQuestionAnswer dto, @RequestParam(defaultValue = DEFAULT_PAGE)int page,int start,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgQuestionAnswer> list = service.select(requestContext, dto);
         List<ChartItemC> tlist = new ArrayList<ChartItemC>();
         int count = 0;
         if(list.size()>0) {
        	 KgQuestionAnswer answer = list.get(0);
        	 KgUserQAnswer qa = new KgUserQAnswer();
        	 qa.setAid(answer.getId());
        	 
        	 List<KgUserQAnswer> uqaList = qService.select(requestContext, qa,page,limit);
              count = qService.adminQueryCount(requestContext, qa);
        	 
        	 for(KgUserQAnswer kuq :uqaList) {
        		 ChartItemC tb = new ChartItemC();
        		 tb.setContent(kuq.getContent());
            	 tb.setId(kuq.getId());
            	 tlist.add(tb);
        	 }
         }
         return new ExtStore(start, limit, count, tlist);
    }
    
    }
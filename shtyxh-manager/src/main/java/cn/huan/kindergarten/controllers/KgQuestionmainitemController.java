package cn.huan.kindergarten.controllers;

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

import cn.huan.kindergarten.bean.TreeBean;
import cn.huan.kindergarten.dto.KgQuestionmainitem;
import cn.huan.kindergarten.service.IKgQuestionmainitemService;

    @Controller
    public class KgQuestionmainitemController extends BaseController{

    @Autowired
    private IKgQuestionmainitemService service;


    @RequestMapping(value = "/kg/questionmainitem/query")
    @ResponseBody
    public ResponseData query(KgQuestionmainitem dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/questionmainitem/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgQuestionmainitem> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/questionmainitem/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgQuestionmainitem> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
 //========================================后台===================================
	
    @RequestMapping(value = "/admin/questionmainitem/query")
    @ResponseBody
    public List<TreeBean> adminQuery(KgQuestionmainitem dto, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgQuestionmainitem> list = service.select(requestContext, dto);
         List<TreeBean> tlist = new ArrayList<TreeBean>();
         for(KgQuestionmainitem km :list) {
        	 TreeBean tb = new TreeBean();
        	 tb.setText(km.getqItemsTitle());
        	 if(("1").equals(km.getItemType())) {
        		 tb.setIconCls("A");  //A饼状图
        	 }else if(("2").equals(km.getItemType())) {
        		 tb.setIconCls("B");	//B条状图
        	 }else {
        		 tb.setIconCls("C");	//C查询列表
        	 }
        	 tb.setLeaf(true);
        	 tb.setId(km.getId()+"");
        	 tlist.add(tb);
         }
         return tlist;
    }
    
    }
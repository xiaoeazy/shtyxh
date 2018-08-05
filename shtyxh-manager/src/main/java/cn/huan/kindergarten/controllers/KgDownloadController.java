package cn.huan.kindergarten.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
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

import cn.huan.kindergarten.dto.KgDownload;
import cn.huan.kindergarten.service.IKgDownloadService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

    @Controller
    public class KgDownloadController extends BaseController{

    @Autowired
    private IKgDownloadService service;


    @RequestMapping(value = "/kg/download/query")
    @ResponseBody
    public ResponseData query(KgDownload dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/download/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgDownload> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/download/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgDownload> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
  //========================================后台===================================
    @RequestMapping(value = "/admin/download/query")
    @ResponseBody
    public ExtStore adminQuery(KgDownload dto, @RequestParam(defaultValue = DEFAULT_PAGE)int page,int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgDownload> list = service.select(requestContext,dto,page,limit);
         int count = service.adminQueryCount(requestContext, null);
         return new ExtStore(start, limit, count, list);
    }
    
    @RequestMapping(value = "/admin/download/queryAll")
    @ResponseBody
    public ExtStore adminQueryAll(KgDownload dto, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgDownload> list = service.select(requestContext,dto);
         int count = service.adminQueryCount(requestContext, null);
         return new ExtStore(null, null, count, list);
    }

    @RequestMapping(value = "/admin/download/submit")
    @ResponseBody
	public ExtAjax adminUpdate( @RequestBody List<KgDownload> dto,HttpServletRequest request) throws FileUploadException, IOException{
    	 
    	 IRequest requestCtx = createRequestContext(request);
    	 for(KgDownload KgDownload:dto) {
    		 String fileName =  KgDownload.getFilePath().substring(KgDownload.getFilePath().lastIndexOf("/")+1);
//        	 String fileTitle = imgName.substring(0,imgName.lastIndexOf("."));
        	 KgDownload.setFileTitle(fileName);
    	 }
         List<KgDownload> list = service.batchUpdate(requestCtx, dto);
         return new ExtAjax(true, null);
    }

    @RequestMapping(value = "/admin/download/remove")
    @ResponseBody
    public ExtAjax adminDelete(HttpServletRequest request,@RequestBody List<KgDownload> dto){
    	 IRequest requestCtx = createRequestContext(request);
    	  String webPath = request.getServletContext().getRealPath("/");
    	  service.adminDelete(requestCtx, webPath, dto);
          return new ExtAjax(true, null);
    }
    }
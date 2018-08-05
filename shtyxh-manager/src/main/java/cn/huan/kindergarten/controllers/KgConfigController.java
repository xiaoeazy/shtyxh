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

import cn.huan.kindergarten.bean.SysConfig;
import cn.huan.kindergarten.dto.KgConfig;
import cn.huan.kindergarten.service.IKgConfigService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

    @Controller
    public class KgConfigController extends BaseController{

    @Autowired
    private IKgConfigService service;


    @RequestMapping(value = "/kg/config/query")
    @ResponseBody
    public ResponseData query(KgConfig dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/config/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgConfig> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/config/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgConfig> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    //========================================后台===================================
    @RequestMapping(value = "/admin/config/query")
    @ResponseBody
    public ExtStore adminQuery(KgConfig dto, @RequestParam(defaultValue = DEFAULT_PAGE) int start,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
    	 IRequest requestContext = createRequestContext(request);
         List<KgConfig> list = service.select(requestContext,dto,1,limit);
         return new ExtStore(start, limit, list.size(), list);
    }

    @RequestMapping(value = "/admin/config/submit")
    @ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgConfig> dto, BindingResult result, HttpServletRequest request){
        IRequest requestCtx = createRequestContext(request);
        List<KgConfig> list = service.batchUpdate(requestCtx, dto);
        List<KgConfig> list2 = service.selectAll(requestCtx);
        for(KgConfig kg :list2) {
			switch(kg.getSyskey()) {
				case "webname":{
					SysConfig.webname=kg.getSysvalue();
					break;
				}
				case "uploadpath":{
					SysConfig.uploadpath=kg.getSysvalue();
					break;
				}
				case "copyright":{
					SysConfig.copyright=kg.getSysvalue();
					break;
				}
				case "keyword":{
					SysConfig.keyword=kg.getSysvalue();
					break;
				}
				case "webdesc":{
					SysConfig.webdesc=kg.getSysvalue();
					break;
				}
				case "webLogo":{
					SysConfig.webLogo=kg.getSysvalue();
					break;
				}
				case "webIco":{
					SysConfig.webIco=kg.getSysvalue();
					break;
				}
				case "ICPlicense":{
					SysConfig.ICPlicense=kg.getSysvalue();
					break;
				}
				case "ICPlicensePath":{
					SysConfig.ICPlicensePath=kg.getSysvalue();
					break;
				}
				case "webIp":{
					SysConfig.webIp=kg.getSysvalue();
					break;
				}
				case "wx":{
					SysConfig.wx=kg.getSysvalue();
					break;
				}
				case "wb":{
					SysConfig.wb=kg.getSysvalue();
					break;
				}
				default:{
					
				}
			}
		}
        return new ExtAjax(true, null);
    }

    }
package cn.huan.kindergarten.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import cn.huan.kindergarten.bean.ZipFile;
import cn.huan.kindergarten.dto.KgAssessmentActivityUserUpload;
import cn.huan.kindergarten.exception.KgFileException;
import cn.huan.kindergarten.service.IKgAssessmentActivityUserUploadService;
import cn.huan.kindergarten.utils.CommonUtil;
import cn.huan.shtyxh.common.bean.ExtStore;

    @Controller
    public class KgAssessmentActivityUserUploadController extends BaseController{

    @Autowired
    private IKgAssessmentActivityUserUploadService service;


    @RequestMapping(value = "/kg/assessment/activity/user/upload/query")
    @ResponseBody
    public ResponseData query(KgAssessmentActivityUserUpload dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/kg/assessment/activity/user/upload/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<KgAssessmentActivityUserUpload> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/kg/assessment/activity/user/upload/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KgAssessmentActivityUserUpload> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    
    
 // ========================================后台===================================
 	@RequestMapping(value = "/admin/assessment/activity/user/upload/query")
 	@ResponseBody
 	public ExtStore adminQuery(KgAssessmentActivityUserUpload dto,HttpServletRequest request) {
 		
 		IRequest requestContext = createRequestContext(request);
 		List<KgAssessmentActivityUserUpload> list = service.select(requestContext, dto);
 		return new ExtStore(null, null, null, list);
 	}
 	
 	
 	@RequestMapping("/admin/assessment/activity/user/upload/download")  
    public String downloadFiles(KgAssessmentActivityUserUpload kauu, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, KgFileException {
 		File fileZip;
		// 文件输出流
		FileOutputStream outStream=null;
		// 压缩流
		ZipOutputStream toClient=null;
			IRequest requestContext = createRequestContext(request);
			List<KgAssessmentActivityUserUpload> list = service.select(requestContext, kauu);
			String webPath = request.getServletContext().getRealPath("/");
			List<ZipFile> files = new ArrayList<ZipFile>();
			for(KgAssessmentActivityUserUpload ku:list) {
				File file = new File(webPath+ku.getFilePath());
				if(file.exists()) {
					ZipFile zipfile = new ZipFile(ku.getFileName(), file);
					files.add(zipfile);
				}
					
			}

			String fileName = UUID.randomUUID().toString() + ".zip";
			// 在服务器端创建打包下载的临时文件
			String outFilePath = webPath + "/zipFile/";

			fileZip = new File(outFilePath + fileName);
			outStream = new FileOutputStream(fileZip);
			toClient = new ZipOutputStream(outStream);
   //  toClient.setEncoding("gbk");
			CommonUtil.zipFile(files, toClient);
			toClient.close();
			outStream.close();
			CommonUtil.downloadFile(fileZip, response, true);
        return null;
    }
 	
 	

 	
 	
    
    }
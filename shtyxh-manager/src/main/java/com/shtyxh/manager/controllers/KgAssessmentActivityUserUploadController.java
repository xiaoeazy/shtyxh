package com.shtyxh.manager.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import com.shtyxh.common.bean.AjaxZipFile;
import com.shtyxh.common.bean.ExtStore;
import com.shtyxh.common.bean.ZipFile;
import com.shtyxh.common.exception.FileException;
import com.shtyxh.manager.dto.KgAssessmentActivityUserUpload;
import com.shtyxh.manager.service.IKgAssessmentActivityUserUploadService;

@Controller
public class KgAssessmentActivityUserUploadController extends BaseController {

	@Autowired
	private IKgAssessmentActivityUserUploadService service;

	@RequestMapping(value = "/kg/assessment/activity/user/upload/query")
	@ResponseBody
	public ResponseData query(KgAssessmentActivityUserUpload dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
		IRequest requestContext = createRequestContext(request);
		return new ResponseData(service.select(requestContext, dto, page, pageSize));
	}

	@RequestMapping(value = "/kg/assessment/activity/user/upload/submit")
	@ResponseBody
	public ResponseData update(@RequestBody List<KgAssessmentActivityUserUpload> dto, BindingResult result,
			HttpServletRequest request) {
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
	public ResponseData delete(HttpServletRequest request, @RequestBody List<KgAssessmentActivityUserUpload> dto) {
		service.batchDelete(dto);
		return new ResponseData();
	}

	// ========================================后台===================================
	@RequestMapping(value = "/admin/assessment/activity/user/upload/query")
	@ResponseBody
	public ExtStore adminQuery(KgAssessmentActivityUserUpload dto, HttpServletRequest request) {

		IRequest requestContext = createRequestContext(request);
		List<KgAssessmentActivityUserUpload> list = service.select(requestContext, dto);
		return new ExtStore(null, null, null, list);
	}

	@RequestMapping("/admin/assessment/activity/user/upload/loadDownloadZipInfo")
	public AjaxZipFile downloadFiles(KgAssessmentActivityUserUpload kauu, HttpServletRequest request,
			HttpServletResponse response) {
		List<ZipFile> files = new ArrayList<ZipFile>();
		try {
			IRequest requestContext = createRequestContext(request);
			List<KgAssessmentActivityUserUpload> list = service.select(requestContext, kauu);
			for (KgAssessmentActivityUserUpload ku : list) {
					ZipFile zipfile = new ZipFile(ku.getFileName(), ku.getFilePath());
					files.add(zipfile);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new AjaxZipFile(false, e.getMessage(), files);
		}

		return new AjaxZipFile(true, "", files);
	}

}
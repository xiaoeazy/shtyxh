package cn.huan.kindergarten.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huan.HTed.account.service.IUserService;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.system.dto.ResponseData;

import cn.huan.kindergarten.dto.KgAssessmentActivityUserProgress;
import cn.huan.kindergarten.service.IKgAssessmentActivityUserProgressService;
import cn.huan.shtyxh.common.bean.ExtAjax;
import cn.huan.shtyxh.common.bean.ExtStore;

@Controller
public class KgAssessmentActivityUserProgressController extends BaseController {
	private static  Map<Integer,String> stateArray = new HashMap<Integer,String>();
	static {
		stateArray.put(10, "UPLOAD");
		stateArray.put(20, "ADMINPASS");
		stateArray.put(30, "ADMINFAILED");
		stateArray.put(40, "TOEXPERT");
		stateArray.put(50, "EXPERTPASS");
		stateArray.put(60, "EXPERTFAILED");
	}
	@Autowired
	private IKgAssessmentActivityUserProgressService service;
	@Autowired
	private IUserService iUserService;

	@RequestMapping(value = "/kg/assessment/activity/user/progress/query")
	@ResponseBody
	public ResponseData query(KgAssessmentActivityUserProgress dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
		IRequest requestContext = createRequestContext(request);
		return new ResponseData(service.select(requestContext, dto, page, pageSize));
	}

	@RequestMapping(value = "/kg/assessment/activity/user/progress/submit")
	@ResponseBody
	public ResponseData update(@RequestBody List<KgAssessmentActivityUserProgress> dto, BindingResult result,
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

	@RequestMapping(value = "/kg/assessment/activity/user/progress/remove")
	@ResponseBody
	public ResponseData delete(HttpServletRequest request, @RequestBody List<KgAssessmentActivityUserProgress> dto) {
		service.batchDelete(dto);
		return new ResponseData();
	}

	// ========================================后台===================================
	@RequestMapping(value = "/admin/assessment/activity/user/progress/query")
	@ResponseBody
	public ExtStore adminQuery(KgAssessmentActivityUserProgress dto,
			@RequestParam(defaultValue = DEFAULT_PAGE) int page, int start,
			@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit, HttpServletRequest request) {
		IRequest requestContext = createRequestContext(request);
		// List<KgNews> list = service.select(requestContext,dto,1,limit);
		List<KgAssessmentActivityUserProgress> list = service.selectWithOtherInfo(requestContext, dto, page, limit);
		Map<Long ,String> map = iUserService.loadAllUserToMap(requestContext);
		String uploadUserName="";
		String expertUserName="";
		for(KgAssessmentActivityUserProgress p:list) {
			uploadUserName = map.get(p.getUploadUserId());
			p.setUploadUserName(uploadUserName);
			if(p.getExpertUserId()!=null) {
				expertUserName = map.get(p.getExpertUserId());
				p.setExpertUserName(expertUserName);
			}
		}
		int count = service.adminQueryCount(requestContext, dto);
		return new ExtStore(start, limit, count, list);
	}

	@RequestMapping(value = "/admin/assessment/activity/user/progress/submit")
	@ResponseBody
	public ExtAjax adminUpdate(@RequestBody List<KgAssessmentActivityUserProgress> dto, BindingResult result,
			HttpServletRequest request) {
		IRequest requestCtx = createRequestContext(request);
		List<KgAssessmentActivityUserProgress> list = service.batchUpdate(requestCtx, dto);
		return new ExtAjax(true, null);
	}

	@RequestMapping(value = "/admin/assessment/activity/user/progress/remove")
	@ResponseBody
	public ExtAjax adminDelete(HttpServletRequest request, @RequestBody List<KgAssessmentActivityUserProgress> dto) {
		service.batchDelete(dto);
		return new ExtAjax(true, null);
	}
}
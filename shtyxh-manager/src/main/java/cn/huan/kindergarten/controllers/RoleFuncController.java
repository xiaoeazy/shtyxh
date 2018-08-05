package cn.huan.kindergarten.controllers;

import org.springframework.stereotype.Controller;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.ResponseData;
import cn.huan.kindergarten.dto.RoleFunc;
import cn.huan.kindergarten.service.IRoleFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class RoleFuncController extends BaseController{

    @Autowired
    private IRoleFuncService service;


    @RequestMapping(value = "/sys/role/func/query")
    @ResponseBody
    public ResponseData query(RoleFunc dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sys/role/func/submit")
    @ResponseBody
	public ResponseData update(@RequestBody List<RoleFunc> dto, BindingResult result, HttpServletRequest request){
		getValidator().validate(dto, result);
		if (result.hasErrors()) {
			ResponseData responseData = new ResponseData(false);
			responseData.setMessage(getErrorMessage(result, request));
			return responseData;
		}
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sys/role/func/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<RoleFunc> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }
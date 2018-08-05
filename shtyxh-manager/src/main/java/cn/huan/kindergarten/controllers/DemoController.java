package cn.huan.kindergarten.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.system.controllers.BaseController;

@Controller
public class DemoController extends BaseController {
	

	
	@RequestMapping(value = "/demo")
	@ResponseBody
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView(getViewPath() + "/admin/demo");
		return view;
	}


}
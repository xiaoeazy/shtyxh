package cn.huan.kindergarten.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.controllers.BaseController;

import cn.huan.kindergarten.dto.KgQuestionsurvey;
/**
 * 报名
 * @author huanghuan
 *
 */
@Controller
public class IndexSignUpController extends BaseController {
	// 默认的登录页
    public static final String VIEW_TRAINING = "/index/signUp/training";
    
	@RequestMapping(value = "/index/signUp/training")
	@ResponseBody
	public ModelAndView training(KgQuestionsurvey dto,HttpServletRequest request, HttpServletResponse response) {
		IRequest requestCtx = createRequestContext(request);
		ModelAndView view = new  ModelAndView(getViewPath() + VIEW_TRAINING);
		return view;
	}
}
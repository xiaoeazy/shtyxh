package cn.huan.kindergarten.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.controllers.BaseController;

import cn.huan.kindergarten.dto.KgQuestionmainitem;
import cn.huan.kindergarten.dto.KgQuestionsurvey;
import cn.huan.kindergarten.service.IKgQuestionmainitemService;
import cn.huan.kindergarten.service.IKgQuestionsurveyService;

@Controller
public class IndexQuestionController extends BaseController {
	// 默认的登录页
    public static final String VIEW_QUESTION = "/index/question/createQuestion";
    @Autowired
    private IKgQuestionsurveyService iKgQuestionsurveyService;
    @Autowired
    private IKgQuestionmainitemService iKgQuestionmainitemService;
    
	@RequestMapping(value = "/index/questionsurvey/query")
	@ResponseBody
	public ModelAndView query(KgQuestionsurvey dto,HttpServletRequest request, HttpServletResponse response) {
		IRequest requestCtx = createRequestContext(request);
		ModelAndView view = new  ModelAndView(getViewPath() + VIEW_QUESTION);
//		dto.setId(1l);
		KgQuestionsurvey questionsurvey = iKgQuestionsurveyService.selectByPrimaryKey(requestCtx, dto);
		KgQuestionmainitem ki = new KgQuestionmainitem();
		ki.setSid(questionsurvey.getId());
		List<KgQuestionmainitem> questionMainItemlist = iKgQuestionmainitemService.questionmainitem(requestCtx, ki);
		view.addObject("questionsurvey",questionsurvey);
		view.addObject("questionMainItemlist",questionMainItemlist);
		return view;
	}
}
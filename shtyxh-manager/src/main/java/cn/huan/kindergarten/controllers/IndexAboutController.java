package cn.huan.kindergarten.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;

import cn.huan.kindergarten.dto.KgIntroduction;
import cn.huan.kindergarten.service.IKgIntroductionService;

@Controller
public class IndexAboutController extends IndexBaseController{
	@Autowired
	private IKgIntroductionService iKgIntroductionService;
	
	@RequestMapping(value = "/index/about")
    @ResponseBody
    public ModelAndView about(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/about/about");
    	 IRequest requestContext = createRequestContext(request);
    	 KgIntroduction ki = new KgIntroduction();
    	 ki.setId(1l);
    	 List<KgIntroduction> list =iKgIntroductionService.select(requestContext, ki);
    	 KgIntroduction result = list.get(0);
    	 loadNavigation(mv, requestContext, CH_XHJJ);
    	 mv.addObject("about",result.getIntroduction());
    	 loadSysConfig(mv);
         return mv;
    }

}
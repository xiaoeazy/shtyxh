package com.shtyxh.manager.index.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.shtyxh.manager.dto.KgContact;
import com.shtyxh.manager.service.IKgContactService;

@Controller
public class IndexContactController extends IndexBaseController{
//	@Autowired
//	private IKgContactService iKgContactService;
//	
//	@RequestMapping(value = "/index/contact")
//    @ResponseBody
//    public ModelAndView contact(HttpServletRequest request) {
//    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/contact/contact");
//    	 IRequest requestContext = createRequestContext(request);
//    	loadNavigation(mv, CH_XHGK);
//    	
//    	List<KgContact> contactList = iKgContactService.selectAll(requestContext);
//    	Map<String ,String > map = new HashMap<String ,String >();
//    	for(KgContact ct :contactList) {
//    		map.put(ct.getSyskey(), ct.getSysvalue());
//    	}
//    	mv.addObject("contactMap",map);
//    	loadSysConfig(mv);
//        return mv;
//    }
	

   
}
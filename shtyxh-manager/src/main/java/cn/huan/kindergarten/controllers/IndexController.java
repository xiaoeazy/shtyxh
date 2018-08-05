package cn.huan.kindergarten.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.account.dto.Role;
import com.huan.HTed.account.dto.User;
import com.huan.HTed.account.dto.UserRole;
import com.huan.HTed.account.service.IRoleService;
import com.huan.HTed.account.service.IUserService;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.impl.RequestHelper;

import cn.huan.kindergarten.bean.SysConfig;
import cn.huan.kindergarten.dto.KgCarousel;
import cn.huan.kindergarten.dto.KgDownload;
import cn.huan.kindergarten.dto.KgIntroduction;
import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.dto.KgNewstype;
import cn.huan.kindergarten.service.IKgAssessmentActivityService;
import cn.huan.kindergarten.service.IKgCarouselService;
import cn.huan.kindergarten.service.IKgDownloadService;
import cn.huan.kindergarten.service.IKgNewsService;
import cn.huan.kindergarten.service.IKgNewstypeService;
import cn.huan.kindergarten.utils.CommonUtil;

@Controller
public class IndexController extends IndexBaseController{
	
	
	@Autowired
	private IKgDownloadService iKgDownloadService;
	@Autowired
	private IKgNewstypeService iKgNewstypeService;
	@Autowired
	private IKgCarouselService iKgCarouselService;
	@Autowired
	private IKgNewsService iKgNewsService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IKgAssessmentActivityService iKgAssessmentActivityService;
	
	@RequestMapping(value = "/")
    @ResponseBody
    public ModelAndView index(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/index");
    	 IRequest requestContext = createRequestContext(request);
    	 KgIntroduction ki = new KgIntroduction();
    	 ki.setId(1l);
    	//查询大轮播图和下载资料
    	 KgCarousel kc = new KgCarousel();
    	 kc.setSortorder("desc");
    	 kc.setSortname("sequence");
    	 
    	 KgDownload kl = new KgDownload();
    	 kl.setSortorder("desc");
    	 kl.setSortname("createdate");
    	 List<KgDownload> downloadList = iKgDownloadService.select(requestContext, kl, 1, 4);
    	 List<KgCarousel> carouselList =iKgCarouselService.select(requestContext, kc, 1, 5);
    	
    	 //查询13条文章
    	 KgNews KgNews = new KgNews();
    	 KgNews.setSortname("sequence");
    	 KgNews.setSortorder("desc");
    	 List<KgNews> newsList =iKgNewsService.select(requestContext, KgNews, 1, 13);
    	 CommonUtil.judgeNewsTitleLength(newsList,17);
    	 
//    	 List<KgNews> newsList2 =iKgNewsService.select(requestContext, null, 2, 6);
//    	 CommonUtil.judgeNewsTitleLength(newsList2,17);
    	 //查询小轮播图
    	 KgNews.setIndexshow("Y");
    	 List<KgNews> newsThumbNailList =iKgNewsService.select(requestContext, KgNews, 1, 5);
    	 for(KgNews kn:newsThumbNailList) {
    		 if(("").equals(kn.getThumbnail())) {
    			 kn.setThumbnail(SysConfig.nonePic);
    		 }
    	 }
    	 CommonUtil.judgeNewsTitleLength(newsThumbNailList,17);
    	 
    	 mv.addObject("downloadList",downloadList);
    	 mv.addObject("carouselList",carouselList);
    	 mv.addObject("newsList",newsList);
    	 mv.addObject("newsThumbNailList",newsThumbNailList);
    	 
//    	 List<KgAssessmentActivity> assessmentList = iKgAssessmentActivityService.select(requestContext, null, 1, 10);
//    	 CommonUtil.judgeAssessmentActivityTitleLength(assessmentList,22);
//    	 mv.addObject("assessmentList",assessmentList);
    	 //类型入口
    	 KgNewstype kne = new KgNewstype();
    	 kne.setShowentrance(true);
    	 KgNewstype knea = iKgNewstypeService.selectOne(requestContext, kne);
    	 mv.addObject("typeEntranceShow",knea);
    	 //公告通知
    	 KgNewstype kn = new KgNewstype();
    	 kn.setShowindex(true);
    	 KgNewstype knt = iKgNewstypeService.selectOne(requestContext, kn);
    	 if(knt!=null) {
    		 KgNews kns = new KgNews();
    		 kns.setTypeid(knt.getId());
    		 List<KgNews> indexShowTypeNews =iKgNewsService.select(requestContext, kns, 1, 5);
    		 knt.setNewsList(indexShowTypeNews);
    		 CommonUtil.judgeNewsTitleLength(indexShowTypeNews,17);
    	 }
    	 mv.addObject("typeNewsShow",knt);
    	 loadNavigation(mv, requestContext, CH_INDEX);
    	 loadSysConfig(mv);
         return mv;
    }
	
	@RequestMapping(value = "/index/loginPage")
    @ResponseBody
    public ModelAndView login(HttpServletRequest request) {
    	 ModelAndView mv = new ModelAndView(getViewPath() + "/index/login");
    	 IRequest requestContext = createRequestContext(request);
    	 loadNavigation(mv, requestContext, CH_INDEX);
    	 loadSysConfig(mv);
         return mv;
    }
	
	@RequestMapping(value = "/index/login")
	@ResponseBody
	public ModelAndView login(User dto, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView view = new ModelAndView(REDIRECT + "/");
		IRequest requestContext = createRequestContext(request);
		try {
			HttpSession session = request.getSession(false);
			// 有session 则不再登录
			if (session != null && session.getAttribute(User.FIELD_USER_ID) != null) {
				 ModelAndView mv = new ModelAndView(REDIRECT + "/");
				return mv;
			}
			
			User sel = new User();
			sel.setUserName(dto.getUserName());
			sel.setPasswordEncrypted( DigestUtils.md5Hex(dto.getPasswordEncrypted()));
			User user = userService.selectOne(requestContext, sel);
			if (user == null) {
				throw new Exception("用户名或者密码错误");
			} else {
				session = request.getSession();
				session.setAttribute(IRequest.FIELD_USER_ID, user.getUserId());
				session.setAttribute("userRealName", user.getRealName());
				setRoleInfo(request, session, user);
			}
		} catch (Exception e) {
			view.setViewName(getViewPath() + "/index/login");
			loadNavigation(view, requestContext, CH_NULL);
			loadSysConfig(view);
			view.addObject("errorInfo", e.getMessage());
		}
		return view;
	}
	
	private void setRoleInfo(HttpServletRequest request, HttpSession session, User user) throws Exception {
		UserRole ur = new UserRole();
		ur.setUserId(user.getUserId());
		List<Role> roles = roleService.adminQueryHave(RequestHelper.createServiceRequest(request), ur);
		if (roles.isEmpty()) {
			request.setAttribute("code", "NO_ROLE");
			throw new Exception("该用户不存在角色");
		}
		List<Long> roleIds = new ArrayList<Long>();
		for (Role role : roles) {
			roleIds.add(role.getRoleId());
		}
		Long[] ids = roleIds.toArray(new Long[roleIds.size()]);

		session.setAttribute(IRequest.FIELD_ALL_ROLE_ID, ids);
		session.setAttribute(IRequest.FIELD_ROLE_ID, roles.get(0).getRoleId());
	}
	
    
   
}
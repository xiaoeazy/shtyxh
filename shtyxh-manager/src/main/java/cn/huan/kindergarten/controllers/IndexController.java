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
import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.dto.KgType;
import cn.huan.kindergarten.service.IKgAssessmentActivityService;
import cn.huan.kindergarten.service.IKgCarouselService;
import cn.huan.kindergarten.service.IKgDownloadService;
import cn.huan.kindergarten.service.IKgNewsService;
import cn.huan.kindergarten.service.IKgTypeService;
import cn.huan.kindergarten.utils.CommonUtil;

@Controller
public class IndexController extends IndexBaseController{
	
	
	@Autowired
	private IKgDownloadService iKgDownloadService;
	@Autowired
	private IKgTypeService iKgTypeService;
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
    	//查询大轮播图和下载资料
    	 KgCarousel kc = new KgCarousel();
    	 kc.setSortorder("desc");
    	 kc.setSortname("sequence");
    	 
    	 KgDownload kl = new KgDownload();
    	 kl.setSortorder("desc");
    	 kl.setSortname("createdate");
    	 List<KgDownload> downloadList = iKgDownloadService.select(requestContext, kl, 1, 4);
    	 List<KgCarousel> carouselList =iKgCarouselService.select(requestContext, kc, 1, 5);
    	
    	 mv.addObject("downloadList",downloadList);
    	 mv.addObject("carouselList",carouselList);
    	 
    	 //类型入口
    	 KgType kne = new KgType();
    	 kne.setShowentrance(true);
    	 KgType knea = iKgTypeService.selectOne(requestContext, kne);
    	 mv.addObject("typeEntranceShow",knea);
    	 //协会动态
    	 KgType kn = new KgType();
    	 kn.setParentid(XHDT_ID);
    	 List<KgType> kntList = iKgTypeService.select(requestContext, kn);
		 for(KgType kt :kntList){
			 KgNews kns = new KgNews();
    		 kns.setTypeid(kt.getId());
    		 List<KgNews> indexShowTypeNews =iKgNewsService.selectWithOtherInfo(requestContext, kns, 1, 5);
    		 kt.setNewsList(indexShowTypeNews);
    		 CommonUtil.judgeNewsTitleLength(indexShowTypeNews,45);
		 }
    	 
    	 //资讯中心
    	 KgType kn3 = new KgType();
    	 kn3.setParentid(ZXZX_ID);
    	 List<KgType> knt3list = iKgTypeService.select(requestContext, kn3,1,3);
		 for(KgType kt:knt3list) {	
			 KgNews kns = new KgNews();
    		 kns.setTypeid(kt.getId());
    		 List<KgNews> indexShowTypeNews =iKgNewsService.selectWithOtherInfo(requestContext, kns, 1, 2);
    		 kt.setNewsList(indexShowTypeNews);
    		 CommonUtil.judgeNewsTitleLength(indexShowTypeNews,14);
		 }
    	 mv.addObject("typeZxzxShowList",knt3list);
    	 mv.addObject("typeXHDTShowList",kntList);
    	 
    	 //调查与研究
    	 KgType kn5 = new KgType();
    	 kn5.setParentid(DCYYJ_ID);
    	 kn5.setRelatetype(2);
    	 List<KgType> knt5list = iKgTypeService.select(requestContext, kn5,1,3);
    	 mv.addObject("typeDcyyjShowList",knt5list);
    	 
    	 //培训与鉴定
    	 KgType kn4 = new KgType();
    	 kn4.setParentid(PXYJD_ID);
//    	 kn4.setHidden(false);
    	 List<KgType> knt4list = iKgTypeService.select(requestContext, kn4);
    	 for(KgType kt:knt4list) {
    		 KgType childKt = new KgType();
    		 childKt.setParentid(kt.getId());
    		 List<KgType> childTypeList = iKgTypeService.select(requestContext, childKt);
    		 kt.setChildType(childTypeList);
		 }
    	 mv.addObject("typePxyjdShowList",knt4list);
    	 
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
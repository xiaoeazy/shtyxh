
package cn.huan.kindergarten.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.controllers.BaseController;

import cn.huan.kindergarten.bean.SysConfig;
import cn.huan.kindergarten.dto.KgConfig;
import cn.huan.kindergarten.dto.KgLink;
import cn.huan.kindergarten.dto.KgNewsSource;
import cn.huan.kindergarten.dto.KgType;
import cn.huan.kindergarten.service.IKgConfigService;
import cn.huan.kindergarten.service.IKgLinkService;
import cn.huan.kindergarten.service.IKgNewsSourceService;
import cn.huan.kindergarten.service.IKgTypeService;

/**
 * IndexBaseController.
 */
@RestController
public class IndexBaseController extends BaseController{
	// 默认的登录页
    public static final String VIEW_LOGIN = "/index/loginPage";
    
    public static final String  CH_NULL = "NULL";//无
	public static final String  CH_INDEX = "CH_INDEX";//首页
	public static final String  CH_XHGK = "CH_XHGK";//协会概况
	public static final String  CH_ZXZX = "CH_ZXZX";//资讯中心
	public static final String  CH_PXYJD = "CH_PXYJD";//培训与鉴定
	public static final String  CH_DCYYJ = "CH_DCYYJ";//调查与研究
	public static final String  CH_XHDT = "CH_XHDT";//协会动态
	public static final String  CH_DJHD = "CH_DJHD";//党建活动
	@Autowired
	private IKgTypeService iKgTypeService;
	@Autowired
	private IKgConfigService iKgConfigService;
	@Autowired
	private IKgNewsSourceService iKgNewsSourceService;
	@Autowired
	private IKgLinkService iKgLinkService;
	
	public void loadNavigation(ModelAndView mv,IRequest requestContext,String chanel  ) {
			KgType t2 = new KgType();
			t2.setParentid(2L);
			KgType t3 = new KgType();
			t3.setParentid(3L);
			List<KgType> xhgkTypeList = iKgTypeService.select(requestContext,t2);
	  	  	List<KgType> zxzxTypeList = iKgTypeService.select(requestContext,t3);
	        List<KgNewsSource> KgNewsSourceList = iKgNewsSourceService.selectAll(requestContext);
	        List<KgLink> linkList = iKgLinkService.selectAll(requestContext);
	        mv.addObject("xhgkTypeList", xhgkTypeList);
	        mv.addObject("zxzxTypeList", zxzxTypeList);
	        mv.addObject("KgNewsSourceList", KgNewsSourceList);
	        mv.addObject("chanel", chanel);
	        mv.addObject("linkList",linkList);
	        
	        List<KgConfig> kgConfigList= iKgConfigService.selectAll(requestContext);
	        for(KgConfig cf:kgConfigList) {
	      	  if(("copyright").equals(cf.getSyskey())) {
	      		  mv.addObject("copyright", cf.getSysvalue());continue;
	      	  }
	      	  if(("ICPlicense").equals(cf.getSyskey())) {
	      		  mv.addObject("ICPlicense", cf.getSysvalue());continue;
	      	  }
	      	  if(("keyword").equals(cf.getSyskey())) {
	      		  mv.addObject("keyword", cf.getSysvalue());continue;
	      	  }
	      	  if(("webdesc").equals(cf.getSyskey())) {
	      		  mv.addObject("webdesc", cf.getSysvalue());continue;
	      	  }
	        }
	  }
	
	public void loadSysConfig(ModelAndView mv) {
		mv.addObject("webname",SysConfig.webname);
		mv.addObject("copyright",SysConfig.copyright);
		mv.addObject("keyword",SysConfig.keyword);
		mv.addObject("webdesc",SysConfig.webdesc);
		mv.addObject("ICPlicense",SysConfig.ICPlicense);
		mv.addObject("webLogo",SysConfig.webLogo);
		mv.addObject("webIco",SysConfig.webIco);
		mv.addObject("ICPlicensePath",SysConfig.ICPlicensePath);
		mv.addObject("webIp",SysConfig.webIp);
		mv.addObject("wx",SysConfig.wx);
		mv.addObject("wb",SysConfig.wb);
		mv.addObject("tel",SysConfig.tel);
		
	}


}


package com.shtyxh.manager.index.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.controllers.BaseController;
import com.shtyxh.common.bean.CommonPath;
import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgAssessmentType;
import com.shtyxh.manager.dto.KgConfig;
import com.shtyxh.manager.dto.KgContact;
import com.shtyxh.manager.dto.KgLink;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgNewsAttribute;
import com.shtyxh.manager.dto.KgNewsSource;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgNewsAttributeService;
import com.shtyxh.manager.utils.CommonFuncUtil;

/**
 * IndexBaseController.
 */
@RestController
public class IndexBaseController extends BaseController{

//	// 默认的登录页
    public static final String VIEW_MAIN = CommonPath.INDEX_VIEW_MAIN;
    public static final String VIEW_LOGIN = CommonPath.INDEX_VIEW_LOGIN;
	// 跳转
    protected static final String REDIRECT = "redirect:";
    protected static final String DEFAULT_PAGE_SIZE_20 = "20";
    public static final String  CH_NULL = "NULL";//无
	public static final String  CH_INDEX = "CH_INDEX";//首页
	public static final String  CH_XHGK = "CH_XHGK";//协会概况
	public static final String  CH_ZXZX = "CH_ZXZX";//资讯中心
	public static final String  CH_PXYJD = "CH_PXYJD";//培训与鉴定
	public static final String  CH_DCYYJ = "CH_DCYYJ";//调查与研究
	public static final String  CH_XHDT = "CH_XHDT";//协会动态
	public static final String  CH_DJHD = "CH_DJHD";//党建活动
	
	public static final Long INDEX_ID = 1L;
	public static final Long XHGK_ID = 2L;
	public static final Long ZXZX_ID = 3L;
	public static final Long PXYJD_ID = 4L;
	public static final Long DCYYJ_ID = 5L;
	public static final Long XHDT_ID = 6L;
	public static final Long DJHD_ID = 7L;
	public static final Long OFFER_ID = 20L;  //招聘
	
	public static final Long DSPJJ_ID = 40L;  //短视频集锦
	
	public static final  Long LJLS_ID =11L; //历届理事
	public static final  Long LSYG_ID =12L; //历史沿革
	public static final  Long HYDW_ID =15L;	//会员单位
	public static final  Long LXFS_ID =16L;	//联系方式
	
	public static final  Long YSZP_ID =20L;	//园所招聘
	
	
	public static final  Long GWPX_ID =21L;	//岗位培训
	public static final  Long YJSGZ_ID =22L;	//幼教上岗证
	public static final  Long ZLPX_ID =23L;	//致立培训
	@Value("${FILE_FILESERVER}")
	private String FILE_FILESERVER;
	

	@Autowired
	private IJedisService iJedisService;
	@Autowired
	private  IKgNewsAttributeService  iKgNewsAttributeService;

	
	public void loadNavigation(ModelAndView mv,String chanel  ) {
			KgType t2 = new KgType();
			t2.setParentid(XHGK_ID);
			KgType t3 = new KgType();
			t3.setParentid(ZXZX_ID);
			KgType t5 = new KgType();
			t5.setParentid(DCYYJ_ID);
			KgType t6 = new KgType();
			t6.setParentid(XHDT_ID);
			KgType t7 = new KgType();
			t7.setParentid(DJHD_ID);
			
			KgType t8 = new KgType();
			t8.setParentid(GWPX_ID);
			
			List<KgType> xhgkTypeList = iJedisService.loadChildType(t2);
	  	  	List<KgType> zxzxTypeList = iJedisService.loadChildType(t3);
	  	  	List<KgType> dcyyjTypeList = iJedisService.loadChildType(t5);
  	  		List<KgType> xhdtTypeList = iJedisService.loadChildType(t6);
  	  		List<KgType> djhdTypeList = iJedisService.loadChildType(t7);
  	  		List<KgType> gwpxTypeList = iJedisService.loadChildType(t8);
  	  		
  	  		List<KgAssessmentType> zzjdList = iJedisService.loadAssessmentTypeAll(); //资质鉴定
	        List<KgNewsSource> KgNewsSourceList = iJedisService.loadNewsSource();
	        List<KgLink> linkList = iJedisService.loadLink();
	        
	        mv.addObject("gwpxTypeList", gwpxTypeList);
	        mv.addObject("xhgkTypeList", xhgkTypeList);
	        mv.addObject("zxzxTypeList", zxzxTypeList);
	        mv.addObject("dcyyjTypeList", dcyyjTypeList);
	        mv.addObject("xhdtTypeList", xhdtTypeList);
	        mv.addObject("djhdTypeList", djhdTypeList);
	        mv.addObject("KgNewsSourceList", KgNewsSourceList);
	        mv.addObject("zzjdList", zzjdList);			//资质鉴定
	        mv.addObject("chanel", chanel);
	        mv.addObject("linkList",linkList);
	        mv.addObject("fileserver",FILE_FILESERVER);
	        
//	        for(KgConfig cf:kgConfigList) {
//	      	  if(("copyright").equals(cf.getSyskey())) {
//	      		  mv.addObject("copyright", cf.getSysvalue());continue;
//	      	  }
//	      	  if(("ICPlicense").equals(cf.getSyskey())) {
//	      		  mv.addObject("ICPlicense", cf.getSysvalue());continue;
//	      	  }
//	      	  if(("keyword").equals(cf.getSyskey())) {
//	      		  mv.addObject("keyword", cf.getSysvalue());continue;
//	      	  }
//	      	  if(("webdesc").equals(cf.getSyskey())) {
//	      		  mv.addObject("webdesc", cf.getSysvalue());continue;
//	      	  }
//	        }
	  }
	
//	
	public void loadSysConfig(HttpServletRequest request,ModelAndView mv) {
//		mv.addObject("webname",SysConfig.webname);
//		mv.addObject("copyright",SysConfig.copyright);
//		mv.addObject("keyword",SysConfig.keyword);
//		mv.addObject("webdesc",SysConfig.webdesc);
//		mv.addObject("ICPlicense",SysConfig.ICPlicense);
//		mv.addObject("webLogo",SysConfig.webLogo);
//		mv.addObject("webIco",SysConfig.webIco);
//		mv.addObject("ICPlicensePath",SysConfig.ICPlicensePath);
//		mv.addObject("webIp",SysConfig.webIp);
//		mv.addObject("wx",SysConfig.wx);
//		mv.addObject("wb",SysConfig.wb);
//		mv.addObject("tel",SysConfig.tel);
		
		List<KgConfig> kgConfigList= iJedisService.loadConfig();
		long visitCount  = iJedisService.loadVisitCount();
		HttpSession session  = (HttpSession)request.getSession();
		if(session.getAttribute("counter")==null){
			 visitCount  =  iJedisService.inrVisitCount();
			 session.setAttribute("counter", 1);
		}
		mv.addObject("visitCount",visitCount);
		for(KgConfig kg :kgConfigList) {
			String value = kg.getSysvalue();
			switch(kg.getSyskey()) {
				case "webname":{
					mv.addObject("webname",value);
					break;
				}
				case "copyright":{
					mv.addObject("copyright",value);
					break;
				}
				case "keyword":{
					mv.addObject("keyword",value);
					break;
				}
				case "webdesc":{
					mv.addObject("webdesc",value);
					break;
				}
				case "webLogo":{
					mv.addObject("webLogo",value);
					break;
				}
				case "webIco":{
					mv.addObject("webIco",value);
					break;
				}
				case "ICPlicense":{
					mv.addObject("ICPlicense",value);
					break;
				}
				case "ICPlicensePath":{
					mv.addObject("ICPlicensePath",value);
					break;
				}
				case "webIp":{
					mv.addObject("webIp",value);
					break;
				}
				case "wx":{
					mv.addObject("wx",value);
					break;
				}
				case "wb":{
					mv.addObject("wb",value);
					break;
				}
				case "tj":{
					mv.addObject("tj",value);
					break;
				}
			}
		}
		 
		List<KgContact> contactList = iJedisService.loadContact();
		for(KgContact ct :contactList) {
			if(("tel").equals(ct.getSyskey())){
				mv.addObject("tel",ct.getSysvalue());
				break;
			}
     	}
		
	}

	public void loadAttriteNews(ModelAndView mv,  int attributeSize){
		//代理
		loadAttriteNews(mv,ZXZX_ID, attributeSize);
	}
	
	
	public void loadAttriteNews(ModelAndView mv,Long typeid,int attributeSize){
		//代理
		List<KgNewsAttribute> rightAttributeList =  iKgNewsAttributeService.select(null, null, 1, attributeSize);
		KgType kgType = new KgType();
		kgType.setParentid(typeid);
		List<KgType> types = iJedisService.loadChildType(kgType);
		List<Long> typeids = new ArrayList<Long>();
		for(KgType kt :types) {
			typeids.add(kt.getId());
		}
        for(KgNewsAttribute ka :rightAttributeList){
        	List<KgNews> newsList=iJedisService.load_NewsPage_Attribute_News( typeids,ka.getId());
        	CommonFuncUtil.judgeNewsTitleLength(newsList,22);
        	ka.setNewsList(newsList);
        }
        mv.addObject("rightAttributeList",rightAttributeList);
	}
	
	   //===================================other======================================
    
    
    public void loadAttriteAssessmentActivity(ModelAndView mv,IRequest requestContext,int attributeSize) {
	 	List<KgNewsAttribute> rightAttributeList =  iKgNewsAttributeService.select(requestContext, null, 1, attributeSize);
        for(KgNewsAttribute ka :rightAttributeList){
        	List<KgAssessmentActivity> assessmentActivityList=iJedisService.load_AssessmentPage_Attribute_News(ka.getId());
        	CommonFuncUtil.judgeAssessmentActivityTitleLength(assessmentActivityList,22);
        	ka.setAssessmentActivityList(assessmentActivityList);
        }
        mv.addObject("rightAttributeList",rightAttributeList);
    }
	
	
//	
//	public void loadNavigation(ModelAndView mv,IRequest requestContext,String chanel  ) {
//			KgType t2 = new KgType();
//			t2.setParentid(XHGK_ID);
//			t2.setHidden(false);
//			KgType t3 = new KgType();
//			t3.setParentid(ZXZX_ID);
//			KgType t5 = new KgType();
//			t5.setParentid(DCYYJ_ID);
//			KgType t6 = new KgType();
//			t6.setParentid(XHDT_ID);
//			KgType t7 = new KgType();
//			t7.setParentid(DJHD_ID);
//			
//			List<KgType> xhgkTypeList = iKgTypeService.select(requestContext,t2);
//	  	  	List<KgType> zxzxTypeList = iKgTypeService.select(requestContext,t3);
//	  	  	List<KgType> dcyyjTypeList = iKgTypeService.select(requestContext,t5);
//  	  		List<KgType> xhdtTypeList = iKgTypeService.select(requestContext,t6);
//  	  		List<KgType> djhdTypeList = iKgTypeService.select(requestContext,t7);
//  	  		List<KgAssessmentType> zzjdList = iKgAssessmentTypeService.select(requestContext, null); //资质鉴定
//	        List<KgNewsSource> KgNewsSourceList = iKgNewsSourceService.selectAll(requestContext);
//	        List<KgLink> linkList = iKgLinkService.selectAll(requestContext);
//	        mv.addObject("xhgkTypeList", xhgkTypeList);
//	        mv.addObject("zxzxTypeList", zxzxTypeList);
//	        mv.addObject("dcyyjTypeList", dcyyjTypeList);
//	        mv.addObject("xhdtTypeList", xhdtTypeList);
//	        mv.addObject("djhdTypeList", djhdTypeList);
//	        mv.addObject("KgNewsSourceList", KgNewsSourceList);
//	        mv.addObject("zzjdList", zzjdList);			//资质鉴定
//	        mv.addObject("chanel", chanel);
//	        mv.addObject("linkList",linkList);
//	        
//	        List<KgConfig> kgConfigList= iKgConfigService.selectAll(requestContext);
//	        for(KgConfig cf:kgConfigList) {
//	      	  if(("copyright").equals(cf.getSyskey())) {
//	      		  mv.addObject("copyright", cf.getSysvalue());continue;
//	      	  }
//	      	  if(("ICPlicense").equals(cf.getSyskey())) {
//	      		  mv.addObject("ICPlicense", cf.getSysvalue());continue;
//	      	  }
//	      	  if(("keyword").equals(cf.getSyskey())) {
//	      		  mv.addObject("keyword", cf.getSysvalue());continue;
//	      	  }
//	      	  if(("webdesc").equals(cf.getSyskey())) {
//	      		  mv.addObject("webdesc", cf.getSysvalue());continue;
//	      	  }
//	        }
//	  }
//	
//	public void loadSysConfig(ModelAndView mv) {
//		mv.addObject("webname",SysConfig.webname);
//		mv.addObject("copyright",SysConfig.copyright);
//		mv.addObject("keyword",SysConfig.keyword);
//		mv.addObject("webdesc",SysConfig.webdesc);
//		mv.addObject("ICPlicense",SysConfig.ICPlicense);
//		mv.addObject("webLogo",SysConfig.webLogo);
//		mv.addObject("webIco",SysConfig.webIco);
//		mv.addObject("ICPlicensePath",SysConfig.ICPlicensePath);
//		mv.addObject("webIp",SysConfig.webIp);
//		mv.addObject("wx",SysConfig.wx);
//		mv.addObject("wb",SysConfig.wb);
//		mv.addObject("tel",SysConfig.tel);
//		
//	}

}

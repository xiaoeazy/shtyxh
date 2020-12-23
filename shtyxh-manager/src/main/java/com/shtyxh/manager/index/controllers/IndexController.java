package com.shtyxh.manager.index.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.shtyxh.manager.dto.KgBaywindow;
import com.shtyxh.manager.dto.KgCarousel;
import com.shtyxh.manager.dto.KgDownload;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgNotice;
import com.shtyxh.manager.dto.KgOffersType;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.dto.KgVideoType;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgBaywindowService;
import com.shtyxh.manager.service.IKgTypeService;
import com.shtyxh.manager.utils.CommonFuncUtil;

@Controller
public class IndexController extends IndexBaseController {


	@Autowired
	private IKgTypeService iKgTypeService;
	@Autowired
	private IKgBaywindowService iKgBaywindowService;
	@Autowired
	private IJedisService iJedisService;
	
	
	@RequestMapping(value = "/")
	@ResponseBody
	public ModelAndView index(HttpServletRequest request) {
		
		IRequest requestContext = createRequestContext(request);
		ModelAndView mv = new ModelAndView("/index/index");
		// IRequest requestContext = createRequestContext(request);
		// 查询大轮播图和下载资料
		List<KgDownload> downloadList = iJedisService.loadDownload();
		List<KgDownload> noPasswordDownloadList = new ArrayList<KgDownload>();
		for(KgDownload kl:downloadList) {
			if(StringUtils.isEmpty(kl.getPassword())) {
				noPasswordDownloadList.add(kl);
				if(noPasswordDownloadList.size()==4)
					break;
			}
		}
//		downloadList = CommonFuncUtil.listToList(downloadList, 4);
		List<KgCarousel> carouselList = iJedisService.loadCarousel();
		carouselList = CommonFuncUtil.listToList(carouselList, 5);
		mv.addObject("downloadList", noPasswordDownloadList);
		mv.addObject("carouselList", carouselList);

		// 协会动态
		KgType kn = new KgType();
		kn.setParentid(XHDT_ID);
		List<KgType> kntList = iJedisService.loadChildType(kn);

		for (KgType kt : kntList) {
			KgNews kns = new KgNews();
			kns.setTypeid(kt.getId());
			List<KgNews> indexShowTypeNews = iJedisService.loadTypeNews(kns);
			indexShowTypeNews = CommonFuncUtil.listToList(indexShowTypeNews, 5);
			kt.setNewsList(indexShowTypeNews);
			CommonFuncUtil.judgeNewsTitleLength(indexShowTypeNews, 40);
		}

		// 资讯中心
		KgType kn3 = new KgType();
		kn3.setParentid(ZXZX_ID);
		List<KgType> knt3list = iJedisService.loadChildType(kn3);
		knt3list = CommonFuncUtil.listToList(knt3list, 99);
		for (KgType kt : knt3list) {
			if (kt.getId() == OFFER_ID) {
//				List<KgOffers> list = iJedisService.loadOffers();
//				list = CommonFuncUtil.listToList(list, 2);
//				kt.setOffersList(list);
//				continue;
			}

			KgNews kns = new KgNews();
			kns.setTypeid(kt.getId());
			List<KgNews> indexShowTypeNews = iJedisService.loadTypeNews(kns);
			indexShowTypeNews = CommonFuncUtil.listToList(indexShowTypeNews, 2);
			kt.setNewsList(indexShowTypeNews);
			CommonFuncUtil.judgeNewsTitleLength(indexShowTypeNews, 12);
		}
		mv.addObject("typeZxzxShowList", knt3list);
		mv.addObject("typeXHDTShowList", kntList);

		//
		// 调查与研究
		KgType kn5 = new KgType();
		kn5.setParentid(DCYYJ_ID);
		kn5.setRelatetype(2);
		List<KgType> knt5list = iKgTypeService.select(requestContext, kn5, 1, 2);
		mv.addObject("typeDcyyjShowList", knt5list);

	
		//招聘 放到鉴定与培训页面中去
		KgType kn6 = iJedisService.loadType(new KgType(OFFER_ID));
		List<KgOffersType> childOfferTypeList = iJedisService.loadAllOffersType();
		kn6.setOfferTypeList(childOfferTypeList);
//		List<KgType> childOfferTypeList = new ArrayList<KgType>();
//		KgType teacherType = new KgType();
//		teacherType.setTypename("幼儿园教师");
//		teacherType.setId(1l);
//		childOfferTypeList.add(teacherType);
//		KgType threeYuanType = new KgType();
//		threeYuanType.setTypename("三大员");
//		threeYuanType.setId(-2l);
//		childOfferTypeList.add(threeYuanType);
//		kn6.setChildType(childOfferTypeList);
		mv.addObject("offerType", kn6);
		
		//短视频集锦
		KgType kn7 = iJedisService.loadType(new KgType(DSPJJ_ID));
		List<KgVideoType> videoTypeList = iJedisService.loadAllVideoType();
		kn7.setVideoTypeList(videoTypeList);
		mv.addObject("dspjjType", kn7);
		// 培训与鉴定
		KgType kn4 = new KgType();
		kn4.setParentid(PXYJD_ID);
		// kn4.setHidden(false);
		List<KgType> knt4list = iJedisService.loadChildType(kn4);
		for (KgType kt : knt4list) {
			KgType childKt = new KgType();
			childKt.setParentid(kt.getId());
			List<KgType> childTypeList = iJedisService.loadChildType(childKt);
			kt.setChildType(childTypeList);
		}
		mv.addObject("typePxyjdShowList", knt4list);
		
		
		
		//显示飘窗
		KgBaywindow kw = new KgBaywindow();
		kw.setIndexshow("Y");
		List<KgBaywindow> list = iKgBaywindowService.select(requestContext, kw);
		if(list.size()!=0){
			mv.addObject("baywindow", list.get(0));
		}
		loadNavigation(mv, CH_INDEX);
		loadSysConfig(request,mv);
		return mv;
	}


	@RequestMapping(value = "/ttview")
	public ModelAndView ttview(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/index/ttview");
		List<KgNotice> carouselList = iJedisService.loadNotice();
		if(carouselList.size()==0) {
			throw new RuntimeException("发生异常！");
		}
		KgNotice kn = carouselList.get(0);
		mv.addObject("kn",kn);
		loadSysConfig(request,mv);
		return mv;
		
	}


}
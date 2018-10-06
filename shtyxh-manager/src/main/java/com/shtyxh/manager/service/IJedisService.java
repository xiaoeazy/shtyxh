package com.shtyxh.manager.service;

import java.util.List;

import com.shtyxh.manager.dto.KgAllonetext;
import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgAssessmentType;
import com.shtyxh.manager.dto.KgCarousel;
import com.shtyxh.manager.dto.KgConfig;
import com.shtyxh.manager.dto.KgContact;
import com.shtyxh.manager.dto.KgDownload;
import com.shtyxh.manager.dto.KgHistory;
import com.shtyxh.manager.dto.KgLink;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgNewsSource;
import com.shtyxh.manager.dto.KgOffers;
import com.shtyxh.manager.dto.KgType;

public interface IJedisService {

	List<KgConfig> loadConfig();

	List<KgLink> loadLink();

	List<KgNewsSource> loadNewsSource();
	
	KgNewsSource loadKgNewsSource(KgNewsSource newsSource);

	List<KgType> loadChildType(KgType type);
	
	KgType loadType(KgType type);

	List<KgAssessmentType> loadAssessmentTypeAll();

	KgNews loadNews(KgNews news);

	List<KgCarousel> loadCarousel();

	List<KgDownload> loadDownload();

	List<KgNews> loadTypeNews(KgNews news);


	

	KgAllonetext loadAllonetext(KgAllonetext at);

	List<KgContact> loadContact();

	List<KgHistory> loadHistory();

	KgNews loadNewsPage_AttributeId4_News(KgNews kn, List<Long> typeidList, Integer page, Integer pageSize);

	List<KgNews> load_NewsPage_Attribute_News(List<Long> typeids, Long attributeId);

	KgOffers loadOffer(KgOffers kgOffers);
	
	List<KgOffers> loadOffers();

	KgNews loadJDYPXPage_AttributeId4_News(KgNews kn, List<Long> typeidList, Integer page, Integer pageSize);

	List<KgAssessmentActivity> loadAssessmentActivityOfType(KgAssessmentActivity activitys);

	KgAssessmentType loadAssessmentType(KgAssessmentType kt);

	KgAssessmentActivity loadAssessmentActivity(KgAssessmentActivity activitys);

	List<KgNews> loadNewsDynamicsThumbnail(KgNews ThumbNews, List<Long> typeidList,Integer page,Integer limit);

	List<KgNews> loadNewsDynamicsTopNewsList(KgNews ThumbNews, List<Long> typeidList, Integer page, Integer limit);

	
	
	//====删除====
	void delHSetOfType();


	void delHSetOfAllOneText();


	void delStringOfHistory();

	void delStringOfContact();

	void delNews();

	void delHsetOfNewsSource();

	void delHsetOfOffer();

	void delStringOfdownload();

	void delStringOfCarousel();

	void delStringOfLink();

	void delStringOfConfig();

	void delAttribute();

	void delHsetOfAssessmentActivity();

	void delHsetOfAssessmentType();


	
	
}
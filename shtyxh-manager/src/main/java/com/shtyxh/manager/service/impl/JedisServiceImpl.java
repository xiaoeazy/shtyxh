package com.shtyxh.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shtyxh.common.utils.JsonUtils;
import com.shtyxh.manager.bean.SysConfig;
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
import com.shtyxh.manager.dto.KgOffersType;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgAllonetextService;
import com.shtyxh.manager.service.IKgAssessmentActivityService;
import com.shtyxh.manager.service.IKgAssessmentTypeService;
import com.shtyxh.manager.service.IKgCarouselService;
import com.shtyxh.manager.service.IKgConfigService;
import com.shtyxh.manager.service.IKgContactService;
import com.shtyxh.manager.service.IKgDownloadService;
import com.shtyxh.manager.service.IKgHistoryService;
import com.shtyxh.manager.service.IKgLinkService;
import com.shtyxh.manager.service.IKgNewsService;
import com.shtyxh.manager.service.IKgNewsSourceService;
import com.shtyxh.manager.service.IKgOffersService;
import com.shtyxh.manager.service.IKgOffersTypeService;
import com.shtyxh.manager.service.IKgTypeService;
import com.shtyxh.redis.service.JedisClient;

@Service
public class JedisServiceImpl  implements IJedisService{
	  /** 日志记录. **/
    private static Logger logger = LoggerFactory.getLogger(JedisServiceImpl.class); 
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private IKgConfigService iKgConfigService;
	@Autowired
	private IKgLinkService iKgLinkService;
	@Autowired
	private IKgNewsSourceService iKgNewsSourceService;
	@Autowired
	private IKgTypeService iKgTypeService;
	@Autowired
	private IKgNewsService iKgNewsService;
	@Autowired
	private IKgAssessmentActivityService iKgAssessmentActivityService;
	@Autowired
	private IKgAssessmentTypeService iKgAssessmentTypeService;
	@Autowired
	private IKgCarouselService iKgCarouselService;
	@Autowired
	private IKgDownloadService iKgDownloadService;
	@Autowired
	private IKgOffersService iKgOffersService;
	@Autowired
	private IKgAllonetextService iKgAllonetextService;
	@Autowired
	private IKgContactService iKgContactService;
	@Autowired
	private IKgHistoryService iKgHistoryService;
	@Autowired
	private IKgOffersTypeService iKgOffersTypeService;
	
	@Value("${REDIS_NEWSSOURCE_HSET}")
	private String REDIS_NEWSSOURCE_HSET;
	@Value("${REDIS_NEWSSOURCE_ALL_NEWSSOURCE_LIST}")
	private String REDIS_NEWSSOURCE_ALL_NEWSSOURCE_LIST;
	@Value("${REDIS_NEWSSOURCE_NEWSSOURCE}")
	private String REDIS_NEWSSOURCE_NEWSSOURCE;
	
	
	@Value("${REDIS_TYPE_HSET}")
	private String REDIS_TYPE_HSET;
	@Value("${REDIS_TYPE_ALL_TYPE_LIST}")
	private String REDIS_TYPE_ALL_TYPE_LIST;
	@Value("${REDIS_TYPE_CHILD_LIST}")
	private String REDIS_TYPE_CHILD_LIST;
	@Value("${REDIS_TYPE_TYPE}")
	private String REDIS_TYPE_TYPE;
	
	
	@Value("${REDIS_ASSESSMENTTYPE_HSET}")
	private String REDIS_ASSESSMENTTYPE_HSET;
	@Value("${REDIS_ASSESSMENTTYPE_ALL_ASSESSMENTTYPE_LIST}")
	private String REDIS_ASSESSMENTTYPE_ALL_ASSESSMENTTYPE_LIST;
	@Value("${REDIS_ASSESSMENTTYPE}")
	private String REDIS_ASSESSMENTTYPE;
	
	
	
	
	
	
	
	@Value("${REDIS_NEWS_HSET}")
	private String REDIS_NEWS_HSET;
	@Value("${REDIS_NEWS_NEWS}")
	private String REDIS_NEWS_NEWS;
	@Value("${REDIS_TYPE_NEWS_WITHOTHERINFO_LIST}")
	private String REDIS_TYPE_NEWS_WITHOTHERINFO_LIST;
	@Value("${REDIS_NEWSPAGE_ATTRIBUTEID_NEWS_LIST}")
	private String REDIS_NEWSPAGE_ATTRIBUTEID_NEWS_LIST;
	
	@Value("${REDIS_CAROUSEL_LIST_STRING}")
	private String REDIS_CAROUSEL_LIST_STRING;
	
	@Value("${REDIS_DOWNLOAD_LIST_STRING}")
	private String REDIS_DOWNLOAD_LIST_STRING;
	
	
	@Value("${REDIS_CONFIG_STRING}")
	private String REDIS_CONFIG_STRING;
	@Value("${REDIS_CONTACT_STRING}")
	private String REDIS_CONTACT_STRING;
	@Value("${REDIS_LINK_LIST_STRING}")
	private String REDIS_LINK_LIST_STRING;
	@Value("${REDIS_HISTORY_LIST_STRING}")
	private String REDIS_HISTORY_LIST_STRING;
	
	@Value("${REDIS_ALLONETEXT_HSET}")
	private String REDIS_ALLONETEXT_HSET;
	@Value("${REDIS_ALLONETEXT}")
	private String REDIS_ALLONETEXT;
	
	@Value("${REDIS_NEWSPAGE_ATTRIBUTEID4_NEWS_STRING}")
	private String REDIS_NEWSPAGE_ATTRIBUTEID4_NEWS_STRING;
	@Value("${REDIS_JDYPXPAGE_ATTRIBUTEID4_NEWS_STRING}")
	private String REDIS_JDYPXPAGE_ATTRIBUTEID4_NEWS_STRING;
	
	
	
	@Value("${REDIS_NEWSDYNAMICSPAGE}")
	private String REDIS_NEWSDYNAMICSPAGE;
	@Value("${REDIS_NEWSDYNAMICSPAGE_THUMBNAIL_NEWS_LIST}")
	private String REDIS_NEWSDYNAMICSPAGE_THUMBNAIL_NEWS_LIST;
	@Value("${REDIS_NEWSDYNAMICSPAGE_TOPNEWS_LIST}")
	private String REDIS_NEWSDYNAMICSPAGE_TOPNEWS_LIST;
	
	
	
	@Value("${REDIS_OFFERS_HSET}")
	private String REDIS_OFFERS_HSET;
	@Value("${REDIS_OFFERS}")
	private String REDIS_OFFERS;
	@Value("${REDIS_OFFERS_ALL_LIST}")
	private String REDIS_OFFERS_ALL_LIST;
	@Value("${REDIS_OFFERS_TYPE_ALL_LIST}")
	private String REDIS_OFFERS_TYPE_ALL_LIST;
	
	
	
	@Value("${REDIS_ASSESSMENTACTIVITY_HSET}")
	private String REDIS_ASSESSMENTACTIVITY_HSET;
	@Value("${REDIS_ASSESSMENTTYPE_ASSESSMENTACTIVITY}")
	private String REDIS_ASSESSMENTTYPE_ASSESSMENTACTIVITY;
	@Value("${REDIS_ASSESSMENTACTIVITY}")
	private String REDIS_ASSESSMENTACTIVITY;
	@Value("${REDIS_ASSESSMENTACTIVITYPAGE_ATTRIBUTEID_NEWS_LIST}")
	private String REDIS_ASSESSMENTACTIVITYPAGE_ATTRIBUTEID_NEWS_LIST;
	
	
	@Override
	public void delAttribute(){
		jedisClient.hdel(REDIS_NEWS_HSET,REDIS_NEWSPAGE_ATTRIBUTEID_NEWS_LIST);
		jedisClient.del(REDIS_NEWSPAGE_ATTRIBUTEID4_NEWS_STRING);
		jedisClient.del(REDIS_JDYPXPAGE_ATTRIBUTEID4_NEWS_STRING);
		
	}
	
	@Override
	public void delHsetOfAssessmentType(){
		jedisClient.del(REDIS_ASSESSMENTTYPE_HSET);
	}
	
	@Override
	public void delHsetOfAssessmentActivity(){
		jedisClient.del(REDIS_ASSESSMENTACTIVITY_HSET);
	}
	
	@Override
	public void delStringOfConfig(){
		jedisClient.del(REDIS_CONFIG_STRING);
	}
	
	@Override
	public void delStringOfLink(){
		jedisClient.del(REDIS_LINK_LIST_STRING);
	}
	
	@Override
	public void delStringOfCarousel(){
		jedisClient.del(REDIS_CAROUSEL_LIST_STRING);
	}
	
	@Override
	public void delStringOfdownload(){
		jedisClient.del(REDIS_DOWNLOAD_LIST_STRING);
	}
	@Override
	public void delHsetOfOffer(){
		jedisClient.del(REDIS_OFFERS_HSET);
	}
	@Override
	public void delHsetOfNewsSource(){
		jedisClient.del(REDIS_NEWSSOURCE_HSET);
	}
	@Override
	public void delNews(){
		jedisClient.del(REDIS_NEWS_HSET);
		jedisClient.del(REDIS_NEWSPAGE_ATTRIBUTEID4_NEWS_STRING);
		jedisClient.del(REDIS_JDYPXPAGE_ATTRIBUTEID4_NEWS_STRING);
		jedisClient.del(REDIS_NEWSDYNAMICSPAGE);
	}
	
	@Override
	public void delStringOfContact(){
		jedisClient.del(REDIS_CONTACT_STRING);
	}
	
	@Override
	public void delHSetOfAllOneText(){
		jedisClient.del(REDIS_ALLONETEXT_HSET);
	}
	
	@Override
	public void delStringOfHistory(){
		jedisClient.del(REDIS_HISTORY_LIST_STRING);
	}
	
	@Override
	public void delHSetOfType(){
		jedisClient.del(REDIS_TYPE_HSET);
	}
	
	@Override
	public   List<KgNews> load_NewsPage_Attribute_News(List<Long> typeids,Long attributeId){
		List<KgNews> list = new ArrayList<KgNews>();
		try {
			String json = jedisClient.hget(REDIS_NEWS_HSET,REDIS_NEWSPAGE_ATTRIBUTEID_NEWS_LIST+":"+attributeId);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				KgNews kn = new KgNews();
	        	kn.setAttributeid(attributeId+"");
				list = iKgNewsService.selectByMap( null,kn,typeids, 1, 5);
				jedisClient.hset(REDIS_NEWS_HSET,REDIS_NEWSPAGE_ATTRIBUTEID_NEWS_LIST+":"+attributeId, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgNews.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("load_NewsPage_Attribute_News失败:",e);
		}
		return list;
	}
	
	@Override
	public   List<KgAssessmentActivity> load_AssessmentPage_Attribute_News(Long attributeId){
		List<KgAssessmentActivity> list = new ArrayList<KgAssessmentActivity>();
		try {
			String json = jedisClient.hget(REDIS_ASSESSMENTACTIVITY_HSET,REDIS_ASSESSMENTACTIVITYPAGE_ATTRIBUTEID_NEWS_LIST+":"+attributeId);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				KgAssessmentActivity kn = new KgAssessmentActivity();
	        	kn.setAttributeid( attributeId+"");
	        	kn.setSortname("createdate");
	        	kn.setSortorder("desc");
				list = iKgAssessmentActivityService.selectWithOtherInfo(null, kn, 1, 5);
				jedisClient.hset(REDIS_ASSESSMENTACTIVITY_HSET,REDIS_ASSESSMENTACTIVITYPAGE_ATTRIBUTEID_NEWS_LIST+":"+attributeId, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgAssessmentActivity.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("load_AssessmentPage_Attribute_News失败:",e);
		}
		return list;
	}
	
	
	@Override
	public   List<KgHistory> loadHistory(){
		List<KgHistory> list = new ArrayList<KgHistory>();
		try {
			String json = jedisClient.get(REDIS_HISTORY_LIST_STRING);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				list = iKgHistoryService.selectAll();
				jedisClient.set(REDIS_HISTORY_LIST_STRING, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgHistory.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadHistory失败:",e);
		}
		return list;
	}
	@Override
	public   List<KgContact> loadContact(){
		List<KgContact> list = new ArrayList<KgContact>();
		try {
			String json = jedisClient.get(REDIS_CONTACT_STRING);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				list = iKgContactService.selectAll();
				jedisClient.set(REDIS_CONTACT_STRING, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgContact.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadContact失败:",e);
		}
		return list;
	}
	
	@Override
	public   List<KgOffers> loadOffers(){
		List<KgOffers> list = new ArrayList<KgOffers>();
		try {
			String json = jedisClient.hget(REDIS_OFFERS_HSET,REDIS_OFFERS_ALL_LIST);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				list = iKgOffersService.selectWithOtherInfo(null,null,null,null);
				jedisClient.hset(REDIS_OFFERS_HSET,REDIS_OFFERS_ALL_LIST, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgOffers.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadOffers失败:",e);
		}
		return list;
	}
	@Override
	public List<KgOffersType> loadAllOffersType(){
		List<KgOffersType> list = new ArrayList<KgOffersType>();
		try {
			String json = jedisClient.hget(REDIS_OFFERS_HSET,REDIS_OFFERS_TYPE_ALL_LIST);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				list = iKgOffersTypeService.selectAll();
				jedisClient.hset(REDIS_OFFERS_HSET,REDIS_OFFERS_TYPE_ALL_LIST, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgOffersType.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadAllOffersType失败:",e);
		}
		return list;
	}
	
	@Override
	public   KgOffers loadOffer(KgOffers kgOffers){
		KgOffers bean  = null;
		try {
			String json = jedisClient.hget(REDIS_OFFERS_HSET,REDIS_OFFERS+":"+kgOffers.getId());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				bean = iKgOffersService.selectByPrimaryKey(null,kgOffers);
				jedisClient.hset(REDIS_OFFERS_HSET,REDIS_OFFERS+":"+kgOffers.getId(), JsonUtils.objectToJson(bean));
			}else{
				bean = JsonUtils.jsonToPojo(json, KgOffers.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadOffers失败:",e);
		}
		return bean;
	}
	
	
	
	@Override
	public   List<KgConfig> loadConfig(){
		List<KgConfig> list = new ArrayList<KgConfig>();
		try {
			String config_json = jedisClient.get(REDIS_CONFIG_STRING);
			//判断是否为空
			if (StringUtils.isBlank(config_json)) {
				list = iKgConfigService.selectAll();
				jedisClient.set(REDIS_CONFIG_STRING, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(config_json, KgConfig.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadConfig失败:",e);
		}
		return list;
	}
	
	@Override
	public   List<KgCarousel> loadCarousel(){
		List<KgCarousel> list = new ArrayList<KgCarousel>();
		try {
			
			String json = jedisClient.get(REDIS_CAROUSEL_LIST_STRING);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				 KgCarousel kc = new KgCarousel();
		    	 kc.setSortorder("desc");
		    	 kc.setSortname("sequence");
				 list = iKgCarouselService.select(null,kc);
				 jedisClient.set(REDIS_CAROUSEL_LIST_STRING, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgCarousel.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadCarousel失败:",e);
		}
		return list;
	}
	
	@Override
	public   List<KgDownload> loadDownload(){
		List<KgDownload> list = new ArrayList<KgDownload>();
		try {
			
			String json = jedisClient.get(REDIS_DOWNLOAD_LIST_STRING);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				 KgDownload kl = new KgDownload();
		    	 kl.setSortorder("desc");
		    	 kl.setSortname("createdate");
				 list = iKgDownloadService.select(null,kl);
				 jedisClient.set(REDIS_DOWNLOAD_LIST_STRING, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgDownload.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadDownload失败:",e);
		}
		return list;
	}
	
	
	@Override
	public   List<KgLink> loadLink(){
		List<KgLink> list = new ArrayList<KgLink>();
		try {
			String json = jedisClient.get(REDIS_LINK_LIST_STRING);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				list = iKgLinkService.selectAll();
				jedisClient.set(REDIS_LINK_LIST_STRING, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgLink.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadLink失败:",e);
		}
		return list;
	}
	
	@Override
	public   List<KgNewsSource> loadNewsSource(){
		List<KgNewsSource> list = new ArrayList<KgNewsSource>();
		try {
			String json = jedisClient.hget(REDIS_NEWSSOURCE_HSET, REDIS_NEWSSOURCE_ALL_NEWSSOURCE_LIST);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				list = iKgNewsSourceService.selectAll();
				jedisClient.hset(REDIS_NEWSSOURCE_HSET,REDIS_NEWSSOURCE_ALL_NEWSSOURCE_LIST, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgNewsSource.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadNewsSource失败:",e);
		}
		return list;
	}
	
	
	
	
	@Override
	public   KgNews loadJDYPXPage_AttributeId4_News(KgNews kn, List<Long> typeidList ,Integer page, Integer pageSize){
		KgNews news= null;
		try {
			String json = jedisClient.get(REDIS_JDYPXPAGE_ATTRIBUTEID4_NEWS_STRING);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				List<KgNews> newsTop=iKgNewsService.selectByMap(null, kn,typeidList, 1, 1);
				if(newsTop.size()!=0) {
		   	   		 if(("").equals(newsTop.get(0).getThumbnail())) {
		   	   			 newsTop.get(0).setThumbnail(SysConfig.nonePic);
		   	   		 }
		    	}else{
		    		
		    	}
				if(newsTop.size()==0){
					jedisClient.set(REDIS_JDYPXPAGE_ATTRIBUTEID4_NEWS_STRING, JsonUtils.objectToJson(null));
				}else{
					jedisClient.set(REDIS_JDYPXPAGE_ATTRIBUTEID4_NEWS_STRING, JsonUtils.objectToJson(newsTop.get(0)));
				}
				
			}else{
				news = JsonUtils.jsonToPojo(json, KgNews.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadJDYPXPage_AttributeId4_News失败:",e);
		}
		return news;
	}
	
	@Override
	public   KgNews loadNewsPage_AttributeId4_News(KgNews kn, List<Long> typeidList ,Integer page, Integer pageSize){
		KgNews news= null;
		try {
			String json = jedisClient.get(REDIS_NEWSPAGE_ATTRIBUTEID4_NEWS_STRING);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				List<KgNews> newsTop=iKgNewsService.selectByMap( null,kn,typeidList, 1, 1);
				if(newsTop.size()!=0) {
		   	   		 if(("").equals(newsTop.get(0).getThumbnail())) {
		   	   			 newsTop.get(0).setThumbnail(SysConfig.nonePic);
		   	   		 }
		    	}else{
		    		
		    	}
				if(newsTop.size()==0){
					jedisClient.set(REDIS_NEWSPAGE_ATTRIBUTEID4_NEWS_STRING, JsonUtils.objectToJson(null));
				}else{
					jedisClient.set(REDIS_NEWSPAGE_ATTRIBUTEID4_NEWS_STRING, JsonUtils.objectToJson(newsTop.get(0)));
				}
				
			}else{
				news = JsonUtils.jsonToPojo(json, KgNews.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadNewsPage_AttributeId4_News失败:",e);
		}
		return news;
	}
	
	@Override
	public   KgNewsSource loadKgNewsSource(KgNewsSource newsSource){
		KgNewsSource kgNewsSource= null;
		try {
			String json = jedisClient.hget(REDIS_NEWSSOURCE_HSET, REDIS_NEWSSOURCE_NEWSSOURCE+":"+newsSource.getId());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				kgNewsSource = iKgNewsSourceService.selectByPrimaryKey(null,newsSource);
				jedisClient.hset(REDIS_NEWSSOURCE_HSET,REDIS_NEWSSOURCE_NEWSSOURCE+":"+newsSource.getId(), JsonUtils.objectToJson(kgNewsSource));
			}else{
				kgNewsSource = JsonUtils.jsonToPojo(json, KgNewsSource.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadKgNewsSource失败:",e);
		}
		return kgNewsSource;
	}
	
	@Override
	public   List<KgType> loadChildType(KgType type){
		List<KgType> list = new ArrayList<KgType>();
		try {
			String json = jedisClient.hget(REDIS_TYPE_HSET, REDIS_TYPE_CHILD_LIST+":"+type.getParentid());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				type.setSortname("sequence");
				type.setSortorder("desc");
				type.setHidden(false);
				list = iKgTypeService.select(null,type);
				jedisClient.hset(REDIS_TYPE_HSET,REDIS_TYPE_CHILD_LIST+":"+type.getParentid(), JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgType.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadChildType失败:",e);
		}
		return list;
	}

	@Override
	public   KgType loadType(KgType type){
		KgType KgType= null;
		try {
			String json = jedisClient.hget(REDIS_TYPE_HSET, REDIS_TYPE_TYPE+":"+type.getId());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				KgType = iKgTypeService.selectByPrimaryKey(null,type);
				jedisClient.hset(REDIS_TYPE_HSET,REDIS_TYPE_TYPE+":"+type.getId(), JsonUtils.objectToJson(KgType));
			}else{
				KgType = JsonUtils.jsonToPojo(json, KgType.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadType失败:",e);
		}
		return KgType;
	}
	
	
	@Override
	public   List<KgAssessmentType> loadAssessmentTypeAll(){
		List<KgAssessmentType> list = new ArrayList<KgAssessmentType>();
		try {
			String json = "";
				json=jedisClient.hget(REDIS_ASSESSMENTTYPE_HSET, REDIS_ASSESSMENTTYPE_ALL_ASSESSMENTTYPE_LIST);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
					list = iKgAssessmentTypeService.selectAll();
					jedisClient.hset(REDIS_ASSESSMENTTYPE_HSET,REDIS_ASSESSMENTTYPE_ALL_ASSESSMENTTYPE_LIST, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgAssessmentType.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadAssessmentType失败:",e);
		}
		return list;
	}
	
	@Override
	public   KgAssessmentType loadAssessmentType(KgAssessmentType kt){
		KgAssessmentType kgAssessmentType = null;
		try {
			String json=jedisClient.hget(REDIS_ASSESSMENTTYPE_HSET, REDIS_ASSESSMENTTYPE+":"+kt.getId());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				kgAssessmentType = iKgAssessmentTypeService.selectByPrimaryKey(null,kt);
					jedisClient.hset(REDIS_ASSESSMENTTYPE_HSET,REDIS_ASSESSMENTTYPE+":"+kt.getId(), JsonUtils.objectToJson(kgAssessmentType));
			}else{
				kgAssessmentType = JsonUtils.jsonToPojo(json, KgAssessmentType.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadAssessmentType失败:",e);
		}
		return kgAssessmentType;
	}
	
	@Override
	public   List<KgAssessmentActivity> loadAssessmentActivityOfType(KgAssessmentActivity activitys){
		List<KgAssessmentActivity> list = new ArrayList<KgAssessmentActivity>();
		try {
			String json=jedisClient.hget(REDIS_ASSESSMENTACTIVITY_HSET, REDIS_ASSESSMENTTYPE_ASSESSMENTACTIVITY+":"+activitys.getAssessmentTypeId());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
					list = iKgAssessmentActivityService.selectWithOtherInfo(null,activitys,null,null);
					jedisClient.hset(REDIS_ASSESSMENTACTIVITY_HSET,REDIS_ASSESSMENTTYPE_ASSESSMENTACTIVITY+":"+activitys.getAssessmentTypeId(), JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgAssessmentActivity.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadAssessmentActivityOfType失败:",e);
		}
		return list;
	}
	
	@Override
	public  KgAssessmentActivity loadAssessmentActivity(KgAssessmentActivity activitys){
		KgAssessmentActivity assessmentActivity = null;
		try {
			String json=jedisClient.hget(REDIS_ASSESSMENTACTIVITY_HSET, REDIS_ASSESSMENTACTIVITY+":"+activitys.getAssessmentTypeId());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				assessmentActivity = iKgAssessmentActivityService.selectByPrimaryKey(null,activitys);
					jedisClient.hset(REDIS_ASSESSMENTACTIVITY_HSET,REDIS_ASSESSMENTACTIVITY+":"+activitys.getAssessmentTypeId(), JsonUtils.objectToJson(assessmentActivity));
			}else{
				assessmentActivity = JsonUtils.jsonToPojo(json, KgAssessmentActivity.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadAssessmentActivityOfType失败:",e);
		}
		return assessmentActivity;
	}
	
	
	

	@Override
	public   KgNews loadNews(KgNews news){
		KgNews kgNews= null;
		try {
			String json = jedisClient.hget(REDIS_NEWS_HSET, REDIS_NEWS_NEWS+":"+news.getId());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				kgNews = iKgNewsService.selectByPrimaryKey(null,news);
				if(kgNews!=null)
					jedisClient.hset(REDIS_NEWS_HSET,REDIS_NEWS_NEWS+":"+news.getId(), JsonUtils.objectToJson(kgNews));
			}else{
				kgNews = JsonUtils.jsonToPojo(json, KgNews.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadNews失败:",e);
		}
		return kgNews;
	}
	
	@Override
	public   List<KgNews> loadTypeNews(KgNews news){
		List<KgNews> list = new ArrayList<KgNews>();
		try {
			String json = jedisClient.hget(REDIS_NEWS_HSET, REDIS_TYPE_NEWS_WITHOTHERINFO_LIST+":"+news.getTypeid());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				list = iKgNewsService.selectWithOtherInfo(null,news,null,null);
				jedisClient.hset(REDIS_NEWS_HSET,REDIS_TYPE_NEWS_WITHOTHERINFO_LIST+":"+news.getTypeid(), JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgNews.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadTypeNews失败:",e);
		}
		return list;
	}
	
	@Override
	public   List<KgNews> loadNewsDynamicsThumbnail(KgNews ThumbNews,List<Long> typeidList,Integer page,Integer limit){
		List<KgNews> list = new ArrayList<KgNews>();
		try {
			String json = jedisClient.hget(REDIS_NEWSDYNAMICSPAGE,REDIS_NEWSDYNAMICSPAGE_THUMBNAIL_NEWS_LIST);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				list = iKgNewsService.selectByMap(null, ThumbNews,typeidList, page, limit);
				 for(KgNews kn:list) {
			   		 if(("").equals(kn.getThumbnail())) {
			   			 kn.setThumbnail(SysConfig.nonePic);
			   		 }
			   	 }
				jedisClient.hset(REDIS_NEWSDYNAMICSPAGE,REDIS_NEWSDYNAMICSPAGE_THUMBNAIL_NEWS_LIST, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgNews.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadNewsDynamicsThumbnail失败:",e);
		}
		return list;
	}
	
	@Override
	public   List<KgNews> loadNewsDynamicsTopNewsList(KgNews ThumbNews,List<Long> typeidList,Integer page,Integer limit){
		List<KgNews> list = new ArrayList<KgNews>();
		try {
			String json = jedisClient.hget(REDIS_NEWSDYNAMICSPAGE,REDIS_NEWSDYNAMICSPAGE_TOPNEWS_LIST);
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				list = iKgNewsService.selectByMap( null,ThumbNews,typeidList, page, limit);
				jedisClient.hset(REDIS_NEWSDYNAMICSPAGE,REDIS_NEWSDYNAMICSPAGE_TOPNEWS_LIST, JsonUtils.objectToJson(list));
			}else{
				list = JsonUtils.jsonToList(json, KgNews.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadNewsDynamicsTopNewsList失败:",e);
		}
		return list;
	}
	
	
	
	@Override
	public   KgAllonetext loadAllonetext(KgAllonetext at){
		KgAllonetext allonetext= null;
		try {
			String json = jedisClient.hget(REDIS_ALLONETEXT_HSET, REDIS_ALLONETEXT+":"+at.getId());
			//判断是否为空
			if (StringUtils.isBlank(json)) {
				allonetext = iKgAllonetextService.selectByPrimaryKey(null,at);
				jedisClient.hset(REDIS_ALLONETEXT_HSET,REDIS_ALLONETEXT+":"+at.getId(), JsonUtils.objectToJson(allonetext));
			}else{
				allonetext = JsonUtils.jsonToPojo(json, KgAllonetext.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("loadAllonetext失败:",e);
		}
		return allonetext;
	}
}
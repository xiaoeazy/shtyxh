package com.shtyxh.manager.service;

import java.util.List;
import java.util.Map;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgNews;

public interface IKgNewsService extends IBaseService<KgNews>, ProxySelf<IKgNewsService>{
	 public List<KgNews> selectWithOtherInfo(IRequest request,  KgNews condition, Integer pageNum, Integer pageSize);
	 
	
	 
	 public void adminDelete(IRequest request, String webPath , List<KgNews> dto) ;

	 public List<KgNews> selectByMap(IRequest request,KgNews news,List<Long> typeids ,Integer pageNum, Integer pageSize);

	 
	 public int adminQueryCount(IRequest request,KgNews record);
	 
	 public int adminQueryCount(IRequest request,KgNews news,List<Long> typeids);



	public List<KgNews> selectLinkNews(IRequest request, KgNews condition, Integer size);



	public int selectCountByMap(IRequest request, KgNews news, List<Long> typeids);
}
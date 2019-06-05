package com.shtyxh.manager.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.mapper.KgNewsMapper;
import com.shtyxh.manager.service.IKgNewsService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgNewsServiceImpl extends BaseServiceImpl<KgNews> implements IKgNewsService{
	@Autowired
	private KgNewsMapper kgNewsMapper;

	@Override
	public List<KgNews> queryAll(IRequest request){
		return kgNewsMapper.queryAll();
	}
	
	@Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<KgNews> selectWithOtherInfo(IRequest request,  KgNews condition ,Integer pageNum, Integer pageSize) {
		if(pageNum!=null&&pageSize!=null)
			PageHelper.startPage(pageNum, pageSize);
        return kgNewsMapper.selectWithOtherInfo( condition);
    }
	
	@Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<KgNews> selectLinkNews(IRequest request,  KgNews condition , Integer size) {
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("size", size);
		 map.put("news", condition);
        return kgNewsMapper.selectLinkNews( map);
    }
	
	@Override
	public List<KgNews> selectByMap(IRequest request,KgNews news,List<Long> typeids  ,Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("typeids", typeids);
		 map.put("news", news);
        return kgNewsMapper.selectByMap( map);
    }
	
	@Override
	public int selectCountByMap(IRequest request,KgNews news,List<Long> typeids) {
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("typeids", typeids);
		 map.put("news", news);
        return kgNewsMapper.selectCountByMap( map);
    }
	
	@Override
	public int adminQueryCount(IRequest request,KgNews record) {
		return  kgNewsMapper.adminQueryCount(record);
	}
	@Override
	public int adminQueryCount(IRequest request,KgNews news,List<Long> typeids) {
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("typeids", typeids);
		 map.put("news", news);
		return kgNewsMapper.adminQueryCountByMap(map);
	}
	
	 public void adminDelete(IRequest request, String webPath , List<KgNews> dto) {
		 self().batchDelete(dto);
		 File file = null;
		 for(KgNews dl :dto) {
			 if(!StringUtils.isEmpty(dl.getThumbnail())) {
				 file = new File(webPath+dl.getThumbnail());
				 if(file.exists())
					 file.delete();
			 }
			
		 }
	 }
}
package com.shtyxh.manager.service.impl;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgAssessmentType;
import com.shtyxh.manager.dto.KgCarousel;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.service.IKgCarouselService;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgCarouselServiceImpl extends BaseServiceImpl<KgCarousel> implements IKgCarouselService{
	@Override
	public int adminQueryCount(IRequest request,KgCarousel record) {
		return  mapper.selectCount(record);
	}
	@Override
	public void adminDelete(IRequest request, String webPath , List<KgCarousel> dto) {
		 self().batchDelete(dto);
		 File file = null;
		 for(KgCarousel dl :dto) {
			 if(!StringUtils.isEmpty(dl.getFilePath())) {
				 file = new File(webPath+dl.getFilePath());
				 if(file.exists())
					 file.delete();
			 }
			
		 }
	 }
}
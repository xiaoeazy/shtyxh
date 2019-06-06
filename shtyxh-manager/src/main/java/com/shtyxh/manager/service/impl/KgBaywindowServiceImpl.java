package com.shtyxh.manager.service.impl;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.shtyxh.manager.dto.KgBaywindow;
import com.shtyxh.manager.dto.KgCarousel;
import com.shtyxh.manager.service.IKgBaywindowService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgBaywindowServiceImpl extends BaseServiceImpl<KgBaywindow> implements IKgBaywindowService{
	@Override
	public int adminQueryCount(IRequest request,KgBaywindow record) {
		return  mapper.selectCount(record);
	}
	
	@Override
	public void adminDelete(IRequest request, String webPath , List<KgBaywindow> dto) {
		 self().batchDelete(dto);
		 File file = null;
		 for(KgBaywindow dl :dto) {
			 if(!StringUtils.isEmpty(dl.getFilePath())) {
				 file = new File(webPath+dl.getFilePath());
				 if(file.exists())
					 file.delete();
			 }
			
		 }
	 }
}
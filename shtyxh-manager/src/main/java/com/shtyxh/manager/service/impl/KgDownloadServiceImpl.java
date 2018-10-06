package com.shtyxh.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.common.utils.HttpClientUtil;
import com.shtyxh.common.utils.JsonUtils;
import com.shtyxh.manager.dto.KgDownload;
import com.shtyxh.manager.service.IKgDownloadService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgDownloadServiceImpl extends BaseServiceImpl<KgDownload> implements IKgDownloadService{
	
	@Value("${FILE_FILESERVER}")
	private String FILE_FILESERVER;
	@Value("${FILE_DELETE_PATH}")
	private String FILE_DELETE_PATH;
	
	@Override
	public int adminQueryCount(IRequest request,KgDownload record) {
		return  mapper.selectCount(record);
	}
	
	public void adminDelete(IRequest request, String webPath , List<KgDownload> dto) {
		 self().batchDelete(dto);
		 List<String> deletePath = new ArrayList<String>();
		 for(KgDownload dl :dto) {
			 deletePath.add(dl.getFilePath());
		 }
		 String json=JsonUtils.objectToJson(deletePath);
		 HttpClientUtil.doPostJson(FILE_FILESERVER+FILE_DELETE_PATH, json);
	}
}
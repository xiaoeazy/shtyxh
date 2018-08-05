package cn.huan.kindergarten.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgDownload;
import cn.huan.kindergarten.service.IKgDownloadService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgDownloadServiceImpl extends BaseServiceImpl<KgDownload> implements IKgDownloadService{
	@Override
	public int adminQueryCount(IRequest request,KgDownload record) {
		return  mapper.selectCount(record);
	}
	
	public void adminDelete(IRequest request, String webPath , List<KgDownload> dto) {
		 self().batchDelete(dto);
		 File file = null;
		 for(KgDownload dl :dto) {
			 file = new File(webPath+dl.getFilePath());
			 if(file.exists())
				 file.delete();
		 }
	}
}
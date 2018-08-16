package cn.huan.kindergarten.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.huan.HTed.account.dto.Role;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgDownload;
import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.mapper.KgNewsMapper;
import cn.huan.kindergarten.service.IKgNewsService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgNewsServiceImpl extends BaseServiceImpl<KgNews> implements IKgNewsService{
	@Autowired
	private KgNewsMapper kgNewsMapper;

	@Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<KgNews> selectWithOtherInfo(IRequest request,  KgNews condition ,Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
        return kgNewsMapper.selectWithOtherInfo( condition);
    }
	@Override
	public List<KgNews> selectByTypeId(IRequest request,  List<Long> typeids ,Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
        return kgNewsMapper.selectByTypeId( typeids);
    }
	
	public int adminQueryCount(IRequest request,KgNews record) {
		return  kgNewsMapper.adminQueryCount(record);
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
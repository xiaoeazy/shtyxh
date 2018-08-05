package cn.huan.kindergarten.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgAssessmentActivityUserProgress;
import cn.huan.kindergarten.dto.KgAssessmentActivityUserUpload;
import cn.huan.kindergarten.mapper.KgAssessmentActivityUserUploadMapper;
import cn.huan.kindergarten.service.IKgAssessmentActivityUserUploadService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgAssessmentActivityUserUploadServiceImpl extends BaseServiceImpl<KgAssessmentActivityUserUpload> implements IKgAssessmentActivityUserUploadService{
	@Autowired
	private KgAssessmentActivityUserUploadMapper KgAssessmentActivityUserUploadMapper;
	
	public List<KgAssessmentActivityUserUpload>  loadUserUploadList(IRequest request,KgAssessmentActivityUserProgress kaup,int page,int limit){
		PageHelper.startPage(page, limit);
		return KgAssessmentActivityUserUploadMapper.loadUserUploadList(kaup);
	}
	
	public int  countUserUploadList(IRequest request,KgAssessmentActivityUserProgress kaup){
		return KgAssessmentActivityUserUploadMapper.countUserUploadList(kaup);
	}
}
package com.shtyxh.manager.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;
import com.shtyxh.manager.dto.KgAssessmentActivityUserUpload;
import com.shtyxh.manager.mapper.KgAssessmentActivityMapper;
import com.shtyxh.manager.service.IKgAssessmentActivityService;
import com.shtyxh.manager.service.IKgAssessmentActivityUserProgressService;
import com.shtyxh.manager.service.IKgAssessmentActivityUserUploadService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgAssessmentActivityServiceImpl extends BaseServiceImpl<KgAssessmentActivity>
		implements IKgAssessmentActivityService {
	@Autowired
	private KgAssessmentActivityMapper kgAssessmentActivityMapper;
	@Autowired
	private IKgAssessmentActivityUserProgressService iKgAssessmentActivityUserProgressService;
	@Autowired
	private IKgAssessmentActivityUserUploadService iKgAssessmentActivityUserUploadService;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<KgAssessmentActivity> selectWithOtherInfo(IRequest request, KgAssessmentActivity condition,
			Integer pageNum, Integer pageSize) {
		if (pageNum != null && pageSize != null)
			PageHelper.startPage(pageNum, pageSize);
		return kgAssessmentActivityMapper.selectWithOtherInfo(condition);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<KgAssessmentActivity> selectWithOtherInfo(IRequest request, KgAssessmentActivityUserProgress condition,
			Integer pageNum, Integer pageSize) {
		if (pageNum != null && pageSize != null)
			PageHelper.startPage(pageNum, pageSize);
		return kgAssessmentActivityMapper.selectWithOtherInfo(condition);
	}

	@Override
	public int adminQueryCount(IRequest request, KgAssessmentActivity record) {
		return kgAssessmentActivityMapper.adminQueryCount(record);
	}

	public void deleteActivity(IRequest request, String webPath, List<KgAssessmentActivity> dlist) {
		for (KgAssessmentActivity kaa : dlist) {
			KgAssessmentActivityUserProgress selectBean = new KgAssessmentActivityUserProgress();
			selectBean.setAssessmentActivityId(kaa.getId());
			List<KgAssessmentActivityUserProgress> dto = iKgAssessmentActivityUserProgressService.select(request,
					selectBean);
			for (KgAssessmentActivityUserProgress kp : dto) {
				KgAssessmentActivityUserUpload ku = new KgAssessmentActivityUserUpload();
				ku.setProgressId(kp.getId());
				List<KgAssessmentActivityUserUpload> userUploadInfo = iKgAssessmentActivityUserUploadService
						.select(request, ku);
				File file = null;
				for (KgAssessmentActivityUserUpload kk : userUploadInfo) {
					if (!StringUtils.isEmpty(kk.getFilePath())) {
						file = new File(webPath + kk.getFilePath());
						if (file.exists())
							file.delete();
					}
				}
				iKgAssessmentActivityUserUploadService.batchDelete(userUploadInfo);
				iKgAssessmentActivityUserProgressService.deleteByPrimaryKey(kp);
			}
		}
		self().batchDelete(dlist);
	}

}
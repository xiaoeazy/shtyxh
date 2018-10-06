package com.shtyxh.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgAssessmentActivity;
import com.shtyxh.manager.dto.KgAssessmentActivityUserProgress;
import com.shtyxh.manager.mapper.KgAssessmentActivityMapper;
import com.shtyxh.manager.mapper.KgAssessmentActivityUserProgressMapper;
import com.shtyxh.manager.service.IKgAssessmentActivityUserProgressService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgAssessmentActivityUserProgressServiceImpl extends BaseServiceImpl<KgAssessmentActivityUserProgress> implements IKgAssessmentActivityUserProgressService{
	@Autowired
	private KgAssessmentActivityUserProgressMapper kgAssessmentActivityUserProgressMapper;

	@Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<KgAssessmentActivityUserProgress> selectWithOtherInfo(IRequest request,  KgAssessmentActivityUserProgress condition ,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return kgAssessmentActivityUserProgressMapper.selectWithOtherInfo( condition);
    }
	
	@Override
	public int adminQueryCount(IRequest request,KgAssessmentActivityUserProgress record) {
		return  kgAssessmentActivityUserProgressMapper.adminQueryCount(record);
	}
	
	@Override 
	public int countLockTable(IRequest request) {
		return  kgAssessmentActivityUserProgressMapper.countLockTable();
	}
}
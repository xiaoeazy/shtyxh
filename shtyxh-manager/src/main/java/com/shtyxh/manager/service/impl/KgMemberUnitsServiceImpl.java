package com.shtyxh.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgMemberUnits;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.mapper.KgMemberUnitsMapper;
import com.shtyxh.manager.mapper.KgNewsMapper;
import com.shtyxh.manager.service.IKgMemberUnitsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgMemberUnitsServiceImpl extends BaseServiceImpl<KgMemberUnits> implements IKgMemberUnitsService{
	@Autowired
	private KgMemberUnitsMapper kgMemberUnitsMapper;

	@Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<KgMemberUnits> selectWithOtherInfo(IRequest request,  KgMemberUnits condition ,Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
        return kgMemberUnitsMapper.selectWithOtherInfo( condition);
    }
	@Override
	public int adminQueryCount(IRequest request,KgMemberUnits record) {
		return  kgMemberUnitsMapper.adminQueryCount(record);
	}
}
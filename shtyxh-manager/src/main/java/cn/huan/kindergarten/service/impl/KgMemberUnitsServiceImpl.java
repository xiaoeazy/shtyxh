package cn.huan.kindergarten.service.impl;

import com.github.pagehelper.PageHelper;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.KgMemberUnits;
import cn.huan.kindergarten.dto.KgNews;
import cn.huan.kindergarten.mapper.KgMemberUnitsMapper;
import cn.huan.kindergarten.mapper.KgNewsMapper;
import cn.huan.kindergarten.service.IKgMemberUnitsService;

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
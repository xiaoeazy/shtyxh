package com.shtyxh.manager.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.mybatis.entity.Example;
import com.huan.HTed.mybatis.entity.Example.Criteria;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgNews;
import com.shtyxh.manager.dto.KgOffers;
import com.shtyxh.manager.mapper.KgOffersMapper;
import com.shtyxh.manager.service.IKgOffersService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgOffersServiceImpl extends BaseServiceImpl<KgOffers> implements IKgOffersService{
	
	@Autowired
	private KgOffersMapper kgOffersMapper;

	@Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<KgOffers> selectWithOtherInfo(IRequest request,  KgOffers condition ,Integer pageNum, Integer pageSize) {
//		if(pageNum!=null&&pageSize!=null)
//			PageHelper.startPage(pageNum, pageSize);
//		Example e = new Example(KgOffers.class);
//		e.setDistinct(false);
//		Criteria c = e.createCriteria();
//		if(StringUtils.isNotEmpty(condition.getPublishunit())) {
//			c.andLike("publishunit", condition.getPublishunit());
//		}
//        return kgOffersMapper.selectByExample(c);
		 return kgOffersMapper.selectWithOtherInfo(condition);
    }
	@Override
	public int adminQueryCount(IRequest request,KgOffers record) {
//		Example e = new Example(KgOffers.class);
//		Criteria c = e.createCriteria();
//		if(StringUtils.isNotEmpty(record.getPublishunit())) {
//			c.andLike("publishunit", record.getPublishunit());
//		}
//		return  mapper.selectCountByExample(c);
		return  kgOffersMapper.adminQueryCount(record);
	}
}
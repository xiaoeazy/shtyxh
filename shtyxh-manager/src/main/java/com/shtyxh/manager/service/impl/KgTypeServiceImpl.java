package com.shtyxh.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgType;
import com.shtyxh.manager.mapper.KgTypeMapper;
import com.shtyxh.manager.service.IKgTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgTypeServiceImpl extends BaseServiceImpl<KgType> implements IKgTypeService{
	@Autowired
	KgTypeMapper kgTypeMapper;
	@Override
	public List<KgType>   findChildType(Long typeid){
		return kgTypeMapper.findChildType(typeid);
	}
	
	@Override
	public KgType findParentType(Long typeid) {
		return kgTypeMapper.findParentType(typeid);
	}
	
	@Override
	public int adminQueryCount(IRequest request,KgType record) {
		return  mapper.selectCount(record);
	}
	@Override
	public void updateDto(IRequest request,List<KgType> dto) {
		List<KgType> dto2 = self().selectAll(request); 
		self().batchUpdate(request, dto);
	}
	@Override
	public KgType selectOne(IRequest request,KgType dto) {
		return mapper.selectOne(dto);
	}
}
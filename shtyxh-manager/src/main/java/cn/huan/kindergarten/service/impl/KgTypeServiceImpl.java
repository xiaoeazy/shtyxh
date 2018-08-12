package cn.huan.kindergarten.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgType;
import cn.huan.kindergarten.mapper.KgTypeMapper;
import cn.huan.kindergarten.service.IKgTypeService;

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
	public int adminQueryCount(IRequest request,KgType record) {
		return  mapper.selectCount(record);
	}
	@Override
	public void updateDto(IRequest request,List<KgType> dto) {
		List<KgType> dto2 = self().selectAll(request); 
		if(dto.get(0).getShowindex()==true) {
			for(KgType t:dto2) {
				t.setShowindex(false);
				t.set__status("update");
			}
			self().batchUpdate(request, dto2);
		}
		if(dto.get(0).getShowentrance()==true) {
			for(KgType t:dto2) {
				t.setShowentrance(false);
				t.set__status("update");
			}
			self().batchUpdate(request, dto2);
		}
		self().batchUpdate(request, dto);
	}
	@Override
	public KgType selectOne(IRequest request,KgType dto) {
		return mapper.selectOne(dto);
	}
}
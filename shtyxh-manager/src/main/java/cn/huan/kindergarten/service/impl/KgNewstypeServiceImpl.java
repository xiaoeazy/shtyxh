package cn.huan.kindergarten.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.KgNewstype;
import cn.huan.kindergarten.service.IKgNewstypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgNewstypeServiceImpl extends BaseServiceImpl<KgNewstype> implements IKgNewstypeService{
	public int adminQueryCount(IRequest request,KgNewstype record) {
		return  mapper.selectCount(record);
	}
	
	public void updateDto(IRequest request,List<KgNewstype> dto) {
		List<KgNewstype> dto2 = self().selectAll(request); 
		if(dto.get(0).getShowindex()==true) {
			for(KgNewstype t:dto2) {
				t.setShowindex(false);
				t.set__status("update");
			}
			self().batchUpdate(request, dto2);
		}
		if(dto.get(0).getShowentrance()==true) {
			for(KgNewstype t:dto2) {
				t.setShowentrance(false);
				t.set__status("update");
			}
			self().batchUpdate(request, dto2);
		}
		self().batchUpdate(request, dto);
	}
	
	public KgNewstype selectOne(IRequest request,KgNewstype dto) {
		return mapper.selectOne(dto);
	}
}
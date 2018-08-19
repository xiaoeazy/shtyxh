package cn.huan.kindergarten.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;

import cn.huan.kindergarten.dto.KgType;

public interface IKgTypeService extends IBaseService<KgType>, ProxySelf<IKgTypeService>{

	public List<KgType> findChildType(Long typeid);
	
	public KgType findParentType(Long typeid);

	public int adminQueryCount(IRequest request, KgType record);

	public void updateDto(IRequest request, List<KgType> dto);
	
	public KgType selectOne(IRequest request,KgType dto);

}
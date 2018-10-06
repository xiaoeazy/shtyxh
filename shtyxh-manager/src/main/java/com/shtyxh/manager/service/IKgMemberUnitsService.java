package com.shtyxh.manager.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgMemberUnits;
import com.shtyxh.manager.dto.KgNews;

public interface IKgMemberUnitsService extends IBaseService<KgMemberUnits>, ProxySelf<IKgMemberUnitsService>{

	List<KgMemberUnits> selectWithOtherInfo(IRequest request, KgMemberUnits condition, Integer pageNum,Integer pageSize);

	int adminQueryCount(IRequest request, KgMemberUnits record);

}
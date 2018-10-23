package com.shtyxh.manager.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.shtyxh.manager.dto.KgUnitType;
import com.shtyxh.manager.service.IKgUnitTypeService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgUnitTypeServiceImpl extends BaseServiceImpl<KgUnitType> implements IKgUnitTypeService{

}
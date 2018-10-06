package com.shtyxh.manager.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.RoleFunc;
import com.shtyxh.manager.service.IRoleFuncService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleFuncServiceImpl extends BaseServiceImpl<RoleFunc> implements IRoleFuncService{

}
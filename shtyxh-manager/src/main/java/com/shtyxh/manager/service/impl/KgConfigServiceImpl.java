package com.shtyxh.manager.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgConfig;
import com.shtyxh.manager.service.IKgConfigService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgConfigServiceImpl extends BaseServiceImpl<KgConfig> implements IKgConfigService{

}
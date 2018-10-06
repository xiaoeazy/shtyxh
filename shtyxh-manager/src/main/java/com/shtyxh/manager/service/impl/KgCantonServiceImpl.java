package com.shtyxh.manager.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgCanton;
import com.shtyxh.manager.service.IKgCantonService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgCantonServiceImpl extends BaseServiceImpl<KgCanton> implements IKgCantonService{

}
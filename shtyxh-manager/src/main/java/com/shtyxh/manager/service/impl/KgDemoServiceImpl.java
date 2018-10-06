package com.shtyxh.manager.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgDemo;
import com.shtyxh.manager.service.IKgDemoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgDemoServiceImpl extends BaseServiceImpl<KgDemo> implements IKgDemoService{

}
package com.shtyxh.manager.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgAllonetext;
import com.shtyxh.manager.service.IKgAllonetextService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgAllonetextServiceImpl extends BaseServiceImpl<KgAllonetext> implements IKgAllonetextService{

}
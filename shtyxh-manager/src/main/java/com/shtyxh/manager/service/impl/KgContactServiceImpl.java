package com.shtyxh.manager.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.dto.KgContact;
import com.shtyxh.manager.service.IKgContactService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgContactServiceImpl extends BaseServiceImpl<KgContact> implements IKgContactService{

}
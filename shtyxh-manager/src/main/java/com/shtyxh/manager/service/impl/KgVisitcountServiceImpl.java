package com.shtyxh.manager.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.shtyxh.manager.dto.KgVisitcount;
import com.shtyxh.manager.service.IKgVisitcountService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgVisitcountServiceImpl extends BaseServiceImpl<KgVisitcount> implements IKgVisitcountService{

}
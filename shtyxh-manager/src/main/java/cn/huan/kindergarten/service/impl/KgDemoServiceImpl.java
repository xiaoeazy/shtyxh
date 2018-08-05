package cn.huan.kindergarten.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.KgDemo;
import cn.huan.kindergarten.service.IKgDemoService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgDemoServiceImpl extends BaseServiceImpl<KgDemo> implements IKgDemoService{

}
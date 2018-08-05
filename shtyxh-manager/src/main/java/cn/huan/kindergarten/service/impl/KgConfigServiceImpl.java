package cn.huan.kindergarten.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.KgConfig;
import cn.huan.kindergarten.service.IKgConfigService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgConfigServiceImpl extends BaseServiceImpl<KgConfig> implements IKgConfigService{

}
package cn.huan.kindergarten.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.KgIntroduction;
import cn.huan.kindergarten.service.IKgIntroductionService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgIntroductionServiceImpl extends BaseServiceImpl<KgIntroduction> implements IKgIntroductionService{

}
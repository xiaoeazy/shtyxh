package cn.huan.kindergarten.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.KgCanton;
import cn.huan.kindergarten.service.IKgCantonService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgCantonServiceImpl extends BaseServiceImpl<KgCanton> implements IKgCantonService{

}
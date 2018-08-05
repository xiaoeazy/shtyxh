package cn.huan.kindergarten.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.KgMemberUnits;
import cn.huan.kindergarten.service.IKgMemberUnitsService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgMemberUnitsServiceImpl extends BaseServiceImpl<KgMemberUnits> implements IKgMemberUnitsService{

}
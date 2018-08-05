package cn.huan.kindergarten.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.KgContact;
import cn.huan.kindergarten.service.IKgContactService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgContactServiceImpl extends BaseServiceImpl<KgContact> implements IKgContactService{

}
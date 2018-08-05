package cn.huan.kindergarten.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.KgAllonetext;
import cn.huan.kindergarten.service.IKgAllonetextService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class KgAllonetextServiceImpl extends BaseServiceImpl<KgAllonetext> implements IKgAllonetextService{

}
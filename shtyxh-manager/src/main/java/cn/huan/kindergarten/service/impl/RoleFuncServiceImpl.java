package cn.huan.kindergarten.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.huan.kindergarten.dto.RoleFunc;
import cn.huan.kindergarten.service.IRoleFuncService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleFuncServiceImpl extends BaseServiceImpl<RoleFunc> implements IRoleFuncService{

}
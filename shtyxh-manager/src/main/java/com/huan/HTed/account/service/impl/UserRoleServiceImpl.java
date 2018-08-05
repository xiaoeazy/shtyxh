package com.huan.HTed.account.service.impl;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.huan.HTed.account.dto.UserRole;
import com.huan.HTed.account.service.IUserRoleService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements IUserRoleService{

}
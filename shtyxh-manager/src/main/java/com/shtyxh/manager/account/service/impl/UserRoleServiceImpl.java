package com.shtyxh.manager.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.account.dto.UserRole;
import com.shtyxh.manager.account.mapper.UserRoleMapper;
import com.shtyxh.manager.account.service.IUserRoleService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements IUserRoleService{
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	public  boolean ifAdminRole(Long userid){
		return userRoleMapper.ifAdminRole(userid);
	}
}
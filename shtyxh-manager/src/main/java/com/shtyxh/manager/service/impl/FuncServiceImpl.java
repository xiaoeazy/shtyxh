package com.shtyxh.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.impl.RequestHelper;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.account.dto.Role;
import com.shtyxh.manager.account.dto.UserRole;
import com.shtyxh.manager.account.service.IRoleService;
import com.shtyxh.manager.dto.Func;
import com.shtyxh.manager.dto.RoleFunc;
import com.shtyxh.manager.mapper.FuncMapper;
import com.shtyxh.manager.service.IFuncService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FuncServiceImpl extends BaseServiceImpl<Func> implements IFuncService{
	@Autowired
	private FuncMapper funcMapper;
	@Autowired
	private IRoleService roleService;
	
	public List<Func> adminqueryNotHave(IRequest request,RoleFunc roleFunc){
		return funcMapper.rolequeryNotHave(roleFunc);
	}
	
	public List<Func> adminQueryHave(IRequest request,RoleFunc roleFunc){
		return funcMapper.roleQueryHave(roleFunc);
	}
	
	public List<Func> adminUserFuncQueryHave(IRequest request,UserRole ur){
		
		List<Role> roles = roleService.adminQueryHave(request, ur);
		if(roles.size()!=0)
			return funcMapper.adminUserFuncQueryHave(roles);
		else
			return new ArrayList<Func>();
	}
}
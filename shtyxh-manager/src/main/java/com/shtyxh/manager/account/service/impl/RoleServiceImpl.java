package com.shtyxh.manager.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.DTOStatus;
import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.manager.account.dto.Role;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.account.dto.UserRole;
import com.shtyxh.manager.account.mapper.RoleMapper;
import com.shtyxh.manager.account.service.IRoleService;
import com.shtyxh.manager.account.service.IUserRoleService;
import com.shtyxh.manager.dto.RoleFunc;
import com.shtyxh.manager.service.IRoleFuncService;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService{
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
    protected RoleMapper roleMapper;
	@Autowired
	private IRoleFuncService roleFuncService;
	
	public List<Role> adminqueryNotHave(IRequest request,UserRole userRole){
		return roleMapper.adminqueryNotHave(userRole);
	}
	
	public List<Role> adminQueryHave(IRequest request,UserRole userRole){
		return roleMapper.adminQueryHave(userRole);
	}
	
	public int adminQueryCount(IRequest request,Role record) {
		return  mapper.selectCount(record);
	}
	
	public void adminDelete(IRequest request, List<Role> dto) {
		batchDelete(dto);
		for(Role role :dto) {
			 UserRole dr = new UserRole(); //这里先删除原先的角色 在添加新的
			 dr.setRoleId(role.getRoleId());
			 List<UserRole> orignalUserRole = userRoleService.select(request, dr);
			 for(UserRole ur :orignalUserRole) {
				 ur.set__status(DTOStatus.DELETE);
			 }
			 userRoleService.batchUpdate(request, orignalUserRole);
		}
	}
	
	public void adminUpdate(IRequest request ,Role role,List<RoleFunc> roleFunc) {
		 List<Role> list = new ArrayList<Role>();
		 list.add(role);
		 batchUpdate(request, list);
		 
		 RoleFunc dr = new RoleFunc(); //这里先删除原先的角色 在添加新的
		 dr.setRoleId(list.get(0).getRoleId());
		 if(roleFunc!=null) {
			 List<RoleFunc> orignalFunc = roleFuncService.select(request, dr);
			 for(RoleFunc ur :orignalFunc) {
				 ur.set__status(DTOStatus.DELETE);
			 }
			 roleFuncService.batchUpdate(request, orignalFunc);
		
			 for(RoleFunc ur :roleFunc) {
				 ur.setRoleId(list.get(0).getRoleId());
				 ur.set__status(DTOStatus.ADD);
			 }
			 roleFuncService.batchUpdate(request, roleFunc);
		 }
	}
}
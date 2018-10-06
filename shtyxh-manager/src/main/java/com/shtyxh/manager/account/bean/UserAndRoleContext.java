package com.shtyxh.manager.account.bean;

import java.util.List;

import com.shtyxh.manager.account.dto.Role;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.account.dto.UserRole;
import com.shtyxh.manager.dto.RoleFunc;

public class UserAndRoleContext {
	private User user;
	private List<UserRole> userRoleList;
	private Role role;
	private List<RoleFunc> roleFuncList;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}
	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<RoleFunc> getRoleFuncList() {
		return roleFuncList;
	}
	public void setRoleFuncList(List<RoleFunc> roleFuncList) {
		this.roleFuncList = roleFuncList;
	}
	
	
}

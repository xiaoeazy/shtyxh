package com.huan.HTed.account.bean;

import java.util.List;

import com.huan.HTed.account.dto.Role;
import com.huan.HTed.account.dto.User;
import com.huan.HTed.account.dto.UserRole;

import cn.huan.kindergarten.dto.RoleFunc;

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

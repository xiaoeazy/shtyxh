package com.huan.HTed.account.mapper;

import com.huan.HTed.mybatis.common.Mapper;

import java.util.List;

import com.huan.HTed.account.dto.Role;
import com.huan.HTed.account.dto.UserRole;

public interface RoleMapper extends Mapper<Role>{
	public List<Role> adminqueryNotHave(UserRole userRole);
	
	public List<Role> adminQueryHave(UserRole userRole);
}
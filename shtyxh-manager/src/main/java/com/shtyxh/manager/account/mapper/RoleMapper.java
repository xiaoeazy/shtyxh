package com.shtyxh.manager.account.mapper;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.account.dto.Role;
import com.shtyxh.manager.account.dto.UserRole;

import java.util.List;

public interface RoleMapper extends Mapper<Role>{
	public List<Role> adminqueryNotHave(UserRole userRole);
	
	public List<Role> adminQueryHave(UserRole userRole);
}
package com.shtyxh.manager.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.account.dto.Role;
import com.shtyxh.manager.dto.Func;
import com.shtyxh.manager.dto.RoleFunc;

public interface FuncMapper extends Mapper<Func>{
	public List<Func> rolequeryNotHave(RoleFunc roleFunc);
	
	public List<Func> roleQueryHave(RoleFunc roleFunc);
	
	public List<Func> adminUserFuncQueryHave(List<Role> roles);
	
}
package com.shtyxh.manager.account.mapper;

import java.util.List;

import com.huan.HTed.mybatis.common.Mapper;
import com.shtyxh.manager.account.dto.Role;
import com.shtyxh.manager.account.dto.User;

public interface UserMapper extends Mapper<User>{
	public List<User> selectWithRole(Role dto);
	public int adminQueryCountWithRole(Role record) ;
	public List<User> selectWithOutAdmin();
	public int adminQueryCountWithOutAdmin() ;
}
package com.huan.HTed.account.mapper;

import java.util.List;

import com.huan.HTed.account.dto.Role;
import com.huan.HTed.account.dto.User;
import com.huan.HTed.mybatis.common.Mapper;

public interface UserMapper extends Mapper<User>{
	public List<User> selectWithRole(Role dto);
	public int adminQueryCountWithRole(Role record) ;
}
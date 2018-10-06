package com.shtyxh.manager.account.service;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.account.dto.Role;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.account.dto.UserRole;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService extends IBaseService<User>, ProxySelf<IUserService>{
	public void adminUpdate(IRequest request ,User user,List<UserRole>  userRoleList);
	public void adminDelete(IRequest request ,List<User> list);
	public int adminQueryCount(IRequest request,User record);
	public User selectOne(IRequest request, User record);
	public List<User> selectWithRole(IRequest request, Role dto);
	public int adminQueryCountWithRole(IRequest request, Role dto);
	public Map<Long ,String> loadAllUserToMap(IRequest request);
	
}
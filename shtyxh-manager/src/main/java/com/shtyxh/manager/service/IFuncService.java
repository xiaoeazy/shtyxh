package com.shtyxh.manager.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.account.dto.UserRole;
import com.shtyxh.manager.dto.Func;
import com.shtyxh.manager.dto.RoleFunc;

public interface IFuncService extends IBaseService<Func>, ProxySelf<IFuncService>{
	public List<Func> adminqueryNotHave(IRequest request,RoleFunc roleFunc);
	
	public List<Func> adminQueryHave(IRequest request,RoleFunc roleFunc);
	
	public List<Func> adminUserFuncQueryHave(IRequest request,UserRole ur);
}
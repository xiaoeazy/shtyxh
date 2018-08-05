package cn.huan.kindergarten.service;

import java.util.List;

import com.huan.HTed.account.dto.UserRole;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;

import cn.huan.kindergarten.dto.Func;
import cn.huan.kindergarten.dto.RoleFunc;

public interface IFuncService extends IBaseService<Func>, ProxySelf<IFuncService>{
	public List<Func> adminqueryNotHave(IRequest request,RoleFunc roleFunc);
	
	public List<Func> adminQueryHave(IRequest request,RoleFunc roleFunc);
	
	public List<Func> adminUserFuncQueryHave(IRequest request,UserRole ur);
}
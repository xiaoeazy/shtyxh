package cn.huan.kindergarten.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.account.dto.Role;
import com.huan.HTed.account.dto.UserRole;
import com.huan.HTed.account.service.IRoleService;
import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.impl.RequestHelper;
import com.huan.HTed.system.service.impl.BaseServiceImpl;

import cn.huan.kindergarten.dto.Func;
import cn.huan.kindergarten.dto.RoleFunc;
import cn.huan.kindergarten.mapper.FuncMapper;
import cn.huan.kindergarten.service.IFuncService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FuncServiceImpl extends BaseServiceImpl<Func> implements IFuncService{
	@Autowired
	private FuncMapper funcMapper;
	@Autowired
	private IRoleService roleService;
	
	public List<Func> adminqueryNotHave(IRequest request,RoleFunc roleFunc){
		return funcMapper.rolequeryNotHave(roleFunc);
	}
	
	public List<Func> adminQueryHave(IRequest request,RoleFunc roleFunc){
		return funcMapper.roleQueryHave(roleFunc);
	}
	
	public List<Func> adminUserFuncQueryHave(IRequest request,UserRole ur){
		
		List<Role> roles = roleService.adminQueryHave(request, ur);
		if(roles.size()!=0)
			return funcMapper.adminUserFuncQueryHave(roles);
		else
			return new ArrayList<Func>();
	}
}
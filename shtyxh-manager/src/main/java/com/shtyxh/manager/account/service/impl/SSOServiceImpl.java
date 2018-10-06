package com.shtyxh.manager.account.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huan.HTed.system.service.impl.BaseServiceImpl;
import com.shtyxh.common.exception.ESessionExcetion;
import com.shtyxh.common.exception.GlobalException;
import com.shtyxh.common.utils.CookieUtils;
import com.shtyxh.common.utils.JsonUtils;
import com.shtyxh.manager.account.dto.User;
import com.shtyxh.manager.account.mapper.UserMapper;
import com.shtyxh.manager.account.service.ISSOService;
import com.shtyxh.redis.service.JedisClient;

@Service
@Transactional(rollbackFor = Exception.class)
public class SSOServiceImpl extends BaseServiceImpl<User> implements ISSOService{
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	

	@Override
	public String userLogin(String username, String password,HttpServletRequest request, HttpServletResponse response) {
		User sel = new User();
		sel.setUserName(username);
		sel.setPasswordEncrypted( DigestUtils.md5Hex(password));
		User user = userMapper.selectOne( sel);
		//如果没有此用户名
		if (null == user ) {
			throw new GlobalException("用户名或密码错误");
		}
		//生成token
		String token = UUID.randomUUID().toString();
		//保存用户之前，把用户对象中的密码清空。
		user.setPasswordEncrypted(null);
		//把用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		//设置session的过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		
		//添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效。
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		//返回token
		return token;
	}
	
	@Override
	public String updateUser(User user,HttpServletRequest request, HttpServletResponse response) {
		 user.setPasswordEncrypted( DigestUtils.md5Hex(user.getPasswordEncrypted()));
		 userMapper.updateByPrimaryKeySelective(user);
		//生成token
		 String token = UUID.randomUUID().toString();
		//保存用户之前，把用户对象中的密码清空。
		 user.setPasswordEncrypted(null);
			//把用户信息写入redis
			jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
			//设置session的过期时间
			jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
			
			//添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效。
			CookieUtils.setCookie(request, response, "TT_TOKEN", token);
			
			//返回token
			return token;
	}
	
	@Override
	public void logout(String token,HttpServletRequest request, HttpServletResponse response) {
		jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
		//添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效。
		CookieUtils.clearCookie(request, response, "TT_TOKEN");
		
	}
	
	@Override
	public User getUserByToken(String token,String type) {
		//根据token从redis中查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		//判断是否为空
		if (StringUtils.isBlank(json)) {
			throw new ESessionExcetion( "此session已经过期，请重新登录",type);
		}
		//更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		//返回用户信息
		return JsonUtils.jsonToPojo(json, User.class);
	}

	
	
}
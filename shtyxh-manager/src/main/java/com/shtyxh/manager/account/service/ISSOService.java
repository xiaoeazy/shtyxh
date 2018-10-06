package com.shtyxh.manager.account.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shtyxh.manager.account.dto.User;

public interface ISSOService {

	public String userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

	public String updateUser(User user, HttpServletRequest request, HttpServletResponse response);

	public void logout(String token, HttpServletRequest request, HttpServletResponse response);

	public User getUserByToken(String token,String type);

}

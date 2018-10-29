package com.lcs.service;

import com.lcs.pojo.Page;
import com.lcs.pojo.User;

public interface UserService {
	public User login(User u);

	public Page conditionQuery(String val, String param, int pageNum, String opt);

	public User queryById(String id);

	public void changeRole(User u);

	public void delById(String id);

	public int updateNickname(String uid, String name);

	public int updateUsername(String uid, String name);

	public int changePw(String id, String pw);

	public int findUserByName(String name);

	public int register(User user);
}

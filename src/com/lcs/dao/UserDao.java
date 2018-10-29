package com.lcs.dao;

import java.util.List;

import com.lcs.pojo.User;

public interface UserDao {
	public User login(User u);

	public List<User> pageQuery(int start,int numPerPage);

	public int queryTotalCount();

	public int conditionQueryTotalCount(String val, String param);

	public List<User> conditionQuery(String val, String param, int start, int numPerPage);

	public User queryById(String id);

	public void changeRole(User u);

	public int updateNickname(String uid, String name);

	public int updateUsername(String uid, String name);

	public int changePw(String id, String pw);

	public int findUserByName(String name);

	public int register(User user);
}

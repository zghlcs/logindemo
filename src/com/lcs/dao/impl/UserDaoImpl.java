package com.lcs.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lcs.dao.UserDao;
import com.lcs.pojo.User;
import com.lcs.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(User u) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where name=? and password=?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class),u.getName(),u.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public int queryTotalCount() {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		long count = 0;
		try {
			count = qr.query("select count(1) from user", new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int)count;
	}
	
	@Override
	public int conditionQueryTotalCount(String val, String param) {
		String sql = "";
		if ("1".equals(val)) {
			sql = "select count(1) from user where name like '%"+param+"%'";
		} else if ("2".equals(val)) {
			sql = "select count(1) from user where nickname like '%"+param+"%'";
		} else if("3".equals(val)){
			sql = "select count(1) from user where role="+param;
		}
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		long c = 0;
		try {
			c = qr.query(sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int)c;
	}

	@Override
	public List<User> pageQuery(int start,int numPerPage) {
		String sql = "select * from user limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<User> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<User>(User.class),start,numPerPage);
		} catch (SQLException e) {
			return null;
		}
		return list;
	}

	@Override
	public List<User> conditionQuery(String val, String param, int start, int numPerPage) {
		String sql = "";
		if ("1".equals(val)) {
			sql = "select*from user where name like '%"+param+"%' limit ?,?";
		} else if ("2".equals(val)) {
			sql = "select*from user where nickname like '%"+param+"%' limit ?,?";
		} else if("3".equals(val)){
			sql = "select*from user where role="+param+" limit ?,?";
		}
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<User> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<User>(User.class), start, numPerPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User queryById(String id) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		User u = null;
		try {
			u = qr.query("select*from user where id=?", new BeanHandler<User>(User.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public void changeRole(User u) {
		String sql = "";
		if (u.getRole()==1) {
			sql = "update user set role=0 where id=?";
		} else {
			sql = "update user set role=1 where id=?";
		}
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		try {
			qr.update(sql, u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updateNickname(String uid, String name) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set nickname=? where id=?";
		int count = 0;
		try {
			count = qr.update(sql, name,uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int updateUsername(String uid, String name) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set name=? where id=?";
		int count = 0;
		try {
			count = qr.update(sql, name,uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int changePw(String id, String pw) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		int count = 0;
		try {
			count = qr.update("update user set password=? where id=?", pw, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int findUserByName(String name) {
		String sql = "select count(1) from user where name=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		long c = 0;
		try {
			c = qr.query(sql, new ScalarHandler<Long>(),name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int)c;
	}

	@Override
	public int register(User user) {
		String sql = "insert into user(name,password,nickname) values(?,?,?) ";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		int count = 0;
		try {
			count = qr.update(sql, user.getName(),user.getPassword(),user.getNickname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}

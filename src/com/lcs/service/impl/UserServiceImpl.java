package com.lcs.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.lcs.dao.UserDao;
import com.lcs.dao.impl.UserDaoImpl;
import com.lcs.pojo.Page;
import com.lcs.pojo.User;
import com.lcs.service.UserService;
import com.lcs.util.JDBCUtils;
import com.lcs.util.MD5Utils;

public class UserServiceImpl implements UserService {

	private static int totalCount;
	private static int totalPageCount;
	private static int numPerPage = 10;
	
	@Override
	public User login(User u) {
		String oldpw = u.getPassword();
		if (oldpw==null) {
			return null;
		}
		String newpw = MD5Utils.getMD5(oldpw);
		u.setPassword(newpw);
		
		UserDao loginDao = new UserDaoImpl();
		return loginDao.login(u);
	}

	@Override
	public Page conditionQuery(String val, String param, int pageNum, String opt) {
		
		Page page = new Page();
		page.setNumPerPage(numPerPage);
		page.setPageNum(pageNum);
		
		UserDao dao = new UserDaoImpl();
		int start = (pageNum-1)*page.getNumPerPage();
		if (pageNum==1||opt!=null) {
			if (!"".equals(param)&&param!=null) {
				if (!"".equals(val)&&val!=null) {
					totalCount = dao.conditionQueryTotalCount(val, param);
				}else{
					totalCount = dao.queryTotalCount();
				}
			} else {
				totalCount = dao.queryTotalCount();
			}
			totalPageCount = (totalCount-1+page.getNumPerPage())/page.getNumPerPage();
		}
		
		List<User> list = null;
		if (!"".equals(param)&&param!=null) {
			if (!"".equals(val)&&val!=null) {
				list = dao.conditionQuery(val, param, start, page.getNumPerPage());
			}else{
				list = dao.pageQuery(start, page.getNumPerPage());
			}
		} else {
			list = dao.pageQuery(start, page.getNumPerPage());
		}
		
		page.setTotalPageCount(totalPageCount);
		page.setTotalCount(totalCount);
		page.setList(list);
		
		return page;
	}

	@Override
	public User queryById(String id) {
		UserDao dao = new UserDaoImpl();
		return dao.queryById(id);
	}

	@Override
	public void changeRole(User u) {
		UserDao dao = new UserDaoImpl();
		dao.changeRole(u);
	}

	@Override
	public void delById(String id) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		try {
			qr.update("delete from user where id=?", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updateNickname(String uid, String name) {
		UserDao dao = new UserDaoImpl();
		return dao.updateNickname(uid, name);
	}

	@Override
	public int updateUsername(String uid, String name) {
		UserDao dao = new UserDaoImpl();
		return dao.updateUsername(uid, name);
	}

	@Override
	public int changePw(String id, String pw) {
		//该密码一定要加密（与注册时对应）
		String newPw = MD5Utils.getMD5(pw);
		
		UserDao dao = new UserDaoImpl();
		return dao.changePw(id, newPw);
	}

	@Override
	public int findUserByName(String name) {
		UserDao dao = new UserDaoImpl();
		return dao.findUserByName(name);
	}

	public int register(User user) {
		
		String newPw = MD5Utils.getMD5(user.getPassword());
		user.setPassword(newPw);
		
		UserDao dao = new UserDaoImpl();
		return dao.register(user);
	}

}

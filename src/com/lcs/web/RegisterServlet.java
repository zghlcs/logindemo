package com.lcs.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.lcs.pojo.User;
import com.lcs.service.UserService;
import com.lcs.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserService s = new UserServiceImpl();
		int c = s.register(user);
		if (c==0) {
			request.setAttribute("msg", "<h2>注册失败</h2>");
			request.getRequestDispatcher("/rgister.jsp").forward(request, response);
		} else {
			response.getWriter().print("<h2>注册成功！ 2秒后返回首页</h2>");
			response.setHeader("refresh", "2;url="+request.getContextPath()+"\\index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

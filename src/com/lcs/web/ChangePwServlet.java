package com.lcs.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcs.service.UserService;
import com.lcs.service.impl.UserServiceImpl;

public class ChangePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pw = request.getParameter("pwname");
		String id = request.getParameter("userid");
		
		UserService s = new UserServiceImpl();
		int c = s.changePw(id, pw);
		if (c==0) {
			request.setAttribute("msg", "修改失败");
			request.getRequestDispatcher("/user/userInfoManager.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "修改成功");
			request.getRequestDispatcher("/user/userInfoManager.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

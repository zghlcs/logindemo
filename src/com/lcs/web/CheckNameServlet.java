package com.lcs.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcs.service.UserService;
import com.lcs.service.impl.UserServiceImpl;

public class CheckNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		
		UserService s = new UserServiceImpl();
		int c = s.findUserByName(name);
		if (c==0) {
			response.getWriter().print("<font color='blue'>可用</font>");
		} else {
			response.getWriter().print("<font color='red'>已被占用</font>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

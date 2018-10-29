package com.lcs.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcs.pojo.User;
import com.lcs.service.UserService;
import com.lcs.service.impl.UserServiceImpl;

public class ChangeUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("userid");
		String name = request.getParameter("username");
		
		UserService service = new UserServiceImpl();
		int count = service.updateUsername(uid, name);		
		
		if (count==0) {
			response.getWriter().println("更新失败!");
		} else {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if ((loginUser.getId()+"").equals(uid)) {
				loginUser.setName(name);
				request.getSession().setAttribute("loginUser", loginUser);
			}
			response.getWriter().println("更新成功");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.lcs.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcs.pojo.User;
import com.lcs.service.UserService;
import com.lcs.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberPw = request.getParameter("rememberPw");//checkbox选中为“on”,没选为null
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		
		UserService loginService = new UserServiceImpl();
		User loginUser = loginService.login(user);
		
		if (loginUser!=null) {
			String encodeName = URLEncoder.encode(username, "utf-8");
			Cookie uCookie = new Cookie("usernameCookie", encodeName);//解决cookie不能保存中文的问题
			uCookie.setPath("/");
			uCookie.setMaxAge(60*60*24);
			response.addCookie(uCookie);
			if (rememberPw!=null) {
				Cookie pCookie = new Cookie("pwCookie", password);
				pCookie.setPath("/");
				pCookie.setMaxAge(60*60*24);
				response.addCookie(pCookie);
			}
			request.getSession().setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else {
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

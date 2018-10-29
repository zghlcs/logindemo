package com.lcs.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcs.service.UserService;
import com.lcs.service.impl.UserServiceImpl;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("userid");
		String val = request.getParameter("val");
		String param = request.getParameter("param");
		String pageNum = request.getParameter("pageNum");
		
		UserService s = new UserServiceImpl();
		s.delById(id);
		
		response.sendRedirect(request.getContextPath()+"/pageQuery?pageNum="+pageNum+"&val="+val+"&param="+param+"&opt=del");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

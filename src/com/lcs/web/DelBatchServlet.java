package com.lcs.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcs.service.UserService;
import com.lcs.service.impl.UserServiceImpl;

public class DelBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String params = request.getParameter("params");
		String[] ids = request.getParameterValues("checkboxName");
		
		if (ids!=null) {
			UserService s = new UserServiceImpl();
			for (String id : ids) {
				s.delById(id);
			}
		}
		response.sendRedirect(request.getContextPath()+"/pageQuery"+params+"&opt=delBatch");//params带‘?’
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

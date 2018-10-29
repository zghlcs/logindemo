package com.lcs.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcs.pojo.Page;
import com.lcs.service.UserService;
import com.lcs.service.impl.UserServiceImpl;

public class PageQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String val = request.getParameter("val");
		String param = request.getParameter("param");
		String pageNum = request.getParameter("pageNum");
		String opt = request.getParameter("opt");//将删除操作与其他操作区别开
		if (pageNum==null||pageNum.equals("")) {
			pageNum="1";
		}
		UserService s = new UserServiceImpl();
		Page page = s.conditionQuery(val, param, Integer.parseInt(pageNum),opt);
		
		request.setAttribute("page", page);
		request.setAttribute("conditionName", val);
		request.setAttribute("conditionParam", param);//el不能通过param名称接收参数--${param}
		request.getRequestDispatcher("/user/queryUserPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

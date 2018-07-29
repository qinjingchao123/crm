package com.bjpowernode.crm.workbench.transaction.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
/**
 * 创建交易
 * @author Administrator
 *
 */
public class CreateTransactionController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//调用service方法,查询所有用户
		UserService us=(UserService)ServiceFactory.getService(new UserServiceImpl());
		List<User> userList=us.queryAllUsers();
		//把数据保存到req中,请求转发
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("/workbench/transaction/save.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	

}

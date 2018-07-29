package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 创建市场活动
 * @author Administrator
 *
 */
public class CreateMarketActivityController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//调用service方法,查询所有用户
		UserService us=(UserService)ServiceFactory.getService(new UserServiceImpl());
		List<User> userList=us.queryAllUsers();
		//根据查询结果,返回响应信息(json)
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(userList);
		
		resp.setContentType("text/json;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		out.write(json);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}

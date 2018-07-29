package com.bjpowernode.crm.workbench.transaction.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.customer.domain.Customer;
import com.bjpowernode.crm.workbench.customer.service.CustomerService;
import com.bjpowernode.crm.workbench.customer.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 根据名称模糊查询客户
 * @author Administrator
 *
 */
public class QueryCustomerForTranByNameController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String name=req.getParameter("name");
		//调用service方法查询客户
		CustomerService cs=(CustomerService)ServiceFactory.getService(new CustomerServiceImpl());
		List<Customer> customerList=cs.queryCustomerForTranByName(name);
		//根据查询结果,返回响应信息(json)
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(customerList);
		
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

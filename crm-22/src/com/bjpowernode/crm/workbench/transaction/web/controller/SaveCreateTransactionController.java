package com.bjpowernode.crm.workbench.transaction.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.transaction.service.TransactionService;
import com.bjpowernode.crm.workbench.transaction.service.impl.TransactionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 保存创建的交易
 * @author Administrator
 *
 */
public class SaveCreateTransactionController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数,封装参数
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("owner",req.getParameter("owner"));
		map.put("amountOfMoney",req.getParameter("amountOfMoney"));
		map.put("name",req.getParameter("name"));
		map.put("expectedClosingDate",req.getParameter("expectedClosingDate"));
		map.put("customerName",req.getParameter("customerName"));
		map.put("customerId",req.getParameter("customerId"));
		map.put("stage",req.getParameter("stage"));
		map.put("type",req.getParameter("type"));
		map.put("source",req.getParameter("source"));
		map.put("activityId",req.getParameter("activityId"));
		map.put("contactsId",req.getParameter("contactsId"));
		map.put("description",req.getParameter("description"));
		map.put("contactSummary",req.getParameter("contactSummary"));
		map.put("nextContactTime",req.getParameter("nextContactTime"));
		map.put("user", req.getSession().getAttribute("user"));
		//调用service方法,保存数据
		TransactionService ts=(TransactionService)ServiceFactory.getService(new TransactionServiceImpl());
		Map<String,Object> retMap=new HashMap<String,Object>();
		try{
			ts.saveCreateTransaction(map);
			retMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			retMap.put("success", false);
		}
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(retMap);
		
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

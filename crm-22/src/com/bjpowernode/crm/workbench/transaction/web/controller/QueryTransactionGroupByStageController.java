package com.bjpowernode.crm.workbench.transaction.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.domain.FunnelVO;
import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.transaction.service.TransactionService;
import com.bjpowernode.crm.workbench.transaction.service.impl.TransactionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 查询交易表中各个阶段数据量
 * @author Administrator
 *
 */
public class QueryTransactionGroupByStageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//调用service方法,查询数据 
		TransactionService ts=(TransactionService)ServiceFactory.getService(new TransactionServiceImpl());
		List<FunnelVO> funnelList=ts.queryTransactionGroupByStage();
		//根据查询结果,返回响应信息(json)
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(funnelList);
		
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

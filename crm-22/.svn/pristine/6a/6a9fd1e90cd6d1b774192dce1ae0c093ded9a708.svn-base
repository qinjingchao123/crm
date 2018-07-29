package com.bjpowernode.crm.workbench.clue.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.clue.service.ClueService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 保存线索转换
 * @author Administrator
 *
 */
public class SaveConvertClueController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数,封装参数
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("clueId", req.getParameter("clueId"));
		map.put("isCreateTransaction", req.getParameter("isCreateTransaction"));
		if("true".equals(req.getParameter("isCreateTransaction"))){
			map.put("amountOfMoney", req.getParameter("amountOfMoney"));
			map.put("transactionName", req.getParameter("transactionName"));
			map.put("expectedClosingDate", req.getParameter("expectedClosingDate"));
			map.put("stage", req.getParameter("stage"));
			map.put("activityId", req.getParameter("activityId"));
		}
		map.put("user", req.getSession().getAttribute("user"));
		//调用service方法,保存线索转换
		Map<String,Object> retMap=new HashMap<String,Object>();
		try{
			ClueService cs=(ClueService)ServiceFactory.getService(new ClueServiceImpl());
			cs.saveConvertClue(map);
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

package com.bjpowernode.crm.workbench.clue.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 根据名称模糊查询市场活动,并且把已经关联过的市场活动排除
 * @author Administrator
 *
 */
public class QueryMarketActivityByNameClueIdController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String name=req.getParameter("name");
		String clueId=req.getParameter("clueId");
		//封装参数
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", name);
		map.put("clueId", clueId);
		//调用service方法,查询市场活动
		MarketActivityService mas=(MarketActivityService)ServiceFactory.getService(new MarketActivityServiceImpl());
		List<MarketActivity> activityList=mas.queryMarketActivityByNameClueId(map);
		//根据查询结果,返回响应信息(json)
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(activityList);
		
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

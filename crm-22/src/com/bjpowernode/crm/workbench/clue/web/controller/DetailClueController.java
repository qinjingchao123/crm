package com.bjpowernode.crm.workbench.clue.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.domain.ClueRemark;
import com.bjpowernode.crm.workbench.clue.service.ClueRemarkService;
import com.bjpowernode.crm.workbench.clue.service.ClueService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueRemarkServiceImpl;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueServiceImpl;
/**
 * 查看线索明细
 * @author Administrator
 *
 */
public class DetailClueController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String id=req.getParameter("id");
		//调用service方法,查询数据
		ClueService cs=(ClueService)ServiceFactory.getService(new ClueServiceImpl());
		Clue clue=cs.queryClueForDetailById(id);
		
		ClueRemarkService crs=(ClueRemarkService)ServiceFactory.getService(new ClueRemarkServiceImpl());
		List<ClueRemark> remarkList=crs.queryClueRemarkByClueId(id);
		
		MarketActivityService mas=(MarketActivityService)ServiceFactory.getService(new MarketActivityServiceImpl());
		List<MarketActivity> activityList=mas.queryMarketActivityByClueId(id);
		//把数据保存到req中
		req.setAttribute("clue", clue);
		req.setAttribute("remarkList", remarkList);
		req.setAttribute("activityList", activityList);
		//请求转发
		req.getRequestDispatcher("/workbench/clue/detail.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}

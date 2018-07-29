package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityRemarkServiceImpl;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
/**
 * 查看市场活动明细
 * @author Administrator
 *
 */
public class DetailMarketActivityController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String id=req.getParameter("id");
		//调用service方法,查询数据 
		MarketActivityService mas=(MarketActivityService)ServiceFactory.getService(new MarketActivityServiceImpl());
		MarketActivity activity=mas.queryMarketActivityForDetailById(id);
		
		MarketActivityRemarkService mars=(MarketActivityRemarkService)ServiceFactory.getService(new MarketActivityRemarkServiceImpl());
		List<MarketActivityRemark> remarkList=mars.queryActivityRemarkByActivityId(id);
		//把数据保存到req中,请求转发
		req.setAttribute("activity", activity);
		req.setAttribute("remarkList", remarkList);
		req.getRequestDispatcher("/workbench/activity/detail.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

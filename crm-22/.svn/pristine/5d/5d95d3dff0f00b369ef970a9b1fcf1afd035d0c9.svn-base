package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.DateUtil;
import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.commons.util.UUIDUtil;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 保存创建的市场活动
 * @author Administrator
 *
 */
public class SaveCreateMarketActivityController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String owner=req.getParameter("owner");
		String type=req.getParameter("type");
		String name=req.getParameter("name");
		String state=req.getParameter("state");
		String startDate=req.getParameter("startDate");
		String endDate=req.getParameter("endDate");
		String budgetCost=req.getParameter("budgetCost");
		String actualCost=req.getParameter("actualCost");
		String description=req.getParameter("description");
		//封装参数
		MarketActivity ma=new MarketActivity();
		if(actualCost!=null&&actualCost.trim().length()>0){
			ma.setActualCost(Long.parseLong(actualCost.trim()));
		}
		if(budgetCost!=null&&budgetCost.trim().length()>0){
			ma.setBudgetCost(Long.parseLong(budgetCost.trim()));
		}
		User user=(User)req.getSession().getAttribute("user");
		ma.setCreateBy(user.getId());
		ma.setCreateTime(DateUtil.formatDateTime(new Date()));//yyyy-MM-dd HH:mm:ss
		ma.setDescription(description);
		ma.setEndDate(endDate);
		ma.setId(UUIDUtil.getUUID());
		ma.setName(name);
		ma.setOwner(owner);
		ma.setStartDate(startDate);
		ma.setState(state);
		ma.setType(type);
		//调用service方法,保存数据
		MarketActivityService mas=(MarketActivityService)ServiceFactory.getService(new MarketActivityServiceImpl());
		int ret=mas.saveCreateMarketActivity(ma);
		//根据处理结果,返回响应信息(json)
		Map<String,Object> map=new HashMap<String,Object>();
		if(ret>0){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
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

package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.domain.PaginationVO;
import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 根据条件分页查询市场活动
 * @author Administrator
 *
 */
public class QueryMarketActivityForPageByConditionController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String pageNoStr=req.getParameter("pageNo");
		String pageSizeStr=req.getParameter("pageSize");
		String name=req.getParameter("name");
		String owner=req.getParameter("owner");
		String type=req.getParameter("type");
		String state=req.getParameter("state");
		String startDate=req.getParameter("startDate");
		String endDate=req.getParameter("endDate");
		//封装参数
		Map<String,Object> map=new HashMap<String,Object>();
		long pageNo=1;
		if(pageNoStr!=null&&pageNoStr.trim().length()>0){
			pageNo=Long.parseLong(pageNoStr.trim());
		}
		int pageSize=10;
		if(pageSizeStr!=null && pageSizeStr.trim().length()>0){
			pageSize=Integer.parseInt(pageSizeStr.trim());
		}
		long beginNo=(pageNo-1)*pageSize;
		map.put("beginNo", beginNo);
		map.put("pageSize", pageSize);
		map.put("name", name);
		map.put("owner", owner);
		map.put("type", type);
		map.put("state", state);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		//调用service方法,查询数据
		MarketActivityService mas=(MarketActivityService)ServiceFactory.getService(new MarketActivityServiceImpl());
		PaginationVO<MarketActivity> vo=mas.queryMarketActivityForPageByCondition(map);
		//根据查询结果,返回响应信息(json)
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
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

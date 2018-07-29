package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 删除市场活动备注
 * @author Administrator
 *
 */
public class DeleteMarketActivityRemarkController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String id=req.getParameter("id");
		//调用service方法,删除数据
		MarketActivityRemarkService mars=(MarketActivityRemarkService)ServiceFactory.getService(new MarketActivityRemarkServiceImpl());
		int ret=mars.deleteActivityRemarkById(id);
		//根据处理结果,返回响应信息(json)
		Map<String,Object> map=new HashMap<String,Object>();
		if(ret>0){
			map.put("success",true);
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

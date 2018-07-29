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
import com.bjpowernode.crm.workbench.clue.service.ClueActivityRelationService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueActivityRelationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 解除线索和市场活动的关联关系
 * @author Administrator
 *
 */
public class SaveUnbundMarketActivityController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String clueId=req.getParameter("clueId");
		String activityId=req.getParameter("activityId");
		//封装参数
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("clueId", clueId);
		map.put("activityId", activityId);
		//调用service 方法,删除数据
		ClueActivityRelationService cars=(ClueActivityRelationService)ServiceFactory.getService(new ClueActivityRelationServiceImpl());
		int ret=cars.deleteClueActivityRelationByClueIdActivtyId(map);
		//根据处理结果,返回响应信息(json)
		Map<String,Object> retMap=new HashMap<String,Object>();
		if(ret>0){
			retMap.put("success", true);
		}else{
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

package com.bjpowernode.crm.workbench.transaction.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 根据阶段名称获取可能性
 * @author Administrator
 *
 */
public class GetPossibilityByStageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String stageText=req.getParameter("stageText");
		//解析配置文件,获取可能性
		/*功能由InitPossibilityContextListener取代
		 * ResourceBundle bundle=ResourceBundle.getBundle("com.bjpowernode.crm.workbench.transaction.web.controller.possibility");
		String value=bundle.getString(stageText);*/
		
		String value=(String)this.getServletContext().getAttribute(stageText);
		//根据解析结果,返回响应信息(json)
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("possibility", value);
		
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

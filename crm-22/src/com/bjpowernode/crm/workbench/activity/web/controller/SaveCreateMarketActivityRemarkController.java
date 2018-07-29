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
import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityRemarkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 保存创建的市场活动备注
 * @author Administrator
 *
 */
public class SaveCreateMarketActivityRemarkController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String noteContent=req.getParameter("noteContent");
		String activityId=req.getParameter("activityId");
		//封装参数
		MarketActivityRemark remark=new MarketActivityRemark();
		remark.setEditFlag(0);
		remark.setId(UUIDUtil.getUUID());
		remark.setMarketingActivitiesId(activityId);
		remark.setNoteContent(noteContent);
		User user=(User)req.getSession().getAttribute("user");
		remark.setNotePerson(user.getId());
		remark.setNoteTime(DateUtil.formatDateTime(new Date()));
		//调用service方法,保存数据 
		MarketActivityRemarkService mars=(MarketActivityRemarkService)ServiceFactory.getService(new MarketActivityRemarkServiceImpl());
		int ret=mars.saveCreateActivityRemark(remark);
		//根据处理结果,返回响应信息(json)
		Map<String,Object> map=new HashMap<String,Object>();
		if(ret>0){
			map.put("success", true);
			map.put("remark", remark);
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

package com.bjpowernode.crm.workbench.clue.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.commons.util.UUIDUtil;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.bjpowernode.crm.workbench.clue.domain.ClueActivityRelation;
import com.bjpowernode.crm.workbench.clue.service.ClueActivityRelationService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueActivityRelationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 保存线索关联市场活动
 * @author Administrator
 *
 */
public class SaveBundMarketActivityController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String[] activityIds=req.getParameterValues("activityId");
		String clueId=req.getParameter("clueId");
		//封装参数(list<Relation>)
		if(activityIds!=null&&activityIds.length>0){
			ClueActivityRelation car=null;
			List<ClueActivityRelation> carList=new ArrayList<ClueActivityRelation>();
			for(String activityId:activityIds){
				car=new ClueActivityRelation();
				car.setActivityId(activityId);
				car.setClueId(clueId);
				car.setId(UUIDUtil.getUUID());
				carList.add(car);
			}
			
			//调用service方法,保存数据 
			ClueActivityRelationService cars=(ClueActivityRelationService)ServiceFactory.getService(new ClueActivityRelationServiceImpl());
			int ret=cars.saveCreateClueActivityRelationByList(carList);
			
			//根据处理结果,返回响应信息(json)
			Map<String,Object> map=new HashMap<String,Object>();
			if(ret>0){
				map.put("success", true);
				
				MarketActivityService mas=(MarketActivityService)ServiceFactory.getService(new MarketActivityServiceImpl());
				List<MarketActivity> activityList=mas.queryMarketActivityByIds(activityIds);
				map.put("activityList", activityList);
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
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

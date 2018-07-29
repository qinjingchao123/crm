package com.bjpowernode.crm.workbench.clue.web.controller;

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
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.service.ClueService;
import com.bjpowernode.crm.workbench.clue.service.impl.ClueServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 保存创建的线索
 * @author Administrator
 *
 */
public class SaveCreateClueController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String owner          =req.getParameter("owner");
		String company        =req.getParameter("company");
		String phone          =req.getParameter("phone");
		String website        =req.getParameter("website");
		String grade          =req.getParameter("grade");
		String industry       =req.getParameter("industry");
		String annualIncome   =req.getParameter("annualIncome");
		String empNums        =req.getParameter("empNums");
		String country        =req.getParameter("country");
		String province       =req.getParameter("province");
		String city           =req.getParameter("city");
		String street         =req.getParameter("street");
		String zipcode        =req.getParameter("zipcode");
		String description    =req.getParameter("description");
		String fullName       =req.getParameter("fullName");
		String appellation    =req.getParameter("appellation");
		String source         =req.getParameter("source");
		String email          =req.getParameter("email");
		String mphone         =req.getParameter("mphone");
		String job            =req.getParameter("job");
		String state          =req.getParameter("state");
		String contactSummary =req.getParameter("contactSummary");
		String nextContactTime=req.getParameter("nextContactTime");
		//封装参数
		Clue c=new Clue();
		if(annualIncome!=null&&annualIncome.trim().length()>0){
			c.setAnnualIncome(Long.parseLong(annualIncome.trim()));
		}
		if(empNums!=null&&empNums.trim().length()>0){
			c.setEmpNums(Integer.parseInt(empNums.trim()));
		}
		c.setAppellation(appellation);
		c.setCity(city);
		c.setCompany(company);
		c.setContactSummary(contactSummary);
		c.setCountry(country);
		c.setCreateBy(((User)req.getSession().getAttribute("user")).getId());
		c.setCreateTime(DateUtil.formatDateTime(new Date()));
		c.setDescription(description);
		c.setEmail(email);
		c.setFullName(fullName);
		c.setGrade(grade);
		c.setId(UUIDUtil.getUUID());
		c.setIndustry(industry);
		c.setJob(job);
		c.setMphone(mphone);
		c.setNextContactTime(nextContactTime);
		c.setOwner(owner);
		c.setPhone(phone);
		c.setProvince(province);
		c.setSource(source);
		c.setState(state);
		c.setStreet(street);
		c.setWebsite(website);
		c.setZipcode(zipcode);
		//调用service方法,保存数据
		ClueService cs=(ClueService)ServiceFactory.getService(new ClueServiceImpl());
		int ret=cs.saveCreateClue(c);
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

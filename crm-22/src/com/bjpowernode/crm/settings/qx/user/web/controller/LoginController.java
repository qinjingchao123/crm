package com.bjpowernode.crm.settings.qx.user.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjpowernode.crm.commons.util.DateUtil;
import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.settings.qx.user.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 用户登录
 * @author Administrator
 *
 */
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String loginAct=req.getParameter("loginAct");
		String loginPwd=req.getParameter("loginPwd");
		String isRemPwd=req.getParameter("isRemPwd");
		//封装参数
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("loginAct", loginAct);
		map.put("loginPwd", loginPwd);
		//调用service方法,查询用户
		UserService us=(UserService)ServiceFactory.getService(new UserServiceImpl());
		User user=us.queryUserByLoginActPwd(map);
		//根据处理结果,返回响应信息
		Map<String,Object> retMap=new HashMap<String,Object>();
		if(user==null){
			//登录失败,账号或者密码错误
			retMap.put("success", false);
			retMap.put("msg", "账号或者密码错误");
		}else{
			
			if(user.getExpireTime()!=null&&DateUtil.parseDateTime(user.getExpireTime()).getTime()<new Date().getTime()){
				//登录失败,账号过期
				retMap.put("success", false);
				retMap.put("msg", "账号已过期!");
			}else if(user.getAllowIps()!=null&&!user.getAllowIps().contains(req.getRemoteAddr())){
				//登录失败,ip受限
				retMap.put("success", false);
				retMap.put("msg", "ip受限!");
			}else if(user.getLockStatus()!=null&&"0".equals(user.getLockStatus())){
				//登录失败,状态被锁住
				retMap.put("success", false);
				retMap.put("msg", "状态被锁住!");
			}else{
				//登录成功 
				retMap.put("success", true);
				
				//把用户保存到session中
				HttpSession session=req.getSession();
				session.setAttribute("user", user);
				
				//如果需要记录密码,把loginAct和loginPwd以cookie的形式保存到客户端
				if("true".equals(isRemPwd)){
					Cookie c1=new Cookie("loginAct",loginAct);
					c1.setMaxAge(60*60*24*10);
					resp.addCookie(c1);
					
					Cookie c2=new Cookie("loginPwd",loginPwd);
					c2.setMaxAge(60*60*24*10);
					resp.addCookie(c2);
				}else{
					Cookie c1=new Cookie("loginAct","");
					c1.setMaxAge(0);
					resp.addCookie(c1);
					
					Cookie c2=new Cookie("loginPwd","");
					c2.setMaxAge(0);
					resp.addCookie(c2);
				}
			}
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

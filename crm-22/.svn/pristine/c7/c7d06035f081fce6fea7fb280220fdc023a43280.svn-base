package com.bjpowernode.crm.settings.qx.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjpowernode.crm.settings.qx.user.domain.User;
/**
 * 登录验证
 * @author Administrator
 *
 */
public class LoginFilter implements Filter {
	private FilterConfig filterConfig;
	/**
	 * 析构方法
	 */
	@Override
	public void destroy() {}
	
	/**
	 * 初始化方法
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		//登录验证
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		
		//如果user存在,则放行
		if(user!=null){
			chain.doFilter(request, servletResponse);
		//如果user不存在
		}else{
			//如果是登录的请求,放行
			if("/settings/qx/user/login.jsp".equals(request.getServletPath())||"/settings/qx/user/login.do".equals(request.getServletPath())){
				chain.doFilter(request, servletResponse);
			//如果不是登录的请求,拦截
			}else{
				response.sendRedirect(request.getContextPath());
			}
		}
	}

}

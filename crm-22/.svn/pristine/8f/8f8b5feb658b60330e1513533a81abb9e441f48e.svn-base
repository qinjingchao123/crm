package com.bjpowernode.crm.commons.web.listener;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 初始化可能性
 * @author Administrator
 *
 */
public class InitPossibilityContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//解析配置文件,缓存数据
		ResourceBundle bundle=ResourceBundle.getBundle("possibility");
		Enumeration<String> keys=bundle.getKeys();
		
		while(keys.hasMoreElements()){
			String key=keys.nextElement();
			String value=bundle.getString(key);
			
			sce.getServletContext().setAttribute(key, value);
		}
	}

}

package com.bjpowernode.crm.commons.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.settings.dictionary.value.domain.DictionaryValue;
import com.bjpowernode.crm.settings.dictionary.value.service.DictionaryValueService;
import com.bjpowernode.crm.settings.dictionary.value.service.impl.DictionaryValueServiceImpl;
/**
 * 缓存市场活动类型
 * @author Administrator
 *
 */
public class QueryMarketActivityTypeContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("========缓存市场活动类型==========");
		//查询市场活动类型
		DictionaryValueService dvs=(DictionaryValueService)ServiceFactory.getService(new DictionaryValueServiceImpl());
		List<DictionaryValue> marketActivityTypeList=dvs.queryDictionaryValueByTypeCode("marketActivityType");
		//把marketActivityTypeList保存到缓存中
		ServletContext context=sce.getServletContext();
		context.setAttribute("marketActivityTypeList", marketActivityTypeList);
	}
	
}

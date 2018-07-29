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
 * 缓存行业
 * @author Administrator
 *
 */
public class QueryIndustryContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//查询行业
		DictionaryValueService dvs=(DictionaryValueService)ServiceFactory.getService(new DictionaryValueServiceImpl());
		List<DictionaryValue> industryList=dvs.queryDictionaryValueByTypeCode("industry");
		//把industryList保存到缓存中
		ServletContext context=sce.getServletContext();
		context.setAttribute("industryList", industryList);
	}
	
}

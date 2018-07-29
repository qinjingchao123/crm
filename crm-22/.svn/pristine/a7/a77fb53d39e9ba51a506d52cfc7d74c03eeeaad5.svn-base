package com.bjpowernode.crm.commons.util;

public class ServiceFactory {
	public static Object getService(Object service){
		return new TransactionInvocationHandler(service).getProxyService();
	}
}

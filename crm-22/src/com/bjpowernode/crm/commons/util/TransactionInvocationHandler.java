package com.bjpowernode.crm.commons.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;

public class TransactionInvocationHandler implements InvocationHandler {
	private Object target;
	public TransactionInvocationHandler(Object target){
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret=null;
		SqlSession session=SqlSessionUtil.getSqlSession();
		try{
			ret=method.invoke(target, args);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
			throw e;
		}finally{
			SqlSessionUtil.closeSqlSession(session);
		}
		return ret;
	}
	
	public Object getProxyService(){
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

}

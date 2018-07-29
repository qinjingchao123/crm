package com.bjpowernode.crm.commons.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSession工具类
 * @author Administrator
 *
 */
public class SqlSessionUtil {
	private static SqlSessionFactory factory=null;
	private static ThreadLocal<SqlSession> loc=new ThreadLocal<SqlSession>();
	static{
		InputStream is=null;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory=new SqlSessionFactoryBuilder().build(is);
	}
	/**
	 * 获取SqlSession
	 * @return
	 */
	public static SqlSession getSqlSession(){
		SqlSession session=loc.get();
		if(session==null){
			session=factory.openSession();
			loc.set(session);
		}
		
		return session;
	}
	/**
	 * 关闭SqlSession
	 * @param session
	 */
	public static void closeSqlSession(SqlSession session){
		if(session!=null){
			session.close();
			loc.remove();
		}
	}
}

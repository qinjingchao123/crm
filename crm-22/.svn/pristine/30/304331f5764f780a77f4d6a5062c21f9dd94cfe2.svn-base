package com.bjpowernode.crm.workbench.contacts.dao;

import java.util.Map;

import com.bjpowernode.crm.workbench.contacts.domain.Contacts;

/**
 * 联系人持久化处理接口
 * @author Administrator
 *
 */
public interface ContactsDao {
	/**
	 * 保存创建的联系人
	 * @param contacts
	 * @return
	 */
	public int saveCreateContacts(Contacts contacts);
	/**
	 * 根据fullName和mphone精确查询联系人
	 * @param map
	 * @return
	 */
	public Contacts queryContactsByFullNameMphone(Map<String,Object> map);
}

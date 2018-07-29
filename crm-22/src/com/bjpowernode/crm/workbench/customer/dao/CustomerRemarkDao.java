package com.bjpowernode.crm.workbench.customer.dao;

import java.util.List;

import com.bjpowernode.crm.workbench.customer.domain.CustomerRemark;

/**
 * 客户备注持久化操作接口
 * @author Administrator
 *
 */
public interface CustomerRemarkDao {
	/**
	 * 批量保存创建的客户备注
	 * @param list
	 * @return
	 */
	public int saveCreateCustomerRemarkByList(List<CustomerRemark> list);
}

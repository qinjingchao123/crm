package com.bjpowernode.crm.workbench.customer.dao;

import java.util.List;

import com.bjpowernode.crm.workbench.customer.domain.Customer;

/**
 * 客户持久化处理接口
 * @author Administrator
 *
 */
public interface CustomerDao {
	/**
	 * 保存创建的客户
	 * @param customer
	 * @return
	 */
	public int saveCreateCustomer(Customer customer);
	/**
	 * 根据name精确查询客户
	 * @param name
	 * @return
	 */
	public Customer queryCustomerByName(String name);
	/**
	 * 根据名称模糊查询客户
	 * @param name
	 * @return
	 */
	public List<Customer> queryCustomerForTranByName(String name);
}

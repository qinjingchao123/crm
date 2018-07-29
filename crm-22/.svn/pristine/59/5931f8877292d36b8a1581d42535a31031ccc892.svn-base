package com.bjpowernode.crm.workbench.customer.service.impl;

import java.util.List;

import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.workbench.customer.dao.CustomerDao;
import com.bjpowernode.crm.workbench.customer.domain.Customer;
import com.bjpowernode.crm.workbench.customer.service.CustomerService;
/**
 * 客户业务处理类
 * @author Administrator
 *
 */
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao=SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
	/**
	 * 根据名称模糊查询客户
	 */
	@Override
	public List<Customer> queryCustomerForTranByName(String name) {
		// TODO Auto-generated method stub
		return customerDao.queryCustomerForTranByName(name);
	}

}

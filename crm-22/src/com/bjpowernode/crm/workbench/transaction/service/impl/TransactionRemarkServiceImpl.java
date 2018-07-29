package com.bjpowernode.crm.workbench.transaction.service.impl;

import java.util.List;

import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.workbench.transaction.dao.TransactionRemarkDao;
import com.bjpowernode.crm.workbench.transaction.domain.TransactionRemark;
import com.bjpowernode.crm.workbench.transaction.service.TransactionRemarkService;
/**
 * 交易备注业务处理类
 * @author Administrator
 *
 */
public class TransactionRemarkServiceImpl implements TransactionRemarkService {
	private TransactionRemarkDao transactionRemarkDao=SqlSessionUtil.getSqlSession().getMapper(TransactionRemarkDao.class);
	/**
	 * 根据tranId查询该交易下所有的备注
	 */
	@Override
	public List<TransactionRemark> queryTransactionRemarkByTranId(String tranId) {
		// TODO Auto-generated method stub
		return transactionRemarkDao.queryTransactionRemarkByTranId(tranId);
	}

}

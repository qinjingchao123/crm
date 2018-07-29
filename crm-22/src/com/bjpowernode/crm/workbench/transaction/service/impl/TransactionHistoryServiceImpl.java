package com.bjpowernode.crm.workbench.transaction.service.impl;

import java.util.List;

import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.workbench.transaction.dao.TransactionHistoryDao;
import com.bjpowernode.crm.workbench.transaction.domain.TransactionHistory;
import com.bjpowernode.crm.workbench.transaction.service.TransactionHistoryService;
/**
 * 交易历史业务处理类
 * @author Administrator
 *
 */
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
	private TransactionHistoryDao transactionHistoryDao=SqlSessionUtil.getSqlSession().getMapper(TransactionHistoryDao.class);
	/**
	 * 根据tranId查询该交易下所有的历史记录
	 */
	@Override
	public List<TransactionHistory> queryTransactionHistoryByTranId(String tranId) {
		// TODO Auto-generated method stub
		return transactionHistoryDao.queryTransactionHistoryByTranId(tranId);
	}

}

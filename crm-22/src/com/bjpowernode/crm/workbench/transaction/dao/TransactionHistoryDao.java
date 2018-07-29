package com.bjpowernode.crm.workbench.transaction.dao;

import java.util.List;

import com.bjpowernode.crm.workbench.transaction.domain.TransactionHistory;

/**
 * 交易历史持久化处理接口
 * @author Administrator
 *
 */
public interface TransactionHistoryDao {
	/**
	 * 保存创建的交易历史
	 * @param th
	 * @return
	 */
	public int saveCreateTransactionHistory(TransactionHistory th);
	/**
	 * 根据tranId查询该交易下所有的历史记录
	 * @param tranId
	 * @return
	 */
	public List<TransactionHistory> queryTransactionHistoryByTranId(String tranId);
}

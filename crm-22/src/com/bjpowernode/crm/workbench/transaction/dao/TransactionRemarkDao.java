package com.bjpowernode.crm.workbench.transaction.dao;

import java.util.List;

import com.bjpowernode.crm.workbench.transaction.domain.TransactionRemark;

/**
 * 交易备注持久化操作接口
 * @author Administrator
 *
 */
public interface TransactionRemarkDao {
	/**
	 * 批量保存创建的交易备注
	 * @param list
	 * @return
	 */
	public int saveCreateTransactionRemarkByList(List<TransactionRemark> list);
	/**
	 * 根据tranId查询该交易下所有的备注
	 * @param tranId
	 * @return
	 */
	public List<TransactionRemark> queryTransactionRemarkByTranId(String tranId);
}

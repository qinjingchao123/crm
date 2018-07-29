package com.bjpowernode.crm.workbench.transaction.service;

import java.util.List;

import com.bjpowernode.crm.workbench.transaction.domain.TransactionRemark;

/**
 * 交易备注业务处理接口
 * @author Administrator
 *
 */
public interface TransactionRemarkService {
	/**
	 * 根据tranId查询该交易下所有的备注
	 * @param tranId
	 * @return
	 */
	public List<TransactionRemark> queryTransactionRemarkByTranId(String tranId);
}

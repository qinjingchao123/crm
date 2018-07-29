package com.bjpowernode.crm.workbench.transaction.dao;

import java.util.List;

import com.bjpowernode.crm.commons.domain.FunnelVO;
import com.bjpowernode.crm.workbench.transaction.domain.Transaction;

/**
 * 交易持久化处理接口
 * @author Administrator
 *
 */
public interface TransactionDao {
	/**
	 * 保存创建的交易
	 * @param tran
	 * @return
	 */
	public int saveCreateTransaction(Transaction tran);
	/**
	 * 根据id查询交易明细信息
	 * @param id
	 * @return
	 */
	public Transaction queryTransactionForDetailById(String id);
	/**
	 * 查询交易表中各个阶段的数据量
	 * @return
	 */
	public List<FunnelVO> queryTransactionGroupByStage();
}

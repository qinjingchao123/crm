package com.bjpowernode.crm.workbench.transaction.service;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.domain.FunnelVO;
import com.bjpowernode.crm.workbench.transaction.domain.Transaction;

/**
 * 交易业务处理接口
 * @author Administrator
 *
 */
public interface TransactionService {
	/**
	 * 保存创建的交易
	 * @param map
	 * @return
	 */
	public int saveCreateTransaction(Map<String,Object> map);
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

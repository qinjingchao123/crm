package com.bjpowernode.crm.workbench.clue.dao;

import java.util.List;

import com.bjpowernode.crm.workbench.clue.domain.ClueRemark;

/**
 * 线索备注持久化处理接口
 * @author Administrator
 *
 */
public interface ClueRemarkDao {
	/**
	 * 根据clueId查询该线索下所有的备注明细信息
	 * @param clueId
	 * @return
	 */
	public List<ClueRemark> queryClueRemarkByClueId(String clueId);
	/**
	 * 根据clueId查询该线索下所有的备注信息
	 * @param clueId
	 * @return
	 */
	public List<ClueRemark> queryClueRemarkForConvertByClueId(String clueId);
	/**
	 * 根据clueId删除该线索下所有的备注
	 * @param clueId
	 * @return
	 */
	public int deleteClueRemarkByClueId(String clueId);
}

package com.bjpowernode.crm.workbench.clue.dao;

import com.bjpowernode.crm.workbench.clue.domain.Clue;

/**
 * 线索持久化处理接口
 * @author Administrator
 *
 */
public interface ClueDao {
	/**
	 * 保存创建的线索
	 * @param clue
	 * @return
	 */
	public int saveCreateClue(Clue clue);
	/**
	 * 根据id查询线索明细信息
	 * @param id
	 * @return
	 */
	public Clue queryClueForDetailById(String id);
	/**
	 * 根据id查询线索信息
	 * @param id
	 * @return
	 */
	public Clue queryClueById(String id);
	/**
	 * 根据id删除线索
	 * @param id
	 * @return
	 */
	public int deleteClueById(String id);
}

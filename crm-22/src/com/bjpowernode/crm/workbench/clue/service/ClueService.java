package com.bjpowernode.crm.workbench.clue.service;

import java.util.Map;

import com.bjpowernode.crm.workbench.clue.domain.Clue;

/**
 * 线索业务处理接口
 * @author Administrator
 *
 */
public interface ClueService {
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
	 * 保存线索转换
	 * @param map
	 * @return
	 */
	public int saveConvertClue(Map<String,Object> map);
}

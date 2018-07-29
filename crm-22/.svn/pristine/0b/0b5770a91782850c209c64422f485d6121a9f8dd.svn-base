package com.bjpowernode.crm.workbench.clue.dao;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.workbench.clue.domain.ClueActivityRelation;

/**
 * 线索和市场活动关联关系持久化处理接口
 * @author Administrator
 *
 */
public interface ClueActivityRelationDao {
	/**
	 * 批量保存线索和活动的关联关系
	 * @param list
	 * @return
	 */
	public int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list);
	/**
	 * 根据clueId和activityId删除线索和市场活动的关联关系
	 * @param map
	 * @return
	 */
	public int deleteClueActivityRelationByClueIdActivtyId(Map<String,Object> map);
	/**
	 * 根据clueId查询线索和市场活动的关联关系
	 * @param clueId
	 * @return
	 */
	public List<ClueActivityRelation> queryClueActivityRelationByClueId(String clueId);
	/**
	 * 根据clueId删除线索和市场活动的关联关系
	 * @param clueId
	 * @return
	 */
	public int deleteClueActivityRelationByClueId(String clueId);
}

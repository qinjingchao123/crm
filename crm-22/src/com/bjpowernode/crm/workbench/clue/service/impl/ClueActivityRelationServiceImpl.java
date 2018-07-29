package com.bjpowernode.crm.workbench.clue.service.impl;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.workbench.clue.dao.ClueActivityRelationDao;
import com.bjpowernode.crm.workbench.clue.domain.ClueActivityRelation;
import com.bjpowernode.crm.workbench.clue.service.ClueActivityRelationService;
/**
 * 线索和市场活动关联关系业务处理类
 * @author Administrator
 *
 */
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {
	private ClueActivityRelationDao clueActivityRelationDao=SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);
	/**
	 * 批量吧奥村线索和市场活动关联关系
	 */
	@Override
	public int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list) {
		// TODO Auto-generated method stub
		return clueActivityRelationDao.saveCreateClueActivityRelationByList(list);
	}
	/**
	 * 根据clueId和activityId删除线索和市场活动的关联关系
	 */
	@Override
	public int deleteClueActivityRelationByClueIdActivtyId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return clueActivityRelationDao.deleteClueActivityRelationByClueIdActivtyId(map);
	}

}

package com.bjpowernode.crm.workbench.clue.service.impl;

import java.util.List;

import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.workbench.clue.dao.ClueRemarkDao;
import com.bjpowernode.crm.workbench.clue.domain.ClueRemark;
import com.bjpowernode.crm.workbench.clue.service.ClueRemarkService;
/**
 * 线索备注业务处理类
 * @author Administrator
 *
 */
public class ClueRemarkServiceImpl implements ClueRemarkService {
	private ClueRemarkDao clueRemarkDao=SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);
	/**
	 * 根据clueId查询该线索下所有的备注
	 */
	@Override
	public List<ClueRemark> queryClueRemarkByClueId(String clueId) {
		// TODO Auto-generated method stub
		return clueRemarkDao.queryClueRemarkByClueId(clueId);
	}

}

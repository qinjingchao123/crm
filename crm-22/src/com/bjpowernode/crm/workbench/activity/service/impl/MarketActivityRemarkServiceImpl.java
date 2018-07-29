package com.bjpowernode.crm.workbench.activity.service.impl;

import java.util.List;

import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.workbench.activity.dao.MarketActivityRemarkDao;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;

public class MarketActivityRemarkServiceImpl implements MarketActivityRemarkService{
	private MarketActivityRemarkDao marketActivityRemarkDao=SqlSessionUtil.getSqlSession().getMapper(MarketActivityRemarkDao.class);
	/**
	 * 根据activityId查询该市场活动下所有的备注
	 */
	@Override
	public List<MarketActivityRemark> queryActivityRemarkByActivityId(String activityId) {
		// TODO Auto-generated method stub
		return marketActivityRemarkDao.queryActivityRemarkByActivityId(activityId);
	}
	/**
	 * 保存创建的市场活动备注
	 */
	@Override
	public int saveCreateActivityRemark(MarketActivityRemark remark) {
		// TODO Auto-generated method stub
		return marketActivityRemarkDao.saveCreateActivityRemark(remark);
	}
	/**
	 * 根据id删除市场活动备注
	 */
	@Override
	public int deleteActivityRemarkById(String id) {
		// TODO Auto-generated method stub
		return marketActivityRemarkDao.deleteActivityRemarkById(id);
	}
	/**
	 * 保存修改的市场活动备注
	 */
	@Override
	public int saveEditActivityRemark(MarketActivityRemark remark) {
		// TODO Auto-generated method stub
		return marketActivityRemarkDao.saveEditActivityRemark(remark);
	}

}

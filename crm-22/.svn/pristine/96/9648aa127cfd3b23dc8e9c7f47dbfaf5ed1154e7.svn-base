package com.bjpowernode.crm.workbench.activity.dao;

import java.util.List;

import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;

/**
 * 市场活动备注持久化处理接口
 * @author Administrator
 *
 */
public interface MarketActivityRemarkDao {
	/**
	 * 根据activityId查询该市场活动下所有的备注
	 * @param activityId
	 * @return
	 */
	public List<MarketActivityRemark> queryActivityRemarkByActivityId(String activityId);
	/**
	 * 保存创建的市场活动备注
	 * @param remark
	 * @return
	 */
	public int saveCreateActivityRemark(MarketActivityRemark remark);
	/**
	 * 根据id删除市场活动备注
	 * @param id
	 * @return
	 */
	public int deleteActivityRemarkById(String id);
	/**
	 * 保存修改的市场活动备注
	 * @param remark
	 * @return
	 */
	public int saveEditActivityRemark(MarketActivityRemark remark);
	/**
	 * 根据activityIds删除这些市场活动下所有的备注
	 * @param activityIds
	 * @return
	 */
	public int deleteActivityRemarkByAcvtivtyIds(String[] activityIds);
}

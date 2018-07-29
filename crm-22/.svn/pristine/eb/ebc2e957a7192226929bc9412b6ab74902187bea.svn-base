package com.bjpowernode.crm.workbench.activity.service.impl;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.domain.PaginationVO;
import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.workbench.activity.dao.MarketActivityDao;
import com.bjpowernode.crm.workbench.activity.dao.MarketActivityRemarkDao;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
/**
 * 市场活动业务处理类
 * @author Administrator
 *
 */
public class MarketActivityServiceImpl implements MarketActivityService {
	private MarketActivityDao marketActivityDao=SqlSessionUtil.getSqlSession().getMapper(MarketActivityDao.class);
	private MarketActivityRemarkDao marketActivityRemarkDao=SqlSessionUtil.getSqlSession().getMapper(MarketActivityRemarkDao.class);
	/**
	 * 保存创建的市场活动
	 */
	@Override
	public int saveCreateMarketActivity(MarketActivity activity) {
		// TODO Auto-generated method stub
		return marketActivityDao.saveCreateMarketActivity(activity);
	}
	/**
	 * 根据条件分页查询市场活动
	 */
	@Override
	public PaginationVO<MarketActivity> queryMarketActivityForPageByCondition(Map<String, Object> map) {
		//调用dao方法,查询数据
		List<MarketActivity> activityList=marketActivityDao.queryMarketActivityForPageByCondition(map);
		long count=marketActivityDao.queryCountOfMarketActivityByCondition(map);
		//把数据封装成vo
		PaginationVO<MarketActivity> vo=new PaginationVO<MarketActivity>();
		vo.setCount(count);
		vo.setDataList(activityList);
		
		return vo;
	}
	/**
	 * 根据ids删除市场活动
	 */
	@Override
	public int deleteMarketActivityByIds(String[] ids) {
		// TODO Auto-generated method stub
		//先删除这些市场活动下所有的备注
		marketActivityRemarkDao.deleteActivityRemarkByAcvtivtyIds(ids);
		
		return marketActivityDao.deleteMarketActivityByIds(ids);
	}
	/**
	 * 根据id查询市场活动信息
	 */
	@Override
	public MarketActivity queryMarketActivityById(String id) {
		// TODO Auto-generated method stub
		return marketActivityDao.queryMarketActivityById(id);
	}
	/**
	 * 保存修改的市场活动
	 */
	@Override
	public int saveEditMarketActivity(MarketActivity activity) {
		// TODO Auto-generated method stub
		return marketActivityDao.saveEditMarketActivity(activity);
	}
	/**
	 * 根据id查询市场活动明细信息
	 */
	@Override
	public MarketActivity queryMarketActivityForDetailById(String id) {
		// TODO Auto-generated method stub
		return marketActivityDao.queryMarketActivityForDetailById(id);
	}
	/**
	 * 根据条件查询市场活动
	 */
	@Override
	public List<MarketActivity> queryMarketActivityByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return marketActivityDao.queryMarketActivityByCondition(map);
	}
	/**
	 * 批量保存创建的市场活动
	 */
	@Override
	public int saveCreateMarketActivityByList(List<MarketActivity> list) {
		// TODO Auto-generated method stub
		return marketActivityDao.saveCreateMarketActivityByList(list);
	}
	/**
	 * 根据clueId查询跟该线索相关联的市场活动
	 */
	@Override
	public List<MarketActivity> queryMarketActivityByClueId(String clueId) {
		// TODO Auto-generated method stub
		return marketActivityDao.queryMarketActivityByClueId(clueId);
	}
	/**
	 * 根据名称模糊查询市场活动,并且把已经关联过的市场活动排除
	 */
	@Override
	public List<MarketActivity> queryMarketActivityByNameClueId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return marketActivityDao.queryMarketActivityByNameClueId(map);
	}
	/**
	 * 根据ids查询市场活动
	 */
	@Override
	public List<MarketActivity> queryMarketActivityByIds(String[] ids) {
		// TODO Auto-generated method stub
		return marketActivityDao.queryMarketActivityByIds(ids);
	}

}

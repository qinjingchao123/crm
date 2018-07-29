package com.bjpowernode.crm.workbench.activity.service;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.domain.PaginationVO;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;

/**
 * 市场活动业务处理接口
 * @author Administrator
 *
 */
public interface MarketActivityService {
	/**
	 * 保存创建的市场活动
	 * @param activity
	 * @return
	 */
	public int saveCreateMarketActivity(MarketActivity activity);
	/**
	 * 根据条件分页查询市场活动
	 * @param map
	 * @return
	 */
	public PaginationVO<MarketActivity> queryMarketActivityForPageByCondition(Map<String,Object> map);
	/**
	 * 根据ids删除市场活动
	 * @param ids
	 * @return
	 */
	public int deleteMarketActivityByIds(String[] ids);
	/**
	 * 根据id查询市场活动信息
	 * @param id
	 * @return
	 */
	public MarketActivity queryMarketActivityById(String id);
	/**
	 * 保存修改的市场活动
	 * @param activity
	 * @return
	 */
	public int saveEditMarketActivity(MarketActivity activity);
	/**
	 * 根据id查询市场活动明细信息
	 * @param id
	 * @return
	 */
	public MarketActivity queryMarketActivityForDetailById(String id);
	/**
	 * 根据条件查询市场活动
	 * @param map
	 * @return
	 */
	public List<MarketActivity> queryMarketActivityByCondition(Map<String,Object> map);
	/**
	 * 批量保存创建的市场活动备注
	 * @param list
	 * @return
	 */
	public int saveCreateMarketActivityByList(List<MarketActivity> list);
	/**
	 * 根据clueId查询跟该线索相关联的市场活动
	 * @param clueId
	 * @return
	 */
	public List<MarketActivity> queryMarketActivityByClueId(String clueId);
	/**
	 * 根据名称模糊查询市场活动,并且把已经关联过的市场都会排除
	 * @param map
	 * @return
	 */
	public List<MarketActivity> queryMarketActivityByNameClueId(Map<String,Object> map);
	/**
	 * 根据ids查询市场活动
	 * @param ids
	 * @return
	 */
	public List<MarketActivity> queryMarketActivityByIds(String[] ids);
}

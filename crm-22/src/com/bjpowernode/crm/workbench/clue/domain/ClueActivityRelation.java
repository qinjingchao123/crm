package com.bjpowernode.crm.workbench.clue.domain;

/**
 * 线索和市场活动关联关系实体类
 * 
 * @author Administrator
 *
 */
public class ClueActivityRelation {
	private String id;
	private String clueId;
	private String activityId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClueId() {
		return clueId;
	}

	public void setClueId(String clueId) {
		this.clueId = clueId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

}

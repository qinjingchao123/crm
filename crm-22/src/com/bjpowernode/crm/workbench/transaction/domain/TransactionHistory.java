package com.bjpowernode.crm.workbench.transaction.domain;

/**
 * 交易历史实体类
 * 
 * @author Administrator
 *
 */
public class TransactionHistory {
	private String id;
	private String stage;
	private long amountOfMoney;
	private String expectedClosingDate;
	private String editTime;
	private String editBy;
	private String transactionId;
	
	private String possibility;//为了显示可能性,扩展属性
	private int orderNo;//为了显示阶段图标,扩展的属性
	
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getPossibility() {
		return possibility;
	}

	public void setPossibility(String possibility) {
		this.possibility = possibility;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public long getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(long amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public String getExpectedClosingDate() {
		return expectedClosingDate;
	}

	public void setExpectedClosingDate(String expectedClosingDate) {
		this.expectedClosingDate = expectedClosingDate;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getEditBy() {
		return editBy;
	}

	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}

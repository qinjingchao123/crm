package com.bjpowernode.crm.workbench.transaction.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.domain.FunnelVO;
import com.bjpowernode.crm.commons.util.DateUtil;
import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.commons.util.UUIDUtil;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.workbench.customer.dao.CustomerDao;
import com.bjpowernode.crm.workbench.customer.domain.Customer;
import com.bjpowernode.crm.workbench.transaction.dao.TransactionDao;
import com.bjpowernode.crm.workbench.transaction.dao.TransactionHistoryDao;
import com.bjpowernode.crm.workbench.transaction.domain.Transaction;
import com.bjpowernode.crm.workbench.transaction.domain.TransactionHistory;
import com.bjpowernode.crm.workbench.transaction.service.TransactionService;
/**
 * 交易业务处理类
 * @author Administrator
 *
 */
public class TransactionServiceImpl implements TransactionService {
	private CustomerDao customerDao=SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
	private TransactionDao transactionDao=SqlSessionUtil.getSqlSession().getMapper(TransactionDao.class);
	private TransactionHistoryDao transactionHistoryDao=SqlSessionUtil.getSqlSession().getMapper(TransactionHistoryDao.class);
	/**
	 * 保存创建的交易
	 */
	@Override
	public int saveCreateTransaction(Map<String, Object> map) {
		String customerId=(String)map.get("customerId");
		User user=(User)map.get("user");
		String customerName=(String)map.get("customerName");
		//如果需要创建客户,则保存客户
		if(customerId==null||customerId.length()==0){
			Customer c=new Customer();
			c.setCreateBy(user.getId());
			c.setCreateTime(DateUtil.formatDateTime(new Date()));
			c.setId(UUIDUtil.getUUID());
			c.setName(customerName);
			c.setOwner(user.getId());
			customerDao.saveCreateCustomer(c);
			
			customerId=c.getId();
		}
		//保存交易
		Transaction tran=new Transaction();
		tran.setActivityId((String)map.get("activityId"));
		String amountOfMoney=(String)map.get("amountOfMoney");
		if(amountOfMoney!=null&&amountOfMoney.trim().length()>0){
			tran.setAmountOfMoney(Long.parseLong(amountOfMoney.trim()));
		}
		tran.setContactsId((String)map.get("contactsId"));
		tran.setContactSummary((String)map.get("contactSummary"));
		tran.setCreateBy(user.getId());
		tran.setCreateTime(DateUtil.formatDateTime(new Date()));
		tran.setCustomerId(customerId);
		tran.setDescription((String)map.get("description"));
		tran.setExpectedClosingDate((String)map.get("expectedClosingDate"));
		tran.setId(UUIDUtil.getUUID());
		tran.setName((String)map.get("name"));
		tran.setNextContactTime((String)map.get("nextContactTime"));
		tran.setOwner((String)map.get("owner"));
		tran.setSource((String)map.get("source"));
		tran.setStage((String)map.get("stage"));
		tran.setType((String)map.get("type"));
		transactionDao.saveCreateTransaction(tran);
		//保存交易历史记录
		TransactionHistory th=new TransactionHistory();
		th.setAmountOfMoney(tran.getAmountOfMoney());
		th.setEditBy(user.getId());
		th.setEditTime(DateUtil.formatDateTime(new Date()));
		th.setExpectedClosingDate(tran.getExpectedClosingDate());
		th.setId(UUIDUtil.getUUID());
		th.setStage(tran.getStage());
		th.setTransactionId(tran.getId());
		transactionHistoryDao.saveCreateTransactionHistory(th);
		return 0;
	}
	/**
	 * 根据id查询交易明细信息
	 */
	@Override
	public Transaction queryTransactionForDetailById(String id) {
		// TODO Auto-generated method stub
		return transactionDao.queryTransactionForDetailById(id);
	}
	/**
	 * 查询交易表中各个阶段的数据量
	 */
	@Override
	public List<FunnelVO> queryTransactionGroupByStage() {
		// TODO Auto-generated method stub
		return transactionDao.queryTransactionGroupByStage();
	}

}

package com.bjpowernode.crm.workbench.clue.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.util.DateUtil;
import com.bjpowernode.crm.commons.util.SqlSessionUtil;
import com.bjpowernode.crm.commons.util.UUIDUtil;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.workbench.clue.dao.ClueActivityRelationDao;
import com.bjpowernode.crm.workbench.clue.dao.ClueDao;
import com.bjpowernode.crm.workbench.clue.dao.ClueRemarkDao;
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.domain.ClueActivityRelation;
import com.bjpowernode.crm.workbench.clue.domain.ClueRemark;
import com.bjpowernode.crm.workbench.clue.service.ClueService;
import com.bjpowernode.crm.workbench.contacts.dao.ContactsActivityRelationDao;
import com.bjpowernode.crm.workbench.contacts.dao.ContactsDao;
import com.bjpowernode.crm.workbench.contacts.dao.ContactsRemarkDao;
import com.bjpowernode.crm.workbench.contacts.domain.Contacts;
import com.bjpowernode.crm.workbench.contacts.domain.ContactsActivityRelation;
import com.bjpowernode.crm.workbench.contacts.domain.ContactsRemark;
import com.bjpowernode.crm.workbench.customer.dao.CustomerDao;
import com.bjpowernode.crm.workbench.customer.dao.CustomerRemarkDao;
import com.bjpowernode.crm.workbench.customer.domain.Customer;
import com.bjpowernode.crm.workbench.customer.domain.CustomerRemark;
import com.bjpowernode.crm.workbench.transaction.dao.TransactionDao;
import com.bjpowernode.crm.workbench.transaction.dao.TransactionRemarkDao;
import com.bjpowernode.crm.workbench.transaction.domain.Transaction;
import com.bjpowernode.crm.workbench.transaction.domain.TransactionRemark;
/**
 * 线索业务处理类
 * @author Administrator
 *
 */
public class ClueServiceImpl implements ClueService {
	private ClueDao clueDao=SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
	private CustomerDao customerDao=SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
	private ContactsDao contactsDao=SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);
	private ClueRemarkDao clueRemarkDao=SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);
	private CustomerRemarkDao customerRemarkDao=SqlSessionUtil.getSqlSession().getMapper(CustomerRemarkDao.class);
	private ContactsRemarkDao contactsRemarkDao=SqlSessionUtil.getSqlSession().getMapper(ContactsRemarkDao.class);
	private ClueActivityRelationDao clueActivityRelationDao=SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);
	private ContactsActivityRelationDao contactsActivityRelationDao=SqlSessionUtil.getSqlSession().getMapper(ContactsActivityRelationDao.class);
	private TransactionDao transactionDao=SqlSessionUtil.getSqlSession().getMapper(TransactionDao.class);
	private TransactionRemarkDao transactionRemarkDao=SqlSessionUtil.getSqlSession().getMapper(TransactionRemarkDao.class);
	/**
	 * 保存创建的线索
	 */
	@Override
	public int saveCreateClue(Clue clue) {
		// TODO Auto-generated method stub
		return clueDao.saveCreateClue(clue);
	}
	/**
	 * 根据id查询线索明细信息
	 */
	@Override
	public Clue queryClueForDetailById(String id) {
		// TODO Auto-generated method stub
		return clueDao.queryClueForDetailById(id);
	}
	/**
	 * 保存线索转换
	 */
	@Override
	public int saveConvertClue(Map<String, Object> map) {
		User user=(User)map.get("user");
		//根据clueId查询线索信息
		String clueId=(String)map.get("clueId");
		Clue clue=clueDao.queryClueById(clueId);
		//把线索中有关公司的信息转换到客户表中
		Customer c=customerDao.queryCustomerByName(clue.getCompany());
		if(c==null){
			c=new Customer();
			c.setAnnualIncome(clue.getAnnualIncome());
			c.setCity(clue.getCity());
			c.setCountry(clue.getCountry());
			c.setCreateBy(user.getId());
			c.setCreateTime(DateUtil.formatDateTime(new Date()));
			c.setDescription(clue.getDescription());
			c.setEmpNums(clue.getEmpNums());
			c.setGrade(clue.getGrade());
			c.setId(UUIDUtil.getUUID());
			c.setIndustry(clue.getIndustry());
			c.setName(clue.getCompany());
			c.setOwner(user.getId());
			c.setPhone(clue.getPhone());
			c.setProvince(clue.getProvince());
			c.setStreet(clue.getStreet());
			c.setWebsite(clue.getWebsite());
			c.setZipcode(clue.getZipcode());
			int ret1=customerDao.saveCreateCustomer(c);
			System.out.println("保存客户ret1="+ret1);
		}
		
		//把线索中有关个人的信息转换到联系人表中
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("fullName", clue.getFullName());
		m.put("mphone", clue.getMphone());
		Contacts co=contactsDao.queryContactsByFullNameMphone(m);
		if(co==null){
			co=new Contacts();
			co.setAppellation(clue.getAppellation());
			co.setCity(clue.getCity());
			co.setContactSummary(clue.getContactSummary());
			co.setCountry(clue.getCountry());
			co.setCreateBy(user.getId());
			co.setCreateTime(DateUtil.formatDateTime(new Date()));
			co.setCustomerId(c.getId());
			co.setDescription(clue.getDescription());
			co.setEmail(clue.getEmail());
			co.setFullName(clue.getFullName());
			co.setId(UUIDUtil.getUUID());
			co.setJob(clue.getJob());
			co.setMphone(clue.getMphone());
			co.setOwner(user.getId());
			co.setProvince(clue.getProvince());
			co.setSource(clue.getSource());
			co.setStreet(clue.getStreet());
			co.setZipcode(clue.getZipcode());
			int ret2=contactsDao.saveCreateContacts(co);
			System.out.println("保存联系人ret2="+ret2);
		}
		
		//根据clueId查询线索备注信息
		List<ClueRemark> crList=clueRemarkDao.queryClueRemarkForConvertByClueId(clueId);
		
		//把线索的备注信息转换到客户备注表中一份
		if(crList!=null&&crList.size()>0){
			CustomerRemark cur=null;
			List<CustomerRemark> curList=new ArrayList<CustomerRemark>();
			
			ContactsRemark cor=null;
			List<ContactsRemark> corList=new ArrayList<ContactsRemark>();
			for(ClueRemark cr:crList){
				cur=new CustomerRemark();
				cur.setCustomerId(c.getId());
				cur.setEditFlag(cr.getEditFlag());
				cur.setEditPerson(cr.getEditPerson());
				cur.setEditTime(cr.getEditTime());
				cur.setId(UUIDUtil.getUUID());
				cur.setNoteContent(cr.getNoteContent());
				cur.setNotePerson(cr.getNotePerson());
				cur.setNoteTime(cr.getNoteTime());
				curList.add(cur);
				
				cor=new ContactsRemark();
				cor.setContactsId(co.getId());
				cor.setEditFlag(cr.getEditFlag());
				cor.setEditPerson(cr.getEditPerson());
				cor.setEditTime(cr.getEditTime());
				cor.setId(UUIDUtil.getUUID());
				cor.setNoteContent(cr.getNoteContent());
				cor.setNotePerson(cr.getNotePerson());
				cor.setNoteTime(cr.getNoteTime());
				corList.add(cor);
			}
			
			int ret3=customerRemarkDao.saveCreateCustomerRemarkByList(curList);
			System.out.println("保存客户备注ret3="+ret3);
			int ret4=contactsRemarkDao.saveCreateCotnactsRemarkByList(corList);
			System.out.println("保存联系人备注ret4="+ret4);
		}
		
		//根据clueId查询线索和市场活动的关联关系
		List<ClueActivityRelation> carList=clueActivityRelationDao.queryClueActivityRelationByClueId(clueId);
		
		//把线索和市场活动的关联关系转换为联系人和市场活动的关联关系表中
		if(carList!=null&&carList.size()>0){
			ContactsActivityRelation coar=null; 
			List<ContactsActivityRelation> coarList=new ArrayList<ContactsActivityRelation>();
			for(ClueActivityRelation car:carList){
				coar=new ContactsActivityRelation();
				coar.setActivityId(car.getActivityId());
				coar.setContactsId(co.getId());
				coar.setId(UUIDUtil.getUUID());
				
				coarList.add(coar);
			}
			int ret5=contactsActivityRelationDao.saveCreateContactsActivityRelationByList(coarList);
			System.out.println("保存联系人和市场活动关联关系ret5="+ret5);
		}
		
		if("true".equals(map.get("isCreateTransaction"))){
			//如果需要创建交易,则还需要往交易表中添加一条记录
			Transaction tran=new Transaction();
			tran.setActivityId((String)map.get("activityId"));
			String amountOfMoney=(String)map.get("amountOfMoney");
			if(amountOfMoney!=null&&amountOfMoney.trim().length()>0){
				tran.setAmountOfMoney(Long.parseLong(amountOfMoney.trim()));
			}
			tran.setContactsId(co.getId());
			tran.setCreateBy(user.getId());
			tran.setCreateTime(DateUtil.formatDateTime(new Date()));
			tran.setCustomerId(c.getId());
			tran.setExpectedClosingDate((String)map.get("expectedClosingDate"));
			tran.setId(UUIDUtil.getUUID());
			tran.setName((String)map.get("transactionName"));
			tran.setOwner(user.getId());
			tran.setStage((String)map.get("stage"));
			int ret6=transactionDao.saveCreateTransaction(tran);
			System.out.println("保存交易ret6="+ret6);
			
			//如果需要创建交易,则还需要把线索的备注信息转换到交易备注表中一份
			if(crList!=null&&crList.size()>0){
				TransactionRemark tr=null;
				List<TransactionRemark> trList=new ArrayList<TransactionRemark>();
				for(ClueRemark cr:crList){
					tr=new TransactionRemark();
					tr.setEditFlag(cr.getEditFlag());
					tr.setEditPerson(cr.getEditPerson());
					tr.setEditTime(cr.getEditTime());
					tr.setId(UUIDUtil.getUUID());
					tr.setNoteContent(cr.getNoteContent());
					tr.setNotePerson(cr.getNotePerson());
					tr.setNoteTime(cr.getNoteTime());
					tr.setTransactionId(tran.getId());
					trList.add(tr);
				}
				int ret7=transactionRemarkDao.saveCreateTransactionRemarkByList(trList);
				System.out.println("保存交易备注ret7="+ret7);
			}
		}
		
		//跟clueId删除线索的备注信息
		int ret8=clueRemarkDao.deleteClueRemarkByClueId(clueId);
		System.out.println("删除线索备注ret8="+ret8);
		//根据clueId删除线索和市场活动的关联关系
		int ret9=clueActivityRelationDao.deleteClueActivityRelationByClueId(clueId);
		System.out.println("删除线索和市场活动关联关系ret9="+ret9);
		//根据id删除线索
		int ret10=clueDao.deleteClueById(clueId);
		System.out.println("删除线索ret10="+ret10);
		
		return 0;
	}

}

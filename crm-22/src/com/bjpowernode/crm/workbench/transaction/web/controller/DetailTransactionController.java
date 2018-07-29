package com.bjpowernode.crm.workbench.transaction.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.settings.dictionary.value.domain.DictionaryValue;
import com.bjpowernode.crm.workbench.transaction.domain.Transaction;
import com.bjpowernode.crm.workbench.transaction.domain.TransactionHistory;
import com.bjpowernode.crm.workbench.transaction.domain.TransactionRemark;
import com.bjpowernode.crm.workbench.transaction.service.TransactionHistoryService;
import com.bjpowernode.crm.workbench.transaction.service.TransactionRemarkService;
import com.bjpowernode.crm.workbench.transaction.service.TransactionService;
import com.bjpowernode.crm.workbench.transaction.service.impl.TransactionHistoryServiceImpl;
import com.bjpowernode.crm.workbench.transaction.service.impl.TransactionRemarkServiceImpl;
import com.bjpowernode.crm.workbench.transaction.service.impl.TransactionServiceImpl;
/**
 * 查看交易明细
 * @author Administrator
 *
 */
public class DetailTransactionController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String id=req.getParameter("id");
		//调用service方法,查询数据
		TransactionService ts=(TransactionService)ServiceFactory.getService(new TransactionServiceImpl());
		Transaction tran=ts.queryTransactionForDetailById(id);
		String possiblity=(String)this.getServletContext().getAttribute(tran.getStage());
		tran.setPossibility(possiblity);
		
		TransactionRemarkService trs=(TransactionRemarkService)ServiceFactory.getService(new TransactionRemarkServiceImpl());
		List<TransactionRemark> remarkList=trs.queryTransactionRemarkByTranId(id);
		
		TransactionHistoryService ths=(TransactionHistoryService)ServiceFactory.getService(new TransactionHistoryServiceImpl());
		List<TransactionHistory> thList=ths.queryTransactionHistoryByTranId(id);
		if(thList!=null&&thList.size()>0){
			for(TransactionHistory th:thList){
				th.setPossibility((String)this.getServletContext().getAttribute(th.getStage()));
			}
		}
		
		//计算失败之前最后一个非失败阶段的orderNo
		List<DictionaryValue> stageList=(List<DictionaryValue>)this.getServletContext().getAttribute("stageList");
		DictionaryValue stageOk=stageList.get(stageList.size()-3);
		if(thList!=null&&thList.size()>0){
			TransactionHistory th=null;
			for(int i=thList.size()-1;i>=0;i--){
				th=thList.get(i);
				if(th.getOrderNo()<stageOk.getOrderNo()){
					req.setAttribute("theOrderNo", th.getOrderNo());
					break;
				}
			}
		}
		
		//把数据保存到req中,请求转发
		req.setAttribute("tran", tran);
		req.setAttribute("remarkList", remarkList);
		req.setAttribute("thList", thList);
		req.getRequestDispatcher("/workbench/transaction/detail.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

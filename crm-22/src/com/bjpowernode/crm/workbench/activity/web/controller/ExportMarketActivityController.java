package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
/**
 * 导出市场活动
 * @author Administrator
 *
 */
public class ExportMarketActivityController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		String name=req.getParameter("name");
		String owner=req.getParameter("owner");
		String type=req.getParameter("type");
		String state=req.getParameter("state");
		String startDate=req.getParameter("startDate");
		String endDate=req.getParameter("endDate");
		//封装参数
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", name);
		map.put("owner", owner);
		map.put("type", type);
		map.put("state", state);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		//调用service方法,查询数据 
		MarketActivityService mas=(MarketActivityService)ServiceFactory.getService(new MarketActivityServiceImpl());
		List<MarketActivity> activityList=mas.queryMarketActivityByCondition(map);
		//使用apache-poi生成excel
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("市场活动列表");
		HSSFRow row=sheet.createRow(0);
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("ID");
		cell=row.createCell(1);
		cell.setCellValue("名称");
		cell=row.createCell(2);
		cell.setCellValue("类型");
		cell=row.createCell(3);
		cell.setCellValue("状态");
		cell=row.createCell(4);
		cell.setCellValue("开始日期");
		cell=row.createCell(5);
		cell.setCellValue("结束日期");
		cell=row.createCell(6);
		cell.setCellValue("所有者");
		cell=row.createCell(7);
		cell.setCellValue("预算成本");
		cell=row.createCell(8);
		cell.setCellValue("实际成本");
		cell=row.createCell(9);
		cell.setCellValue("创建者");
		cell=row.createCell(10);
		cell.setCellValue("创建时间");
		cell=row.createCell(11);
		cell.setCellValue("描述");
		if(activityList!=null&&activityList.size()>0){
			MarketActivity ma=null;
			for(int i=0;i<activityList.size();i++){
				ma=activityList.get(i);
				
				row=sheet.createRow(i+1);
				cell=row.createCell(0);
				cell.setCellValue(ma.getId());
				cell=row.createCell(1);
				cell.setCellValue(ma.getName());
				cell=row.createCell(2);
				cell.setCellValue(ma.getType());
				cell=row.createCell(3);
				cell.setCellValue(ma.getState());
				cell=row.createCell(4);
				cell.setCellValue(ma.getStartDate());
				cell=row.createCell(5);
				cell.setCellValue(ma.getEndDate());
				cell=row.createCell(6);
				cell.setCellValue(ma.getOwner());
				cell=row.createCell(7);
				cell.setCellValue(ma.getBudgetCost());
				cell=row.createCell(8);
				cell.setCellValue(ma.getActualCost());
				cell=row.createCell(9);
				cell.setCellValue(ma.getCreateBy());
				cell=row.createCell(10);
				cell.setCellValue(ma.getCreateTime());
				cell=row.createCell(11);
				cell.setCellValue(ma.getDescription());
			}
		}
		/*OutputStream os=new FileOutputStream("d:/poi/activity.xls");
		wb.write(os);
		wb.close();
		os.close();*/
		//把excel文件返回浏览器
		resp.setContentType("application/octet-stream;charset=UTF-8");
		String browser=req.getHeader("User-Agent");
		String filename=URLEncoder.encode("市场活动列表", "UTF-8");
		if(browser.toLowerCase().contains("firefox")){//Firefox FireFox  Firefox
			filename=new String("市场活动列表".getBytes("UTF-8"),"ISO8859-1");
		}
		resp.addHeader("Content-Disposition", "attachment;filename="+filename+".xls");
		//获取输出流
		OutputStream os2=resp.getOutputStream();
		/*InputStream is=new FileInputStream("d:/poi/activity.xls");
		byte[] buff=new byte[256];
		int len;
		while((len=is.read(buff))!=-1){
			os2.write(buff, 0, len);
		}*/
		
		wb.write(os2);
		os2.flush();
		//is.close();
		wb.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

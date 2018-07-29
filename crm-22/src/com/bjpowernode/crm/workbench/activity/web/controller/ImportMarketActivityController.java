package com.bjpowernode.crm.workbench.activity.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;

import com.bjpowernode.crm.commons.util.ServiceFactory;
import com.bjpowernode.crm.commons.util.UUIDUtil;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;
import com.bjpowernode.crm.workbench.activity.service.impl.MarketActivityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 导入市场活动
 * @author Administrator
 *
 */
public class ImportMarketActivityController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String,Object> retMap=new HashMap<String,Object>();
		int ret=0;
		
		//使用apache-fileupload插件获取文件
		//1.创建磁盘文件工厂对象(保存参数,准备工作)
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//2.设置临时目录 
		String tempPath=this.getServletContext().getRealPath("/tempFile");//根据虚拟目录获取实际目录
		factory.setRepository(new File(tempPath));
		//3.创建处理文件上传的核心处理类对象
		ServletFileUpload upload=new ServletFileUpload(factory);
		try{
			//4.使用核心处理对象,解析请求,获取数据
			List<FileItem> itemList=upload.parseRequest(req);
			if(itemList!=null&&itemList.size()>0){
				for(FileItem item:itemList){
					//如果是普通的文本数据,则打印到控制台
					if(item.isFormField()){
						String name=item.getFieldName();//获取name值
						String value=item.getString("UTF-8");//获取value值
						System.out.println(name+"="+value);
					//如果是文件,在d盘上生成一个目标文件
					}else{
						/*String upPath=this.getServletContext().getRealPath("/upFile");
						String fileName=item.getName();//获取上传的文件名
						item.write(new File(upPath+"/"+fileName));*///文件会自动创建,目录必须手动创建
						
						//使用apache-poi解析excel文件,封装参数(List<MarketActivity>)
						//根据excel文件创建HSSFWorkbook对象,封装了这个excel文件中所有的信息
						/*InputStream is=new FileInputStream(upPath+"/"+fileName);
						HSSFWorkbook wb=new HSSFWorkbook(is);*/
						
						InputStream is=item.getInputStream();
						HSSFWorkbook wb=new HSSFWorkbook(is);
						//通过wb获取HSSFSheet对象,封装页的所有信息
						HSSFSheet sheet=wb.getSheetAt(0);
						HSSFRow row=null;
						MarketActivity ma=null;
						HSSFCell cell=null;
						List<MarketActivity> activityList=new ArrayList<MarketActivity>();
						for(int i=1;i<=sheet.getLastRowNum();i++){
							row=sheet.getRow(i);
							ma=new MarketActivity();
							ma.setId(UUIDUtil.getUUID());
							ma.setType("d127fa495fc64b2e8edfb4becfc5f375");
							ma.setState("67a6d0c74e2d409881dd0416a9dfbe97");
							User user=(User)req.getSession().getAttribute("user");
							ma.setOwner(user.getId());
							ma.setCreateBy(user.getId());
							ma.setCreateTime(com.bjpowernode.crm.commons.util.DateUtil.formatDateTime(new Date()));
							for(int j=0;j<row.getLastCellNum();j++){
								cell=row.getCell(j);
								if(j==0){
									ma.setName(getCellValueFromCell(cell));
								}else if(j==1){
									ma.setStartDate(getCellValueFromCell(cell));
								}else if(j==2){
									ma.setEndDate(getCellValueFromCell(cell));
								}else if(j==3){
									ma.setBudgetCost((long)Double.parseDouble(getCellValueFromCell(cell)));
								}else if(j==4){
									ma.setActualCost((long)Double.parseDouble(getCellValueFromCell(cell)));
								}else if(j==5){
									ma.setDescription(getCellValueFromCell(cell));
								}
							}
							activityList.add(ma);
							
							if(activityList.size()%3==0){
								//批量保存一次
								//调用service方法,保存数据
								MarketActivityService mas=(MarketActivityService)ServiceFactory.getService(new MarketActivityServiceImpl());
								ret+=mas.saveCreateMarketActivityByList(activityList);
								System.out.println("批量保存ret="+ret);
								activityList.clear();
							}
						}
						
						if(activityList.size()>0){
							//最后保存一次
							MarketActivityService mas=(MarketActivityService)ServiceFactory.getService(new MarketActivityServiceImpl());
							ret+=mas.saveCreateMarketActivityByList(activityList);
							System.out.println("最后保存一次ret="+ret);
						}
						
						//根据处理结果,返回响应信息(json)
						retMap.put("success", true);
						retMap.put("count", ret);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			retMap.put("success", false);
			retMap.put("count", ret);
		}
		
		//把retMap转化为json,返回客户端
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(retMap);
		
		resp.setContentType("text/json;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		out.write(json);
		out.flush();
	}
	public static String getCellValueFromCell(HSSFCell cell){
		String str="";
		switch(cell.getCellType()){
			case HSSFCell.CELL_TYPE_STRING:
				str=cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				str=String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				if(DateUtil.isCellDateFormatted(cell)){
					Date d=cell.getDateCellValue();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					str=sdf.format(d);
				}else{
					str=String.valueOf(cell.getNumericCellValue());
				}
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
					str=cell.getCellFormula();
					break;
			default:str="";
				
		}
		return str;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

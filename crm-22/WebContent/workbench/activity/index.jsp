<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

<!--  PAGINATION plugin -->
<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/css/jquery.bs_pagination.min.css">
<script type="text/javascript" src="jquery/bs_pagination/js/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/js/localization/en.js"></script>

<!-- copy -->
<script type="text/javascript" src="jquery/clipboard/clipboard.min.js"></script>
<script type="text/javascript" src="jquery/clipboard/clipboardmy.js"></script>

<script type="text/javascript">

	//new Clipboard('.btn');

	$(function(){
		new Clipboard('.btn1');
		
		//以下日历插件在FF中存在兼容问题，在IE浏览器中可以正常使用。
		/*
		$("#startTime").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
	        autoclose: true,
	        todayBtn: true,
	        pickerPosition: "bottom-left"
		});
		
		$("#endTime").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
	        autoclose: true,
	        todayBtn: true,
	        pickerPosition: "bottom-left"
		});
		*/
		
		//当页面加载完成之后,对容器调用工具函数
		$(".mydate").datetimepicker({
			  language: 'zh-CN',//显示中文
			  format: 'yyyy-mm-dd',//显示格式
			  minView: "month",//设置只显示到月份    0,1,2,3,4  分别代表分,时,日,月,年
			  initialDate: new Date(),//初始化当前日期
			  autoclose: true,//选中自动关闭
			  todayBtn: true,//显示今日按钮
			  clearBtn:true
	    });
		
		//定制字段
		$("#definedColumns > li").click(function(e) {
			//防止下拉菜单消失
	        e.stopPropagation();
	    });
		
		//给"创建"按钮添加单击事件
		$("#createActivityBtn").click(function(){
			//重置表单
			$("#createActivityForm")[0].reset();
			
			//发送请求
			$.ajax({
				url:'workbench/activity/createMarketActivity.do',
				type:'post',
				success:function(data){
					//遍历数组,设置所有者
					var htmlStr="";
					$.each(data,function(index,obj){
						if(obj.id=='${user.id}'){
							htmlStr+="<option value='"+obj.id+"' selected>"+obj.name+"</option>";
						}else{
							htmlStr+="<option value='"+obj.id+"'>"+obj.name+"</option>";
						}
					});
					$("#create-owner").html(htmlStr);
					//显示模态窗口
					$("#createActivityModal").modal("show");
				}
			});
		});
		
		//给"保存"按钮添加单击事件
		$("#saveCreateActivityBtn").click(function(){
			//收集参数
			var owner       =$("#create-owner").val();
			var type        =$("#create-type").val();
			var name        =$.trim($("#create-name").val());
			var state       =$("#create-state").val();
			var startDate   =$("#create-startDate").val();
			var endDate     =$("#create-endDate").val();
			var budgetCost  =$.trim($("#create-budgetCost").val());
			var actualCost  =$.trim($("#create-actualCost").val());
			var description =$.trim($("#create-description").val());
			//表单验证
			if(name==null||name.length==0){
				alert("名称不能为空!");
				return;
			}
			if(startDate!=null&&startDate.length>0&&endDate!=null&&endDate.length>0){
				//2017-09-17   yyyy-MM-dd
				//2017-08-17
				if(startDate>endDate){
					alert("结束日期不能比开始日期小!");
					return;
				}
			}
			var regExp=/^([1-9]\d*|0)$/;
			if(budgetCost!=null&&budgetCost.length>0&&!regExp.test(budgetCost)){
				alert("预算成本只能是非负整数!");
				return;
			}
			if(actualCost!=null&&actualCost.length>0&&!regExp.test(actualCost)){
				alert("实际成本只能是非负整数!");
				return;
			}
			//发送请求
			$.ajax({
				url:'workbench/activity/saveCreateMarketActivity.do',
				data:{
					owner		: owner       ,
					type        : type        ,
					name        : name        ,
					state       : state       ,
					startDate   : startDate   ,
					endDate     : endDate     ,
					budgetCost  : budgetCost  ,
					actualCost  : actualCost  ,
					description : description 
				},
				type:'post',
				success:function(data){
					if(data.success){
						//关闭模态窗口
						$("#createActivityModal").modal("hide");
						//刷新市场活动列表(保留)
						display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
					}else{
						alert("创建失败!");
						$("#createActivityModal").modal("show");
					}
				}
			});
		});
		
		//当页面加载完成之后,显示所有数据的第一页
		display(1,10);
		
		//给"查询"按钮添加单击事件,显示第一页数据,保持每页显示条数不变
		$("#queryActivityBtn").click(function(){
			display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
		});
		
		//当页面加载完成之后,默认选中"添加字段"下所有的复选框
		$("#definedColumns input[type='checkbox']").prop("checked",true);
		
		//给"添加字段"下所有的复选框添加单击事件
		$("#definedColumns input[type='checkbox']").click(function(){
			if(this.checked){
				$("td[name='"+this.name+"']").show();
			}else{
				$("td[name='"+this.name+"']").hide();
			}
		});
		
		//给"删除"按钮添加单击事件
		$("#deleteActivityBtn").click(function(){
			//收集参数
			var ckdIds=$("#activityListTBody input[type='checkbox']:checked");
			if(ckdIds.size()==0){
				alert("请选择要删除的市场活动!");
				return;
			}
			
			if(window.confirm("确定删除吗?")){
				//id=xxx&id=xxx&.....&id=xxxx
				var ids="";
				$.each(ckdIds,function(index,obj){
					ids+="id="+obj.value+"&";
				});
				ids=ids.substr(0,ids.length-1);
				
				$.ajax({
					url:'workbench/activity/deleteMarketActivity.do',
					data:ids,
					type:'post',
					success:function(data){
						if(data.success){
							display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
						}else{
							alert("删除失败!");
						}
					}
				});
			}
		});
		
		//给"全选"按钮添加单击事件
		$("#ckd_all").click(function(){
			$("#activityListTBody input[type='checkbox']").prop("checked",this.checked);
		});
		
		//给列表中所有的复选框添加单击事件
		$("#activityListTBody").on("click","input[type='checkbox']",function(){
			if($("#activityListTBody input[type='checkbox']").size()==$("#activityListTBody input[type='checkbox']:checked").size()){
				$("#ckd_all").prop("checked",true);
			}else{
				$("#ckd_all").prop("checked",false);
			}
		});
		
		//给"修改"按钮添加单击事件
		$("#editActivityBtn").click(function(){
			//收集参数
			var ckdIds=$("#activityListTBody input[type='checkbox']:checked");
			if(ckdIds.size()==0){
				alert("请选择要修改市场活动!");
				return;
			}
			if(ckdIds.size()>1){
				alert("每次只能修改一条市场活动!");
				return;
			}
			var id=ckdIds.val();
			//发送请求
			$.ajax({
				url:'workbench/activity/editMarketActivity.do',
				data:{
					id:id
				},
				type:'post',
				success:function(data){
					//设置所有者
					var htmlStr="";
					$.each(data.userList,function(index,obj){
						if(obj.id==data.activity.owner){
							htmlStr+="<option value='"+obj.id+"' selected>"+obj.name+"</option>";
						}else{
							htmlStr+="<option value='"+obj.id+"'>"+obj.name+"</option>";
						}
					});
					$("#edit-owner").html(htmlStr);
					//设置类型和状态
					$("#edit-type").val(data.activity.type);
					$("#edit-state").val(data.activity.state);
					//设置其它字段
					$("#edit-id").val(data.activity.id);
					$("#edit-name").val(data.activity.name);
					$("#edit-startDate").val(data.activity.startDate);
					$("#edit-endDate").val(data.activity.endDate);
					$("#edit-budgetCost").val(data.activity.budgetCost);
					$("#edit-actualCost").val(data.activity.actualCost);
					$("#edit-description").val(data.activity.description);
					//显示模态窗口
					$("#editActivityModal").modal("show");
				}
			});
		});
		
		//给"更新"按钮添加单击事件
		$("#saveEditActivityBtn").click(function(){
			//收集参数
			var id=$("#edit-id").val();
			var name=$.trim($("#edit-name").val());
			var startDate=$("#edit-startDate").val();
			var endDate=$("#edit-endDate").val();
			var budgetCost=$.trim($("#edit-budgetCost").val());
			var actualCost=$.trim($("#edit-actualCost").val());
			var description=$.trim($("#edit-description").val());
			var owner=$("#edit-owner").val();
			var type=$("#edit-type").val();
			var state=$("#edit-state").val();
			//表单验证(作业)
			
			//发送请求
			$.ajax({
				url:'workbench/activity/saveEditMarketActivity.do',
				data:{
					id:id,
					name:name,
					startDate:startDate,
					endDate:endDate,
					budgetCost:budgetCost,
					actualCost:actualCost,
					description:description,
					owner:owner,
					type:type,
					state:state
				},
				type:'post',
				success:function(data){
					if(data.success){
						//关闭模态窗口
						$("#editActivityModal").modal("hide");
						//刷新市场活动列表,保持页号和每页显示条数都不变
						display($("#pageNoDiv").bs_pagination('getOption', 'currentPage'),$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
					}else{
						alert("修改失败!");
						$("#editActivityModal").modal("show");
					}
				}
			});
		});
		
		//给"导出"按钮添加单击事件
		$("#exportActivityBtn").click(function(){
			//收集参数
			var name=$("#query-name").val();
			var owner=$("#query-owner").val();
			var type=$("#query-type").val();
			var state=$("#query-state").val();
			var startDate=$("#query-startDate").val();
			var endDate=$("#query-endDate").val();
			
			//发送请求
			window.location.href="workbench/activity/exportMarketActivity.do?name="+name+"&owner="+owner+"&type="+type+"&state="+state+"&startDate="+startDate+"&endDate="+endDate;
		});
		
		//给"导入"按钮添加单击事件
		$("#importActivityBtn").click(function(){
			//收集参数
			var fileName=$("#activityFile").val();
			var suffix=fileName.substr(fileName.lastIndexOf(".")+1).toLowerCase();
			if(!(suffix=='xls'||suffix=='xlsx')){
				alert("只支持.xls或.xlsx格式的文件!");
				return;
			}
			var activityFile=$("#activityFile")[0].files[0];
			if(activityFile.size>5*1024*1024){
				alert("文件大小不超过5MB!");
				return;
			}
			//发送请求
			//FormData是ajax提供的一个接口,它可以模拟键值对向服务器提交数据
			//FormData最大的优势是不但能提交文本数据,还能提交二进制数据 
			var formData=new FormData();
			formData.append("activityFile",activityFile);
			formData.append("username","zhangsan");
			$.ajax({
				url:'workbench/activity/importMarketActivity.do',
				data:formData,
				type:'post',
				processData:false,//processData主要是配合contentType使用的,默认情况下,ajax对数据进行application/x-www-form-urlencoded编码之前,统一把所有数据转化为字符串;把processData设置为false,可以阻止这种行为.
				contentType:false,//默认情况下,ajax向服务器发送数据之前,统一把所有的数按照application/x-www-form-urlencoded方式变;把contentType设置为false,可以阻止这种行为.
				success:function(data){
					if(data.success){
						//关闭模态窗口
						$("#importActivityModal").modal("hide");
						//提示信息
						alert("成功导入"+data.count+"条记录!");
						//刷新市场活动,显示第一页数据,保持每页显示条数不变
						display(1,$("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
					}else{
						alery("成功导入前"+data.count+"条记录!其余数据,请检查数据格式!");
						$("#importActivityModal").modal("show");
					}
				}
			});
		});
	});
	
	function display(pageNo,pageSize){
		//收集参数
		//var pageNo=1;
		//var pageSize=10;
		var name=$("#query-name").val();
		var owner=$("#query-owner").val();
		var type=$("#query-type").val();
		var state=$("#query-state").val();
		var startDate=$("#query-startDate").val();
		var endDate=$("#query-endDate").val();
		//发送请求
		$.ajax({
			url:'workbench/activity/queryMarketActivityForPageByCondition.do',
			data:{
				pageNo:pageNo,
				pageSize:pageSize,
				name:name,
				owner:owner,
				type:type,
				state:state,
				startDate:startDate,
				endDate:endDate
			},
			type:'post',
			success:function(data){
				//显示总条数
				//$("#totalRows").html(data.count);//功能由翻页插件取代
				//显示市场活动列表
				var htmlStr="";
				$.each(data.dataList,function(index,obj){
					htmlStr+="<tr>";
					htmlStr+="<td><input value='"+obj.id+"' type='checkbox' /></td>";
					htmlStr+="<td name='name'><a style='text-decoration: none; cursor: pointer;' onclick='window.location.href=\"workbench/activity/detailMarketActivity.do?id="+obj.id+"\";'>"+obj.name+"</a></td>";
					
					if($("#definedColumns input[name='type']").prop("checked")){
						htmlStr+="<td name='type'>"+obj.type+"</td>";
					}else{
						htmlStr+="<td name='type' style='display:none'>"+obj.type+"</td>";
					}
					
					if($("#definedColumns input[name='state']").prop("checked")){
						htmlStr+="<td name='state'>"+obj.state+"</td>";
					}else{
						htmlStr+="<td name='state' style='display:none'>"+obj.state+"</td>";
					}
					
					htmlStr+="<td name='startDate'>"+obj.startDate+"</td>";
					htmlStr+="<td name='endDate'>"+obj.endDate+"</td>";
					htmlStr+="<td name='owner'>"+obj.owner+"</td>";
					htmlStr+="<td name='budgetCost'>"+obj.budgetCost+"</td>";
					htmlStr+="<td name='actualCost'>"+obj.actualCost+"</td>";
					htmlStr+="<td name='createBy'>"+obj.createBy+"</td>";
					htmlStr+="<td name='createTime'>"+obj.createTime+"</td>";
					htmlStr+="<td name='editBy'>"+(obj.editBy==null?'':obj.editBy)+"</td>";
					htmlStr+="<td name='editTime'>"+(obj.editTime==null?'':obj.editTime)+"</td>";
					htmlStr+="<td name='description'>"+(obj.description==null?'':obj.description)+"</td>";
					htmlStr+="</tr>";
				});
				$("#activityListTBody").html(htmlStr);
				
				//隔行换颜色
				$("#activityListTBody tr:odd").addClass("active");
				
				//显示翻页信息
				 //计算总页数
				 var totalPages=1;
				 if(data.count%pageSize==0){
					 totalPages=data.count/pageSize;
				 }else{
					 totalPages=parseInt(data.count/pageSize)+1;
				 }
				$("#pageNoDiv").bs_pagination({
					   currentPage:pageNo,//当前页
					   rowsPerPage:pageSize,//每页显示条数
					   totalRows:data.count, //总条数
				       totalPages:totalPages,  //总页数. 必须手动计算.
				       
				       
				       visiblePageLinks:2,//最多可以显示的卡片数
				       
				       showGoToPage:true, //是否显示跳转到第几页
				       showRowsPerPage:true, //是否显示每页显示条数
				       showRowsInfo:true, //是否显示记录信息
				       //用来监听页号切换的事件.event就代表页号切换的事件,pageObj代表页信息(pageNo和pageSize).
				       onChangePage: function(event,pageObj) { // returns page_num and rows_per_page after a link has clicked
				       	  //alert(pageObj.currentPage);
				       	  //alert(pageObj.rowsPerPage);
				    	   display(pageObj.currentPage,pageObj.rowsPerPage);
				       }
				  });
			}
		});
	}
</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="createActivityForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">
								  <!-- <option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option> -->
								</select>
							</div>
							<label for="create-marketActivityType" class="col-sm-2 control-label">类型</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-type">
								  <option></option>
								  <c:if test="${not empty marketActivityTypeList }">
								  	 <c:forEach var="tl" items="${marketActivityTypeList }">
								  	 	<option value="${tl.id }">${tl.text }</option>
								  	 </c:forEach>
								  </c:if>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-name">
							</div>
							<label for="create-marketActivityState" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-state">
								  <option></option>
								  <c:if test="${not empty marketActivityStateList }">
								  	 <c:forEach var="sl" items="${marketActivityStateList }">
								  	 	<option value="${sl.id }">${sl.text }</option>
								  	 </c:forEach>
								  </c:if>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control mydate" id="create-startDate" readonly="readonly">
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control mydate" id="create-endDate" readonly="readonly">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-actualCost" class="col-sm-2 control-label">实际成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-actualCost">
							</div>
							<label for="create-budgetCost" class="col-sm-2 control-label">预算成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-budgetCost">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveCreateActivityBtn" type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
						<input type="hidden" id="edit-id">
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">
								  <!-- <option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option> -->
								</select>
							</div>
							<label for="edit-marketActivityType" class="col-sm-2 control-label">类型</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-type">
								  <option></option>
								  <c:if test="${not empty marketActivityTypeList }">
								  	 <c:forEach var="tl" items="${marketActivityTypeList }">
								  	 	<option value="${tl.id }">${tl.text }</option>
								  	 </c:forEach>
								  </c:if>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-name" value="发传单">
							</div>
							<label for="edit-marketActivityState" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-state">
								  <option></option>
								  <c:if test="${not empty marketActivityStateList }">
								  	 <c:forEach var="sl" items="${marketActivityStateList }">
								  	 	<option value="${sl.id }">${sl.text }</option>
								  	 </c:forEach>
								  </c:if>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startDate" value="2020-10-10">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endDate" value="2020-10-20">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-actualCost" class="col-sm-2 control-label">实际成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-actualCost" value="4,000">
							</div>
							<label for="edit-budgetCost" class="col-sm-2 control-label">预算成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-budgetCost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveEditActivityBtn" type="button" class="btn btn-primary">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- 导入市场活动的模态窗口 -->
	<div class="modal fade" id="importActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
				</div>
				<div class="modal-body" style="height: 350px;">
					<div style="position: relative;top: 20px; left: 50px;">
						请选择要上传的文件：<small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
					</div>
					<div style="position: relative;top: 40px; left: 50px;">
						<input type="file" id="activityFile">
					</div>
					<div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
						<h3>重要提示</h3>
						<ul>
							<li>给定文件的第一行将视为字段名。</li>
							<li>请确认您的文件大小不超过5MB。</li>
							<li>从XLS/XLSX文件中导入全部重复记录之前都会被忽略。</li>
							<li>复选框值应该是1或者0。</li>
							<li>日期值必须为MM/dd/yyyy格式。任何其它格式的日期都将被忽略。</li>
							<li>日期时间必须符合MM/dd/yyyy hh:mm:ss的格式，其它格式的日期时间将被忽略。</li>
							<li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
							<li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
						</ul>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="importActivityBtn" type="button" class="btn btn-primary">导入</button>
				</div>
			</div>
		</div>
	</div>
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 130%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input id="query-name" class="form-control" type="text">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input id="query-owner" class="form-control" type="text">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">类型</div>
					  <select id="query-type" class="form-control">
					  	  <option></option>
					      <c:if test="${not empty marketActivityTypeList }">
								  	 <c:forEach var="tl" items="${marketActivityTypeList }">
								  	 	<option value="${tl.id }">${tl.text }</option>
								  	 </c:forEach>
						  </c:if>
					  </select>
				    </div>
				    
				     <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">复制框</div>
				      <input id="test-copy" class="form-control" type="text" value="testcopy">
				    </div>
				  </div>
				  </div>
				  
				  <br>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">状态</div>
					  <select id="query-state" class="form-control">
					  	<option></option>
					    <c:if test="${not empty marketActivityStateList }">
								  	 <c:forEach var="sl" items="${marketActivityStateList }">
								  	 	<option value="${sl.id }">${sl.text }</option>
								  	 </c:forEach>
						  </c:if>
					  </select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="query-startDate" />
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="query-endDate">
				    </div>
				  </div>
				  
				  
				  <label for="website">Website:</label>
<input type="text" id="website" value="http://www.sitepoint.com/" />
<button data-copytarget="#website">copy</button>

<label for="twitter">Twitter:</label>
<input type="text" id="twitter" value="https://twitter.com/craigbuckler" />
<button data-copytarget="#twitter">copy</button>
				  
				 <!--  <input type="text" id="website" value="http://www.sitepoint.com/" />
					<button id="testCopyBtn1" type="button" class="btn1 btn btn-default" data-copytarget="#website">copy</button> -->
				  <button id="testCopyBtn" type="button" data-clipboard-target="#test-copy" class="btn1 btn btn-default">testCopy</button>
				  <button id="queryActivityBtn" type="button" class="btn btn-default">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button id="createActivityBtn" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button id="editActivityBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button id="deleteActivityBtn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal"><span class="glyphicon glyphicon-import"></span> 导入</button>
				  <button id="exportActivityBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 导出</button>
				</div>
				
				<div class="btn-group" style="position: relative; top: 18%; left: 5px;">
					<button type="button" class="btn btn-default">添加字段</button>
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						<span class="caret"></span>
						<span class="sr-only">Toggle Dropdown</span>
					</button>
					<ul id="definedColumns" class="dropdown-menu" role="menu"> 
						<li><a href="javascript:void(0);"><input name="name" type="checkbox"/> 名称</a></li>
						<li><a href="javascript:void(0);"><input name="type" type="checkbox"/> 类型</a></li>
						<li><a href="javascript:void(0);"><input name="state" type="checkbox"/> 状态</a></li>
						<li><a href="javascript:void(0);"><input name="startDate" type="checkbox"/> 开始日期</a></li>
						<li><a href="javascript:void(0);"><input name="endDate" type="checkbox"/> 结束日期</a></li>
						<li><a href="javascript:void(0);"><input name="owner" type="checkbox"/> 所有者</a></li>
						<li><a href="javascript:void(0);"><input name="budgetCost" type="checkbox"/> 预算成本</a></li>
						<li><a href="javascript:void(0);"><input name="actualCost" type="checkbox"/> 实际成本</a></li>
						<li><a href="javascript:void(0);"><input name="createBy" type="checkbox"/> 创建者</a></li>
						<li><a href="javascript:void(0);"><input name="createTime" type="checkbox"/> 创建时间</a></li>
						<li><a href="javascript:void(0);"><input name="editBy" type="checkbox"/> 修改者</a></li>
						<li><a href="javascript:void(0);"><input name="editTime" type="checkbox"/> 修改时间</a></li>
						<li><a href="javascript:void(0);"><input name="description" type="checkbox"/> 描述</a></li>
					</ul>
				</div>

				<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
					<form class="form-inline" role="form">
					  <div class="form-group has-feedback">
					    <input type="email" class="form-control" style="width: 300px;" placeholder="支持任何字段搜索">
					    <span class="glyphicon glyphicon-search form-control-feedback"></span>
					  </div>
					</form>
				</div>
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input id="ckd_all" type="checkbox" /></td>
							<td name="name">名称</td>
							<td name="type">类型</td>
							<td name="state">状态</td>
							<td name="startDate">开始日期</td>
							<td name="endDate">结束日期</td>
							<td name="owner">所有者</td>
							<td name="budgetCost">预算成本</td>
							<td name="actualCost">实际成本</td>
							<td name="createBy">创建者</td>
							<td name="createTime">创建时间</td>
							<td name="editBy">修改者</td>
							<td name="editTime">修改时间</td>
							<td name="desciption" width="10%">描述</td>
						</tr>
					</thead>
					<tbody id="activityListTBody">
						<!-- <tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">发传单</a></td>
							<td>广告</td>
							<td>激活的</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
							<td>zhangsan</td>
							<td>5,000</td>
							<td>4,000</td>
							<td>zhangsan</td>
							<td>2017-01-18 10:10:10</td>
							<td>zhangsan</td>
							<td>2017-01-19 10:10:10</td>
							<td>发传单....</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">发传单</a></td>
							<td>广告</td>
							<td>激活的</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
							<td>zhangsan</td>
							<td>5,000</td>
							<td>4,000</td>
							<td>zhangsan</td>
							<td>2017-01-18 10:10:10</td>
							<td>zhangsan</td>
							<td>2017-01-19 10:10:10</td>
							<td>发传单....</td>
						</tr> -->
					</tbody>
				</table>
				<div id="pageNoDiv"></div>
			</div>
			
			<!-- <div style="height: 50px; position: relative;top: 30px;">
				<div>
					<button type="button" class="btn btn-default" style="cursor: default;">共<b id="totalRows">50</b>条记录</button>
				</div>
				<div class="btn-group" style="position: relative;top: -34px; left: 110px;">
					<button type="button" class="btn btn-default" style="cursor: default;">显示</button>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							10
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">20</a></li>
							<li><a href="#">30</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
				</div>
				<div style="position: relative;top: -88px; left: 285px;">
					<nav>
						<ul class="pagination">
							<li class="disabled"><a href="#">首页</a></li>
							<li class="disabled"><a href="#">上一页</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">下一页</a></li>
							<li class="disabled"><a href="#">末页</a></li>
						</ul>
					</nav>
				</div>
			</div> -->
			
		</div>
		
	</div>
</body>
</html>
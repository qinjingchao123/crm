<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>演示echarts报表插件</title>
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/echarts/js/echarts.min.js"></script>
<script type="text/javascript">
	$(function(){
		//当页面加载完成之后,查询交易表中各个阶段的数据量
		$.ajax({
			url:'workbench/transaction/queryTransactionGroupByStage.do',
			type:'post',
			success:function(data){
				 // 基于准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById('main'));

		        // 指定图表的配置项和数据
		        var option = {
		        	    title: {
		        	        text: '学生就业统计报表',
		        	        subtext: '交易各阶段数据量统计'
		        	    },
		        	    tooltip: {
		        	        trigger: 'item',
		        	        formatter: "{a} <br/>{b} : {c}"
		        	    },
		        	    series: [
		        	        {
		        	            name: '数据量',
		        	            type: 'funnel',
		        	            left: '10%',
		        	            width: '80%',
		        	            data: data
		        	        },
		        	    ]
		        	};


		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
			}
		});
	});
</script>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>
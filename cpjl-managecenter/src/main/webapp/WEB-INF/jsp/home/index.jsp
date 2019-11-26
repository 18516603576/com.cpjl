<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>触屏精灵_管理中心</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.js" charset="gb2312"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/base.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home/index.css">
  
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/simpletree/s.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/simpletree/s.css" type="text/css">
  <script type="text/javascript">
$(function(){ 
	$(".st_tree").SimpleTree({ 
		/* 可无视代码部分*/
		click:function(a){
			if(!$(a).attr("hasChild"))
				alert($(a).attr("ref"));
		} 
	}); 
});
</script>
  
</head>
<body>
<table id="frame" cellpadding="0" cellspacing="0" width="100%" height="100%">
<tr>
<td colspan="2" height="60" id="topFrame">
	<ul>
	   <li class="logo" onclick="location.href='${pageContext.request.contextPath}/home/index'">管理中心</li> 
	   <li class="word">
 	      你好,${user.userName }&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/login/logout">退出</a>&nbsp;|&nbsp;<a href="http://127.0.0.1:7003/index.html" target="_blank">触屏预览</a>
	   </li> 
	</ul>
</td>
</tr>
<tr>
<td valign="top" width="160"  id="leftFrame">
	 
	  <div id="leftcat" class="st_tree" >	
	  
			<ul>
				<li><a href="javascript:;" target="main">视频管理</a></li>
				<ul show="true">
			        <li><a href="${pageContext.request.contextPath}/video/list?cName=视频" target="main">视频</a></li>
			        <li><a href="${pageContext.request.contextPath}/videocat/list?cName=视频栏目" target="main">视频栏目</a></li>
			    </ul>
				 
				<li><a href="javascript:;" target="main">文章管理</a></li> 
				<ul show="true">
			       <li><a href="${pageContext.request.contextPath}/article/list?cName=文章" target="main">文章</a></li>
				   <li><a href="${pageContext.request.contextPath}/articlecat/list?cName=文章栏目" target="main">文章栏目</a></li>
			    </ul>
				<li><a href="${pageContext.request.contextPath}/user/list?cName=用户管理" target="main">用户管理</a></li>
				 
          </ul>
	 
	</div>
</td>
<td valign="top" width="100%"  id="mainframe">
	<iframe src="${pageContext.request.contextPath}/home/welcome" id="main" name="main" width="100%" height="100%" frameborder="0"  style="overflow:visible;display:block;"></iframe>
</td>
</tr>
</table> 
<script>
 $("#mainframe").css("width", (document.documentElement.clientWidth-$("#tree").width())+"px");    //解决小于等于ie8的浏览器左边宽度问题
 $("#leftcat").css("height",(document.documentElement.clientHeight-100)+"px");  //设置leftcat的高度，超出是出现滚动条
 </script>
</body>
</html>
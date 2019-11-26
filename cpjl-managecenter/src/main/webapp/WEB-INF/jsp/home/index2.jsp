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
  
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/tree/jquery.ztree.core-3.5.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/tree/css.css" type="text/css">
 <SCRIPT type="text/javascript" >
  <!--
	var zTree;
	var demoIframe;

	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					demoIframe.attr("src",treeNode.file);
					return true;
				}
			}
		}
	};

	var zNodes =[
 		
		 
		 
		 

		 {id:7, pId:0, name:"视频管理", file:"${pageContext.request.contextPath}/video/list?cName=视频管理", open:true},
		 {id:6, pId:0, name:"视频栏目", file:"${pageContext.request.contextPath}/videocat/list?cName=视频栏目", open:true},
		 {id:5, pId:0, name:"文章管理", file:"${pageContext.request.contextPath}/article/list?cName=文章管理", open:true},
		 {id:4, pId:0, name:"文章栏目", file:"${pageContext.request.contextPath}/articlecat/list?cName=文章栏目", open:true},
		 {id:3, pId:0, name:"用户管理", file:"${pageContext.request.contextPath}/user/list?cName=用户管理", open:true}
 
 		 
	];

	$(document).ready(function(){
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		demoIframe = $("#main");
		demoIframe.bind("load", loadReady);
		//var zTree = $.fn.zTree.getZTreeObj("tree");
		//zTree.selectNode(zTree.getNodeByParam("id", 101));

	});

	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
		
		var min_h = document.documentElement.clientHeight-30-1; 
		if (h < min_h) h = min_h;
	//	demoIframe.height(h);  
	}

  //-->
  </SCRIPT>  
 
</head>
<body>
<table id="frame" cellpadding="0" cellspacing="0" width="100%" height="100%">
<tr>
<td colspan="2" height="60" id="topFrame">
	<ul>
	   <li class="logo" onclick="location.href='${pageContext.request.contextPath}/home/index'">管理中心</li> 
	   <li class="word">
 	      你好,${user.userName }&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/login/logout">退出</a>&nbsp;|&nbsp;<a href="http://127.0.0.1:8020/dehong/index.html" target="_blank">触屏预览</a>
	   </li> 
	</ul>
</td>
</tr>
<tr>
<td valign="top" width="160"  id="leftFrame">
	<div id="leftcat" style="height:400px;">	
	   <ul id="tree" class="ztree" style=" overflow:auto;"></ul>   
<%-- 	   <ul class="cat ho"><a href="${pageContext.request.contextPath}/treeDevice/listUI" target="main">苹果树</a></ul> --%>
<%-- 	   <ul class="cat ho"><a href="${pageContext.request.contextPath}/leader/listUI" target="main">领导介绍</a></ul> --%>
 
<%-- 	   <ul class="cat ho"><a href="${pageContext.request.contextPath}/police/listUI" target="main">获奖干警及宪法宣誓</a></ul> --%>
<%-- 	   <ul class="cat ho"><a href="${pageContext.request.contextPath}/system/listUI" target="main">法律法规</a></ul> --%>
<%-- 	   <ul class="cat ho"><a href="${pageContext.request.contextPath}/case/listUI" target="main">案件查询</a></ul> --%>
<%-- 	   <ul class="cat ho"><a href="${pageContext.request.contextPath}/video/listUI" target="main">视频</a></ul> --%>
<%-- 	   <ul class="cat ho"><a href="${pageContext.request.contextPath}/game/listUI" target="main">小游戏</a></ul> --%>
	   
<%-- 	   <ul class="cat ho"><a href="${pageContext.request.contextPath}/managerAction_listUI.action" target="main">帐号管理</a></ul> --%>
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
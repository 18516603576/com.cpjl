<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>产品列表</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/base.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/list.css">
 
 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/base.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/iframeWin.js"></script> 
 
<style type="text/css">
 #mTable td {  padding: 10px 5px; }
 dl dt{ float:left; margin-right:20px;}
 dl dd{ float:left; text-align:left; }
 dl dd .title{ font-size:24px;  }
 dl dd .order{ line-height:30px;  }
 dl dd .date{ color:#666;}
 
 
 .parent-tr  .title{font-weight:bold; }
 
#act .right{ width:500px; } 
tr.default .defaultProduct a{  display: inline-block;  width: 80px; height: 24px; line-height: 24px; text-align: center;  border: 1px solid #ff5000; border-radius: 3px; background: #ffd6cc; color: #f30;} 
tr.default .operate a{ color:#666; cursor:auto; } 

.child td{ font-size:12px; color:#666; }
</style>   		
</head>

<body>
<script> 
var sb_cfg = {
	 
}
</script>
	<div id="top">
		<div class="left">
			<span class="big">${cName }&nbsp;</span> 
			<span id="span_catName"></span> 
			<a  id="thisPage" href="${pageContext.request.contextPath}/videocat/list?cName=${cName}"  class="on">栏目列表</a>
			<a href="javascript:new openWin({document:window.parent.document,width:'90%',height:'90%',title:'添加栏目',src:'${pageContext.request.contextPath}/videocat/insert?deviceid=${entity.deviceid }'}).start()" >添加栏目</a>		
		</div>
		<div class="right">
					     
		</div>   
	</div>
	
	<table border="0" cellpadding="0" cellspacing="1"  id="mTable" >
		 
		<tr class="captail">
		 	
 		<td style="text-align:left;">栏目名称</td> 		
 		 <td style="width:10%;">类型</td> 
 		<td style="width:10%;">排序</td> 
       	<td style="width:15%;">操作</td>
	</tr>
	
		<c:forEach var="list" items="${list}"> 
	        <tr  class="list tr"  >
	                        
							<td style="text-align:left;" class="title">${list.name}</td>							
							<td>
							<c:choose>
							  <c:when test="${list.type==1 }">
							       单页面
							  </c:when>
							  <c:when test="${list.type==2 }">
							       文字列表
							  </c:when>
							  <c:when test="${list.type==3 }">
							       图片列表
							  </c:when>
							</c:choose>
							  
							</td>
							<td class="idx">${list.idx}</td>
						 
							 
							<td class="operate">
									    <a href="javascript:;" class="update" data-id="${list.id }">修改</a>
		   					            <a href="javascript:;" class="del" value="${list.id }">删除</a>
		   					</td>
			</tr>
			
<!-- 			子栏目 -->
					<c:forEach var="child" items="${list.children}"> 
			        <tr  class="child tr"  >
			                        
									<td style="text-align:left; padding-left:40px;" class="title">${child.name}</td>							
									<td>
									<c:choose>
									  <c:when test="${child.type==1 }">
									       单页面
									  </c:when>
									  <c:when test="${child.type==2 }">
									       文字列表
									  </c:when>
									  <c:when test="${child.type==3 }">
									       图片列表
									  </c:when>
									</c:choose>
									  
									</td>
									<td class="idx">${child.idx}</td>
								 
									 
									<td class="operate">
											    <a href="javascript:;" class="update" data-id="${child.id }">修改</a>
				   					            <a href="javascript:;" class="del" value="${child.id }">删除</a>
				   					</td>
					</tr>
		       
		  		</c:forEach>
<!-- 			子栏目 -->       
  		</c:forEach>
  		
  		 
		
	</table>
</body>

<script type="text/javascript">
	//搜索 
	var beforeFun1 = function(){  
		   var $wd = $("#wd");
           if($wd.val()==$wd.attr("default_data")){  $wd.val("") }  
       }
	var submit1 =  new Submit({ submitType: "normal" ,formId: "form2" ,btnId: "btn2" ,beforeFun: beforeFun1    });
	submit1.focusBlur("#wd","#999","#000");
	submit1.clickOrEnter();
</script>
   
 <script>
 //删除操作
 		var init1 = new Init();
 		 
		$(document).delegate("a.del","click",function(){ 
 			 init1.doo(false,'',$(this),'${pageContext.request.contextPath}/videocat/delete','确定删除当前记录吗？',false);
		});
		$(document).delegate("#sel","click",function(){
			 var o = $("input[name='selId']"),  t = $(this).is(":checked");   
			 for(var i=0;i<o.length;i++){ 				  
					 o.eq(i).prop("checked",t); 
			 }
	    });
		
		$(".tr").mouseenter(function(){
		    $(this).children("td").css("backgroundColor","#e4e4e4");	
		})
		$(".tr").mouseleave(function(){
		    $(this).children("td").css("backgroundColor","");	
		})
		
		//修改
		$(document).delegate("a.update","click",function(){ 
			 var id = $(this).attr("data-id");
			 new openWin({document:window.parent.document,width:'90%',height:'90%',title:'修改栏目',src:'${pageContext.request.contextPath}/videocat/update?id='+id}).start();
		});
		
		
 </script>
  

</html>
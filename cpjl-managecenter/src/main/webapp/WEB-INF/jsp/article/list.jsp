<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>列表页面</title>
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
 </style>
</head>
<body>
<script>
  var bf_cfg = {
		   "articlecatList" : ${articlecatList} 
	      ,"articlecatid":"${entity.articlecatid}" 
	      
  }
</script>
 
 		<div id="top">
					<div class="left">
					    <span class="big">${cName }&nbsp;</span> 
  						<a  id="thisPage" href="${pageContext.request.contextPath}/article/list?cName=${cName}"  class="on">文章列表</a>
  						<a href="javascript:new openWin({document:window.parent.document,width:'90%',height:'90%',title:'添加文章',src:'${pageContext.request.contextPath}/article/insert?articlecatid=${entity.articlecatid }'}).start()" >添加文章</a>						
  						 
 					</div>
					<div class="right">
					     
					</div>   
		</div>
		
		
<table border="0" cellpadding="0" cellspacing="1"  id="mTable" >
   <tr class="captail">
 		<td colspan="3" id="act">
		<div class="left">
		<INPUT TYPE="checkbox" name="sel" id="sel" class="pc" >全选&nbsp;&nbsp;
 		<input type="button" name="batchDel" id="batchDel" class="pb w80" value=" 批量删除 " >
		</div>
		<div class="right">
		     <form name="form2" id="form2" method="post" action="?cName=${cName }">
		                     <span id="span_articlecatid"></span>
							 <input type="text" class="pt" name="wd" id="wd" value="${entity.wd}"  default_data="标题" autocomplete="off">
							 <INPUT NAME="btn2" id="btn2" TYPE="submit" class="pb" VALUE=" 搜 索 ">
		     </form>	 
		</div>
		</td>
 	</tr>
 	
	<tr class="captail">
 		<td width="5%">选择</td>
 		<td style="text-align:left;">文章</td>
         
         
 		<td  width="15%">操作</td>
	</tr>
 	<c:forEach var="list" items="${list}">
	<tr class="tr"> 
 		<td><input type="checkbox" name="selId" class="pc" value="${list.id }"></td>
 		<td>
 		<dl>
 		 <dt>
 		  <c:if test="${list.img!=null && list.img!='' }">
 		   <img style="width:102.4px;height: 76.8px" src="${list.img}" />
 		   </c:if>
 		 <dt>
 		 <dd>
 		   <p class="title">${list.title }</p> 
 		   <p class="order">排序： ${list.idx }</p>
 		   <p class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"   value="${list.createtime}" /></p>
 		 </dd>
 		 </dl>
        </td>
         
      
		<td class="operate"><a href="javascript:new openWin({document:window.parent.document,width:'90%',height:'90%',title:'修改文章',src:'${pageContext.request.contextPath}/article/update?id=${list.id }'}).start()">修改</a>
		    
		   <a href="javascript:void(0)" class="del" value="${list.id }">删除</a>
		</td>
	</tr>
  </c:forEach>
 	 
	<TR>
		<TD ALIGN='CENTER' BGCOLOR='#FFFFFF' COLSPAN='3'>
		     <%@include file="../public/pagination.jspf"%>
 		</TD>
	</TR>
  </table>
  
  
  
	
</body>

<script>
    	 //分类加载
		  initArticlecatid();		   
		    
		  function initArticlecatid(){
			     var str = "<select name='articlecatid' id='articlecatid'  style='width:240px;height: 30px;'>";
			     str = str+"<option value=''>-- 请选择 --</option>"; 	
			     var articlecatList = bf_cfg.articlecatList;
			     for(var i=0;i<articlecatList.length;i++){			    	  
			    	  str = str+"<option value='"+articlecatList[i].id+"'>"+articlecatList[i].name+"</option>";			    	  
			    	 
			    	  var children = articlecatList[i].children;
			    	  for(var j=0;j<children.length;j++){			    	  
				    	  str = str+"<option value='"+children[j].id+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+children[j].name+"</option>";			    	  
				    	 
				     }
			     }
			     str = str + "</select>";			   
			     $("#span_articlecatid").html(str);	
			     $("#articlecatid option[value='"+bf_cfg.articlecatid+"']").attr("selected",true);
		   }
		  
		  
		
		   
		</script>
	
  <script>
 		var init1 = new Init();
        $(document).delegate("#batchDel","click",function(){ 
			 init1.doo(true,$("input[name='selId']:checked"),'','${pageContext.request.contextPath}/article/deleteBatch','确定删除所选记录吗？',true);
		});
		$(document).delegate("a.del","click",function(){ 
 			 init1.doo(false,'',$(this),'${pageContext.request.contextPath}/article/delete','确定删除当前记录吗？',true);
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
		
 </script>
 <script type="text/javascript">
 			var beforeFun1 = function(){  
			   var $wd = $("#wd");
 	           if($wd.val()==$wd.attr("default_data")){  $wd.val("") }  
  	        }
  			var submit1 =  new Submit({ submitType: "normal" ,formId: "form2" ,btnId: "btn2" ,beforeFun: beforeFun1    });
			submit1.focusBlur("#wd","#999","#000");
			submit1.clickOrEnter();
			
			//切换栏目
			  $("#articlecatid").bind("change",function(){
				  $("#wd").val("");
				  submit1.start(); 
			  })    
</script> 
</html>
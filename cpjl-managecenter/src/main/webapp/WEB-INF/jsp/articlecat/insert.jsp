<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>

 
<style type="text/css">
     body{  margin:10px;   overflow:visible; }
   #mTable{ width:100%; margin-top:20px; }
   #mTable tr,#mTable td,#mTable th{ height:24px; padding:5px; text-align:left;  }
   #mTable #baned{ color:#ff0000; }
   #mTable .r{ width:120px; text-align:right; white-space:nowrap;}  
   #mTable .description{ width:500px; height:50px; }
   

#top{ height:40px;  }
#top .left{ float:left;    height:40px; line-height:40px;   }
#top .left a.on{  color:#ff0000; }
#top .left a:hover{   background-color:#f0f0f0;  text-decoration:none;  }
#top .big{ font-size:24px; color:#999; }
#top .right{ float:right;  width:240px; height:40px; line-height:40px;   }


.menu{ position:relative; width:100%; height:24px; margin-top:5px; border-bottom:solid 1px #3f73b3;  }
.menu .one{ float:left;  margin:0px 10px;  height:24px; line-height:24px; padding:0px 15px; font-size:14px;   color: #004b91; cursor:pointer; }
.menu .one.hover{    font-weight: bold;border:solid 1px #3f73b3;  border-bottom:none; background-color:#ecf4fc;   }
.list .one{ display:none;}

.save{ position:fixed; bottom:10px; right:20px; height:54px; text-align:right;  z-index:1000001; }
.save #btn1{ float:right; width:90px; height:30px;  display:block;  }
#all_tips{  color:#f00; display:block; }
 

a.into{ padding:3px; border:solid 1px #666; background:#ddd; color:#000;  text-decoration:none;  border-radius:3px; }
a.into:hover{border:solid 1px #F90; color:#004b91; }


  

 </style>
 
 <style>
.div-upload{ position:relative;  width:88px; height:118px;    text-align:center;  z-index:2; }
.div-upload .div-preview{ position:absolute;   width:88px; height:88px;border:solid 1px #ccc; margin:0 auto;  z-index:1;}
.div-upload img{ max-width:88px; max-height:88px;}
.div-upload .div-selectFile{ position:absolute; width:88px; height:88px; overflow:hidden; z-index:2; }
.div-upload .selectFile{  width:88px; height:88px; opacity:0;  }

.div-upload .div-del{ position:absolute; top:0px; left:0px; width:88px; height:26px; background-color:#000; opacity:0.5; display:none;
z-index:3; }
.div-upload .div-del a{  display:block; width:24px; height:24px; float:right; margin-top:1px; margin-right:1px;
background: url(/platform-admin/static/ueditor/dialogs/image/images/icons.png) no-repeat;  background-position: -44px -20px;
z-index:3; }
.div-upload .div-del a:hover{ background-position: -44px 4px; }
.div-upload .row-btn{ position:absolute; top:90px; width:90px; text-align:center; line-height:30px; }
</style>

</head>
<body>
<script> 
var sb_cfg = {
		 "articlecatList" : ${articlecatList} 
		,"parentid":"${entity.parentid}"
		,"typeList" : [{"id":1,"name":"单页面"},{"id":2,"name":"文字列表"},{"id":3,"name":"图片列表"}]
		,"type":"${entity.type}"
}
</script>
<form name="form1" id="form1"  method="post" action="${pageContext.request.contextPath}/articlecat/insertDo"> 
 
<div class="menu">
  <li class="one">基本信息</li>   
 </div>
  
 <ul class="list">
	 <li class="one" id="list1">
       
    		 <table  border="0" cellpadding="0" cellspacing="1"  id="mTable">
    		  
			   <tr class="news">
				   <td class="r">栏目名称：</td>
				   <td>
					 <input type="text" name="name" id="name"  class="pt w250" value="${entity.name }">
					  
 				   </td>
			    </tr>	
			    
			    <tr class="news">
				   <td class="r">父栏目：</td>
				   <td>
					  <span id="span_parentid"></span>
 				   </td>
			    </tr>
			    
			    <tr class="news">
				   <td class="r">栏目类型：</td>
				   <td>
					  <span id="span_type"></span>
 				   </td>
			    </tr>
			    
  				<tr class="news">
				   <td class="r">排序：</td>
				   <td>
					 <input type="text" name="idx" id="idx"  class="pt" value="${entity.idx==null ? 10000 : entity.idx }">
					 
 				   </td>
			     </tr>  
			     
			   
			  
 			 </table>
			
 
 <!-- box1 End -->
     </li>
      
    
     
  </ul>		
    <div class="save">
     
 	     <div class="errors">
				     <c:forEach  items="${errors}" var="entry">
				       <label class="error">${entry.value }；</label> 
				     </c:forEach>
		  </div>
 	   
 	   <input type="submit" name="btn1"  id="btn1" class="pb saving" value=" 保 存 ">
 	   
  </div>
</form>   
 
<script language="JavaScript">
 	   $("body").focus();    //IE在第二次打开iframeWin窗口获取不到焦点 
 </script>
 
 <script>
    
  //页面切换
 		  Init.prototype.menuList = function(menu,list){
		        var menu = $(menu),
					list = $(list);
					
					for(var i=0;i<menu.length;i++){
					   menu.eq(i).bind("click",fun(i));
				    }
				    fun(0)(); 
					
					function fun(i){
 			        return function(){ 
					     menu.removeClass("hover");
						 menu.eq(i).addClass("hover");
  					     list.hide();
					     list.eq(i).show();
 						}
				   }
 		  }
  </script>

    
   <script language="JavaScript">
 	   $("body").focus();    //IE在第二次打开iframeWin窗口获取不到焦点
	   var init1 = new Init();
	 //  init1.insertInto();
	   init1.menuList(".menu .one",".list .one");
    </script>
      <script>
    	 //分类加载
		  initParentid();		   
		    
		  function initParentid(){
			     var str = "<select name='parentid' id='parentid'  style='width:240px;height: 30px;'>";
			     str = str+"<option value=''>-- 请选择 --</option>";	
			     var articlecatList = sb_cfg.articlecatList;
			     for(var i=0;i<articlecatList.length;i++){			    	  
			    	  str = str+"<option value='"+articlecatList[i].id+"'>"+articlecatList[i].name+"</option>";			    	  
			    	 
			     }
			     str = str + "</select>";			   
			     $("#span_parentid").html(str);	
			     $("#parentid option[value='"+sb_cfg.parentid+"']").attr("selected",true);
		   }
		  
		  
		//分类加载
		  initType();		   
		    
		  function initType(){
			     var str = "<select name='type' id='type'  style='width:240px;height: 30px;'>";
			     str = str+"<option value=''>-- 请选择 --</option>";	
			     var typeList = sb_cfg.typeList;
			     for(var i=0;i<typeList.length;i++){			    	  
			    	  str = str+"<option value='"+typeList[i].id+"'>"+typeList[i].name+"</option>";			    	  
			    	 
			     }
			     str = str + "</select>";			   
			     $("#span_type").html(str);	 
			     $("#type option[value='"+sb_cfg.type+"']").attr("selected",true);
		   }
		  
		</script>

</body>
</html>
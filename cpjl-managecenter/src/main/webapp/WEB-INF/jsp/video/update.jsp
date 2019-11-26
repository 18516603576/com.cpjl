<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改文档</title> 
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.js"></script> 
 <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/UploadSelector/main.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/js/UploadSelector/main.css">

 <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/UploadVideoSelector/main.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/js/UploadVideoSelector/main.css">
<style type="text/css">
   body{  margin:10px;   overflow:visible; }
   #mTable{ width:100%;  }
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
.list .one{position:relative; display:none;}

.save{ position:fixed; bottom:10px; right:20px; height:54px; text-align:right;  z-index:1000001; }
.save #btn1{ float:right; width:90px; height:30px;  display:block;  }
#all_tips{  color:#f00; display:block; }
 

a.into{ padding:3px; border:solid 1px #666; background:#ddd; color:#000;  text-decoration:none;  border-radius:3px; }
a.into:hover{border:solid 1px #F90; color:#004b91; }

  

 </style>
 <style>
#right-thumbnail-img{position:absolute; right:20px; top:20px; } 
#right-thumbnail-img p{ font-size:12px; line-height:30px; text-align:center; }
</style>
</head>
<body>
<script>
  var bf_cfg = {
		  "uuid":"<%=UUID.randomUUID().toString() %>" 
		  ,"videocatList" : ${videocatList} 
	      ,"videocatid":"${entity.videocatid}"
	      ,"img":"${entity.img}"
	      ,"url":"${entity.url}"
  }
</script>
      
		
             <form name="form1" id="form1"  method="post" action="${pageContext.request.contextPath}/video/updateDo "> 		
             <input type="hidden" name="id" value="${entity.id }" /> 
  
            <div id="right-thumbnail-img">
	           <div class="div-img"></div>  
	           <p>缩略图 500*500px</p>
	         </div>
	         	 
    		 <table  border="0" cellpadding="0" cellspacing="1"  id="mTable">
    		  
			   <tr class="news">
				   <td class="r">标题：</td>
				   <td>
					 <input type="text" name="title" id="title"  class="pt w350" value="${entity.title }">
					  
 				   </td>
			    </tr>	
			    
			     <tr class="news">
				   <td class="r">视频：</td>
				   <td>
					 
             <div class="div-video"></div>
    		   
					 
 				   </td>
			    </tr>	  		
			    
			    <tr class="news">
				   <td class="r">归属栏目：</td>
				   <td> 
					   <span id="span_videocatid"></span>
 				   </td>
			    </tr>  			   
				 	
  				<tr class="news">
				   <td class="r">排序：</td>
				   <td>
					 <input type="text" name="idx" id="idx"  class="pt" value="${entity.idx==null ? 10000 : entity.idx }">
					 
 				   </td>
			     </tr>  
				    
 			 </table>
   
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
    	 //分类加载
		  initArticlecatid();		   
		    
		  function initArticlecatid(){
			     var str = "<select name='videocatid' id='videocatid'  style='width:240px;height: 30px;'>";
			     str = str+"<option value=''>-- 请选择 --</option>"; 	
			     var videocatList = bf_cfg.videocatList;
			     for(var i=0;i<videocatList.length;i++){			    	  
			    	  str = str+"<option value='"+videocatList[i].id+"'>"+videocatList[i].name+"</option>";			    	  
			    	 
			    	  var children = videocatList[i].children;
			    	  for(var j=0;j<children.length;j++){			    	  
				    	  str = str+"<option value='"+children[j].id+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+children[j].name+"</option>";			    	  
				    	 
				     }
			     }
			     str = str + "</select>";			   
			     $("#span_videocatid").html(str);	
			     $("#videocatid option[value='"+bf_cfg.videocatid+"']").attr("selected",true);
		   }
		  
		   
		</script>
	
	
	<script> 
	   var noImageSrc ="${pageContext.request.contextPath}/static/img/no.png";
	   var submitUrl = "${pageContext.request.contextPath}/upload/thumbnail?width=300&height=300";  
	     
       //2、初始化图片列表 
	   addImgs(bf_cfg.img);   
	    
	   
	   //添加一个上传图片控件   
	   function addImgs(val){   
		   var thumbnailSubmitUrl = submitUrl;      
		   new UploadSelector({ 
		          "where":$(".div-img")
			      ,"imageInputName":"img"
				  ,"imageInputVal":val
				  ,"imageUrlPrefix":""
				  ,"noImageSrc":noImageSrc
				  ,"submitUrl":thumbnailSubmitUrl 
				  ,"isBatch":false
				  ,"addOne":function(val){
					  addImgs(val);  
				  }
				  }).init();
	   }  
    	 </script>
    	 
    	 <script> 
    	 //上传视频
	    
	   var submitUrl = "${pageContext.request.contextPath}/upload/video?uuid="+bf_cfg.uuid;  
	   var videoProgressUrl = "${pageContext.request.contextPath}/upload/videoProgress?uuid="+bf_cfg.uuid; 
	     
        
	   addVideo(bf_cfg.url);    
	   //添加一个上传图片控件   
	   function addVideo(val){    
		   new UploadVideoSelector({ 
		          "where":$(".div-video")
			      ,"videoInputName":"url"
				  ,"videoInputVal":val
				  ,"videoUrlPrefix":"" 
				  ,"submitUrl":submitUrl 
				  ,"videoProgressUrl":videoProgressUrl
				  ,"uuid":bf_cfg.uuid
				  }).init(); 
	   }  
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
</body>
</html>
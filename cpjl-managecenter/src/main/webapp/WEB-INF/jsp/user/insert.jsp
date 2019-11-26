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


 

 </style>
 <style>
.uploadImg{  right:20px; top:20px; width:152px; height:182px; text-align:center;  z-index:2; }
.uploadImg .previewImg{ border:solid 1px #ccc; }
.uploadImg img{ width:150px; height:150px;}
.uploadImg .btn{ width:80px; margin:0 auto; }
.uploadImg a{  line-height:30px;  display:block; float:left; }
.uploadImg .selectFile{ position:relative; width:40px; height:30px; overflow:hidden; }
.uploadImg .selectFile  input{ position:absolute;left:0px; top:0px;z-index:2; width:30px; height:30px;   }
.uploadImg .selectFile label{ position:absolute; left:0px; top:0px; z-index:1; width:30px; height:30px;  }
</style>
</head>
<body>
<script>
  var ed_config = {
  }
</script>
      
		
             <form name="form1" id="form1"  method="post" action="${pageContext.request.contextPath}/user/insertDo "> 		 
    		  
    		 <table  border="0" cellpadding="0" cellspacing="1"  id="mTable">
    		 
    		   <tr class="news">
				   <td class="r">头像：</td>
				   <td>
					 
              <div class="uploadImg">
    		         <div class="previewImg">
    		            
    		         </div>
    		         <input type="hidden" name="avartar" id="avartar" value="${entity.avartar }" />
    		         <div class="btn">
    		         <a href="" class="selectFile">
    		             <input type="file" name="selectFile" id="selectFile" style="width:40px; height:30px; opacity:0; ">
    		             <label>上传</label>
    		         </a>	 
    		         <a href="javascript:;" class="uploadDel">删除</a>      
    		         </div>  
    		   </div>
    		   
					 
 				   </td>
			    </tr>	  		
    		   
			   <tr class="news">
				   <td class="r">用户名：</td>
				   <td>
					 <input type="text" name="userName" id="userName"  class="pt w350" value="${entity.userName }">
					  
 				   </td>
			    </tr>	  			   
				 	
  				<tr class="news">
				   <td class="r">真实姓名：</td>
				   <td>
					 <input type="text" name="realName" id="realName"  class="pt" value="${entity.realName }">
					 
 				   </td>
			     </tr>  
			     <tr class="news">
				   <td class="r">手机号：</td>
				   <td>
					 <input type="text" name="mobile" id="mobile"  class="pt" value="${entity.mobile }">
					 
 				   </td>
			     </tr>  
			     <tr class="news">
				   <td class="r">职位：</td>
				   <td>
					 <input type="text" name="position" id="position"  class="pt" value="${entity.position }">
					 
 				   </td>
			     </tr> 
			     
			     
			     <tr class="news">
				   <td class="r">启用用户：</td>
				   <td>
					 <input type="checkbox" name="isAble" id="isAble"  class="pc" value="1" <c:if test="entity.isAble"> checked="checked" </c:if>>是					 
 				   </td>
			     </tr>  
			      
				   
			    
			  <TR>
				  <td colspan="2" class="sub" style="text-align:center;">
				  <div class="divline"></div>
				  <div class="errors">
				     <c:forEach  items="${errors}" var="entry">
				       <label class="error">${entry.value }；</label> 
				     </c:forEach>
				  </div>
				  <input type="submit" name="btn1"  id="btn1" class="pb" value=" 保 存 ">
				  </td>
			  </tr>
 			 </table>
			</form> 
 
  
 
<script language="JavaScript">
 	   $("body").focus();    //IE在第二次打开iframeWin窗口获取不到焦点
 </script>
    <script>
    //上传图片
	   $("#selectFile").bind("change",uploadSubmit);
	   $(".uploadImg .uploadDel").bind("click",uploadDel);
	   uploadInit();
	   function uploadInit(){
		   var val = $(".uploadImg #avartar").val();
		   $previewImg = $(".uploadImg .previewImg");
		    
		   if(val==""){ 
			   $previewImg.append("<img  src='${pageContext.request.contextPath}/static/img/no.png' />");
		   }
		   else
		   {
			   $previewImg.append("<img  src='${ftpDomain}"+val+"' />"); 
		   }
	   }
	   function uploadSubmit(){
         var formdata=new FormData();    
         formdata.append('file',$('#selectFile').get(0).files[0]);

         $.ajax({
             url:'${pageContext.request.contextPath}/upload/user',
             type:'post',
             contentType:false,
             data:formdata,
             processData:false,
             success:function(info){      
                 $('.uploadImg img').attr('src','${ftpDomain}'+info.model.url);
                 $('.uploadImg #avartar').val(info.model.url);
             },
             error:function(err){
                 console.log(err)
             }
         });
     }
	   
	   function uploadDel(){
		    
		   $('.uploadImg img').attr('src','${pageContext.request.contextPath}/static/img/no.png');
        $('.uploadImg #avartar').val('');
	   }
    		   </script>
</body>
</html>
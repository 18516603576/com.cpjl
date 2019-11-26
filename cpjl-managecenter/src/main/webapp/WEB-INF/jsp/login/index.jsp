<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>触屏精灵_后台登录</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/base.css">
 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.myValidate.js"></script>
<style type="text/css">
 body{ background-color:#eeeeee}
.main{ background-image:url(${pageContext.request.contextPath}/static/img/loginbg.png); background-repeat:no-repeat; margin:0 auto;  margin-top:80px;}
.main a.forgot{ color:#888; }

.tit{ font-size:18px; color:#333333; }
.td{ font-size:16px; color:#333333;}
.td input{   border:#999999 solid 1px; font-size:14px;margin-left:15px;font-weight:bold;}
.pb{ width:100px; height:30px; }
.pt{ height:28px; } 
.w210{ width:210px; }
.submit{  background:url(common/image/button.png) 1px 1px; }

#return-error{ font-size:12px; color:#f00; line-height:18px;}
 </style>
</head>
<body>
 <table width="743" height="332" border="0" align="center" cellpadding="0" cellspacing="0" class="main">
  <tr>
    <td colspan="2" height="94" valign="middle" style="padding-top:10px;">&nbsp;</td>
  </tr>
  <tr>
    <td width="294"></td>
    <td width="449" height="343" align="center" valign="top">
	<form name="form_login" id="form_login" method="post" action="${pageContext.request.contextPath}/login/verify">
	<table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr><td colspan="2" height="10"></td></tr>
	  <tr>
        <td width="111" height="36" align="right" class="td">用户名</td>
        <td width="289" height="36" align="left" class="td"><input type="text" name="userName" id="userName" value="${entity.userName}" class="pt w210"  placeholder="用户名"/></td>
      </tr>
      <tr>
        <td width="111" height="36" align="right" class="td">密　码</td>
        <td height="36" align="left" class="td"><input type="password" name="password" id="password" class="pt w210" autoComplete="false" />
          </td>
      </tr>
      
     
      <tr>
        <td width="111" height="36" align="right" class="td">验证码</td>
        <td height="36" align="left" class="td">
		   <input name="code" id="code" type="text" class="pt w80"  maxlength="4"  style="float:left;"/>
		    <img id="codeImg" style="float:left; margin-left:7px; width:100px; height:30px; " src="${pageContext.request.contextPath}/login/imageCode" 
		    onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/login/imageCode?s='+Math.random();" alt="验证码" title="看不清楚?换一张" 
		    onclick="src='${pageContext.request.contextPath}/login/imageCode?s='+Math.random()" />
		</td>
	 </tr>
		
		<tr>
        <td height="36" colspan="2" align="center" id="return-error"> 
	       <c:forEach var="errors" items="${errors}">
	          <div id="${errors.key }-error" class="error">${errors.value }</div>    
		    </c:forEach> 
		    ${errors.msg }
         </td>
        </tr>
      <tr>
       <td height="36" colspan="2" class="btn" align="center">
          <input type="submit" name="submit1" value="登 录"  class="pb w100"  />
       </td>
        </tr>
    </table>
	</form>
	</td>
  </tr>
</table>
 
  <script>

 
 
	 $("#form_login1").validate({  	   
	   	   ignore:[],
	   	   errorElement:"div",
		   errorLabelContainer: $("#return-error"), 
	   	   submitHandler:function(form) {  
	   		    form.submit();  
	   		},
	   		rules: {
	   			name:{required: true,rangelength : [1,20]},
	   			password : {required: true, rangelength:[1,20]}
	   			 
	   		},
	   		messages:{
	   		   name : {
	   				required: "请输入用户名",
	   				rangelength:"用户名长度为 {0} - {1}个字符"
	   				},
	   			password : {
	   				required : "请输入密码",
	   				rangelength : "密码长度为 {0} - {1}个字符"
	   			} 
	   		}
	   	});
 
 
	      </script>
<script>
if(window.top.location!=window.self.location){
	window.top.location=window.self.location;
}
</script>
</body>
</html>
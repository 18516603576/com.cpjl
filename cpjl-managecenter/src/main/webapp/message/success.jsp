<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 
<HTML>
<HEAD>
    <TITLE>保存成功</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style>
        .main{ width:400px; height:300px; margin:0 auto; margin-top:50px; border:solid 1px #ccc; }
        .msg{
           height:30px; line-height:30px;
           text-align:center; margin:90px 0px;
        }
        .btn-row{
          width:140px;
          margin:0 auto;
        }
        .btn{  
         float:left; margin:0px 15px;
         padding:10px;  width:100px; height:20px; line-height:20px;
         text-align:center; background-color:#00cd0c;
         border-radius:5px; color:#fff; text-decoration:none; 
         display:block;
         }
    </style>
</HEAD>   
<BODY>
 
 <div class="main">
	<p class="msg"> 保存成功！ </p>
	 
	<div class="btn-row">
	<a class="btn sure" href="javascript:parent.window.frames['main'].out.callback({'s':'200', 'str':'添加成功'});">确定</a>
 
   </div>
</div>	 
 
</BODY>
</HTML>

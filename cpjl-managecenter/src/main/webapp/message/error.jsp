<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="org.apache.commons.lang.StringEscapeUtils" %>
 
<HTML>
<HEAD>
    <TITLE>错误信息</TITLE>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 
</HEAD>   
<BODY>
 
	<p> 错误信息： </p>
	<p><%= StringEscapeUtils.escapeHtml(java.net.URLDecoder.decode(request.getParameter("id"))) %></p> 
	
	<input type="button" name="btn1" id="btn1" onclick="javascript:parent.window.frames['main'].out.callback({'s':'200', 'str':'添加成功'});" value=" 确定 ">
 
</BODY>
</HTML>

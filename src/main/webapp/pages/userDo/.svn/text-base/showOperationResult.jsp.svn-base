<%@page import="java.util.ArrayList"%>
<%@page import="by.satalin.library.servises.JspService"%>
<%@ page import="by.satalin.library.datamodel.User, java.util.*" %>
<%@ taglib uri="/WEB-INF/taglib1.tld" prefix="esc" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>результат выполнения операции</title>
<meta http-equiv="Content-Type" content="text; charset=UTF-8">
</head>
<body>
	<%
	{
		JspService servise = new JspService(session);
		User user = servise.getUser();
		servise.deleteResponseFromSession(session);
		if (user != null) {
    %>
		<esc:Escape>
		 OK User=  <%=user.getNickName()%> ID=
		<%=user.getId()%>
		</esc:Escape>
		<%
		}
	}
		%>
</body>
</html>
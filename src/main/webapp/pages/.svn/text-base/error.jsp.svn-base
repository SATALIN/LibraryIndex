<%@page import="by.satalin.library.exceptions.BaseException"%>
<%@page import="by.satalin.library.servises.UserService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="by.satalin.library.command.CommandResponse"%>
<%@ page import="by.satalin.library.datamodel.User, java.util.*" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Произошла ошибка</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% UserService service = new UserService();
try{
	//TODO remake
	User user = (User)session.getAttribute("USER");
	if (user!= null){
      if (service.login(user)!=null){%>
      <base>
<meta http-equiv="Refresh" content="3; URL=/LibraryIndex/pages/userDo/listUser.jsp">
<% }else{%>
<meta http-equiv="Refresh" content="3; URL=index.jsp">
<% }}else {
	%><meta http-equiv="Refresh" content="3; URL=index.jsp"><%
}
}catch(BaseException e){%>
	<meta http-equiv="Refresh" content="3; URL=index.jsp">
	<%}%>
</head>
<body>
<h1 color="red">Error</h1>
<% 		CommandResponse commandResponse = (CommandResponse) session.getAttribute("SAVERESPONSE");
		session.setAttribute("SAVERESPONSE", null);
		if(commandResponse!=null){ 
		%><h1><label ><%=commandResponse.getException().toString()%><h1><label ><%	
		}
%>
</body>
</html>
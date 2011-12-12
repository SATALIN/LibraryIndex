<%@page import="by.satalin.library.command.CommandResponse"%>
<%@page import="by.satalin.library.servises.JspService"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="by.satalin.library.datamodel.User, java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib1.tld" prefix="esc" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Список всех полльзователей</title>
</head>
<body>
<jsp:include page="/pages/commandMenu.jsp"></jsp:include>	
	<%{
	JspService servise = new JspService(session);
	List<User> users;
	users = servise.getUsers();
	User delUser = servise.getUser();
	servise.deleteResponseFromSession(session);%>
					<javascript>
					<%if (delUser != null){ %>
		            	alert(User deleted! id=<%=delUser.getId() %>);
		            	<% }%>
		            </javascript>
				<TABLE BORDER="1" CELLPADDING="3" CELLSPACING="1">
				<TR>
					<TH>Идентификатор</TH>
					<TH>Никнейм</TH>
					<TH>Права</TH>
					<TH>Редактировать</TH>
					<TH>Удалить</TH>
				</TR>
				<%  for (User user: users) { %>
				<TR>
					<TD><%= user.getId() %></TD>
					<TD><esc:Escape><%= user.getNickName() %></esc:Escape>	</TD>
					<TD><%= user.getRole().toString() %></TD>
					<TD>
					<form method="POST" action="editUser.do">
						 <input type="hidden" id="command" name="command" value="GETUSER" />
						 <input type="hidden" id="ID" name="ID" value="<%=user.getId() %>"/> 
					  	 <input type="submit" value="Edit" />
					</form>	
					</TD>
					<TD>
					<form method="POST" action="deleteUser.do">
						 <input type="hidden" id="command" name="command" value="DELETEUSER" />
						 <input type="hidden" id="isReturnList" name="IS_RETURN_LIST" value="True" />
						 <input type="hidden" id="ID" name="ID" value="<%=user.getId() %>"/> 
					  	 <input type="submit" value="Delete" />
					</form>	
					</TD>
				</TR>
				
				<%
				} 
		}	
		%>
</body >
</html>
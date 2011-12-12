<%@page import="by.satalin.library.servises.JspService"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="by.satalin.library.datamodel.Library, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Отображение списка всех библиотек</title>
</head>
<body>
 <jsp:include page="/pages/commandMenu.jsp"></jsp:include></td>
 
	<%  JspService servise = new JspService(session);
		List<Library> libraries;
		libraries = servise.getLibraries();
		servise.deleteResponseFromSession(session);%>	
	
	<TABLE BORDER="1" CELLPADDING="3" CELLSPACING="1">
		<TR>
			<TH>Название библиотеки</TH>
			<TH>Количество книг</TH>
		</TR>
		<%  for (Library library: libraries) { %>
		<TR>
			<TD><%= library.getTitle() %></TD>
			<TD><%= library.getBooks().size() %></TD>
		</TR>
		<% } %>
	</TABLE>

</body>
</html>
<%@page import="java.util.ArrayList"%>
<%@page import="by.satalin.library.servises.JspService"%>
<%@ page import="by.satalin.library.datamodel.Book, java.util.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Найти книгу в библиотеках</title>
</head>
<body>
<jsp:include page="/pages/commandMenu.jsp"></jsp:include>
	<form method="POST" action="find.do">
		<input type="hidden" id="command" name="command" value="FIND" /><br> 
		<label for="name">Enter author name </label> <br> 
		<input type="text" id="AUTHOR" name="AUTHOR" /><br> 
		<label for="name">Enter	title book </label><br> 
		<input type="text" id="NAME" name="NAME" /><br> 
		<input type="submit" value="Find" />
		<input type="reset" value="Reset" />
	</form>
	<%{
	long timeBegin = Calendar.getInstance().getTimeInMillis();
	JspService servise = new JspService(session);
	List<Book> books;
	books = servise.getBooks();
	Book responseBook = servise.getBook(); 
	servise.deleteResponseFromSession(session);
	if (responseBook!= null){%>
	<javascript> alert(book has been update id=<%=responseBook.getId() %>);
	</javascript>
	<%}
	timeBegin = Calendar.getInstance().getTimeInMillis(); %>
	<h6>начало построения таблицы :<%=Calendar.getInstance().getTimeInMillis()%></h6>
					
		<TABLE BORDER="1" CELLPADDING="3" CELLSPACING="1">
	    <TR>
	        <TH>Состояние</TH>
	        <TH>Идентификатор</TH>
	        <TH>Автор</TH>
	        <TH>Название</TH>
	        <TH>Взята :</TH>
	        <TH>Взять/Вернуть</TH>
	    </TR>
	<%  for (Book book : books) { %>
		<TR>
			<TD>
				<CENTER>
				<%if (book.getDate() == null){  %>
					FREE
				<%}else {  %>	
					ORDER
				<%}%>
				</CENTER>
			</TD>
			<TD><%=book.getId()%>
			</TD>
			<TD><%=book.getAuthor()%>
			</TD>
			<TD><%=book.getTitle()%>
			</TD>
			<TD><% if (book.getDate()!= null){ %>  <%=book.getDate()%><%} %>
			</TD>
			<TD><% if (book.getDate()!= null){ %>
			  		<form method="POST" action="return.do">
						 <input type="hidden" id="command" name="command" value="RETURN" />
						 <input type="hidden" id="isReturnList" name="IS_RETURN_LIST" value="True" />
						 <input type="hidden" id="AUTHOR" name="AUTHOR" />
						 <input type="hidden" id="NAME" name="NAME" /> 
						 <input type="hidden" id="ID" name="ID" value="<%=book.getId() %>"/> 
					  	 <input type="submit" value="RETURN" />
					</form>
			  <%}else{ %>
			  		<form method="POST" action="order.do">
						 <input type="hidden" id="command" name="command" value="ORDER" />
						 <input type="hidden" id="isReturnList" name="IS_RETURN_LIST" value="True" />
						 <input type="hidden" id="AUTHOR" name="AUTHOR" />
						 <input type="hidden" id="NAME" name="NAME" /> 
						 <input type="hidden" id="ID" name="ID" value="<%=book.getId() %>"/> 
					  	 <input type="submit" value="ORDER" />
					</form>
			  <%} %>
			</TD>
		</TR>
		<% } %>
	</TABLE>
<% 
	 long timeEnd = Calendar.getInstance().getTimeInMillis();
     long razn = timeEnd - timeBegin; %>
	<h6>закончилось построение таблицы :<%=Calendar.getInstance().getTimeInMillis()%></h6>
	<h6>разница =<%=String.valueOf(razn)%></h6>
		<% } %>	
</body>
</html>
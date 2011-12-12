<%@page import="java.util.ArrayList"%>
<%@page import="by.satalin.library.servises.JspService"%>
<%@ page import="by.satalin.library.datamodel.User,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Редактирование пользователя</title>
</head>
<body>
	<jsp:include page="/pages/commandMenu.jsp"></jsp:include>
	<%
	{
		JspService servise = new JspService(session);
		List<User> users;
		User user = servise.getUser();

		if (user != null) {
%>
	<h1>
		Редактируется пользователь ID=
		<%=user.getId()%></h1>
	<form method="POST" action="editUser.do">
		<input type="hidden" id="command" name="command" value="EDITUSER" /><br>
		<input type="hidden" id="id" name="ID" value="<%=user.getId()%>" /><br>
		<label for="name" color="red">Никнейм пользователя </label> <br>
		<input type="text" id="nickName" name="NICKNAME"
			value="<%=user.getNickName()%>" /><br> <label for="name">Имя
		</label> <br> <input type="text" id="fName" name="FNAME"
			value="<%=user.getFirstName()%>" /><br> <label for="name">Фамилия
		</label> <br> <input type="text" id="lName" name="LNAME"
			value="<%=user.getLastName()%>" /><br> <label for="name">Права</label>
		<br> <select name="ROLE">
			<option value="OPERATOR">OPERATOR</option>
			<option value="ABONENT">ABONENT</option>
		</select> <label for="name">Пароль </label> <br> <input type="text"
			id="password" name="PASSWORD" value="<%=user.getPassword()%>" /><br>
		<input type="submit" value="Edit" /> <input type="reset"
			value="Reset" />
	</form>
	<%
		}
		}
	%>
	<jsp:include page="/pages/userDo/showOperationResult.jsp"></jsp:include>
</body>
</html>
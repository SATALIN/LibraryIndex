<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Введите логин и пароль для входа</title>
</head>
<body>
	<form method="POST" action="LoginServlet">
		<label for="name">Enter your name </label> <br> 
		<input type="text" id="LOGIN" name="LOGIN" /><br> 
		<label for="name">Enter	your password </label><br> 
		<input type="password" id="PASSWORD" name="PASSWORD" /><br> 
		<input type="submit" value="Login" />
		<input type="reset" value="Reset Form" />
	</form>
</body>
</html>
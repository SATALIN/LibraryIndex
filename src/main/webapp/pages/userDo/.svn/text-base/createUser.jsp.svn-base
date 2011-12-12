<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Создание пользователя</title>
</head>
<body>
<jsp:include page="/pages/commandMenu.jsp"></jsp:include>
<form method="POST" action="deleteUser.do">
		 <input type="hidden" id="command" name="command" value="CREATEUSER" /><br> 
		<label for="name" color="red">Введите никнейм (Login) </label> <br> 
		<input type="text" id="nickName" name="NICKNAME" /><br> 
		<label for="name" color="red">Введите пароль </label> <br> 
		<input type="text" id="password" name="PASSWORD" /><br>
		<label for="name" color="red">Права </label> <br> 
		<select name="ROLE" >
   			 <option value="OPERATOR">OPERATOR</option>
 			 <option value="ABONENT">ABONENT</option>
 		</select>		
		<input type="submit" value="Create" />
		<input type="reset" value="Reset" />
	</form>	
<jsp:include page="/pages/userDo/showOperationResult.jsp"></jsp:include>	
</body>
</html>
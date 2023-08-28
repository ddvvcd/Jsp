<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="ub" class="sub1.UserBean">
	<jsp:setProperty property="uid" name="user"/>
	<jsp:setProperty property="name" name="user"/>
	<jsp:setProperty property="hp" name="user"/>
	<jsp:setProperty property="age" name="user"/>
	
</jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>userProc</title>
	</head>
	<body>
		<h3>전송 데이터 출력</h3>
		<p>
			아이디 : <%= ub.getUid() %><br>
			아이디 : <%= ub.getName() %><br>
			아이디 : <%= ub.getHp() %><br>
			아이디 : <%= ub.getAge() %><br>
		</p>
	</body>
</html>
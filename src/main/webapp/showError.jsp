<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<% String url = application.getContextPath() + "/"; %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>

<body>
<br><br><br>

	<center>
		<h3>${requestScope.errorMsg}</h3>
			
		<br><br><br>
		<!-- 내 현재위치(프로젝트 위치) 반환? -->
		<a href="${pageContext.request.contextPath}/index.html">메인 화면으로 이동하기</a>
	</center>
	
</body>
</html>
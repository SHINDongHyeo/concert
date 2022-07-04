<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<br><br><br>
<center>

<h3>재능 기부자 정보 수정하기 - 재능 분야 수정</h3>
<hr><p>

<form action="probono?command=activistUpdate" method="post">
<table border="1">
	<thead>
		<tr>
			<th>기부자 id</th><th>기부자명</th><th>비밀번호</th><th>전공분야</th>
		</tr>
 	<tr>
 		<td><input type="text" name="activistId"  value="${activist.id}" readonly></td>
 		<td>${activist.name}</td>
 		<td>${activist.password}</td>
 		<td><input type="text" name="major" value="${activist.major}"></td>
 	</tr>
 	
 	<tr>
 		<td colspan="4">
 			<input type="submit" value="수정">
 			&nbsp;&nbsp;&nbsp;
 			<input type="reset" value="취소">
 		</td>
 	</tr>
 	
</table>
</form>
<a href="${pageContext.request.contextPath}/index.html">메인 화면으로 이동하기</a>

</center>
</body>
</html>
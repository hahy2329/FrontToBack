<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>이메일 보내기</h2>
<form action="${contextPath }/email/send" method="post"> 
<!-- post방식으로 자료를 컨트롤러로 보냄 -->
 
	발신자 이름 : <input type="text" name="senderName"><br>
	발신자 이메일 : <input type="text" name="senderMail"><br>
	수신자 이메일 : <input  type="text" name="receiveMail"><br>
	제목 : <input type="text" name="subject"><br>
	내용 : <textarea rows="5" cols="80" name="message"></textarea>
	<br>
	<input type="submit" value="전송">
	</form>
	<span style="color:red;">${message}</span>
</body>
</html>
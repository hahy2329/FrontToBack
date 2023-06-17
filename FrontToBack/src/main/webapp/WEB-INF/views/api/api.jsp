<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	$.ajax({
		method : "GET",
		url : "https://dapi.kakao.com/v3/search/book?target=title",
		data : { "query" : "미움받을 용기" },
		headers : {"Authorization" : "KakaoAK 9291681c4b5f1c251ec456e619d7f1ed"}
			
		
		
		
	});
		done(function(msg){
			
			alert("Data Saved : " + msg);
			
		});
		

</script>
</head>
<body>
	<h1>내 사이트임  ㅎㅇ</h1>
	





</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	
	var isValid = false;
	
	$().ready(function(){
		
		$("#btnOverlapped").click(function(){
			
			
			var memberId = $("#memberId").val();
			var passwd = $("#passwd").val();
			
			if(passwd == ''){
				alert("패스워드를 입력해주세요.");
				return false;
			}
			
			$.ajax({
				
				type : "get",
				url : "${contextPath}/member/checkDuplicatedPasswd?passwd="+passwd+"&memberId="+memberId,
				success : function(data){
					if(data == "duplicate"){
						
						alert("확인되었습니다.");
						isValid=true;
						$("#btnOverlapped").remove();
						$(".answer").append("<p style='color: green;'>"+"확인되었습니다." + "</p>");
						
					}
					
					else{
						alert("패스워드를 다시 확인해주세요.");
						isValid=false;
						$(".answer").append("<p style='color: red;'>"+"패스워드를 다시 확인해주세요." + "</p>");
					}
					
					
				}
				
				
			});
			
		});
		
		$("form").submit(function(){
			
			if(isValid == false){
				alert("패스워드를 확인해주세요.");
				return false;
			}
			
			if(isValid == true){
				alert("정상적으로 등록되었습니다.");
				return true;
			}
			
			
		});
		
		
		
		
	});


</script>
</head>
<body>



	<div>
		<br><br><br><tr>
	
	</div>
	<div align="center">
	<fieldset>
		<form action="${contextPath }/boardAdvance/knowledgeAddBoard" method="post">	
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="memberId" id="memberId" value="${sessionScope.memberId }" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="passwd" id="passwd" required="required">
					<input type="button" id="btnOverlapped" value="인증">
					<p class="answer"></p>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="subject" required="required">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="50" name="content" required="required"></textarea>
					<script>CKEDITOR.replace("content")</script>
				</td>
			</tr>
			<tr>
				<td>포지션</td>
				<td><input type="text" name="sort" value="${sessionScope.sort }" readonly="readonly"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="글쓰기">
					<input type="reset" value="재작성">
					<input type="button" onclick="location.href='${contextPath}/boardAdvance/knowledgeList'" value="목록보기">
				</td>
			</tr>
			
		</table>
		</form>
	</fieldset>
	
	</div>
</body>
</html>
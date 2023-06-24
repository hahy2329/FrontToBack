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
			
			$(".answer").empty();
			
			var writer = $("#writer").val();
			var passwd = $("#passwd").val();
			
			if(passwd == ''){
				alert("패스워드를 입력해주세요.");
				return false;
			}
			
			$.ajax({
				
				type : "get",
				url : "${contextPath}/boardAdvance/checkDuplicatedPasswd?passwd="+passwd+"&writer="+writer,
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
				
				return true;
			}
			
			
		});
		
		
		
		
	});


</script>
</head>
<body>

	<div align="center" style="padding-top: 100px">
	<fieldset>
		<form action="${contextPath }/boardAdvance/knowledgeUpdateReply" method="post">	
		<table border="1" style="width: 700px; text-align: left">
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tr>
				<td>작성일</td>
				<td><fmt:formatDate value="${knowledgeReplyDTO.enrollDt }" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" name="writer" id="writer" value="${knowledgeReplyDTO.writer }" readonly="readonly">
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
				<td>내용</td>
				<td>
					<textarea rows="10" cols="50" name="content" required="required">${knowledgeReplyDTO.content }</textarea>
					<script>CKEDITOR.replace("content")</script>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="수정">
					<input type="hidden" name="replyId" value="${knowledgeReplyDTO.replyId }">
					<input type="hidden" name="boardId" value="${knowledgeReplyDTO.boardId }">
					<input type="reset" value="재작성">
					<input type="button" onclick="location.href='${contextPath}/boardAdvance/knowledgeDetail?boardId=${knowledgeReplyDTO.boardId }'" value="목록보기">
				</td>
			</tr>
			
		</table>
		</form>
	</fieldset>
	
	</div>
</body>
</html>
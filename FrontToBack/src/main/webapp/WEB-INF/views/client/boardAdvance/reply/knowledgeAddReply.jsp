<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add reply</title>
<script>
	
	var isValidWriter = false;
	var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	
	$().ready(function(){
		
		$("btnOverlapped").click(function(){
			
			var writer = $("#writer").val();
			
			
			if(writer == ''){
				alert("작성자를 입력해주세요.");
				return;
			}
			
			if(writer.search(/\s/) != -1){
				
				alert("공백은 허용할 수 없습니다.");
				return false;
			}//공백 체크
			
			if(special_pattern.test(writer) == true) {
				alert("특수문자는 허용할 수 없습니다.");
				return false;
			}//특수문자 체크
			
			$.ajax({
				
				type="get",
				url : "${contextPath}/boardAdvance/checkDuplicatedWriter?writer=" +writer,
				success : function(data){
					
					if(data == "duplicate"){
						alert("사용할 수 있는 작성자입니다.");
						isValidWriter = true;
					}
					
					else{
						alert("사용할 수 없는 작성자입니다.");
						isValidWriter = false;
					}
					
				}
				
			});
			
			
			
		});
		
		$("form").submit(function(){
			
			if(isValidWriter == false){
				alert("작성자를 확인해주세요.");
				return false;
			}
			if(isValidWriter == true){
				if($("#passwd").val() == $("#confirmPasswd").val()){
					alert("등록중입니다. 잠시만 기다려주세요.");
					return true;
				}
				else{
					alert("패스워드를 다시 확인해주세요.");
					return false;
					
				}
				
				
			}
			
			
		});
		
		
		
		
	});

</script>
</head>
<body>

	<div align="center" style="padding-top: 100px">
		<form action="${contextPath }/boardAdvance/KnowledgeAddReply" method="post" >
			<h2>댓글 입력하기</h2>
			<br>
			<table style="width: 700px;" border="1">
				<colgroup>
					<col width="20%">
					<col width="80%">
				</colgroup>
				<tr>
					<td>작성자</td>
					<td>
						<input type="text" name="writer" id="writer" placeholder="작성자를 입력해주세요." required="required" maxlength="15"/>
						<input type="button" id="btnOverlapped" value="중복확인">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password"  name="passwd" id="passwd" placeholder="비밀번호를 입력해주세요." required="required"/>
						<input type="password" id="confirmPasswd" placeholder="비밀번호를 한 번 더 입력부탁드립니다." required="required">
					</td>
				</tr>
				<tr>
					<td>글내용</td>
					<td>
						<textarea rows="10" cols="50" name="content" placeholder="200자 이내로 작성하세요." required="required"></textarea>
					</td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<input type="hidden" name="boardId" value="${boardId }">
						<input type="submit" value="댓글쓰기">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
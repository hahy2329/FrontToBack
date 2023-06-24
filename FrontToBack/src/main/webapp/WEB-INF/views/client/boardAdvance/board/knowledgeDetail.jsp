<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center" style="padding-top: 100px ">
	<fieldset>
		<table border="1" style="width:700px; text-align: left" class="table table-hover">
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tr>
				<td>작성일</td>
				<td><fmt:formatDate value="${knowledgeDTO.enrollDt }" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>${knowledgeDTO.memberId }</td>
			</tr>
			<tr>
				<td>포지션</td>
				<td>${knowledgeDTO.sort }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${knowledgeDTO.readCnt }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${knowledgeDTO.subject }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="50" name="content" required="required" readonly="readonly">${knowledgeDTO.content }</textarea>
					<script>CKEDITOR.replace("content")</script>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="right">
					<input type="button" value="삭제" onclick="location.href='${contextPath}/boardAdvance/knowledgeRemoveBoard?boardId=${knowledgeDTO.boardId }'">
					<input type="button" value="수정" onclick="location.href='${contextPath}/boardAdvance/knowledgeUpdateBoard?boardId=${knowledgeDTO.boardId }'">					
					<input type="button" onclick="location.href='${contextPath}/boardAdvance/knowledgeList'" value="목록보기">
				</td>
			</tr>
		</table>
		
		<br>
		<hr>
		<br>
		
		<h4>댓글 리스트(${allReplyCnt })</h4>
		<table style="width: 700px;" border="1">
			<c:forEach var="replyDTO" items="${knowledgeReplyDTO }">
				<tr>
					<td>
						작성일 : <fmt:formatDate value="${replyDTO.enrollDt }" pattern="yyyy-MM-dd"/> <br>
						작성자 : ${replyDTO.writer } <br> 
						내용 : ${replyDTO.content }
						<input type="button" value="수정" onclick="location.href='${contextPath}/boardAdvance/knowledgeUpdateReply?replyId=${replyDTO.replyId }'">
						<input type="button" value="삭제" onclick="location.href='${contextPath}/boardAdvance/knowledgeRemoveReply?replyId=${replyDTO.replyId }'">
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td align="right">
					<input type="button" value="댓글작성" onclick="location.href='${contextPath}/boardAdvance/KnowledgeAddReply?boardId=${knowledgeDTO.boardId }'">
				</td>
			</tr>
		</table>
	</fieldset>
	
	</div>
	
	
	
</body>
</html>
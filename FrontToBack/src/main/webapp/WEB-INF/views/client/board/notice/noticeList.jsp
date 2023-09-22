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
	$.ready(function(){
		
		$("#onePageViewCnt").val("${onePageViewCnt}");
		$("#searchKeyword").val("${searchKeyword}");
		
		
	});
	
	function getBoardList(){
		
		var url = "${contextPath}/boardAdvance/noticeList";
			url +="?searchKeyword=" + $("#searchKeyword").val();
			url +="&searchWord="+$("#searchWord").val();
			url +="&onePageViewCnt="+$("#onePageViewCnt").val();
		
		location.href= url;
	}


</script>
</head>
<body>
	<!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                         <a href="${contextPath }/"><i class="fa fa-home"></i> Home</a>
                         <a>커뮤니티</a>
                         <span>공지사항</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
	<section class="shop-cart spad">
	   <div class="container">
		<div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                    <h2>공지사항</h2>
                        <table>
                        	<colgroup>
                        		<col width="10%">
                        		<col width="30%">
                        		<col width="20%">
                        		<col width="20%">
                        		<col width="10%">
                        		<col width="10%">
                        	</colgroup>
                        		<tr>
                        			<td>
                        				조회 : <span style="color: red;">${allBoardCnt }</span>개
                        			</td>
                        			<td colspan="5" align="right">
                        				<select id="onePageViewCnt" onchange="getBoardList()">
                        					<option <c:if test="${onePageViewCnt eq 5 }">selected</c:if>>5</option>
                        					<option <c:if test="${onePageViewCnt eq 7 }">selected</c:if>>7</option>
                        					<option <c:if test="${onePageViewCnt eq 10 }">selected</c:if>>10</option>
                        				</select>
                        			</td>
                        		</tr>
                               <tr align="center">
                                   <th>번호</th>
                                   <th>제목</th>
                                   <th>관리자 아이디</th>
                                   <th>포지션</th>
                                   <th>작성일</th>
                                   <th>조회수</th>
                               </tr>
                            <tbody id="boardList">
                                <c:forEach var="noticeDTO" items="${boardList }">
                                	<tr align="center">
                                		<c:set var="startBoardIdx" value="${startBoardIdx + 1 }"/>
                                		<td>${startBoardIdx }</td>
                                		<td align="left">
                                			<a href="${contextPath }/boardAdvance/noticeDetail?boardId=${noticeDTO.boardId}">${noticeDTO.subject }</a>
                                		</td>
                                		<td>${noticeDTO.adminId }</td>
                                		<td>${noticeDTO.sort }</td>
                                		<td><fmt:formatDate value="${noticeDTO.enrollDt }" pattern="yyyy-MM-dd"/></td>
                                		<td>${noticeDTO.readCnt }</td>
                                	</tr>
                                </c:forEach>
                            </tbody>
                            </table>
                            <div><br><br><br><hr></div>
                            	<div align="center">
                            		<select id="searchKeyword">
                            			<option value="total">전체검색</option>
                            			<option value="subject">제목</option>
                            			<option value="adminId">관리자 아이디</option>
                            			<option value="content">내용</option>
                            		</select>
                            		<input type="text" id="searchWord" name="searchWord" value="${searchWord }">
                            		<input type="button" value="검색" onclick="getBoardList()">
                            	</div>
                        <div style="display: table; margin-left: auto; margin-right: auto">
                        	<ul>
                        		<c:if test="${startPage > 10 }">
                        				<a href="${contextPath }/boardAdvance/noticeList?currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt }&searchKeyword=${searchKeyword }&searchWord=${searchWord }">이전 &nbsp;</a>
                        		</c:if>
                        		<c:forEach var="i" begin="${startPage }" end="${endPage }">
                        				<a href="${contextPath }/boardAdvance/noticeList?currentPageNumber=${i }&onePageViewCnt=${onePageViewCnt  }&searchKeyword=${searchKeyword }&searchWord=${searchWord}">${i }&nbsp;</a>
                        		</c:forEach>
                        		<c:if test="${endPage != allPageCnt && endPage >=10}">
                        				<a href="${contextPath }/baordAdvance/noticeList?currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt  }&searchKeyword=${searchKeyword }&searchWord=${searchWord}">다음</a>
                        		</c:if>
                        	</ul>
                        </div>
                    </div>
                </div>
            </div>
         </div>
      </section>
</body>
</html>
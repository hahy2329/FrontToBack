<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
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
		
		var url = "${contextPath}/admin/knowledgeList";
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
                        <a>관리</a>
                        <span>지식</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
    
    <!-- Shop Section Begin -->
    <section class="shop spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="shop__sidebar">
                        <div class="sidebar__categories">
                            <div class="section-title">
                                <h4>관리</h4>
                            </div>
                            <div class="categories__accordion">
                                <div class="accordion" id="accordionExample">
                                	<div class="card">
                                        <div class="card-heading active">
                                            <a data-target="#collapseOne" href="${contextPath }/admin/noticeList">공지사항</a>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-heading active">
                                            <a data-toggle="collapse" data-target="#collapseOne">커뮤니티</a>
                                        </div>
                                        <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <ul>
                                                    <li><a href="${contextPath }/admin/knowledgeList">지식</a></li>
                                                    <li><a href="${contextPath }/admin/qnaList">Q&A</a></li>
                                                    <li><a href="${contextPath }/admin/studyList">스터디그룹</a></li>
                                                    <li><a href="${contextPath }/admin/bookList">도서추천</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-heading">
                                            <a data-toggle="collapse" data-target="#collapseTwo">유저관리</a>
                                        </div>
                                        <div id="collapseTwo" class="collapse" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <ul>
                                                    <li><a href="${contextPath }/admin/memberDelete">회원삭제</a></li>
                                                    <li><a href="${contextPath }/admin/memberList">회원리스트 조회</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                 
                    
                <div class="trend__content">
                    <div class="section-title">
                        <h4>지식</h4>
                    </div>
                    
                    <div align="right">
                     	조회 : <span style="color:red;">${allBoardCnt }</span>개
                	<select id="onePageViewCnt" onchange="getBoardList()">
                		<option value="5">5</option>
                		<option value="7">7</option>
                		<option value="10">10</option>
                	</select>
                	<p></p>
                	</div>
                    <c:forEach var="knowledgeDTO" items="${boardList }">
                    <div class="trend__item">
                        <div class="trend__item__text">
                            <a href="${contextPath }/admin/knowledgeDetail?boardId=${knowledgeDTO.boardId}"><strong>${knowledgeDTO.subject }</strong></a>
                            <div class="rating">
                                <p><i class="fa fa-heart-o"></i>&nbsp; ${knowledgeDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${knowledgeDTO.enrollDt }" pattern="yyyy-MM-dd"/> / ${knowledgeDTO.memberId }</p>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div> 
           </div>
           <div align="center">
                	<select id="searchKeyword">
                          <option value="total">전체검색</option>
                          <option value="subject">제목</option>
                          <option value="memberId">아이디</option>
                          <option value="content">내용</option>
                      </select>
                      <input type="text" id="searchWord" name="searchWord" value="${searchWord }">
                      <input type="button" value="검색" onclick="getBoardList()">
                </div>
                 <div style="display: table; margin-left: auto; margin-right: auto">
                 <p></p>
                        	<ul>
                        		<c:if test="${startPage > 10 }">
                        			
                        				<a href="${contextPath }/admin/knowledgeList?currentPageNumber=${startPage - 10}&onePageViewCnt=${onePageViewCnt }&searchKeyword=${searchKeyword }&searchWord=${searchWord }">이전 &nbsp;</a>
                        			
                        		</c:if>
                        		<c:forEach var="i" begin="${startPage }" end="${endPage }">
                        			
                        				<a href="${contextPath }/admin/knowledgeList?currentPageNumber=${i }&onePageViewCnt=${onePageViewCnt  }&searchKeyword=${searchKeyword }&searchWord=${searchWord}">${i }&nbsp;</a>
                        			
                        		</c:forEach>
                        		<c:if test="${endPage != allPageCnt && endPage >=10}">
                        			
                        				<a href="${contextPath }/admin/knowledgeList?currentPageNumber=${startPage + 10}&onePageViewCnt=${onePageViewCnt  }&searchKeyword=${searchKeyword }&searchWord=${searchWord}">다음</a>
                        			
                        			
                        		</c:if>
                        	</ul>
                      </div>
         
    </section>
    <!-- Shop Section End -->

</body>
</html>
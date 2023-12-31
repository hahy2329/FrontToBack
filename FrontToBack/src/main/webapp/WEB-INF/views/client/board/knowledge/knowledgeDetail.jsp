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
	<!-- Breadcrumb Begin -->
	<div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="${contextPath }/"><i class="fa fa-home"></i> Home</a>
                        <a>커뮤니티</a>
                        <a>지식</a>
                        <span>세부정보</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
   	<!-- Breadcrumb End -->
   	
	<!-- Blog Details Section Begin -->
    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-8">
                    <div class="blog__details__content">
                        <div class="blog__details__item">
                            <img src="img/blog/details/blog-details.jpg" alt="">
                            <div class="blog__details__item__title">
                                <span class="tip">지식</span>
                                <h4>${knowledgeDTO.subject }</h4>
                                <ul>
                                    <li>by <span>${knowledgeDTO.memberId }</span></li>
                                    <li><fmt:formatDate value="${knowledgeDTO.enrollDt }" pattern="yyyy-MM-dd"/></li>
                                    <li>조회수: ${knowledgeDTO.readCnt }</li>
                                    <li>${knowledgeDTO.sort }</li>
                                </ul>
                            </div>
                        </div>
                        <div class="blog__details__desc">
                           ${knowledgeDTO.content }
                        </div>
                        <div class="blog__details__tags">
                        <c:choose>
                        	<c:when test="${sessionScope.memberId eq knowledgeDTO.memberId }">
                        		<a href="${contextPath}/boardAdvance/knowledgeUpdateBoard?boardId=${knowledgeDTO.boardId }">수정</a>
                            	<a href="${contextPath}/boardAdvance/knowledgeRemoveBoard?boardId=${knowledgeDTO.boardId }">삭제</a>
                        	</c:when>
                        	<c:otherwise>
                        		<a href="${contextPath}/boardAdvance/knowledgeList">목록보기</a>
                        	</c:otherwise>
                        </c:choose>
                        </div>
                        <div class="blog__details__btns">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="blog__details__btn__item">
                                        <h5><h6 style="font-weight: bold">댓글 리스트<h7 style="color: red;">&nbsp;(${allReplyCnt }개)</h7></h6></h5>
                                    </div>
                                </div>
                               <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="blog__details__btn__item blog__details__btn__item--next">
                                     <c:if test="${sessionScope.memberId ne null }">
                                        <h6><a href="${contextPath}/boardAdvance/KnowledgeAddReply?boardId=${knowledgeDTO.boardId }">댓글 작성 <i class="fa fa-angle-right"></i></a></h6>
                                    </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="blog__details__comment">
                            <c:forEach var="replyDTO" items="${knowledgeReplyDTO }">
	                            <div class="blog__comment__item">
	                                <div class="blog__comment__item__text">
	                                    <h6>${replyDTO.memberId }</h6>
	                                    <p>${replyDTO.content }</p>
	                                    <ul>
	                                        <li><i class="fa fa-clock-o"></i><fmt:formatDate value="${replyDTO.enrollDt }"/></li>
	                                        <c:if test="${sessionScope.memberId eq replyDTO.memberId}">
	                                        	<li><i class="fa fa-heart-o"></i><a href="${contextPath}/boardAdvance/knowledgeUpdateReply?replyId=${replyDTO.replyId }">수정</a></li>
	                                        	<li><i class="fa fa-share"></i><a href="${contextPath}/boardAdvance/knowledgeRemoveReply?replyId=${replyDTO.replyId }">삭제</a></li>
											</c:if>                                    
	                                    </ul>
	                                </div>
	                            </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Details Section End -->
</body>
</html>
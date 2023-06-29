<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="${contextPath }/"><i class="fa fa-home"></i> Home</a>
                        <span>MY활동내역</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	
	
	<section class="trend spad">
    <div class="container">
        <div class="row">
        	<div class="col-lg-4 col-md-4 col-sm-6">
                <div class="trend__content">
                    <div class="section-title">
                        <h4>지식</h4>
                    </div>
                    <c:forEach var="knowledgeDTO" items="${knowledgeList }">
                    <div class="trend__item">
                        <div class="trend__item__text">
                            <a href="${contextPath }/boardAdvance/knowledgeDetail?boardId=${knowledgeDTO.boardId}"><strong>${knowledgeDTO.subject }</strong></a>
                            <div class="rating">
                                <p><i class="fa fa-heart-o"></i>&nbsp; ${knowledgeDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${knowledgeDTO.enrollDt }" pattern="yyyy-MM-dd"/> / ${knowledgeDTO.memberId }</p>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <div class="trend__content">
                    <div class="section-title">
                        <h4>Q&A</h4>
                    </div>
                    <c:forEach var="qnaDTO" items="${qnaList }">
                    <div class="trend__item">
                        <div class="trend__item__text">
                            <a href="${contextPath }/boardAdvance/qnaDetail?boardId=${qnaDTO.boardId}"><strong>${qnaDTO.subject }</strong></a>
                            <div class="rating">
                                <p><i class="fa fa-heart-o"></i>&nbsp; ${qnaDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${qnaDTO.enrollDt }" pattern="yyyy-MM-dd"/> / ${qnaDTO.memberId }</p>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-6">
                <div class="trend__content">
                    <div class="section-title">
                        <h4>스터디 그룹</h4>
                    </div>
                    <c:forEach var="studyDTO" items="${studyList }">
                    <div class="trend__item">
                        <div class="trend__item__text">
                            <a href="${contextPath }/boardAdvance/studyDetail?boardId=${studyDTO.boardId}"><strong>${studyDTO.subject }</strong></a>
                            <div class="rating">
                                <p><i class="fa fa-heart-o"></i>&nbsp; ${studyDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${studyDTO.enrollDt }" pattern="yyyy-MM-dd"/> / ${studyDTO.memberId }</p>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
            
            <div class="col-lg-4 col-md-4 col-sm-6">
                <div class="trend__content">
                    <div class="section-title">
                        <h4>도서 추천</h4>
                    </div>
                    <c:forEach var="bookDTO" items="${bookList }">
                    <div class="trend__item">
                        <div class="trend__item__text">
                            <a href="${contextPath }/boardAdvance/bookDetail?boardId=${bookDTO.boardId}"><strong>${bookDTO.subject }</strong></a>
                            <div class="rating">
                                <p><i class="fa fa-heart-o"></i>&nbsp; ${bookDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${bookDTO.enrollDt }" pattern="yyyy-MM-dd"/> / ${bookDTO.memberId }</p>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
            
        </div>
    </div>
</section>
	
	
	
</body>
</html>
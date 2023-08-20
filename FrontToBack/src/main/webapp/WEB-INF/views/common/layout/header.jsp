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

	 <!-- Header Section Begin -->
    <header class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-lg-2">
                    <div class="header__logo">
                        <a href="${contextPath }/"><img src="${contextPath }/resources/ashion-master/img/FrontToBack.png" alt=""></a>
                    </div>
                </div>
                <!-- admin카테고리 기능 및 로그인 페이지에 admin표기 즉 2가지 도 추가해야함 -->
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="${contextPath }/">Home</a></li>
                          <c:if test="${sessionScope.adminId eq null }">
                            <li><a href="${contextPath }/boardAdvance/noticeList">공지사항</a></li>
                          </c:if>
                           <li><a href="${contextPath }/bookSearch">도서검색</a></li>
                           <li><a href="${contextPath }/video">영상검색</a></li>
                          <c:if test="${sessionScope.memberId ne null }">
                          	 <li><a href="#">커뮤니티</a>
                                <ul class="dropdown">
                                    <li><a href="${contextPath }/boardAdvance/knowledgeList">지식</a></li>
                                    <li><a href="${contextPath }/boardAdvance/qnaList">Q&A</a></li>
                                    <li><a href="${contextPath }/boardAdvance/studyList">스터디 그룹</a></li>
                                    <li><a href="${contextPath }/boardAdvance/bookList">도서 추천</a></li>
                                </ul>
                            </li>
                            <li><a href="${contextPath }/member/myActivity?memberId=${sessionScope.memberId}">my활동내역</a></li>
                            <li><a href="#">개인정보</a>
                            	<ul class="dropdown">
                            		<li><a href="${contextPath }/member/updateMember?memberId=${sessionScope.memberId}">개인정보 변경</a></li>
                            		<li><a href="${contextPath }/member/removeMember?memberId=${sessionScope.memberId}">회원탈퇴</a></li>
                            	</ul>
                            
                            </li>
                          </c:if>
                         <c:if test="${sessionScope.adminId ne null }">
                         	<li><a href="${contextPath }/admin/adminPage">홈페이지 관리</a></li>
                         	<li><a href="https://dashboard.tawk.to/?lang=ko#/chat">채팅상담 관리</a></li>
                         	<li><a href="${contextPath }/email/write">메일 발송</a></li>
                         </c:if>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                    <c:choose>
                    	<c:when test="${sessionScope.memberId eq null and sessionScope.adminId eq null }">
	                        <div class="header__right__auth">
	                            <strong><a href="${contextPath }/member/loginMember">Login</a></strong>
	                            <a href="${contextPath }/admin/loginAdmin"></a>
	                            <strong><a href="${contextPath }/member/registerMember">Register</a></strong>
	                        </div>
                        </c:when>
                        <c:otherwise>
	                        <c:if test="${sessionScope.memberId ne null }">
	                        	 <div class="header__right__auth">
		                            <a>${sessionScope.memberId }님 로그인 </a>
		                            <a href="${contextPath }/member/logoutMember"> logout</a>
		                        </div>
		                     </c:if>
		                     
		                     <c:if test="${sessionScope.adminId ne null }">
		                     	<div class="header__right__auth">
		                            <a>${sessionScope.adminId }님 로그인 </a>
		                            <a href="${contextPath }/admin/logoutAdmin"> logout</a>
		                        </div>
		                     </c:if>   
                        </c:otherwise>
                        
                    </c:choose>
                        
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->
	

</body>
</html>
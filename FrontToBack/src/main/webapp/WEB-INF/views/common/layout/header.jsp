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
                        <a href="${contextPath }/"><img src="${contextPath }/resources/ashion-master/img/logo.png" alt=""></a>
                    </div>
                </div>
                <!-- admin카테고리 기능 및 로그인 페이지에 admin표기 즉 2가지 도 추가해야함 -->
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="${contextPath }/">Home</a></li>
                            <li><a href="">공지사항</a></li>
                            <li><a href="${contextPath }/api">IT뉴스</a></li>
                            <li><a href="./shop.html">커뮤니티</a></li>
                          <c:if test="${sessionScope.memberId ne null}">  
                            <li><a href="./blog.html">my활동내역</a></li>
                            <li><a href="./contact.html">개인정보변경</a></li>
                          </c:if>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                    <c:choose>
                    	<c:when test="${sessionScope.memberId eq null }">
	                        <div class="header__right__auth">
	                            <a href="${contextPath }/member/login">Login</a>
	                            <a href="${contextPath }/member/register">Register</a>
	                        </div>
                        </c:when>
                        <c:otherwise>
                        	 <div class="header__right__auth">
	                            <a>${sessionScope.memberId }님 로그인 </a>
	                            <a href="${contextPath }/member/logout"> logout</a>
	                        </div>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

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
                         <span>관리자 로그인</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

 <!-- Contact Section Begin -->
    <section class="contact spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="contact__content">
                        
                        <div class="contact__form" align="center">
                            <h5>관리자 로그인</h5>
                            <form action="${contextPath }/admin/loginAdmin" method="post">
                            
                            	<p>아이디<span>*</span></p>
                                <input type="text" name="adminId" placeholder="아이디를 입력해주세요." required="required">
                                
                                <p>비밀번호</p>
                                <input type="password" name="passwd" placeholder="비밀번호를 입력해주세요." required="required">
                                
                                <button type="submit" class="site-btn">로그인</button>
                               
                            </form>
                        </div>
                        <div><br><br><hr></div>
                        <div class="contact__address" align="left">
                            <h5>FRONTTOBACK INFO</h5>
                            <ul>
                                <li>
                                    <h6><i class="fa fa-map-marker"></i> 개발자 : 한주석</h6>
                                </li>
                                <li>
                                    <h6><i class="fa fa-phone"></i> 포지션/전화번호 : 백엔드/123-4567-8922</h6>
                                </li>
                                <li>
                                    <h6><i class="fa fa-headphones"></i> 이메일 : abcdef@abcdef.com</h6>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</section>
<!-- Contact Section End -->
	

</body>
</html>
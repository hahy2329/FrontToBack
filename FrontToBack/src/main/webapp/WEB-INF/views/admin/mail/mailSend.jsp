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
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="${contextPath }/"><i class="fa fa-home"></i> Home</a>
                        <span>메일 발송</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
    	<br><br>
    </div>
    <section class="contact spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="contact__content">
                        <div class="contact__form">
                            <h5>메일 발송</h5>
                            <form action="${contextPath }/email/send" method="post">
                                <input type="text" name="senderName" placeholder="발신자 이름">
                                <input type="email" name="senderMail" placeholder="발신자 이메일">
                                <input type="email" name="receiveMail" placeholder="수신자 이메일">
                                <input type="text" name="subject" placeholder="제목">
                                <textarea name="message" placeholder="내용을 입력해주세요."></textarea>
                                <button type="submit" class="site-btn">전송</button>
                            </form>
                            <span style="color:red;">${message}</span>
                        </div>
                        <div>
                        	<br><br>
                        </div>
                        <div class="contact__address">
                     		 <h5>FRONTTOBACK INFO</h5>
                           	 <ul>
                                <li>
                                    <h6><i class="fa fa-map-marker"></i> 개발자 : 한주석</h6>
                                </li>
                                <li>
                                    <h6><i class="fa fa-phone"></i> 포지션/전화번호 : 백엔드/123-4567-8922</h6>
                                </li>
                                <li>
                                    <h6><i class="fa fa-headphones"></i> 이메일 : gkswntjr2329@gmail.com</h6>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="contact__map">
                        <iframe
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d48158.305462977965!2d-74.13283844036356!3d41.02757295168286!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c2e440473470d7%3A0xcaf503ca2ee57958!2sSaddle%20River%2C%20NJ%2007458%2C%20USA!5e0!3m2!1sen!2sbd!4v1575917275626!5m2!1sen!2sbd"
                        height="780" style="border:0" allowfullscreen="">
                    </iframe>
                	</div>
           	 	</div>
        	</div>
    	</div>
	</section>
</body>
</html>
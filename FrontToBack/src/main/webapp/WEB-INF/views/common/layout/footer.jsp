<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	$().ready(function(){
		
		$("#clicks").click(function(){
			
			var data = $("#keywords").val();
			
			$.ajax({
				
				method : "GET",
				url : "https://dapi.kakao.com/v2/search/web",
				data : {"query" : data},
				headers : {"Authorization" : "KakaoAK 9291681c4b5f1c251ec456e619d7f1ed"},
				success : function(data){
					
					var url = data.documents[0].url
					location.href = url;
					
				}
				
				
				
				
			});
			
			
		});
		
		
		
	});


</script>
</head>
<body>

	<!-- Footer Section Begin -->
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-7">
                <div class="footer__about">
                    <div class="footer__logo">
                        <a href="${contextPath }/"><img src="${contextPath }/resources/ashion-master/img/FrontToBack.png" alt=""></a>
                    </div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                    cilisis.</p>
                    <div class="footer__payment">
                        <a href="#"><img src="${contextPath }/resources/ashion-master/img/payment/payment-1.png" alt=""></a>
                        <a href="#"><img src="${contextPath }/resources/ashion-master/img/payment/payment-2.png" alt=""></a>
                        <a href="#"><img src="${contextPath }/resources/ashion-master/img/payment/payment-3.png" alt=""></a>
                        <a href="#"><img src="${contextPath }/resources/ashion-master/img/payment/payment-4.png" alt=""></a>
                        <a href="#"><img src="${contextPath }/resources/ashion-master/img/payment/payment-5.png" alt=""></a>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-3 col-sm-5">
                <div class="footer__widget">
                    <h6>Administrator</h6>
                    <ul>
                        <li><a href="${contextPath }/administrator/introduce">관리자 소개</a></li>
                        <li><a href="${contextPath }/boardAdvance/noticeList">공지사항</a></li>
                        <li><a href="${contextPath }/administrator/inquiry">문의사항</a></li>
                        <li><a href="${contextPath }/administrator/bug">버그제보</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-2 col-md-3 col-sm-4">
                <div class="footer__widget">
                    <h6>채용안내</h6>
                    <ul>
                        <li><a href="https://www.jobkorea.co.kr/">잡코리아</a></li>
                        <li><a href="https://www.saramin.co.kr/zf_user/">사람인</a></li>
                        <li><a href="#">미지정</a></li>
                        <li><a href="#">미지정</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-4 col-md-8 col-sm-8">
                <div class="footer__newslatter">
                    <h6>나무위키 검색</h6>
                    <form action="#">
                        <input type="text" id="keywords" placeholder="키워드를 입력해주세요.">
                        <button type="submit" id="clicks" class="site-btn">검색</button>
                    </form>
                    <div class="footer__social">
                        <a href="https://ko-kr.facebook.com/"><i class="fa fa-facebook"></i></a>
                        <a href="https://twitter.com/i/flow/login"><i class="fa fa-twitter"></i></a>
                        <a href="https://www.youtube.com/"><i class="fa fa-youtube-play"></i></a>
                        <a href="https://www.instagram.com/"><i class="fa fa-instagram"></i></a>
                        <a href="https://www.pinterest.co.kr/"><i class="fa fa-pinterest"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                <div class="footer__copyright__text">
                    <p>Copyright &copy; <script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a></p>
                </div>
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->
	

</body>
</html>
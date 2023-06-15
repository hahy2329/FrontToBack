<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><tiles:insertAttribute name="title" /></title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="${contextPath }/resources/ashion-master/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/ashion-master/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/ashion-master/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/ashion-master/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/ashion-master/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/ashion-master/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/ashion-master/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${contextPath }/resources/ashion-master/css/style.css" type="text/css">
    <script src="${contextPath }/resources/ashion-master/js/jquery-3.3.1.min.js"></script>
    <script src="${contextPath }/resources/ckeditor/ckeditor.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function execDaumPostcode() {
		    new daum.Postcode({
		        oncomplete: function(data) {
		
		            var fullRoadAddr = data.roadAddress; 
		            var extraRoadAddr = ''; 
		
		            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                extraRoadAddr += data.bname;
		            }
		            if (data.buildingName !== '' && data.apartment === 'Y'){
		               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		            }
		            if (extraRoadAddr !== ''){
		                extraRoadAddr = ' (' + extraRoadAddr + ')';
		            }
		            if (fullRoadAddr !== ''){
		                fullRoadAddr += extraRoadAddr;
		            }
		
		            document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
		            document.getElementById('roadAddress').value = fullRoadAddr;
		            document.getElementById('jibunAddress').value = data.jibunAddress;
		          
		        }
		    }).open();
		}
	</script>
</head>
<body>

	 <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
    
    <!-- Header Section Begin -->
    	<tiles:insertAttribute name="header" />
     <!-- Header Section End -->
     
     <!-- Body Section Begin -->	
     	<tiles:insertAttribute name="body" />
     <!-- Body Section End -->	
     
     <!-- Footer Section Begin -->
     	<tiles:insertAttribute name="footer" />
     <!-- Footer Section End -->
     
     
     <!-- Search Begin -->
<div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
            <input type="text" id="search-input" placeholder="Search here.....">
        </form>
    </div>
</div>
<!-- Search End -->

<!-- Js Plugins -->
<script src="${contextPath }/resources/ashion-master/js/bootstrap.min.js"></script>
<script src="${contextPath }/resources/ashion-master/js/jquery.magnific-popup.min.js"></script>
<script src="${contextPath }/resources/ashion-master/js/jquery-ui.min.js"></script>
<script src="${contextPath }/resources/ashion-master/js/mixitup.min.js"></script>
<script src="${contextPath }/resources/ashion-master/js/jquery.countdown.min.js"></script>
<script src="${contextPath }/resources/ashion-master/js/jquery.slicknav.js"></script>
<script src="${contextPath }/resources/ashion-master/js/owl.carousel.min.js"></script>
<script src="${contextPath }/resources/ashion-master/js/jquery.nicescroll.min.js"></script>
<script src="${contextPath }/resources/ashion-master/js/main.js"></script>
     

</body>
</html>
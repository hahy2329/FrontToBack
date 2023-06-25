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

	 <!-- Categories Section Begin -->
    <section class="categories">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6 p-0">
                    <div class="categories__item categories__large__item set-bg"
                    data-setbg="img/categories/category-1.jpg">
                    <div class="categories__text">
                        <h1>FRONTTOBack</h1>
                        <p>안녕하세요. 개발자 한주석입니다. 여러분들과 함께 IT지식을 공유하고, 개발에 한 걸음 더 흥미를 느낄수 있도록 나아가고 싶습니다. 감사합니다.</p>
                        <a href="#">지금 시작하기</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="${contextPath }/resources/ashion-master/img/categories/bestseller.jpg">
                            <div class="categories__text">
                                <h4>지식</h4>
                                <a href="#" style="color: blue;">이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="${contextPath }/resources/ashion-master/img/categories/generalbook.jpg">
                            <div class="categories__text">
                                <h4>Q&A</h4>
                                <a href="#" style="color: blue;">이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="${contextPath }/resources/ashion-master/img/categories/newbook.jpg">
                            <div class="categories__text">
                                <h4>스터디 그룹</h4>
                                <a href="#" style="color: blue;">이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="${contextPath }/resources/ashion-master/img/categories/steadyseller.jpg">
                            <div class="categories__text">
                                <h4>도서 추천!</h4>
                                <a href="#" style="color: blue;">이동하기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Categories Section End -->

<!-- Banner Section Begin -->
<section class="banner set-bg" data-setbg="${contextPath }/resources/ashion-master/img/banner/banner-1.jpg">
    <div class="container">
        <div class="row">
            <div class="col-xl-7 col-lg-8 m-auto">
                <div class="banner__slider owl-carousel">
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>FRONTTOBACK</span>
                            <h1>Knowledge</h1>
                            <a href="#">이동하기</a>
                        </div>
                    </div>
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>FRONTTOBACK</span>
                            <h1>Q&A</h1>
                            <a href="#">이동하기</a>
                        </div>
                    </div>
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>FRONTTOBACK</span>
                            <h1>Study Group</h1>
                            <a href="#">이동하기</a>
                        </div>
                    </div>
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>FRONTTOBACK</span>
                            <h1>Book Recommend!</h1>
                            <a href="#">이동하기</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Banner Section End -->
	

</body>
</html>
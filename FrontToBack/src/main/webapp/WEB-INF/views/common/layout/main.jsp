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
    <section class="categories">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6 p-0">
                    <div class="categories__item categories__large__item set-bg"
                    data-setbg="${contextPath }/resources/ashion-master/img/categories/developer.jpg">
                     <div class="categories__text">
                        <h1>FRONTTOBACK</h1>
                        <a href="${contextPath }/member/registerMember">지금 시작하기</a>
                     </div>
                	</div>
            	</div>
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="${contextPath }/resources/ashion-master/img/categories/bestseller.jpg">
                            <div class="categories__text">
                                <h4>지식</h4>
                                <a href="${contextPath }/boardAdvance/knowledgeList" style="color: blue;">이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="${contextPath }/resources/ashion-master/img/categories/generalbook.jpg">
                            <div class="categories__text">
                                <h4>Q&A</h4>
                                <a href="${contextPath }/boardAdvance/qnaList" style="color: blue;">이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="${contextPath }/resources/ashion-master/img/categories/newbook.jpg">
                            <div class="categories__text">
                                <h4>스터디 그룹</h4>
                                <a href="${contextPath }/boardAdvance/studyList" style="color: blue;">이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="${contextPath }/resources/ashion-master/img/categories/steadyseller.jpg">
                            <div class="categories__text">
                                <h4>도서 추천</h4>
                                <a href="${contextPath }/boardAdvance/bookList" style="color: blue;">이동하기</a>
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
<section class="banner set-bg" data-setbg="${contextPath }/resources/ashion-master/img/banner/api.jpg">
    <div class="container">
        <div class="row">
            <div class="col-xl-7 col-lg-8 m-auto">
                <div class="banner__slider owl-carousel">
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>FRONTTOBACK</span>
                            <h1>Book Search</h1>
                            <a href="${contextPath }/bookSearch">이동하기</a>
                        </div>
                    </div>
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>FRONTTOBACK</span>
                            <h1>Video Search</h1>
                            <a href="${contextPath }/video">이동하기</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!--Start of Tawk.to Script-->
	<c:if test="${sessionScope.memberId ne null }">
		<script type="text/javascript">
			var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
			(function(){
			var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
			s1.async=true;
			s1.src='https://embed.tawk.to/64a790fecc26a871b026dde3/1h4n8sphh';
			s1.charset='UTF-8';
			s1.setAttribute('crossorigin','*');
			s0.parentNode.insertBefore(s1,s0);
			})();
		</script>
	</c:if>
<!--End of Tawk.to Script-->

<!-- Trend Section Begin -->
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
            <div class="col-lg-4 col-md-4 col-sm-6">
                <div class="trend__content">
                    <div class="section-title">
                        <h4>공지사항</h4>
                    </div>
                    <c:forEach var="noticeDTO" items="${noticeList }">
	                    <div class="trend__item">
	                        <div class="trend__item__text">
	                            	<a href="${contextPath }/boardAdvance/noticeDetail?boardId=${noticeDTO.boardId}"><strong>${noticeDTO.subject }</strong></a>
	                            <div class="rating">
	                                <p><i class="fa fa-heart-o"></i>&nbsp; ${noticeDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${noticeDTO.enrollDt }" pattern="yyyy-MM-dd"/> /${noticeDTO.adminId }</p>
	                            </div>
	                        </div>
	                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Trend Section End -->



	

</body>
</html>
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

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="${contextPath }/"><i class="fa fa-home"></i> Home</a>
                        <span>관리</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Shop Section Begin -->
    <section class="shop spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="shop__sidebar">
                        <div class="sidebar__categories">
                            <div class="section-title">
                                <h4>관리</h4>
                            </div>
                            <div class="categories__accordion">
                                <div class="accordion" id="accordionExample">
                                    <div class="card">
                                        <div class="card-heading active">
                                            <a data-toggle="collapse" data-target="#collapseOne">커뮤니티</a>
                                        </div>
                                        <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <ul>
                                                    <li><a href="${contextPath }/admin/noticeList">공지사항</a></li>
                                                    <li><a href="${contextPath }/admin/knowledgeList">지식</a></li>
                                                    <li><a href="${contextPath }/admin/qnaList">Q&A</a></li>
                                                    <li><a href="${contextPath }/admin/studyList">스터디그룹</a></li>
                                                    <li><a href="${contextPath }/admin/bookList">도서추천</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-heading">
                                            <a data-toggle="collapse" data-target="#collapseTwo">유저관리</a>
                                        </div>
                                        <div id="collapseTwo" class="collapse" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <ul>
                                                    <li><a href="${contextPath }/admin/memberDelete">회원삭제</a></li>
                                                    <li><a href="${contextPath }/admin/memberList">회원리스트 조회</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                <div class="trend__content">
                    <div class="section-title">
                        <h4>종류 별 인기글</h4>
                    </div>
                    <div class="trend__item">
                        <div class="trend__item__text">
                            <a href="${contextPath }/admin/knowledgeDetail?boardId=${knowledgeDTO.boardId}"><strong>${knowledgeDTO.subject }</strong></a>
                            <div class="rating">
                                <p><strong>지식</strong> /&nbsp;<i class="fa fa-heart-o"></i>&nbsp; ${knowledgeDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${knowledgeDTO.enrollDt }" pattern="yyyy-MM-dd"/> /${knowledgeDTO.memberId }</p>
                            </div>
                        </div>
                    </div>
                     <div class="trend__item">
                        <div class="trend__item__text">
                            <a href="${contextPath }/admin/qnaDetail?boardId=${qnaDTO.boardId}"><strong>${qnaDTO.subject }</strong></a>
                            <div class="rating">
                                <p><strong>Q&A</strong> /&nbsp;<i class="fa fa-heart-o"></i>&nbsp; ${qnaDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${qnaDTO.enrollDt }" pattern="yyyy-MM-dd"/> /${qnaDTO.memberId }</p>
                            </div>
                        </div>
                    </div>
                     <div class="trend__item">
                        <div class="trend__item__text">
                            <a href="${contextPath }/admin/studyDetail?boardId=${studyDTO.boardId}"><strong>${studyDTO.subject }</strong></a>
                            <div class="rating">
                                <p><strong>스터디 그룹</strong> /&nbsp;<i class="fa fa-heart-o"></i>&nbsp; ${studyDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${studyDTO.enrollDt }" pattern="yyyy-MM-dd"/> /${studyDTO.memberId }</p>
                            </div>
                        </div>
                    </div>
                     <div class="trend__item">
                        <div class="trend__item__text">
                            <a href="${contextPath }/admin/bookDetail?boardId=${bookDTO.boardId}"><strong>${bookDTO.subject }</strong></a>
                            <div class="rating">
                                <p><strong>도서추천</strong> /&nbsp;<i class="fa fa-heart-o"></i>&nbsp; ${bookDTO.readCnt } / <i class="fa fa-clock-o"></i> &nbsp;<fmt:formatDate value="${bookDTO.enrollDt }" pattern="yyyy-MM-dd"/> /${bookDTO.memberId }</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            </div>
    	</section>
    <!-- Shop Section End -->
    <section class="discount">
    <div class="container" align="center">
        <div class="row">
            <div class="col-lg-6 p-0">
                <div class="discount__pic">
                    <img src="${contextPath }/resources/ashion-master/img/human.jpg" alt="">
                </div>
            </div>
            <div class="col-lg-6 p-0">
                <div class="discount__text">
                    <div class="discount__text__title">
                        <span>Member</span>
                        <h2>Count 2023</h2>
                        <h5><span>회원 수</span>${count } </h5>
                    </div>
                    <div>
                            <h4><strong>${nowTime }</strong></h4>
                    </div>        
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
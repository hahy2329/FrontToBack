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
                        <a>홈페이지 관리</a>
                        <a>유저관리</a>
                        <span>회원리스트 조회</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
    	<br><br>
    </div>
    	<div align="center">
           <h4>회원리스트 조회</h4>
         </div>
         <div>
         	<br><br><br>
         </div>
            <div class="container">
            <div class="row">
                <div class="col-lg-11">
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th style="color: red;">회원ID</th>
                                    <th>회원 이름</th>
                                    <th style="color: brown;">이메일</th>
                                    <th style="color: green;">이메일 수신</th>
                                    <th style="color: blue;">포지션</th>
                                    <th align="center">주소</th>
                                </tr>
                            </thead>
                            <c:if test="${memberList ne null }">
                           	 	<c:forEach var="memberList" items="${memberList }">
                            		<tbody>
                                		<tr>
                                  		  <td class="cart__product__item1">${memberList.memberId }</td>
                                   		  <td class="cart_title1">${memberList.memberNm }</td>
                                    	  <td class="cart_publisher1">${memberList.email }</td>
                                    	  <td class="cart__price1">${memberList.emailstsYn }</td>
                                    	  <td class="cart_status1">${memberList.sort }</td>
                                    	  <td class="cart_status1">
                                    	  			${memberList.roadAddress}<br> 
													${memberList.jibunAddress}<br>
													${memberList.namujiAddress}<br>
                                   		  </td>
                                		</tr>
                            		</tbody>
                            
                            	</c:forEach>
                            </c:if>
                            <c:if test="${memberList eq null }">
                            	<tr align="center">
                            		<td colspan="6"><strong>조회된 내용이 없습니다.</strong></td>
                            	</tr>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href="${contextPath }/admin/memberExcelExport">Excel Export</a>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
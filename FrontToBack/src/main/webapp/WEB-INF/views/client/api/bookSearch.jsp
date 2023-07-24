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
	
	
	
	$("#click").click(function(){	
		
		for(var i=1; i<31; i++){	
			$(".cart__product__item"+i).empty(); //empty는 요소 안의 내용을 지워준다. 반대로 remove는 요소 자체를 지운다.
			$(".cart_title" +i).empty();
			$(".cart_status" +i).empty();
			$(".cart__price" +i).empty();
			$(".cart_publisher"+i).empty();
			$(".cart_url"+i).empty();
		}	
		
		var data = $("#keyword").val();
		
		$.ajax({
			method : "GET",
			url : "https://dapi.kakao.com/v3/search/book?target=title",
			data : { "query" : data, "size" : 30 },
			headers : {"Authorization" : "KakaoAK 9291681c4b5f1c251ec456e619d7f1ed"},
			success : function(data){ //length : 10기준
				console.log(data);
				console.log(data.documents[0].title);
				console.log(data.documents[0].thumbnail);
				for(var i=0; i<30; i++){
					$(".cart__product__item"+(i+1)).append("<img src='"+data.documents[i].thumbnail+"'/>");
					$(".cart_title"+(i+1)).append("<strong>"+data.documents[i].title+"</strong>");
					$(".cart_status"+(i+1)).append("<h6>" + data.documents[i].status +"</h6>");			
					$(".cart__price"+(i+1)).append("<h6>" + data.documents[i].price + "원</h6>");
					$(".cart_publisher"+(i+1)).append("<strong>" + data.documents[i].publisher + "</strong>");
					$(".cart_url"+(i+1)).append("<a href='"+data.documents[i].url + "'>"+"click" +"</a>");
				}
			}	
			
			
			
		});
		
		
			
	});
		
});
</script>
</head>
<body>
	
	
	 <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="${contextPath }/"><i class="fa fa-home"></i> Home</a>
                        <span>도서검색</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
    	
    	<div class="row" align="center">
                <div class="col-lg-6">
                    <div class="discount__content">
                        <h6>도서 검색</h6>
                        <form action="#">
                            <input type="text" id="keyword" placeholder="도서를 입력해주세요.">
                            <button type="submit" id="click" class="site-btn">검색</button>
                        </form>
                    </div>
                </div>
                
            </div>
            <div>
            	<br><br><hr>
            </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-11">
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th style="color: red;">책 정보</th>
                                    <th>책 제목</th>
                                    <th style="color: brown; ">출판사</th>
                                    <th style="color: green;">가격</th>
                                    <th style="color: blue;">상태</th>
                                    <th>url</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="cnt"  begin="1" end="30">
                                <tr>
                                    <td class="cart__product__item${cnt }"></td>
                                    <td class="cart_title${cnt }"></td>
                                    <td class="cart_publisher${cnt }"></td>
                                    <td class="cart__price${cnt }"></td>
                                    <td class="cart_status${cnt }"></td>
                                    <td class="cart_url${cnt }"></td>
                                </tr>
                                </c:forEach>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href="#">검색 위치로</a>
                    </div>
                </div>
            </div>
            
        </div>
    </section>
	





</body>
</html>
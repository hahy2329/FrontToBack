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
		
		for(var i=1; i<21; i++){	
			$(".cart__product__item"+i).empty(); //empty는 요소 안의 내용을 지워준다. 반대로 remove는 요소 자체를 지운다.
			$(".cart_title" +i).empty();
			$(".cart__price" +i).empty();
			$(".cart_publisher"+i).empty();
			$(".cart_url"+i).empty();
		}	
		
		var data = $("#keyword").val();
		
		
		$.ajax({
			method : "GET",
			url : "https://dapi.kakao.com/v2/search/vclip",
			data : { "query" : data },
			headers : {"Authorization" : "KakaoAK 9291681c4b5f1c251ec456e619d7f1ed"},
			success : function(data){ //length : 10기준
				console.log(data);
				console.log(data.documents[0].title);
				console.log(data.documents[0].thumbnail);
				for(var i=0; i<20; i++){
					$(".cart__product__item"+(i+1)).append("<img src='"+data.documents[i].thumbnail+"'/>");
					$(".cart_title"+(i+1)).append("<h7>"+data.documents[i].title.substring(0,40)+"</h7>");
					$(".cart__price"+(i+1)).append("<h6>" + data.documents[i].play_time + "분</h6>");
					$(".cart_publisher"+(i+1)).append("<strong>" + data.documents[i].author + "</strong>");
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
                        <span>영상검색</span>
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
                        <h6>영상 검색</h6>
                        <form action="#">
                            <input type="text" id="keyword" placeholder="키워드를 입력해 주세요.">
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
                        	<colgroup>
                        		<col width="20%">
                        		<col width="30%">
                        		<col width="20%">
                        		<col width="10%">
                        		<col width="10%">                        	
                        	</colgroup>
                            <thead>
                                <tr>
                                    <th style="color: red;">썸네일</th>
                                    <th align="center">제목</th>
                                    <th style="color: brown; ">닉네임</th>
                                    <th style="color: green;">시간</th>
                                    <th>url</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="cart__product__item1"></td>
                                    <td class="cart_title1"></td>
                                    <td class="cart_publisher1"></td>
                                    <td class="cart__price1"></td>
                                    <td class="cart_url1"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item2"></td>
                                    <td class="cart_title2"></td>
                                    <td class="cart_publisher2"></td>
                                    <td class="cart__price2"></td>
                                    <td class="cart_url2"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item3"></td>
                                    <td class="cart_title3"></td>
                                    <td class="cart_publisher3"></td>
                                    <td class="cart__price3"></td>
                                    <td class="cart_url3"></td>
                                </tr>
                                 <tr>
                                    <td class="cart__product__item4"></td>
                                    <td class="cart_title4"></td>
                                    <td class="cart_publisher4"></td>
                                    <td class="cart__price4"></td>
                                    <td class="cart_url4"></td>
                                </tr>
                                
                                 <tr>
                                    <td class="cart__product__item5"></td>
                                    <td class="cart_title5"></td>
                                    <td class="cart_publisher5"></td>
                                    <td class="cart__price5"></td>
                                    <td class="cart_url5"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item6"></td>
                                    <td class="cart_title6"></td>
                                    <td class="cart_publisher6"></td>
                                    <td class="cart__price6"></td>
                                    <td class="cart_url6"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item7"></td>
                                    <td class="cart_title7"></td>
                                    <td class="cart_publisher7"></td>
                                    <td class="cart__price7"></td>
                                    <td class="cart_url7"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item8"></td>
                                    <td class="cart_title8"></td>
                                    <td class="cart_publisher8"></td>
                                    <td class="cart__price8"></td>
                                    <td class="cart_url8"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item9"></td>
                                    <td class="cart_title9"></td>
                                    <td class="cart_publisher9"></td>
                                    <td class="cart__price9"></td>
                                    <td class="cart_url9"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item10"></td>
                                    <td class="cart_title10"></td>
                                    <td class="cart_publisher10"></td>
                                    <td class="cart__price10"></td>
                                    <td class="cart_url10"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item11"></td>
                                    <td class="cart_title11"></td>
                                    <td class="cart_publisher11"></td>
                                    <td class="cart__price11"></td>
                                    <td class="cart_url11"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item12"></td>
                                    <td class="cart_title12"></td>
                                    <td class="cart_publisher12"></td>
                                    <td class="cart__price12"></td>
                                    <td class="cart_status12"></td>
                                    <td class="cart_url12"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item13"></td>
                                    <td class="cart_title13"></td>
                                    <td class="cart_publisher13"></td>
                                    <td class="cart__price13"></td>
                                    <td class="cart_status13"></td>
                                    <td class="cart_url13"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item14"></td>
                                    <td class="cart_title14"></td>
                                    <td class="cart_publisher14"></td>
                                    <td class="cart__price14"></td>
                                    <td class="cart_status14"></td>
                                    <td class="cart_url14"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item15"></td>
                                    <td class="cart_title15"></td>
                                    <td class="cart_publisher15"></td>
                                    <td class="cart__price15"></td>
                                    <td class="cart_status15"></td>
                                    <td class="cart_url15"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item16"></td>
                                    <td class="cart_title16"></td>
                                    <td class="cart_publisher16"></td>
                                    <td class="cart__price16"></td>
                                    <td class="cart_status16"></td>
                                    <td class="cart_url16"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item17"></td>
                                    <td class="cart_title17"></td>
                                    <td class="cart_publisher17"></td>
                                    <td class="cart__price17"></td>
                                    <td class="cart_status17"></td>
                                    <td class="cart_url17"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item18"></td>
                                    <td class="cart_title18"></td>
                                    <td class="cart_publisher18"></td>
                                    <td class="cart__price18"></td>
                                    <td class="cart_status18"></td>
                                    <td class="cart_url18"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item19"></td>
                                    <td class="cart_title19"></td>
                                    <td class="cart_publisher19"></td>
                                    <td class="cart__price19"></td>
                                    <td class="cart_status19"></td>
                                    <td class="cart_url19"></td>
                                </tr>
                                <tr>
                                    <td class="cart__product__item20"></td>
                                    <td class="cart_title20"></td>
                                    <td class="cart_publisher20"></td>
                                    <td class="cart__price20"></td>
                                    <td class="cart_status20"></td>
                                    <td class="cart_url20"></td>
                                </tr>
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
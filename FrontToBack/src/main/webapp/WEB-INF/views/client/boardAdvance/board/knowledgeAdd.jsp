<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	
	var isValid = false;
	
	$().ready(function(){
		
		$("#btnOverlapped").click(function(){
			
			$(".answer").empty();
			
			var memberId = $("#memberId").val();
			var passwd = $("#passwd").val();
			
			if(passwd == ''){
				alert("패스워드를 입력해주세요.");
				return false;
			}
			
			$.ajax({
				
				type : "get",
				url : "${contextPath}/member/checkDuplicatedPasswd?passwd="+passwd+"&memberId="+memberId,
				success : function(data){
					if(data == "duplicate"){
						
						alert("확인되었습니다.");
						isValid=true;
						$("#btnOverlapped").remove();
						$(".answer").append("<p style='color: green;'>"+"확인되었습니다." + "</p>");
						
					}
					
					else{
						alert("패스워드를 다시 확인해주세요.");
						isValid=false;
						$(".answer").append("<p style='color: red;'>"+"패스워드를 다시 확인해주세요." + "</p>");
					}
					
					
				}
				
				
			});
			
		});
		
		$("form").submit(function(){
			
			if(isValid == false){
				alert("패스워드를 확인해주세요.");
				return false;
			}
			
			if(isValid == true){
				
				return true;
			}
			
			
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
                        <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <a>커뮤니티</a>
                        <a>지식</a>
                        <span>게시글 작성</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Contact Section Begin -->
    <section class="contact spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="contact__content">
                       
                        <div class="contact__form">
                            <h5>게시글 작성</h5>
                            <form action="${contextPath }/boardAdvance/knowledgeAddBoard" method="post">
                                <input type="text" name="memberId" id="memberId" value="${sessionScope.memberId }" readonly="readonly" placeholder="아이디">
                                <input type="password" name="passwd" id="passwd" required="required" placeholder="비밀번호">
                                <input type="button"  class="site-btn" id="btnOverlapped" style="color: white;" value="인증" placeholder="비밀번호 재입력">
								<p class="answer"></p>
                                <input type="text" name="subject" required="required" placeholder="제목">
                                <textarea rows="10" cols="50" placeholder="내용" name="content" required="required"></textarea>
                                <input type="text" name="sort" value="${sessionScope.sort }" readonly="readonly">
                                <button type="submit" class="site-btn">글쓰기</button>
                                <button class="site-btn"><a href="${contextPath}/boardAdvance/knowledgeList" style="color: white;">목록보기</a></button>
                            </form>
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
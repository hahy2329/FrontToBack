<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="${contextPath }/"><i class="fa fa-home"></i> Home</a>
                        <a>개인정보</a>
                        <span>개인정보 변경</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h6 class="coupon__link"><span class="icon_tag_alt"></span> <a href="${contextPath }/">메인화면으로 돌아가기</a></h6>
                </div>
            </div>
            <form action="${contextPath }/member/updateMember" class="checkout__form" method="post">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>개인정보변경</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>아이디 <span>*</span></p>
                                    	<input type="text" id="memberId" name="memberId"  value="${memberDTO.memberId }" readonly="readonly">
                                    <p>비밀번호 <span>*</span></p>
                                    	<input type="password" name="passwd" id="passwd" placeholder="비밀번호를 입력해주세요." required="required">
                                    	<input type="button"  class="site-btn" id="btnOverlapped" style="color: white;" value="인증">
									<p class="answer"></p>
                                    <p>이름 <span>*</span></p>
                                    	<input type="text" name="memberNm" value="${memberDTO.memberNm }" placeholder="이름을 입력해주세요." required="required">
                                    <p>우편번호<span>*</span></p>
                                    	<input type="text" id="zipcode" name="zipcode" value="${memberDTO.zipcode }" style="width: 50%;" required="required">
                                    	<input type="button" value="검색" onclick="execDaumPostcode();" style="width: 20%; padding-left: 0">
                                    <p>도로명 주소 <span>*</span></p>
                                    	<input type="text" id="roadAddress" name="roadAddress" value="${memberDTO.roadAddress }" placeholder="도로명주소를 입력하세요." required="required">
                                 	<p>지번 주소 <span>*</span></p>
                                    	<input type="text" id="jibunAddress" name="jibunAddress" value="${memberDTO.jibunAddress }" placeholder="지번주소를 입력하세요." required="required">
                                    <p>나머지 주소 <span>*</span></p>
                                    	<input type="text" id="namujiAddress" name="namujiAddress" value="${memberDTO.namujiAddress }" placeholder="나머지주소를 입력하세요.">
                                    <p>email <span>*</span></p>
                                    	<input type="email" name="email" placeholder="이메일을 입력해주세요." value="${memberDTO.email }" required="required">
                                </div>
                              </div>
                            	<div class="col-lg-12">
                                    <label for="acc">
                                        	이메일 수신 동의하시겠습니까?<input type="checkbox" id="emailstsYn" name="emailstsYn" value="Y"<c:if test="${memberDTO.emailstsYn eq 'Y' }">checked</c:if>>
                                        <span class="checkmark"></span>
                                    </label>
                                    <p>이메일 수신을 동의하실 경우, 사이트 내 새로운 정보가 업데이트 시 정기적으로 알림이 갈 예정입니다.</p>
                                </div>
                                <div>
                                 <strong>포지션<span>*</span></strong><p></p>
                                    	<input type="radio" name="sort" value="프론트엔드" required="required" <c:if test="${memberDTO.sort eq '프론트엔드' }">checked</c:if>> 프론트엔드&nbsp;
                                    	<input type="radio" name="sort" value="백엔드" required="required" <c:if test="${memberDTO.sort eq '백엔드' }">checked</c:if>> 백엔드&nbsp;
                                    	<input type="radio" name="sort" value="AI" required="required" <c:if test="${memberDTO.sort eq 'AI' }">checked</c:if>> AI&nbsp;
                                    	<input type="radio" name="sort" value="데이터분석" required="required" <c:if test="${memberDTO.sort eq '데이터분석' }">checked</c:if>> 데이터 분석&nbsp;
                                    	<input type="radio" name="sort" value="기타" required="required" <c:if test="${memberDTO.sort eq '기타' }">checked</c:if>> 기타 &nbsp;
                                 </div>
                                <div class="checkout__order">
	                                <h5>알림</h5>
	                                <div class="checkout__order__widget">
	                                       	정말로 변경하시겠습니까?<p>변경을 원치 않으실 경우 뒤로가기 버튼을 눌러주시고, 변경을 계속 진행하실 경우 아래의 전송버튼을 눌러주세요.</p>
	                                </div>
	                                <button type="submit" class="site-btn">전송</button>
                            	</div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
</body>
</html>
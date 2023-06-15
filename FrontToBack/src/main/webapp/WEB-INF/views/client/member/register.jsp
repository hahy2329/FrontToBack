<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	var isValidId = false;
	var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	
	
	$().ready(function(){
		
		$("#btnOverlapped").click(function(){
			
			var memberId = $("#memberId").val();
			
			if(memberId == ''){
				alert("ID를 입력하세요.");
				return;
			}
			
			
			if(memberId.search(/\s/) != -1){
				
				alert("공백은 허용할 수 없습니다.");
				return false;
			}//공백 체크
			if(special_pattern.test(memberId) == true) {
				alert("특수문자는 허용할 수 없습니다.");
				return false;
			}//특수문자 체크
			
			
			
			
			
			
			$.ajax({
				
				type :"get",
				url : "${contextPath}/member/checkDuplicatedId?memberId=" +memberId,
				success : function(data){
					if(data == "duplicate"){
						alert("사용할 수 있는 ID입니다.");
						isValidId = true;
					}
					else{
						alert("사용할 수 없는 ID입니다.");
						isValidId= false;
					}
				}		
				
			});
			
		});
		
		$("form").submit(function(){
			
			
			if($("#smsstsYn").val() != "Y"){
				$(this).val("N");
			}
			if(isValidId==false){
				alert("아이디를 확인해주세요.");
				return false;
			}
			if(isValidId==true){
				if($("#passwd").val() == $("#confirmPasswd").val()){
					alert("가입을 축하드립니다.");
					return true;
					
				}
			
				else{
					alert("패스워드를 다시 확인해주세요.");
					return false;
				}
				
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
                        <span>Register</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h6 class="coupon__link"><span class="icon_tag_alt"></span> <a href="${contextPath }/">메인화면으로 돌아가기</a></h6>
                </div>
            </div>
            <form action="${contextPath }/member/register" class="checkout__form" method="post">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>회원가입</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>아이디 <span>*</span></p>
                                    <input type="text" id="memberId" name="memberId" required="required" placeholder="아이디를 입력해주세요.">
                                    <input type="button" id="btnOverlapped" value="중복확인">
                                    
                                    <p>이름 <span>*</span></p>
                                    <input type="text" name="memberNm" placeholder="이름을 입력해주세요.">
                                    
                                     <p>비밀번호 <span>*</span></p>
                                    <input type="password" name="passwd" id="passwd" placeholder="비밀번호를 입력해주세요." required="required">
                                    <p>비밀번호 확인<span>*</span></p>
                                    <input type="password" id="confirmPasswd" placeholder="비밀번호 재확인 부탁드립니다." required="required">
                              
                                    
                                    <p>우편번호<span>*</span></p>
                                    <input type="text" id="zipcode" name="zipcode" style="width: 50%;">
                                    <input type="button" value="검색" onclick="execDaumPostcode();" style="width: 20%; padding-left: 0">
                                    
                                    <p>도로명 주소 <span>*</span></p>
                                    <input type="text" id="roadAddress" name="roadAddress" placeholder="도로명주소를 입력하세요.">
                                 	
                                 	<p>지번 주소 <span>*</span></p>
                                    <input type="text" id="jibunAddress" name="jibunAddress" placeholder="지번주소를 입력하세요.">
                                    
                                     <p>나머지 주소 <span>*</span></p>
                                    <input type="text" id="namujiAddress" name="namujiAddress" placeholder="나머지주소를 입력하세요.">
                                    
                                    <p>email <span>*</span></p>
                                    <input type="email" name="email" placeholder="이메일을 입력해주세요.">
                                </div>
                            </div>
                            
                              
                           
                            <div class="col-lg-12">
                                
                                    <label for="acc">
                                        	이메일 수신 동의하시겠습니까?
                                       <input type="checkbox" id="emailstsYn" name="emailstsYn" value="Y">
                                        <span class="checkmark"></span>
                                    </label>
                                    <p>이메일 수신을 동의하실 경우, 사이트 내 새로운 정보가 업데이트 시 정기적으로 알림이 갈 예정입니다.</p>
                                    
                                </div>
                                
                                <div>
                                 <strong>포지션<span>*</span></strong><p></p>
                                    
                                    	프론트엔드&nbsp; <input type="radio" name="sort" value="front">
                                    	백엔드&nbsp;<input type="radio" name="sort" value="back">
                                    	AI&nbsp; <input type="radio" name="sort" value="ai">
                                    	데이터 분석&nbsp; <input type="radio" name="sort" value="data">
                                    	기타 &nbsp;<input type="radio" name="sort" value="all">
                                 </div>
                                
                                
                                 <div class="checkout__order">
                                <h5>알림</h5>
                                
                                <div class="checkout__order__widget">
                                   
                                       	정말로 가입하시겠습니까?
                                    <p>가입을 원치 않으실 경우 뒤로가기 버튼을 눌러주시고, 가입을 계속 진행하실 경우 아래의 전송버튼을 눌러주세요.</p>
                                  
                                </div>
                                <button type="submit" class="site-btn">전송</button>
                            </div>
                            </div>
                        </div>
                  
                        
                    </div>
                </form>
            </div>
        </section>
        <!-- Checkout Section End -->
	

</body>
</html>
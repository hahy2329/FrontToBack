<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	
	
	$().ready(function(){	
		$("form").submit(function(){
			
			var passwd = $("#passwd").val();
			var memberId = $("#memberId").val();
			
			
			if($("#passwd").val()==$("#confirmPasswd").val()){
				
				
				$.ajax({
					
					type : "get",
					url : "${contextPath}/member/checkDuplicatedPasswd?passwd="+passwd+"&memberId="+memberId,
					success : function(data){
						if(data == "duplicate"){
							
							alert("정보를 확인중입니다. 잠시만 기다려주십시오.");
							return true;
							
						}
						
						else{
							
							return false;
						}
						
						
					}
					
					
				});
				
				
			}
			
			else{
				alert("패스워드를 다시 확인해주세요.");
				return false;
				
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
                        <a href="${contextPath }/"><i class="fa fa-home"></i> Home</a>
                        <span>Update</span>
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
            <form action="${contextPath }/member/removeMember" class="checkout__form" method="post">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>회원탈퇴</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>아이디 <span>*</span></p>
                                    <input type="text" id="memberId" name="memberId"  value="${memberDTO.memberId }" readonly="readonly">
                                    
                                     <p>비밀번호 <span>*</span></p>
                                    <input type="password" name="passwd" id="passwd" placeholder="비밀번호를 입력해주세요." required="required">
                                    <p>비밀번호 확인<span>*</span></p>
                                    <input type="password" id="confirmPasswd" placeholder="비밀번호를 한 번 더 입력부탁드립니다." required="required">
                                    
                                </div>
                            </div>
                               
                                
                                
                                 <div class="checkout__order">
                                <h5>알림</h5>
                                
                                <div class="checkout__order__widget">
                                   
                                       	정말로 탈퇴하시겠습니까?
                                    <p>탈퇴를 원치 않으실 경우 뒤로가기 버튼을 눌러주시고, 탈퇴를 계속 진행하실 경우 아래의 전송버튼을 눌러주세요.</p>
                                  
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
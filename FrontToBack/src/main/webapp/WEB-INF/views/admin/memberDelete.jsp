<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	var check = false;
	
	$().ready(function(){
		
		$("#btnOverlapped").click(function(){
			
			$(".answer").empty();
			
			var memberId = $("#memberId").val();
			
			if(memberId == ''){
				alert("ID를 입력해주세요.");
				return;
			}
			
			$.ajax({
				
				type: "get",
				url : "${contextPath}/admin/checkDuplicatedMemberId?memberId="+memberId,
				success : function(data){
					
					if(data == "duplicate"){
						alert("확인되었습니다.");
						check=true;
						$("#btnOverlapped").remove();
						$(".answer").append("<p style='color: green;'>"+"확인되었습니다." +"</p>");
					}
					
					else{
						alert("ID를 다시 확인해주세요.");
						check = false;
						$(".answer").append("<p style='color: red;'>" +"ID를 다시 확인해주세요."+"</p>");
						
					}
				}
				
				
				
			});
		});
		$("form").submit(function(){
			
			
			if(check == true){
				
				return true;
			}
			
			if(check == false){
				alert("아이디를 다시 확인해주세요.");
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
                        <a>홈페이지 관리</a>
                        <a>유저관리</a>
                        <span>회원삭제</span>
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
                                            <a data-target="#collapseOne" href="${contextPath }/admin/noticeList">공지사항</a>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-heading active">
                                            <a data-toggle="collapse" data-target="#collapseOne">커뮤니티</a>
                                        </div>
                                        <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <ul>
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
                 
            
                <div class="trend__content">
                    <div class="section-title">
                        <h4>회원삭제</h4>
                    </div>
                    
                    
                    
                    <div class="contact__form" align="center">
                              <form action="${contextPath }/admin/memberDelete" method="post">
                            	<p>아이디<span>*</span></p>
                                <input type="text" name="memberId" id="memberId" placeholder="아이디를 입력해주세요." required="required">
                             	<input type="button" id="btnOverlapped" value="중복확인">
                                <p class="answer"></p>
                                
                                <button type="submit" class="site-btn">삭제</button>
                                
                               </form> 
                      </div>
                      
                      </div>
                     </div>
                    </div>
                      
         
    </section>
    <!-- Shop Section End -->
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
<link rel="stylesheet" type="text/css" href="/moviecube/resources/css/findid.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>

<%@ include file="/WEB-INF/views/main/body_header.jspf"%>

<body>
<section class="bg0 p-t-104 p-b-116">
<div class="container">
	<!-- 모달창 -->
	<div class="modal fade" id="defaultModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="mtext-115 modal-title">알림</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
					</div>
					<div class="modal-body">
						<p class="stext-112 modal-contents"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	<!-- 모달창 -->

	<div class="mtext-105 cl2 p-b-15 m-l-70 m-lr-15-lg ">
		아이디 / 비밀번호 찾기
	</div>
	<div class="p-all-25 bor10 m-r-35 m-l-70 m-lr-15-lg">
		<p class="stext-115 m-b-15" style="line-height: 23px;">아이핀을 이용하여 회원에 가입하신 경우에는 회사가 회원님의 주민등록번호를 보유하고 있지 않은 관계로, 아이핀 등록기관의 실명확인 및 본인인증 서비스와 연계하여 아이디 및 패스워드 찾기 서비스가 제공되오니 참고하여 주시기 바랍니다. (실명확인 및 본인인증서비스 제공 기관 : 나이스신용평가정보㈜)<br>본인인증 시 제공되는 정보는 해당 인증기관에서 직접 수집 하며, 인증 이외의 용도로 이용 또는 저장하지 않습니다.</p>
		<p class="stext-115" style="line-height: 23px;">* 이용안내 <span>고객센터 &gt; 1:1</span>문의 또는 <span>ARS 1544-0070</span> (09:00~21:00)</p>
	</div>
	
	<div class="flex-w flex-tr">
		<div class="size-210 p-l-70 p-r-10 p-t-55 p-b-70 p-lr-15-lg w-full-md">
			<div class="mtext-110 cl2 m-b-20">아이디 찾기</div>
			<ul class="nav nav-tabs" role="tablist">
				<li class="nav-item"><a class="bg6 cl6 nav-link active" data-toggle="tab" role="tab" href="#find_id_easy" title="간편 아이디 찾기">간편찾기</a></li>
			<!-- 	<li class="nav-item"><a class="bg6 cl6 nav-link" href="#find_id_hard" role="tab" data-toggle="tab" title="본인인증으로 아이디 찾기">본인인증으로 찾기</a></li> -->
			</ul>

			<div class="tab-content">
				<!-- 아이디 간편찾기// -->
				<div class="tab-pane active" id="find_id_easy" role="tabpanel" aria-expanded="true">
				<form name="findId" id="findId" role="form">
					<ul class="easy_input_wrap">
						<li>
							<label for="name" class="stext-111 size-220 dis-inline-block">이름</label>
							<div class="stext-111 pos-relative txt-middle dis-inline-block">
								<input class="bor10 p-l-5" type="text" id="name" name="MEMBER_NAME" maxlength="30">
							</div>
						</li>

						<li>
							<label for="" class="stext-111 size-220 dis-inline-block">법정생년월일</label>
							<div class="stext-111 pos-relative txt-middle dis-inline-block">
								<input class="bor10 p-l-5 onlyNumberSlash" type="text" id="age" name="MEMBER_AGE" placeholder="YYYY/MM/DD" maxlength="10">
							</div>
						</li>

						<li>
							<label for="" class="stext-111 size-220 dis-inline-block">휴대폰</label>
							<div class="stext-111 pos-relative txt-middle dis-inline-block">
								<input class="bor10 p-l-5 onlyNumber" type="text" id="phone" name="MEMBER_PHONE" placeholder="번호만" maxlength="11">
							</div>
						</li>
					</ul>
					<div class="submit_wrap">
						<button type="button" id="fi" class="flex-c-m stext-107 float-r cl13 size-301 bor21 p-lr-15 hov-tag2 trans-04 m-r-5 m-b-5"><span class="blind">확인</span></button> 
					</div>
				</form>
				</div>
				<!-- //아이디 간편찾기 -->

				<!-- 아이디 본인인증으로 찾기// -->
				<!-- <div class="tab-pane" id="find_id_hard" role="tabpanel" aria-expanded="false">
					<div class="find_hard_wrap">
						<div class="pt50">아이핀(i-PIN)으로 찾기</div>
						<button type="button" title="새 창 열림" class="img_btn user mt13 mb40" onclick="fnPopupIPIN('FINDID')">인증기관을 통해 인증 후 확인</button>
						<div>휴대폰으로 찾기</div>
						<button type="button" title="새 창 열림" class="img_btn user mt13" onclick="fnPopupCheckPlus('FINDID')">인증기관을 통해 인증 후 확인</button>
					</div>
				</div> -->
				<!-- //아이디 본인인증으로 찾기  -->
			</div>
		</div>

		<div class="size-210 p-l-10 p-r-70 p-t-55 p-b-70 p-lr-15-lg w-full-md">
			<div class="mtext-110 cl2 m-b-20">비밀번호 찾기</div>
			<ul class="nav nav-tabs" role="tablist">
				<li class="nav-item"><a class="bg6 cl6 nav-link active" href="#find_pw_easy" role="tab" data-toggle="tab" title="간편 비밀번호 찾기">간편찾기</a></li>
				<!-- <li class="nav-item"><a class="bg6 cl6 nav-link" href="#find_pw_hard" role="tab" data-toggle="tab" title="본인인증으로 비밀번호 찾기">본인인증으로 찾기</a></li> -->
			</ul>

			<div class="tab-content">
				<!-- 비번 간편찾기// -->
				<div class="tab-pane active" id="find_pw_easy" role="tabpanel" aria-expanded="true">
				<form id="findPw" role="form" method="post">
					<!-- 휴대폰// -->
					<ul class="easy_input_wrap">
						<li>
							<label for="id" class="stext-111 size-220 dis-inline-block">아이디</label>
							<div class="stext-111 pos-relative txt-middle dis-inline-block">
								<input class="bor10 p-l-5" type="text" id="id" name="MEMBER_ID" maxlenth="16">
							</div>
						</li>

						<li>
							<label for="name1" class="stext-111 size-220 dis-inline-block">이름</label>
							<div class="stext-111 pos-relative txt-middle dis-inline-block">
								<input class="bor10 p-l-5" type="text" id="name1" name="MEMBER_NAME1" maxlength="30">
							</div>
						</li>

						<li>
							<label for="" class="stext-111 size-220 dis-inline-block">휴대폰</label>
							<div class="stext-111 pos-relative txt-middle dis-inline-block">
								<input class="bor10 p-l-5 onlyNumber" type="text" id="phone1" name="MEMBER_PHONE1" placeholder="번호만" maxlength="11">
							</div>
						</li>
					</ul>
					
					<div class="submit_wrap">
						<button type="button" id="fp" class="flex-c-m stext-107 float-r cl13 size-301 bor21 p-lr-15 hov-tag2 trans-04 m-r-5 m-b-5"><span class="blind">확인</span></button> 
					</div>
				</form>
				</div>
					<!-- 휴대폰// -->
				<!-- //비번 간편찾기 -->

				<!-- 비번 본인인증으로 찾기// -->
				<!-- <div class="tab-pane" id="find_pw_hard" role="tabpanel" aria-expanded="false">
					<div class="find_hard_wrap">
						<div class="radio_wrap pt45">
							<span class="iradio_minimal checked" style="vertical-align: middle; display: inline-block; width: 18px; height: 18px; position: relative;" aria-checked="false" aria-disabled="false"><input type="radio" title="비밀번호 본인인증 아이핀 선택" id="user_radio1" name="find_pw_radio" value="IPIN" class="icheck" style="position: absolute; top: 0px; left: 0px; display: block; width: 16px; height: 16px; padding: 0px; margin: 0px; background: rgb(255, 255, 255); z-index: 0; border: 0px; opacity: 1;"><ins class="iCheck-helper" style="position:absolute; width:16px; height:16px; top:0px; left:0px; background-color:#fff; z-index:0"></ins></span>
							<label for="user_radio1" class="mr9">아이핀(i-PIN)</label>
							<span class="iradio_minimal" style="vertical-align: middle; display: inline-block; width: 18px; height: 18px; position: relative;" aria-checked="false" aria-disabled="false"><input type="radio" title="비밀번호 본인인증 휴대폰 선택" id="user_radio2" name="find_pw_radio" value="PHONE" class="icheck" style="position: absolute; top: 0px; left: 0px; display: block; width: 16px; height: 16px; padding: 0px; margin: 0px; background: rgb(255, 255, 255); z-index: 0; border: 0px; opacity: 1;"><ins class="iCheck-helper" style="position:absolute; width:16px; height:16px; top:0px; left:0px; background-color:#fff; z-index:0"></ins></span>
							<label for="user_radio2">휴대폰</label>
						</div>
						<p>인증기관을 통해 인증이 확인 되면 비밀번호를<br>초기화 하고 새로운 비밀번호를 등록 할 수 있습니다.</p>
						<div class="find_pw_hard_input">
							<label for="find_pw_ipin" class="mr8">아이디</label>
							<div class="inputs">
								<input type="text" title="아이디 입력" id="find_pw_ipin" name="web_Id" maxlength="16" style="width: 199px;" allowtype="alphabet number">
							</div>
						</div>
						<button type="button" class="img_btn user mt13" title="새 창 열림" onclick="findPasswordByAuth()">인증기관을 통해 인증 후 확인</button>
					</div>
				</div> -->
				<!-- //비번 본인인증으로 찾기 -->
			</div>
		</div>
		
		<script>
			$(function(){
				
				var modalContents = $(".modal-contents");
				var modal = $("#defaultModal");
				
				 $(".onlyNumberSlash").keyup(function(event){
	                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
	                        var inputVal = $(this).val();
	                        $(this).val(inputVal.replace(/[^/0-9]/gi,''));
	                    }
	                });
				
				 $(".onlyNumber").keyup(function(event){
	                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
	                        var inputVal = $(this).val();
	                        $(this).val(inputVal.replace(/[^0-9]/gi,''));
	                    }
	                });
				 
				 //--------validation 검사
				 $( "#fi" ).click(function(){
					 
					 if($('#name').val()==""){
					    	modalContents.text("이름은 필수 입력 사항입니다.");
	                     	modal.modal('show');
	                       
	                     	$('#name').focus();
	                     	return false;
					 } 
					 
					 if($('#age').val()==""){
							modalContents.text("생년월일은 필수 입력 사항입니다.");
							modal.modal('show');
						 
							$('#age').focus();
							return false;
					 } 
					 
					 if($('#phone').val()==""){
							modalContents.text("휴대폰 번호는 필수 입력 사항입니다.");
						 	modal.modal('show');
						 
						 	$('#phone').focus();
						 	return false;
					 }
					 
<<<<<<< HEAD
					 var name = $("#name").val();
					 var age = $("#age").val();
					 var phone = $("#phone").val();
					 
					 var data = {
					 	"MEMBER_NAME" : name,
					 	"MEMBER_AGE" : age,
					 	"MEMBER_PHONE" : phone
					 }
					 
					 $.ajax({
					 	type : "POST",
						url : "<c:url value='/member/find.do'/>",
						dataType : "json",
						data : data,
						
						success : function(data){
					 		if(data.findid.MEMBER_ID != null){
					 			modalContents.text("회원님의 아이디는["+data.findid.MEMBER_ID+"]입니다.");
							 	modal.modal('show');
						 	}
						},
						error : function(error){
								alert("error : "+error);
							}
					 });
=======
					 return findId();
					
>>>>>>> 2f24f14e2eb38df9eac54d8e4cdb671a356b4268
				 });
				 
				 
				 $( "#fp" ).click(function(){
					 
					 if($('#id').val()==""){
							modalContents.text("아이디는 필수 입력 사항입니다.");
							modal.modal('show');
						 
							$('#id').focus();
							return false;
					 } 
					 
					 if($('#name1').val()==""){
					    	modalContents.text("이름은 필수 입력 사항입니다.");
	                     	modal.modal('show');
	                       
	                     	$('#name1').focus();
	                     	return false;
					 } 
					
					 if($('#phone1').val()==""){
							modalContents.text("휴대폰 번호는 필수 입력 사항입니다.");
						 	modal.modal('show');
						 
						 	$('#phone1').focus();
						 	return false;
					 }
					 
<<<<<<< HEAD
					 var id = $("#id").val();
					 var name1 = $("#name1").val();
					 var phone1 = $("#phone1").val();
					 
					 var data = {
						"MEMBER_ID" : id,
						"MEMBER_NAME" : name1,
						"MEMBER_PHONE" : phone1
					 }
					 
					 $.ajax({
						type : "POST",
						url : "<c:url value='/member/find1.do'/>",
						dataType : "json",
						data : data,
							
							success : function(data){
						 		if(data.findpw.MEMBER_PASSWD1 != null){
						 			modalContents.text("회원님의 비밀번호는["+data.findpw.MEMBER_PASSWD1+"]입니다.");
								 	modal.modal('show');
							 	}
							},
							error : function(error){
									alert("error : "+error);
							}
					 });
=======
					 return findPw();
>>>>>>> 2f24f14e2eb38df9eac54d8e4cdb671a356b4268
				 });
			});
			
			function findId(){
				var findId = document.getElementById("findId");
				findId.action="/moviecube/member/find.do";
				findId.submit();
			}
			
			function findPw(){
				var findPw = document.getElementById("findPw");
				findPw.action="/moviecube/member/find1.do";
				findPw.submit();
			}
		</script>
	</div>
</div>
</section>

<%@ include file="/WEB-INF/views/main/script.jspf" %>
</body>
</html>
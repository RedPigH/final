<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
<script src="//code.jquery.com/jquery.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function aaaa(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extra").value = extraAddr;
            
            } else {
                document.getElementById("extra").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("zipcode").value = data.zonecode;
            document.getElementById("address1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("address2").focus();
        }
    }).open();
}
</script>
</head>

<body class="animsition">
<%@ include file="/WEB-INF/views/main/body_header.jspf"%>
<%@ include file="../member/loginForm.jspf"%>

	<div class="container" style="margin-top:150px;">
		<!-- 좌우측의 공간 확보 -->
		<div class="flex-w flex-tr">
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
		<!--// 모달창 -->

		<div class="size-219 bor10 p-lr-70 p-t-55 p-b-70 p-lr-15-lg w-full-md" style="margin:auto;">
		<form id="joinForm" role="form" method="post">
			<div id="divId">
				<label for="inputId" style="display: inline-block;" class="cl2 mtext-114">아이디</label>
				<input type="button" style="display: inline-block; position:float; right;" value="ID중복확인" id="checkId" class="flex-c-m stext-118 cl0 bg1 bor2 hov-btn1 p-lr-15 trans-04 pointer">
				<div class="bor8 m-b-20 ">
					<input type="text" class="stext-111 cl8 plh3 size-129 p-lr-15 onlyAlphabetAndNumber"
						id="id" name="MEMBER_ID" data-rule-required="true"
						placeholder="30자이내의 알파벳, 언더스코어(_), 숫자만 입력 가능합니다." maxlength="30">
				</div>
			</div>
			
			<div id="divPassword">
				<label for="inputPassword" class="cl2 mtext-114">패스워드</label>
				<div class="bor8 m-b-20 ">
					<input type="password" class="stext-111 cl8 plh3 size-129 p-lr-15" id="password"
						name="MEMBER_PASSWD1" data-rule-required="true" placeholder="패스워드"
						maxlength="30">
				</div>
			</div>
			
			<div id="divPasswordCheck">
				<label for="inputPasswordCheck" class="cl2 mtext-114">패스워드
					확인</label>
				<div class="bor8 m-b-20 ">
					<input type="password" class="stext-111 cl8 plh3 size-129 p-lr-15" id="passwordCheck"
						name="MEMBER_PASSWD2" data-rule-required="true"
						placeholder="패스워드 확인" maxlength="30">
				</div>
			</div>
			<div id="divName">
				<label for="inputName" class="cl2 mtext-114">이름</label>
				<div class="bor8 m-b-20 ">
					<input type="text" class="stext-111 cl8 plh3 size-129 p-lr-15 onlyHangul" id="name"
						name="MEMBER_NAME" data-rule-required="true"
						placeholder="한글만 입력 가능합니다." maxlength="15">
				</div>
			</div>


			<div id="divEmail">
				<label for="inputEmail" class="cl2 mtext-114">이메일</label>
				<div class="bor8 m-b-20 ">
					<input type="email" class="stext-111 cl8 plh3 size-129 p-lr-15" id="email"
						name="MEMBER_EMAIL" data-rule-required="true" placeholder="이메일"
						maxlength="40">
				</div>
			</div>

			<div id="divZipcode">
				<label for="inputZipcode" class="cl2 mtext-114">주소</label>
				<div class="how-pos4-parent">
						<input type="text" style="margin-bottom: 5px; display: inline-block;" class="bor8 stext-111 cl8 plh3 size-128 p-lr-15" id="zipcode" name="MEMBER_ZIPCODE" data-rule-required="true" placeholder="우편번호" maxlength="6">
						<input type="button" onclick="aaaa()"  value="우편번호 찾기" style="display: inline-block;" class="flex-c-m size-128 cl0 bg1 bor20 hov-btn1 p-lr-15 trans-04 pointer">
				</div>
			</div>

			<div id="divAddress1">
				<div class="bor8" style="margin-bottom: 5px;">
					<input type="text" class="stext-111 cl8 plh3 size-129 p-lr-15" id="address1"
						name="MEMBER_ADDRESS1" data-rule-required="true" placeholder="주소"
						maxlength="40">
				</div>
			</div>

			<div id="divAddress2">
				<div class="bor8" style="margin-bottom: 5px;">
					<input type="text" class="stext-111 cl8 plh3 size-129 p-lr-15" id="address2"
						name="MEMBER_ADDRESS2" data-rule-required="true"
						placeholder="상세주소" maxlength="40">
				</div>
			</div>

			<div id="divExtra">
				<div class="bor8 m-b-20 ">
					<input type="text" class="stext-111 cl8 plh3 size-129 p-lr-15" id="extra"
						data-rule-required="true" placeholder="참고항목" maxlength="40">
				</div>
			</div>

			<div id="divPhoneNumber">
				<label for="inputPhoneNumber" class="cl2 mtext-114 jointext">휴대폰
					번호</label>
				<div class="bor8 m-b-20 ">
					<input type="tel" class="stext-111 cl8 plh3 size-129 p-lr-15 onlyNumber" id="phoneNumber"
						name="MEMBER_PHONE" data-rule-required="true"
						placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11">
				</div>
			</div>

			<div id="divAge">
				<label for="inputAge" class="cl2 mtext-114 jointext">생일</label>
				<div class="bor8 m-b-20 ">
					<input type="text" class="stext-111 cl8 plh3 size-129 p-lr-15 onlyNumberSlash" id="age"
						name="MEMBER_AGE" data-rule-required="true"
						placeholder="YYYY/MM/DD형식으로 작성해주세요." maxlength="10">
				</div>
			</div>

			<div class="divSex">
				<label for="inputSex" class="cl2 mtext-114 jointext">성별</label>
				<div class="bor8 m-b-20 ">
					<select class="stext-111 cl8 plh3 size-129 p-lr-15" id="gender">
						<option value="M">남</option>
						<option value="F">여</option>
					</select>
				</div>
			</div>



			<div class="divEmail">
				<label for="inputEmailReceiveYn" class="cl2 mtext-114 jointext">이메일
					수신여부</label>
				<div class="col-lg-10 m-tb-15">
					<label class="radio-inline" style="display: inline-block;"> <input type="radio"
						id="emailReceiveYn" name="emailReceiveYn" value="Y" checked>
						동의합니다.
					</label> <label class="radio-inline" style="display: inline-block;"> <input type="radio"
						id="emailReceiveYn" name="emailReceiveYn" value="N"> 동의하지
						않습니다.
					</label>
				</div>
			</div>
			<div class="">
				<label for="inputPhoneNumber" class="cl2 mtext-114 jointext">SMS
					수신여부</label>
				<div class="col-lg-10 m-tb-15">
					<label class="radio-inline" style="display: inline-block;"> <input type="radio"
						id="smsReceiveYn" name="smsReceiveYn" value="Y" checked>
						동의합니다.
					</label> <label class="radio-inline" style="display: inline-block;"> <input type="radio"
						id="smsReceiveYn" name="smsReceiveYn" value="N"> 동의하지
						않습니다.
					</label>
				</div>
			</div>

			<input type="hidden" name="admin" value="0">

			<div class="flex-w flex-t p-t-27 p-b-33">
				<span class="mtext-101 cl2">
					<button class="flex-c-m stext-101 size-130 cl0 bg1 bor1 hov-btn1 p-lr-15 trans-04 pointer" id="sm">완료</button>
				</span>
			</div>
		</form>
		</div>

		<script>
        
        var checkid = 0;
        
            $(function(){
                //모달을 전역변수로 선언
                var modalContents = $(".modal-contents");
                var modal = $("#defaultModal");
                 
                $('.onlyAlphabetAndNumber').keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val($(this).val().replace(/[^_a-z0-9]/gi,'')); //_(underscore), 영어, 숫자만 가능
                    }
                });
                 
                $(".onlyHangul").keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val(inputVal.replace(/[a-z0-9]/gi,''));
                    }
                });
             
                $(".onlyNumber").keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val(inputVal.replace(/[^0-9]/gi,''));
                    }
                });
                
                $(".onlyNumberSlash").keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val(inputVal.replace(/[^/0-9]/gi,''));
                    }
                });
                 
                //------- 검사하여 상태를 class에 적용
                $('#id').keyup(function(event){
                     
                    var divId = $('#divId');
                     
                    if($('#id').val()==""){
                        divId.removeClass("has-success");
                        divId.addClass("has-error");
                    }else{
                        divId.removeClass("has-error");
                        divId.addClass("has-success");
                    }
                });
                 
                $('#password').keyup(function(event){
                     
                    var divPassword = $('#divPassword');
                     
                    if($('#password').val()==""){
                        divPassword.removeClass("has-success");
                        divPassword.addClass("has-error");
                    }else{
                        divPassword.removeClass("has-error");
                        divPassword.addClass("has-success");
                    }
                });
                 
                $('#passwordCheck').keyup(function(event){
                     
                    var passwordCheck = $('#passwordCheck').val();
                    var password = $('#password').val();
                    var divPasswordCheck = $('#divPasswordCheck');
                     
                    if((passwordCheck=="") || (password!=passwordCheck)){
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                    }
                });
                 
                $('#name').keyup(function(event){
                     
                    var divName = $('#divName');
                     
                    if($.trim($('#name').val())==""){
                        divName.removeClass("has-success");
                        divName.addClass("has-error");
                    }else{
                        divName.removeClass("has-error");
                        divName.addClass("has-success");
                    }
                });
                 
                $('#age').keyup(function(event){
                     
                    var divAge = $('#divAge');
                     
                    if($.trim($('#age').val())==""){
                    	divAge.removeClass("has-success");
                    	divAge.addClass("has-error");
                    }else{
                    	divAge.removeClass("has-error");
                    	divAge.addClass("has-success");
                    }
                });
                 
                $('#email').keyup(function(event){
                     
                    var divEmail = $('#divEmail');
                     
                    if($.trim($('#email').val())==""){
                        divEmail.removeClass("has-success");
                        divEmail.addClass("has-error");
                    }else{
                        divEmail.removeClass("has-error");
                        divEmail.addClass("has-success");
                    }
                });
                 
                $('#phoneNumber').keyup(function(event){
                     
                    var divPhoneNumber = $('#divPhoneNumber');
                     
                    if($.trim($('#phoneNumber').val())==""){
                        divPhoneNumber.removeClass("has-success");
                        divPhoneNumber.addClass("has-error");
                    }else{
                        divPhoneNumber.removeClass("has-error");
                        divPhoneNumber.addClass("has-success");
                    }
                });
                
                $('#zipcode').keyup(function(event){
                    
                    var divZipcode = $('#divZipcode');
                     
                    if($.trim($('#zipcode').val())==""){
                    	divZipcode.removeClass("has-success");
                    	divZipcode.addClass("has-error");
                    }else{
                    	divZipcode.removeClass("has-error");
                    	divZipcode.addClass("has-success");
                    }
                });
                
               $('#address1').keyup(function(event){
                    
                    var divAddress1 = $('#divAddress1');
                     
                    if($.trim($('#address1').val())==""){
                    	divAddress1.removeClass("has-success");
                    	divAddress1.addClass("has-error");
                    }else{
                    	divAddress1.removeClass("has-error");
                    	divAddress1.addClass("has-success");
                    }
                });
               
               $('#address2').keyup(function(event){
                   
                   var divAddress2 = $('#divAddress2');
                    
                   if($.trim($('#address2').val())==""){
                	   divAddress2.removeClass("has-success");
                	   divAddress2.addClass("has-error");
                   }else{
                	   divAddress2.removeClass("has-error");
                	   divAddress2.addClass("has-success");
                   }
               });
               
                //-------아이디 중복 확인
                $( "#checkId" ).click(function() {
					//id를 param
                	var id = $("#id").val();
					
					$.ajax({
						async: true,
						type : 'POST',
						data : id,
						url : "/moviecube/member/checkId.do",
						dataType : "json",
						contentType : "application/json; charset=UTF-8",
						success : function(data){
							if($('#id').val()==""){
								Swal.fire("","아이디를 입력해주세요.","warning");
							} else if(data.count > 0) {
								Swal.fire("","아이디가 존재합니다. 다른 아이디를 입력해주세요.","warning");
								$("#divId").addClass("has-error")
			                    $("#divId").removeClass("has-success")
			                    $("#id").focus();
							} else {
								Swal.fire("","사용가능한 아이디입니다.","info");
								$("#divId").addClass("has-error")
			                    $("#divId").removeClass("has-success")
			                    $("#password").focus();
								//아이디가 중복되지 않으면 checkid=1
								checkid = 1;
							}
						},
						error : function(error){
							Swal.fire("error : "+error);
						}
					});
                });
               
                //------- validation 검사
                $( "#sm" ).click(function( event ) {
                     
                    var provision = $('#provision');
                    var memberInfo = $('#memberInfo');
                    var divId = $('#divId');
                    var divPassword = $('#divPassword');
                    var divPasswordCheck = $('#divPasswordCheck');
                    var divName = $('#divName');
                    var divAge = $('#divAge');
                    var divEmail = $('#divEmail');
                    var divPhoneNumber = $('#divPhoneNumber');
                    var divZipcode = $('#divZipcode');
                    var divAddress1 = $('#divAddress1');
                    var divAddress2 = $('#divAddress2');
                    
                    //아이디 검사
                    if($('#id').val()==""){
                        modalContents.text("아이디를 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divId.removeClass("has-success");
                        divId.addClass("has-error");
                        $('#id').focus();
                        return false;
                    }else{
                        divId.removeClass("has-error");
                        divId.addClass("has-success");
                    }
                    
                    //중복확인 검사
                    if(checkid==0){
                        modalContents.text("중복확인 해주세요.");
                        modal.modal('show');
                    	return false;
                    }
                    
                    //패스워드 검사
                    if($('#password').val()==""){
                        modalContents.text("패스워드를 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divPassword.removeClass("has-success");
                        divPassword.addClass("has-error");
                        $('#password').focus();
                        return false;
                    }else{
                        divPassword.removeClass("has-error");
                        divPassword.addClass("has-success");
                    }
                     
                    //패스워드 확인
                    if($('#passwordCheck').val()==""){
                        modalContents.text("패스워드 확인을 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                        $('#passwordCheck').focus();
                        return false;
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                    }
                     
                    //패스워드 비교
                    if($('#password').val()!=$('#passwordCheck').val() || $('#passwordCheck').val()==""){
                        modalContents.text("패스워드가 일치하지 않습니다.");
                        modal.modal('show');
                         
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                        $('#passwordCheck').focus();
                        return false;
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                    }
                     
                    //이름
                    if($('#name').val()==""){
                        modalContents.text("이름을 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divName.removeClass("has-success");
                        divName.addClass("has-error");
                        $('#name').focus();
                        return false;
                    }else{
                        divName.removeClass("has-error");
                        divName.addClass("has-success");
                    }
                     
                    //나이
                    if($('#age').val()==""){
                        modalContents.text("생일을 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divAge.removeClass("has-success");
                        divAge.addClass("has-error");
                        $('#age').focus();
                        return false;
                    }else{
                    	divAge.removeClass("has-error");
                    	divAge.addClass("has-success");
                    }
                     
                    //이메일
                    if($('#email').val()==""){
                        modalContents.text("이메일을 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divEmail.removeClass("has-success");
                        divEmail.addClass("has-error");
                        $('#email').focus();
                        return false;
                    }else{
                        divEmail.removeClass("has-error");
                        divEmail.addClass("has-success");
                    }
                    
                    //휴대폰 번호
                    if($('#phoneNumber').val()==""){
                        modalContents.text("휴대폰 번호를 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divPhoneNumber.removeClass("has-success");
                        divPhoneNumber.addClass("has-error");
                        $('#phoneNumber').focus();
                        return false;
                    }else{
                        divPhoneNumber.removeClass("has-error");
                        divPhoneNumber.addClass("has-success");
                    }
                    
                    //우편번호
                    if($('#zipcode').val()==""){
                        modalContents.text("우편 번호를 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divZipcode.removeClass("has-success");
                        divZipcode.addClass("has-error");
                        $('#zipcode').focus();
                        return false;
                    }else{
                    	divZipcode.removeClass("has-error");
                    	divZipcode.addClass("has-success");
                    }
                    
                    //주소
                    if($('#address1').val()==""){
                        modalContents.text("주소를 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divAddress1.removeClass("has-success");
                        divAddress1.addClass("has-error");
                        $('#address1').focus();
                        return false;
                    }else{
                    	divAddress1.removeClass("has-error");
                    	divAddress1.addClass("has-success");
                    }
                    
                    //상세주소
                    if($('#address2').val()==""){
                        modalContents.text("상세주소를 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                         
                        divAddress2.removeClass("has-success");
                        divAddress2.addClass("has-error");
                        $('#address2').focus();
                        return false;
                    }else{
                    	divAddress2.removeClass("has-error");
                        divAddress2.addClass("has-success");
                    }
                     
                 	return join();
                });
                 
            });
            
            function join(){
            	Swal.fire("","회원가입을 축하합니다.","success")
            	var joinform = document.getElementById("joinForm");
            	joinform.action="/moviecube/member/join.do";
            	joinform.submit();
            }
            
            
            
        </script>
		<!--// 본문 들어가는 부분 -->
		<hr />
		</div>
	</div>
	<!-- 푸터 들어가는 부분 -->
<%@ include file="/WEB-INF/views/main/script.jspf" %>
	<!--// 푸터 들어가는 부분 -->
</body>
</html>
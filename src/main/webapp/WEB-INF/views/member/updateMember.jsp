<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
<link rel="stylesheet" type="text/css" href="/moviecube/resources/css/member.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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

<body class="animsition" style="animation-duration: 1500ms; opacity: 1;">

<c:if test="${empty sessionScope.userLoginInfo}">
	<script type="text/javascript">
		alert("로그인 해주세요.");
		window.location.replace("/moviecube/main.do");
	</script>
</c:if>


<c:if test="${not empty sessionScope.userLoginInfo}">

<%@ include file="/WEB-INF/views/main/body_header.jspf"%>
<%@ include file="/WEB-INF/views/main/wishList.jspf" %>
<%@ include file="/WEB-INF/views/member/loginForm.jspf" %>
<%@ include file="/WEB-INF/views/member/updatePassword.jspf" %>
<%@ include file="/WEB-INF/views/member/deleteMember.jspf" %>

<div id="container" class="container">

	<div class="width-fixed mypage_membership_wrap" style="position: relative;" id="mypageMembershipWrap">
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
		
		<div class="h2_mypage">
			<h3 class="sub_title">개인정보 수정</h3> <span> 회원님의 정보를 정확히 입력해주세요. </span>
		</div>

		<div class="personal_info_last">
			<button type="button" class="img_btn psw_change mr6 flex-c-m stext-111 cl0 bg1 bor2 hov-btn4 trans-04 js-show-modal3">비밀번호 변경</button>
			<button type="button" class="img_btn personal_quit mr6 flex-c-m stext-111 cl0 bg1 bor2 hov-btn4 trans-04 js-show-modal4">회원탈퇴</button>
		</div>

		<div class="user_wrap">
		<form id="update" name="update" enctype="multipart/form-data" method="post">
			<div id="userJoinContainer" class="form-style">
				<div class="clearfix">
					<span class="text-sub mb10">*표시 항목은 필수입력 항목입니다.</span>
				</div>

				<!-- 프로필과 아이디 -->
				<div class="clearfix myInfoArea1">
					<img name="photo" id="profilePhotoImg" style="width: 100px; height: 100px;" src="http://image2.megabox.co.kr/mop/home/user/profile_m.png" alt="프로필" class="img-circle pull-left" data-original="" data-image="">
					<div class="pull-left textArea">
						<span class="profile_btn_wrap">
							<strong>${sessionScope.userLoginInfo.MEMBER_ID}</strong>
							<input type="hidden" name="MEMBER_ID" value="${sessionScope.userLoginInfo.MEMBER_ID}">
							<div>
							<button id="imgUploadBtn" class="img_btn flex-c-m stext-111 cl13 bor21 hov-tag2 trans-04">찾아보기</button>
							<button id="imgDeleteBtn" class="img_btn flex-c-m stext-111 cl13 bor21 hov-tag2 trans-04" onclick="profileDel()">삭제</button>
							</div>
						</span>
					</div>
				</div>

				<h4>기본정보 (필수입력)</h4>
				<table class="form-table">
					<caption class="blind2">개인정보(필수입력)수정:비밀번호 입력,이름,생년월일,휴대폰,이메일 수정</caption>
					<colgroup>
						<col width="181">
						<col width="">
					</colgroup>
						<tbody><tr><th scope="row" id="th_myInfo_password"><label for="inputtext2">*비밀번호</label></th>
						<td headers="th_myInfo_password">
							<input type="password" id="psw" name="MEMBER_PASSWD1">
						</td>
					</tr>
					<tr>
						<th scope="row" id="th_myInfo_name"><label for="inputtext4">*이름</label></th>
						<td headers="th_myInfo_name">
							<input type="text" id="memberName" name="MEMBER_NAME" value="${sessionScope.userLoginInfo.MEMBER_NAME}" fieldname="이름">
						</td>
					</tr>

					<tr>
						<th scope="row" id="th_myInfo_birthday"><label id="modUserInfo_birthday" for="inputtext5">*생년월일</label></th>
						<td headers="th_myInfo_birthday">
							<input type="text" id="memberAge" name="MEMBER_AGE" value="${sessionScope.userLoginInfo.MEMBER_AGE}" fieldname="생일" placeholder="YYYY/MM/DD" maxlength="10">
						</td>
					</tr>

					<tr>
						<th scope="row" id="th_myInfo_mobile"><label id="modUserInfo_phone" for="inputtext8">*휴대폰</label></th>
						<td headers="th_myInfo_mobile">
							<input type="text" id="memberPhone" name="MEMBER_PHONE" value="${sessionScope.userLoginInfo.MEMBER_PHONE}" fieldname="생일" maxlenght="11">
						</td>
					</tr>
					<tr>
						<th scope="row" id="th_myInfo_email"><label id="modUserInfo_email" for="inputtext9">*이메일</label></th>
						<td headers="th_myInfo_email">
							<input type="text" id="memberEmail" name="MEMBER_EMAIL" value="${sessionScope.userLoginInfo.MEMBER_EMAIL}" fieldname="이메일" validate="email">
						</td>
					</tr>
				</tbody></table>

				<h4>부가정보 (선택입력)</h4>
				<table class="form-table">
					<caption class="blind2">개인정보 부가정보 (선택입력)수정:주소,선호영화관 수정</caption>
					<colgroup>
						<col width="181">
						<col width="">
					</colgroup>
					<tbody><tr>
						<th scope="row" id="th_myInfo_address"><label for="inputtext6">주소</label></th>
						<td headers="th_myInfo_address">
						<div class="how-pos4-parent">
							<input type="text" value="${sessionScope.userLoginInfo.MEMBER_ZIPCODE}" style="margin-bottom: 5px; display: inline-block;" id="zipcode" name="MEMBER_ZIPCODE" data-rule-required="true" placeholder="우편번호" maxlength="6">
							<button type="button" onclick="aaaa()" style="display: inline-block; width: 120px; height: 22px;" class="flex-c-m stext-111 cl0 bg1 bor2 hov-btn4 trans-04 m-l-20">우편번호 찾기</button>
							<input type="text" value="${sessionScope.userLoginInfo.MEMBER_ADDRESS1}" id="address1" name="MEMBER_ADDRESS1" data-rule-required="true" placeholder="주소" maxlength="40">
							<input type="text" style="margin-top:5px; display: inline-block" value="${sessionScope.userLoginInfo.MEMBER_ADDRESS2}" id="address2" name="MEMBER_ADDRESS2" data-rule-required="true" placeholder="상세주소" maxlength="40">
							<input type="text" style="display: inline-block;" id="extra" data-rule-required="true" placeholder="참고항목" maxlength="40">
						</div>
						</td>
					</tr>
				</tbody></table>

				
		<!-- 마케팅 활용을 위한 개인정보 수집 이용 안내 -->
		
		<div class="personal_information_wrap">
			<div id="" class="form-style">
				<h4>마케팅 활용을 위한 개인정보 수집 이용 안내</h4>
				<table class="form-table">
					<caption class="blind2">개인정보 수집 및 마케팅 활용을 위한 개인정보 수집 목적, 수집 항목, 보유 및 이용 기간, 동유여부</caption>
					<colgroup>
						<col width="30%">
						<col width="25%">
						<col width="30%">
						<col width="">
					</colgroup>
					<thead>
						<tr>
							<th scope="col" id="th_myInfo_purpose">수집 목적</th>
							<th scope="col" id="th_myInfo_item">수집 항목</th>
							<th scope="col" id="th_myInfo_term">보유 및 이용 기간</th>
							<th scope="col" id="th_myInfo_agree">동의여부</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td headers="th_myInfo_purpose" class="pl20">
									고객 맞춤형 상품 및 서비스 추천.<br>
									당사 신규 상품/서비스 안내 및 권유.<br>
									사은/할인 행사 등 각종 이벤트 정보 등의 안내 및 권유
							</td>
							<td headers="th_myInfo_item">
									이메일, 휴대폰번호, 주소, 생년월일, 선호영화관, 문자/이메일/앱푸쉬 정보 수신동의여부, 서비스 이용기록, 포인트 적립 및 사용 정보, 접속로그
							</td>
							<td headers="th_myInfo_term">
									회원 탈퇴 시 혹은 이용 목적 달성 시까지
							</td>
							<td headers="th_myInfo_agree" class="ml20">
								<label class="radio-inline"> <span style="vertical-align: middle; display: inline-block; width: 18px; height: 18px; position: relative;"><input type="radio" style="position: absolute; width: 16px; height: 16px; padding: 0px; margin: 0px;" id="smsReceiveYn" name="smsReceiveYn" value="Y" checked></span> <strong>동의</strong></label>
								<label class="radio-inline"> <span style="vertical-align: middle; display: inline-block; width: 18px; height: 18px; position: relative;"><input type="radio" style="position: absolute; width: 16px; height: 16px; padding: 0px; margin: 0px;" id="smsReceiveYn" name="smsReceiveYn" value="N"></span> <strong>동의 않음</strong></label>
							</td>
						</tr>
					</tbody>
				</table>

				<h2 class="h2_mypage marktingAgree">
					<span class="sub_title mypage st14">마케팅정보 수신동의</span>
				</h2>
				<div class="agree_area">
					<p class="txt">이벤트, 신규 서비스, 할인 혜택 등의 소식을 전해드립니다. (소멸포인트 및 공지성 안내 또는 거래정보와 관련된 내용은 수신동의 여부와 상관없이 발송됩니다.)</p>
					<label class="radio-line" style="display: inline-block;"> <span style="vertical-align: middle; display: inline-block; width: 18px; height: 18px; position: relative;"><input type="radio" style="position: absolute; width: 16px; height: 16px; padding: 0px; margin: 0px;" id="marketingYn" name="marketingYn" value="Y" checked></span> <strong>동의</strong></label>
					<label class="radio-line" style="display: inline-block;"> <span style="vertical-align: middle; display: inline-block; width: 18px; height: 18px; position: relative;"><input type="radio" style="position: absolute; width: 16px; height: 16px; padding: 0px; margin: 0px;" id="marketingYn" name="marketingYn" value="N"></span> <strong>동의 않음</strong></label>
				</div>
			</div>
		</div>
		<!-- //마케팅 활용을 위한 개인정보 수집 이용 안내 -->
		
			<div class="btn_wrap text-center mt50 mb50">
				<button type="button" class="img_btn user cancel mr7 flex-c-m stext-111 cl0 bg1 bor2 hov-btn4 trans-04" onclick="history.back()">취소</button>
				<button type="button" class="img_btn user modify ml7 flex-c-m stext-111 cl0 bg1 bor2 hov-btn4 trans-04" id="up">수정</button>
			</div>
		</div>
	</form>
	
	<script>
	$(function(){
		
		 var modalContents = $(".modal-contents");
         var modal = $("#defaultModal");
		
		$("#up").click(function(){
			if($('#psw').val()==""){
				modalContents.text("비밀번호는 필수 입력사항입니다.");
                modal.modal('show');
                $('#psw').focus();
                return false;
			}
			
			if($('#memberName').val()==""){
				modalContents.text("비밀번호는 필수 입력사항입니다.");
                modal.modal('show');
                $('#memberName').focus();
                return false;
			}
			
			if($('#memberAge').val()==""){
				modalContents.text("비밀번호는 필수 입력사항입니다.");
                modal.modal('show');
                $('#memberAge').focus();
                return false;
			}
			
			if($('#memberPhone').val()==""){
				modalContents.text("비밀번호는 필수 입력사항입니다.");
                modal.modal('show');
                $('#memberPhone').focus();
                return false;
			}
			
			if($('#memberEmail').val()==""){
				modalContents.text("비밀번호는 필수 입력사항입니다.");
                modal.modal('show');
                $('#memberEmail').focus();
                return false;
			}
				return update();
		});
	});
	
	function update(){
    	var update = document.getElementById("update");
		update.action="/moviecube/member/updateMember.do";
		update.submit();
	}
	</script>
	</div>
</div>

<%@ include file="/WEB-INF/views/main/script.jspf" %>

</div>
</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>테스트</title>
<%-- 	<%@ include file="/WEB-INF/views/main/head.jspf" %> --%>

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
<body>
개인정보 수정페이지 (테스트)
<hr>
	<form action="/moviecube/modifyMyInfo.do">
		<!-- 비밀번호, 생년월일, 휴대폰, 이메일, 주소 -->
		비밀번호 : <input type="text" name="member_id" value="${memberMap.MEMBER_PASSWD1 }"> <br>
		생년월일 : <input type="text" name="member_name" value="${memberMap.MEMBER_AGE }"> <br> 
		휴대폰 : <input type="text" name="member_phone" value="${memberMap.MEMBER_PHONE }"> <br>
		이메일 : <input type="text" name="member_phone" value="${memberMap.MEMBER_PHONE }"> <br>
		주소 : <input type="text" name="member_phone" value="${memberMap.MEMBER_PHONE }"> <br> 
		 
		<div class="form-group" id="divZipcode">
                <label for="inputZipcode" class="col-lg-2 control-label">주소</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="zipcode" name="MEMBER_ZIPCODE" value="${memberMap.MEMBER_ZIPCODE }" data-rule-required="true" style="width:300px; display:inline;" placeholder="우편번호" maxlength="6">
                    <input type="button" onclick="aaaa()" value="우편번호 찾기" class="btn btn-default" style="position: absolute; left:320px;">
                </div>
                
        </div>
		<div class="form-group" id="divAddress1">
               <div class="col-lg-10">
                   <input type="text" class="form-control" id="address1" name="MEMBER_ADDRESS1" value="${memberMap.MEMBER_ADDRESS1}" data-rule-required="true" placeholder="주소" maxlength="40">
               </div>
       </div>
            
      <div class="form-group" id="divAddress2">
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="address2" name="MEMBER_ADDRESS2" value="${memberMap.MEMBER_ADDRESS2}" data-rule-required="true" placeholder="상세주소" maxlength="40">
                </div>
            </div>
		 
		<input type="submit" value="변경하기">
	</form>

	<%-- <c:choose>
		<c:when test="${fn:length(memberMap) > 0 }">
		<br>이름 : ${memberMap.MEMBER_NAME }
		<br>연락처 : ${memberMap.MEMBER_PHONE }
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="4">개인정보를 불러올 수 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose> --%>
</body>
</html>
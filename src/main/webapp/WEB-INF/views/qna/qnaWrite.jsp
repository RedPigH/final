<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
<script src="/moviecube/resources/js/jquery-1.10.2.min.js"></script>
<script src="/moviecube/resources/js/jquery-ui.js"></script>
<script src="/moviecube/resources/js/admin_common.js"></script>

</head>

<body class="animsition">
	<%@ include file="/WEB-INF/views/main/body_header.jspf"%>

	<header class="header-v4">
		<div class="container-menu-desktop"></div>
	</header>


	<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
			<div class="col-lg-6 col-xl-6 m-lr-auto m-t-50">
				<div class="flex-w flex-sb-m p-t-18 p-b-30 p-lr-50 p-lr-15-sm"
					style="justify-content: center;">
					<h4 class="mtext-109 cl2 p-b-30" style="padding-bottom: 0px">1:1
						문의</h4>
				</div>
			</div>
			<p class="stext-107 cl6 p-b-20" style="text-align: -webkit-center;">
				문의 사항이 있으면 글을 남겨주세요. 답변 내용은 마이페이지에서 확인 할 수 있습니다.</p>

			<form id="frm" name="frm" enctype="multipart/form-data" class="col-lg-6 col-xl-6 m-lr-auto m-t-50">
				<div class="bor19 m-b-20" style="justify-content: center;">
					<input name="QNA_SUB" class="stext-111 cl2 plh3 size-116 p-lr-18"
						type="text" placeholder="제목 *"> <input type="hidden"
						name="QNA_ID" value="${sessionScope.userLoginInfo.MEMBER_NAME}">
				</div>

				<div class="bor19 m-b-20" style="justify-content: center;">
					<textarea name="QNA_CONTENT"
						class="stext-111 cl2 plh3 size-124 p-lr-18 p-tb-15"
						placeholder="문의내용을 입력하세요..."></textarea>
				</div>

				<div class="bor19 size-218" style="justify-content: center;">
					<input type="file" name="file" type="text">
				</div>
				
				
				<div class="flex-w flex-sb-m p-b-30 p-lr-15-sm"
					style="justify-content: flex-end;">
					<div
						class="flex-c-m stext-106 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-10"
						id="write" onclick="qnaWrite();">작성완료</div>
				</div>
			</form>
		</div>
	</div>

	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top"> <i
			class="zmdi zmdi-chevron-up"></i>
		</span>
	</div>



	<%@ include file="/WEB-INF/views/main/body_footer.jspf"%>
	<%@ include file="/WEB-INF/views/member/loginForm.jspf"%>
	<%@ include file="/WEB-INF/views/main/script.jspf"%>
</body>


<form id="commonForm" name="common"></form>

<script>
	function qnaWrite() {

		var form = document.frm;
		var member_no = "${sessionScope.userLoginInfo.MEMBER_NO}";

		if (member_no.length == 0) {
			swal("로그인 후 이용하세요", "", "error");
			return;
		} else if (form.QNA_SUB.value == "") {
			swal("제목을 입력하세요", "", "error");
			return;
		} else if (form.QNA_CONTENT.value == "") {
			swal("내용을 입력하세요", "", "error");
			return;
		} else {
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/qnaWrite.do'/>");
			comSubmit.addParam("QNA_NO", $("#QNA_NO").val());
			comSubmit.submit();
		}
	}
</script>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>공지사항</title>
<script src="/moviecube/resources/js/admin_common.js"></script>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
</head>

<body class="animsition" style="opacity: 1; animation-duration: 1500ms;">

	<%@ include file="../main/body_header.jspf"%>


	<form class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="col-lg-10 col-xl-7 m-lr-auto m-t-50">
			<div class="flex-w flex-sb-m p-t-18 p-b-30 p-lr-50 p-lr-15-sm" style="justify-content: center;">
					<h4 class="mtext-109 cl2 p-b-30" style="padding-bottom: 0px;">공지사항
					</h4>
				</div>
				</div>
				<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart" style="margin: 0 0 0 0;">
								<tbody>
									<tr class="table_head">
										<th class="column-1">글번호</th>
										<th class="column-2">제목</th>
										<th class="column-3">날짜</th>
									</tr>


									<c:choose>
										<c:when test="${fn:length(noticeList) > 0}">
											<c:forEach items="${noticeList}" var="row">

												<tr class="table_row" style="border-bottom: 1px dashed #edeaea;">
													<td class="column-1">${row.NOTICE_NO}</td>
													<td class="column-2"><a href="javascript:void(0);" name="NOTICE_SUB"
														style="color: #555;">${row.NOTICE_SUB} <input
															type="hidden" id="NOTICE_NO" value="${row.NOTICE_NO}" /></a></td>
													<td class="column-3"><c:set var="TextValue"
															value="${row.NOTICE_REGDATE}" />
														${fn:substring(TextValue,0,10)}</td>
												</tr>

											</c:forEach>
										</c:when>
										<c:otherwise>
									등록된 게시물이 없습니다
								</c:otherwise>
									</c:choose>

								</tbody>
							</table>
						</div>

						<div
							class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-l-40 p-lr-15-sm">
							<div class="flex-w flex-m m-r-20 m-tb-5">
								<div class="paging">${pagingHtml}</div>
							</div>

							<div class="flex-c-m stext-101 cl2 size-119 p-lr-15 trans-04 m-tb-10">
							<div class="rs1-select2 bor8 bg0" style="width: 100px">

									<select class="js-select2" name="searchNum">
										<option value="0">제목</option>
										<option value="1">내용</option>
									</select>
									<div class="dropDownSelect2"></div>
								</div>
								<input
									class="stext-104 cl2 plh4 size-117 bor4 p-lr-20 m-r-10 m-tb-5"
									type="text" name="isSearch" placeholder="제목,내용을 입력해주세요">
								<input type="submit" class="flex-c-m stext-106 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-10" value="검색">
									
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>





	<div class="container-menu-desktop">
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top"> <i
				class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
	</div>

	<%@ include file="../main/body_footer.jspf"%>

	<%@ include file="../member/loginForm.jspf"%>

	<%@ include file="../store/store_ProductModal.jspf"%>

	<%@ include file="../main/script.jspf"%>

</body>

<form id="commonForm" name="common"></form>

<script type="text/javascript">
	$(document).ready(function() {

		$("a[name='NOTICE_SUB']").on("click", function(e) { // 공지사항 제목 클릭
			e.preventDefault();
			fn_openBoardDetail($(this));
		});
	});

	function fn_openBoardDetail(obj) {
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='noticeDetail.do' />");
		comSubmit.addParam("NOTICE_NO", obj.parent().find("#NOTICE_NO").val());
		comSubmit.addParam("currentPage", "${currentPage}");
		comSubmit.submit();
	}
</script>

</html>
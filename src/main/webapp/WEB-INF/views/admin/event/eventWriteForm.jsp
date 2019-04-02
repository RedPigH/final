<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String cp = request.getContextPath();
%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>EVENT 관리자</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/resources/css/admin_import.css" />
<script src="<%=cp%>/resources/js/jquery-1.10.2.min.js"></script>
<script src="<%=cp%>/resources/js/admin_common.js"></script>
</head>
<body>

	<div class="admin">
		<div class="logo">
			<h1>
				<a href="<%=cp%>/event/eventList.do">MovieCube Administrator -
					EVENT Register</a>
			</h1>
		</div>
	</div>

	<div class="admin_grp">
		<div class="admin_list">
			<ul>
				<li class="on"><a href="<%=cp%>movieList.do">이벤트 정보</a></li>
				<li><a href="<%=cp%>/admin/cinemaList.do">영화관</a></li>
				<li><a href="<%=cp%>/admin/cinemaList.do">영화관</a></li>
				<li><a href="<%=cp%>/admin/screenList.do">상영관</a></li>
				<li><a href="<%=cp%>/admin/insertSeatForm.do">상영관좌석</a></li>
				<li><a href="<%=cp%>/admin/timeList.do">영화시간표</a></li>
				<li><a href="<%=cp%>/admin/noticeList.do">공지사항</a></li>
				<li><a href="<%=cp%>/admin/faqList.do">FAQ</a></li>
				<li><a href="<%=cp%>/admin/qnaList.do">Q&amp;A</a></li>
				<li><a href="<%=cp%>/admin/memberList.do">회원정보</a></li>
			</ul>
		</div>

		<div class="admin_ct">
			<h3 class="sub_tit">이벤트 정보 등록</h3>
			<form id="frm" name="frm" enctype="multipart/form-data">
				<div class="tbl_type_01">
					<table>

						<colgroup>
							<col style="width: 120px;" />
							<col />
						</colgroup>
					</table>

					<div id="fileDiv">
						<table>
							<colgroup>
								<col style="width: 120px;" />
								<col />
							</colgroup>
							<tbody>
							<tbody>
								<tr>
									<th scope="row">이벤트 제목</th>
									<td><input type="text" class="txt w200" id="EVENT_NAME"
										name="EVENT_NAME" /> <font color="green"><span
											class="ibk">예) (캡틴마블) MX관 특별 포스터 증정 이벤트 </span></font></td>
								</tr>

								<tr>
									<th scope="row">이벤트 타입</th>
									<td><select name="EVENT_TYPE" class="slct w200">
											<option value="무비큐브 이벤트">무비큐브 이벤트</option>
											<option value="영화 이벤트">영화 이벤트</option>
											<option value="제휴 이벤트">제휴 이벤트</option>
									</select></td>
								</tr>

								<tr>
									<th scope="row">이벤트 시작일</th>
									<td><input type="date" class="txt w200"
										id="EVENT_OPENDATE" name="EVENT_OPENDATE" /> <font
										color="red"></font></td>
								</tr>

								<tr>
									<th scope="row">이벤트 마감일</th>
									<td><input type="date" class="txt w200"
										id="EVENT_CLOSEDATE" name="EVENT_CLOSEDATE" /> <font
										color="red"></font></td>
								</tr>


								<tr>
									<th scope="row">동영상 URL</th>
									<td><input type="text" class="txt w200" id="EVENT_URL"
										name="EVENT_URL" /> <font color="red"></font></td>
								</tr>


							</tbody>
						</table>


						<div id="fileDiv">
							<table>
								<colgroup>
									<col style="width: 120px;" />
									<col />
								</colgroup>
								<tbody>

									<tr>
										<th scope="row">이벤트 이미지</th>
										<td><input type="file" class="txt" name="EVENT_ORGNAME_0" />
											<input type="hidden" name="EVENT_NO"
											value="${param.EVENT_NO }" /> <a href="#this"
											class="btn btnC_04 btnP_04" id="addFile"> <span>이미지
													추가</span>
										</a> <a href="this" class="btn btnC_04 btnP_04" id="delete"
											name="delete"><span>삭제</span></a></td>
									</tr>
								</tbody>
							</table>


						</div>

						<div class="btn_type_03">

							<a href="#this" class="btn btnC_04 btnP_04"
								style="padding-left: 10px;" id="write"> <span>작성하기</span>
							</a> <a href="#this" class="btn btnC_04 btnP_04"
								style="padding-left: 10px;" id="list"> <span>목록으로</span>
							</a>
						</div>
			</form>
		</div>
	</div>

	<form id="commonForm" name="common"></form>

	<script type="text/javascript">
		var gfv_count = 1;

		$(document).ready(function() {
			$("#list").on("click", function(e) { //목록으로 버튼
				e.preventDefault();
				fn_openBoardList();
			});

			$("#write").on("click", function(e) { //작성하기 버튼
				e.preventDefault();
				fn_insertBoard();
			});

			$("#addFile").on("click", function(e) { //파일 추가 버튼
				e.preventDefault();
				fn_addFile();
			});

			$("a[name='delete']").on("click", function(e) { //삭제 버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		});

		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/eventList.do' />");
			comSubmit.submit();
		}

		function fn_insertBoard() {
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/eventWrite.do' />");
			comSubmit.submit();
		}

		function fn_addFile() {
			var str = "<p><input type='file' name='EVENT_ORGNAME_"
					+ (gfv_count++)
					+ "'><a href='#this' class='btn btnC_04 btnP_02' name='delete'><span>삭제</span></a></p>";
			$("#fileDiv").append(str);
			$("a[name='delete']").on("click", function(e) { //삭제 버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		}

		function fn_deleteFile(obj) {
			obj.parent().remove();
		}
	</script>
</body>
</html>
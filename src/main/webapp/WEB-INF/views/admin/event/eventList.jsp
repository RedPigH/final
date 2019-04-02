<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>Event 관리자</title>
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
				<a href="<%=cp%>/eventList.do">MovieCube Administrator -
					EVENT List</a>
			</h1>
		</div>
	</div>

	<div class="admin_grp">
		<div class="admin_list">
			<ul>
				<li class="on"><a href="<%=cp%>/eventList.do">이벤트 정보</a></li>
				<li><a href="<%=cp%>/admin/movieList.do">영화 정보</a></li>
				<li><a href="<%=cp%>/admin/cinemaList.do">영화관</a></li>
				<li><a href="<%=cp%>/admin/screenList.do">상영관</a></li>
				<li><a href="<%=cp%>/admin/insertSeatForm.do">상영관 좌석</a></li>
				<li><a href="<%=cp%>/admin/timeList.do">영화시간표</a></li>
				<li><a href="<%=cp%>/admin/noticeList.do">공지사항</a></li>
				<li><a href="<%=cp%>/admin/faqList.do">FAQ</a></li>
				<li><a href="<%=cp%>/admin/qnaList.do">Q&amp;A</a></li>
				<li><a href="<%=cp%>/admin/memberList.do">회원정보</a></li>
			</ul>
		</div>
		<div class="admin_ct">
			<div class="movie_list">
				<h3 class="sub_tit">이벤트</h3>
				<ul>
					<c:choose>
						<c:when test="${fn:length(eventList) > 0}">
							<c:forEach items="${eventList}" var="row">

								<li><a href="#this" name="poster" class="list"> <img
										src="<%=cp%>/resources/upload/event/${row.EVENT_SAVNAME}" width="350" height="350"
										
										alt="이벤트" /> <input type="hidden" id="EVENT_NO"
										value="${row.EVENT_NO}"> <input type="hidden"
										id="currentPage" value="${currentPage}"> <span
										class="detail">상세보기</span>
										<div class="explan">
											<p>
												<strong>이벤트 제목</strong> : ${row.EVENT_NAME} 
											</p>
											
											<p>
											<strong>번호</strong> :${row.EVENT_NO}
											</p>

											<p>
												<strong></strong> : ${row.EVENT_OPENDATE}
											</p>

											<p>
												<strong>~</strong> :${row.EVENT_CLOSEDATE}
											</p>
										</div>
								</a></li>
							</c:forEach>
						</c:when>
						<c:otherwise>
					조회된 결과가 없습니다.
				</c:otherwise>
					</c:choose>
				</ul>
			</div>

				<div class="btn_type_03">
				<a href="#this" class="btn btnC_01 btnP_04" id="write">
					<span>글쓰기</span>
				</a>
			</div>
			
			<form id="commonForm" name="common"></form>

			<script type="text/javascript">
				$(document).ready(function() {
					$("#write").on("click", function(e) { //글쓰기 버튼
						e.preventDefault();
						fn_openBoardWrite();
					});

					$("a[name='poster']").on("click", function(e) { // 이벤트 제목 클릭
						e.preventDefault();
						fn_openBoardDetail($(this));
					});
				});

				function fn_openBoardWrite() {
					var comSubmit = new ComSubmit();
					comSubmit.setUrl("<c:url value='/eventWriteForm.do' />");
					comSubmit.submit();
				}

				function fn_openBoardDetail(obj) {
					var comSubmit = new ComSubmit();
					comSubmit.setUrl("<c:url value='/eventDetail.do' />");
					comSubmit.addParam("EVENT_NO", obj.parent().find(
							"#EVENT_NO").val());
					comSubmit.addParam("currentPage", "${currentPage}");
					comSubmit.submit();
				}
			</script>
</body>
</html>
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
<meta charset="UTF-8">
<title>MovieCube 관리자</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/resources/css/admin_import.css" />
<script src="<%=cp%>/resources/js/jquery-1.10.2.min.js"></script>
<script src="<%=cp%>/resources/js/admin_common.js"></script>

<script type="text/javascript">
	function eventDelete() {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			location.href = 'eventDelete.do?EVENT_NO=${map.EVENT_NO}';
		} else { //취소
			return;
		}
	}
</script>
</head>

<body>


	<div class="admin">
		<div class="logo">
			<h1>
				<a href="<%=cp%>/eventList.do">MovieCube Administrator - Event
					Detail </a>
			</h1>
		</div>
	</div>

	<div class="admin_grp">
		<div class="admin_list">
			<ul>
				<li class="on"><a href="<%=cp%>eventList.do">영화 정보</a></li>
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

<%-- 		<div class="admin_ct">
			<div class="movie_grp">
				<div class="movie_pic">
					<a href="#this" name="poster"> <span class="detail">크게보기</span>
						<img src="<%=cp%>/resources/upload/event/${map.EVENT_SAVNAME_0}"
						title="클릭하시면 원본크기로 보실 수 있습니다." style="cursor: pointer;"
						onclick="doImgPop('<%=cp%>/resources/upload/event/${map.EVENT_SAVNAME_0}')" />
					</a>
				</div> --%>
				<div class="movie_txt">
					<p class="tit">${map.EVENT_NAME}</p>
					<dl>
						<dt>기간</dt>
						<dd>${map.EVENT_OPENDATE}</dd>
						<dd>${map.EVENT_CLOSEDATE}</dd>
						<dt>타입</dt>
						<dd>${map.EVENT_TYPE}</dd>
						<dt>동영상</dt>
						<dd>${map.EVENT_URL}</dd>
					</dl>
					<div class="movie_btn">
						<span class="btn btnC_02 btnF_01 mr10"></span> <span></span> <a
							href="<%=cp%>/eventModifyForm.do?EVENT_NO=${map.EVENT_NO}"
							class="btn btnC_01 btnF_04" style="padding-left: 10px;"> <span>내용 수정</span></a>

						<a onClick="eventDelete()" class="btn btnC_01 btnF_04"
							style="padding-left: 10px;"> <span>삭제</span></a> <a
							href="<%=cp%>/eventList.do?currentPage=${currentPage}"
							class="btn btnC_01 btnF_04" style="padding-left: 10px;"> <span>목록</span>
						</a>
					</div>
				</div>
				<h3 class="sub_tit">이미지</h3>
				<div class="movie_steel">
					<div class="inner">
						<ul>
							<c:forEach var="row" items="${eventDetail}">
								<li><img
									src="<%=cp%>/resources/upload/event/${row.EVENT_SAVNAME}"
									alt="" /></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>

	<script type="text/javascript">
		function doImgPop(img) {
			img1 = new Image();
			img1.src = (img);
			imgControll(img);
		}

		function imgControll(img) {
			if ((img1.width != 0) && (img1.height != 0)) {
				viewImage(img);
			} else {
				controller = "imgControll('" + img + "')";
				intervalID = setTimeout(controller, 20);
			}
		}

		function viewImage(img) {
			W = img1.width;
			H = img1.height;
			O = "width=" + W + ",height=" + H + ",scrollbars=yes";
			imgWin = window.open("", "", O);
			imgWin.document.write("<html><head><title>이미지상세보기</title></head>");
			imgWin.document.write("<body topmargin=0 leftmargin=0>");
			imgWin.document
					.write("<img src="
							+ img
							+ " onclick='self.close()' style='cursor:pointer;' title ='클릭하시면 창이 닫힙니다.'>");
			imgWin.document.close();
		}
	</script>
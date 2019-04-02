<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<% String cp = request.getContextPath(); %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>MovieCube 관리자</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<link rel="stylesheet" type="text/css" href="<%= cp %>/resources/css/admin_import.css" />
<script src="<%= cp %>/resources/js/jquery-1.10.2.min.js"></script>
<script src="<%= cp %>/resources/js/admin_common.js"></script>

<script type="text/javascript">

	$(function(){
		var schedule_btn = $(".schedule_delete");
		
		schedule_btn.each(function(){
			var btn = $(this).children('.btn');
		
			btn.on('click',function(){
				var check = confirm("정말 삭제하시겠습니까?");	
				if(check){
					return true;
				}else{
					return false;
				}
			})
		})
	}) 
	
	function screenDelete() {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			location.href = 'screenDelete.do?SCREEN_NO=${map.SCREEN_NO}&currentPage={currentPage}}';
		} else { //취소
			return;
		}
	}
</script>

</head>

<div class="admin">
	<div class="logo">
	<h1><a href="<%=cp %>/admin/noticeList.do">MovieCube Administrator - Screen Regist</a></h1>
	</div>
	<div class="logo" style="float: right;">
			<a href="<%=cp%>/main.do" class="toMain" style="font-size: 15px;">메인으로</a>
	</div>
</div>

<div class="admin_grp">
	<div class="admin_list">
		<ul>
			<li><a href="<%=cp%>/admin/movieList.do">영화 정보</a></li>
			<li><a href="<%=cp%>/admin/cinemaList.do">영화관</a></li>
			<li class="on"><a href="<%=cp%>/admin/screenList.do">상영관</a></li>
			<li><a href="<%=cp%>/admin/insertSeatForm.do">상영관 좌석</a></li>
			<li><a href="<%=cp%>/admin/timeList.do">영화시간표</a></li>
			<li><a href="<%=cp%>/admin/storeList.do">STORE</a>
			<li><a href="<%=cp%>/admin/eventList.do">EVENT</a>
			<li><a href="<%=cp%>/admin/noticeList.do">공지사항</a></li>
			<li><a href="<%=cp%>/admin/faqList.do">FAQ</a></li>
			<li><a href="<%=cp%>/admin/qnaList.do">Q&amp;A</a></li>
			<li><a href="<%=cp%>/admin/memberList.do">회원정보</a></li>
		</ul>
	</div>
	
	<div class="admin_ct">
		<div class="movie_list">
			<h3 class="sub_tit">상영관 상세 정보</h3>
			<div class="tbl_type_01">
				<table>
					<caption>상영관</caption>
					<colgroup>
						<col style="width : 200px;" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">상영관 번호</th>
							<td>${map.SCREEN_NO}</td>
						</tr>
						<tr>
							<th scope="row">상영관 이름
							<td align="left">${map.SCREEN_NAME}</td>
						</tr>
						<tr>
							<th scope="row">상영관 타입</th>
							<td>${map.SCREEN_TYPE}</td>
						</tr>
						<tr>
							<th scope="row">소속 영화관  </th>
							<td>${map.CINEMA_NAME}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="admin_ct">
		<h3 class="sub_tit">좌석 정보</h3>
			<div class="tbl_type_02">
				<table>
					<caption>시간표 등록</caption>
					<colgroup>
						<col style="width:25%" />
						<col style="width:25%" />
						<col style="width:25%" />
						<col style="width:25%" />
					</colgroup>
					
					<thead>
						<tr>
							<th scope="col">상영관 </th>
							<th scope="col">좌석 행</th>
							<th scope="col">좌석 열</th>
							<th scope="col">삭제</th>
							
						</tr>
					</thead>
	
					<tbody>
					<c:choose>
					<c:when test="${fn:length(seatList) > 0}">	
						<c:forEach var="row" items="${seatList}">
						<tr>
							<td><a href="#this" name="SCREEN_NAME">${row.SCREEN_NAME}
							<input type="hidden" id="SEAT_NO" value="${row.SEAT_NO}"/></a></td>
							<td>${row.SEAT_ROW}</td>
							<td>${row.SEAT_COL}</td>
							<td class="schedule_delete">
							<a href = "deleteSeat.do?SEAT_NO=${row.SEAT_NO}&currentPage=${currentPage}" class="btn btnC_04 btnP_03">
								<span>삭제</span>
							</a></td>
						</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
					<tr>
						<td colspan="13" class="tac">등록된 좌석이 없습니다.</td>
					</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
		
		<div class="paging">${pagingHtml}</div>
		
		<div class="btn_type_03">
			
			<span class="btn btnC_04 btnP_04">
				<input type="button" onclick="location.href='screenModifyForm.do?SCREEN_NO=${map.SCREEN_NO}'" value="수정" />
			</span>
			<span class="btn btnC_04 btnP_04" style="padding-left: 10px;">
				<input type="button" onclick="screenDelete()" value="삭제" />
			</span>
			<a href="#none" style="padding-left: 10px;" class="btn btnC_04 btnP_04" onclick="location.href='screenList.do?currentPage=${currentPage}' ">
				<span>목록</span>
			</a>
		</div>
	</div>
</div>
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
//AJAX select box
function screenSelect(CINEMA_NO){
$.ajax({
 type: "POST",
 url: "<c:url value='/admin/ScreenSelect.do'/>",
 dataType:"json",
 data: {param:CINEMA_NO},
 
 success: function(data){           
  $("#selectScreen").find("option").remove().end().append("<option value=''>전체</option>");
  
  for(var idx = 0; idx < data.result.length; idx++){
	  $("#selectScreen").append("<option value='"+data.result[idx].SCREEN_NO+"'>"+data.result[idx].SCREEN_NAME+"</option>")
  }
 },
   error: function (jqXHR, textStatus, errorThrown) {
   alert("오류가 발생하였습니다.");
  }                     
 });
}
</script>
</head>

<div class="admin">
	<div class="logo">
	<h1><a href="<%=cp %>/admin/cinemaList.do">MovieCube Administrator - Time Regist</a></h1>
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
			<li><a href="<%=cp%>/admin/screenList.do">상영관</a></li>
			<li><a href="<%=cp%>/admin/insertSeatForm.do">영화 좌석</a></li>
			<li class="on"><a href="<%=cp%>/admin/timeList.do">영화시간표</a></li>
			<li><a href="<%=cp%>/admin/storeList.do">STORE</a>
			<li><a href="<%=cp%>/admin/eventList.do">EVENT</a>
			<li><a href="<%=cp%>/admin/noticeList.do">공지사항</a></li>
			<li><a href="<%=cp%>/admin/faqList.do">FAQ</a></li>
			<li><a href="<%=cp%>/admin/qnaList.do">Q&amp;A</a></li>
			<li><a href="<%=cp%>/admin/memberList.do">회원정보</a></li>
		</ul>
	</div>
	
	<div class="admin_ct">
		<h3 class="sub_tit">영화 시간 등록</h3>
		<form id="frm" name="frm" enctype="multipart/form-data">
			<div class="tbl_type_01">
				<table>
					<caption>영화 시간표 등록 </caption>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
					
						<tr>
							<th scope="row">영화제목</th>
							<td>
								<select class="slct w300" name="selectMovie">
									<c:forEach var="movie" items="${movieList}">
									<option value="${movie.MOVIE_NO}">${movie.MOVIE_NAME} / ${movie.MOVIE_TYPE}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						
						<tr>
							<th scope="row">영화관</th>
							<td>
								<select class="slct w300" name="selectCinema" id = "selectCinema" onchange = "screenSelect(this.value);">
									<option value="">선 택</option>
									<c:forEach var="cinema" items="${cinemaList}">
									<option value="${cinema.CINEMA_NO}">${cinema.CINEMA_NAME}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						
						<tr>
							<th scope="row">상영관</th>
							<td>
								<select class="slct w300" name="selectScreen" id = "selectScreen">
									<option value = "">선 택</option>
								</select>
								<%-- <select class="slct w300" name="selectScreen">
									<c:forEach var="screen" items="${screenList}">
									<option value="${screen.SCREEN_NO}">${screen.SCREEN_NAME}</option>
									</c:forEach>
								</select> --%>
							</td>
						</tr>
						
						<tr>
							<th scope="row">상영일</th>
							<td>
								<input type="date" class="txt w300" id="TIME_DATE" name="TIME_DATE" />
								<font color="red"></font>
							</td>
						</tr>
						
						<tr>
							<th scope="row">시작시간</th>
							<td>
								<select name="START_TIME" class="slct w300">
									<c:forEach begin="0" end="23" var="hour">
										<option value="${hour}:00">${hour}:00</option>
										<option value="${hour}:10">${hour}:10</option>
										<option value="${hour}:20">${hour}:20</option>
										<option value="${hour}:30">${hour}:30</option>
										<option value="${hour}:40">${hour}:40</option>
										<option value="${hour}:50">${hour}:50</option>
									</c:forEach>
										<option value="24:00">24:00</option>
								</select>
						</tr>
						
						<tr>
							<th scope="row">종료시간</th>
							<td>
								<select name="END_TIME" class="slct w300">
									<c:forEach begin="0" end="23" var="hour">
										<option value="${hour}:00">${hour}:00</option>
										<option value="${hour}:10">${hour}:10</option>
										<option value="${hour}:20">${hour}:20</option>
										<option value="${hour}:30">${hour}:30</option>
										<option value="${hour}:40">${hour}:40</option>
										<option value="${hour}:50">${hour}:50</option>
									</c:forEach>
										<option value="24:00">24:00</option>
								</select>
							</td>
					</tbody>
				</table>
			</div>
			
			<div class="btn_type_03">
				
				<a href="#this" class="btn btnC_04 btnP_04" id="write">
					<span>작성하기</span>
				</a>
			
				<a href="#this" class="btn btnC_04 btnP_04" style="padding-left: 10px;" id="list">
					<span>목록으로</span>
				</a>
			</div>
		</form>
	</div>
</div>

<form id="commonForm" name="common"></form>

<script type="text/javascript">
        var gfv_count = 1;
     
        $(document).ready(function(){
            $("#list").on("click", function(e){ //목록으로 버튼
                e.preventDefault();
                fn_openBoardList();
            });
             
            $("#write").on("click", function(e){ //작성하기 버튼
                e.preventDefault();
                fn_insertBoard();
            });
             
        });
         
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='timeList.do' />");
            comSubmit.submit();
        }
         
        function fn_insertBoard(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='timeWrite.do' />");
            comSubmit.submit();
        }
         
    </script>
</body>
</html>								
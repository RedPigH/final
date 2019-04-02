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
	function cinemaDelete() {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			location.href = 'screenDelete.do?SCREEN_NO=${map.SCREEN_NO}';
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
		<h3 class="sub_tit">상영관 등록</h3>
		<form id="frm">
			<div class="tbl_type_01">
				<table>
					<caption></caption>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
					
						<tr>
							<th scope="row">상영관 이름</th>
							<td>
								<input type="text" class="txt w200" id="SCREEN_NAME" name="SCREEN_NAME" value="${map.SCREEN_NAME}" />
								<input type="hidden" id="CINEMA_NO" name="SCREEN_NO" value="${map.SCREEN_NO}">
								<font color="red"></font>
							</td>
						</tr>
						
						<tr>
							<th scope="row">상영관 타입</th>
							<td>
								<select name="SCREEN_TYPE" class="slct w200" value="${map.SCREEN_TYPE}">
									<option value="일반" <c:if test="${map.MOVIE_TYPE == '일반'}"> selected</c:if>>2D</option>
									<option value="3D" <c:if test="${map.MOVIE_TYPE == '3D'}"> selected</c:if>>3D</option>
									<option value="4D" <c:if test="${map.MOVIE_TYPE == '4D'}"> selected</c:if>>4D</option>
									<option value="IMAX" <c:if test="${map.MOVIE_TYPE == 'IMAX'}"> selected</c:if>>IMAX</option>
								</select>
							</td>
						</tr>
											
						<tr>
							<th scope="row">영화관 번호</th>
							<td>
								<select name="CINEMA_NO" class="slct w200" >
									<option value="1">강남 무비큐브</option>
									<option value="2">교대 무비큐브</option>
									<option value="3">사당 무비큐브</option>
								</select>
								
								<font color="red"></font>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_type_03">
				<a href="#this" class="btn btnC_04 btnP_04" id="write">
					<span>수정하기</span>
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
             
            $("#write").on("click", function(e){ //수정하기 버튼
                e.preventDefault();
                fn_insertBoard();
            });
        });
         
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='screenList.do' />");
            comSubmit.submit();
        }
         
        function fn_insertBoard(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='screenModify.do' />");
            comSubmit.submit();
        }
    </script>
</body>
</html>
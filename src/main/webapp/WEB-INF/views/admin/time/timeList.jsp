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
<script src="<%= cp %>/resources/js/jquery-ui.js"></script>
<script src="<%= cp %>/resources/js/admin_common.js"></script>

<script type="text/javascript">
	
	$(function(){
		var schedule_btn = $(".schedule_delete");
		
		schedule_btn.each(function(){
			var btn = $(this).children('.btn');
			
			btn.on('click',function(){
				var check = confirm("삭제하시겠습니까?");	
				if(check){
					return true;
				}else{
					return false;
				}
			})
		})
	}) 
	
	function timeDelete() {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			location.href = 'timeDelete.do';
		} else { //취소
			return;
		}
	}

</script>
</head>

<div class="admin">
	<div class="logo">
	<h1><a href="<%=cp %>/admin/timeList.do">MovieCube Administrator - Time List</a></h1>
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
			<li><a href="<%=cp%>/admin/insertSeatForm.do">상영관 좌석</a></li>
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
		<h3 class="sub_tit">영화시간표 리스트</h3>
			<div class="tbl_type_02">
				<table>
					<caption>시간표 등록</caption>
					<colgroup>
						<col style="width:20%" />
						<col style="width:20%" />
						<col style="width:12%" />
						<col style="width:12%" />
						<col style="width:12%" />
						<col style="width:12%" />
						<col style="width:12%" />

					</colgroup>
					<thead>
						<tr>
							<th scope="col">영화목록</th>
							<th scope="col">영화관</th>
							<th scope="col">상영관</th>
							<th scope="col">상영일</th>
							<th scope="col">시작시간</th>
							<th scope="col">종료시간</th>
							<th scope="col">삭제 </th>
						</tr>
					</thead>
					
				<tbody>
				<c:choose>
					<c:when test="${fn:length(timeList) > 0}">	
						<c:forEach var="row" items="${timeList}">
						<tr>
							<td>
							<a href="#this" name="MOVIE_NAME">${row.MOVIE_NAME} / ${row.MOVIE_TYPE}
							<input type="hidden" id="TIME_NO" value="${row.TIME_NO}"/></a></td>
							<td>${row.CINEMA_NAME}</td>
							<td>${row.SCREEN_NAME}</td>
							<td><c:set var="TextValue" value="${row.TIME_DATE}"/> ${fn:substring(TextValue,0,10)}</td>
							<td>${row.START_TIME}</td>
							<td>${row.END_TIME}</td>
							
							<td class="schedule_delete">
								<a href="timeDelete.do?TIME_NO=${row.TIME_NO}&currentPage=${currentPage}" class="btn btnC_04 btnP_03"> <span>삭제</span></a> 
							</td>
							 
							 <%--   
							<td><span class="btn btnC_04 btnP_04" style="padding-left: 10px;">
								<input type="button" onclick="timeDelete()" value="삭제" />
								<input type="hidden" id="TIME_NO" value="${row.TIME_NO}"/>
								<input type="hidden" id="currentPage" value="${currentPage}"/></span>
							</td>
								 --%>						 
							<%-- <td>
							<a href="timeDelete.do?TIME_NO=${row.TIME_NO}" class = "btn btnC_04 btnP_03"><span>삭제</span></a>
							</td> --%>
							
							
						</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
					<tr>
						<td colspan="13" class="tac">등록된 스케쥴이 없습니다.</td>
					</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
		
		<div class="btn_type_03">
			<a href="#this" class="btn btnC_01 btnP_04" id="write">
				<span>시간표 등록하기</span>
			</a>
		</div>
		
		<div class="search_form">
			<form>
				<div class="inner">
					<select class="slct w100" name="searchNum">
						<option value="0">영화제목</option>
						<option value="1">영화관</option>
						<option value="2">상영관</option>
					</select>
					<input class="txt w100" type="text" name="isSearch" />
					<span class="btn btnC_04 btnP_04">
						<input type="submit" value="검색" />
					</span>
				</div>
			</form>	
		</div>
		
		<!-- paging -->
		<div class="paging">
			${pagingHtml}
		</div>
	</div>
</div>
				
<form id="commonForm" name="common"></form>

<script type="text/javascript">
        $(document).ready(function(){
            $("#write").on("click", function(e){ //글쓰기 버튼
                e.preventDefault();
                fn_openBoardWrite();
            }); 
             
            $("a[name='MOVIE_NAME']").on("click", function(e){ // 영화관 이름 클릭
                e.preventDefault();
                fn_openBoardDetail($(this));
            });
        });
         
        function fn_openBoardWrite(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='timeWriteForm.do' />");
            comSubmit.submit();
        }
         
        function fn_openBoardDetail(obj){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='timeDetail.do' />");
            comSubmit.addParam("TIME_NO", obj.parent().find("#TIME_NO").val());
            comSubmit.addParam("currentPage", "${currentPage}");
            comSubmit.submit();
        }
    </script> 
</body>
</html>	
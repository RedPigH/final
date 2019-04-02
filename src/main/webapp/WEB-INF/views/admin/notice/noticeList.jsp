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
</head>

<body>

<div class="admin">
	<div class="logo">
	<h1><a href="<%=cp %>/admin/noticeList.do">MovieCube Administrator - Notice List</a></h1>
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
			<li><a href="<%=cp%>/admin/timeList.do">영화시간표</a></li>
			<li><a href="<%=cp%>/admin/storeList.do">STORE</a>
			<li><a href="<%=cp%>/admin/eventList.do">EVENT</a>
			<li class="on"><a href="<%=cp%>/admin/noticeList.do">공지사항</a></li>
			<li><a href="<%=cp%>/admin/faqList.do">FAQ</a></li>
			<li><a href="<%=cp%>/admin/qnaList.do">Q&amp;A</a></li>
			<li><a href="<%=cp%>/admin/memberList.do">회원정보</a></li>
		</ul>
	</div>
	
	<div class="admin_ct">
		<h3 class="sub_tit">공지사항</h3>
		<div class="tbl_type_02">
			<table>
				<caption>번호,제목,글쓴이,날짜,조회를 나타내는 공지사항 표</caption>
				<colgroup>
					<col style="width:10%;" />
					<col style="width:70%;" />
					<col style="width:10%;" />
					<col style="width:10%;" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목
						<th scope="col">작성자</th>
						<th scope="col">날짜</th>
					</tr>
				</thead>
				<tbody>
					
				<c:choose>
					<c:when test="${fn:length(noticeList) > 0}">
            			<c:forEach items="${noticeList}" var="row">
						<tr>
							<td>${row.NOTICE_NO}</td>
							<td class="subject"><a href="#this" name="NOTICE_SUB">${row.NOTICE_SUB}
							<input type="hidden" id="NOTICE_NO" value="${row.NOTICE_NO}"/></a></td>
							<td>${row.NOTICE_ID}</td>
							<td><c:set var="TextValue" value="${row.NOTICE_REGDATE}"/>
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
		<div class="btn_type_03">
			<a href="#this" class="btn btnC_01 btnP_04" id="write">
				<span>공지사항 등록하기</span>
			</a>
		</div>
				
		<div class="search_form">
			<form>
				<div class="inner">
					<select class="slct w100" name="searchNum">
						<option value="0">제목</option>
						<option value="1">내용</option>
					</select>
					<input class="txt w100" type="text" name="isSearch" />
					<span class="btn btnC_04 btnP_04">
						<input type="submit" value="검색" />
					</span>
				</div>
			</form>	
		</div>
		
		<div class="paging">${pagingHtml}</div>
	</div>
</div>

<form id="commonForm" name="common"></form>

<script type="text/javascript">
        $(document).ready(function(){
            $("#write").on("click", function(e){ //글쓰기 버튼
                e.preventDefault();
                fn_openBoardWrite();
            }); 
             
            $("a[name='NOTICE_SUB']").on("click", function(e){ // 영화제목, 영화포스터 클릭
                e.preventDefault();
                fn_openBoardDetail($(this));
            });
        });
         
        function fn_openBoardWrite(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='noticeWriteForm.do' />");
            comSubmit.submit();
        }
         
        function fn_openBoardDetail(obj){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='noticeDetail.do' />");
            comSubmit.addParam("NOTICE_NO", obj.parent().find("#NOTICE_NO").val());
            comSubmit.addParam("currentPage", "${currentPage}");
            comSubmit.submit();
        }
    </script> 
</body>
</html>
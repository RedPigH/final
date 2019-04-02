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
<style type="text/css">

hr { border-top:1px solid #9C9C9C; border-bottom:1px solid #F6F6F6; }

</style>
</head>

<body>

<div class="admin">
	<div class="logo">
	<h1><a href="<%=cp %>/admin/movieList.do">MovieCube Administrator - Movie Modify</a></h1>
	</div>
	<div class="logo" style="float: right;">
			<a href="<%=cp%>/main.do" class="toMain" style="font-size: 15px;">메인으로</a>
	</div>
</div>

<div class="admin_grp">
	<div class="admin_list">
		<ul>
			<li class="on"><a href="<%=cp%>/admin/movieList.do">영화 정보</a></li>
			<li><a href="<%=cp%>/admin/cinemaList.do">영화관</a></li>
			<li><a href="<%=cp%>/admin/screenList.do">상영관</a></li>
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
		<h3 class="sub_tit">슬라이드 이미지 수정</h3>
		<form id="frm" name="frm" enctype="multipart/form-data">
		
		<div class="tbl_type_01">
				<table>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">포스터</th>
							<td>
								<input type="hidden" id="slider" name="slider" value="${map2.SLIDER_NO}">
								<a href="#this" id="name" name="name"><font color="red">${map2.SLIDER_ORGNAME}</font>&nbsp;&nbsp;&nbsp;</a>
								<font color="blue"><span class="ibk">파일이 이미 등록되어 있습니다. 새로 등록하시면 기존 파일이 삭제됩니다.</span></font><br/><br>
								<input type="file" class="txt" name="SLIDER_ORGNAME" />
							</td>
						</tr>
					</tbody>
				</table>	
		</div>	
		<div class="btn_type_03">
				<a href="#this" class="btn btnC_04 btnP_04" id="write">
				<input type="hidden" name="MOVIE_NO" value="${param.MOVIE_NO}"/>
					<span>수정하기</span>
				</a>
			
				<a href="#this" class="btn btnC_04 btnP_04" id="list" style="padding-left: 10px;">
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
            $("#list").on("click", function(e){ // 목록으로 버튼
                e.preventDefault();
                fn_openBoardList();
            });
             
            $("#write").on("click", function(e){ // 수정하기 버튼
                e.preventDefault();
                fn_insertBoard();
            });
             
            $("a[name='delete']").on("click", function(e){ //삭제 버튼
                e.preventDefault();
                fn_deleteFile($(this));
            }); 
        });
         
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/admin/movieList.do' />");
            comSubmit.submit();
        }
         
        function fn_insertBoard(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='movieModify3.do' />");
            comSubmit.submit();
        }
         
        function fn_deleteFile(obj){
            obj.parent().remove();
        } 
        
    </script>
</body>
</html>			
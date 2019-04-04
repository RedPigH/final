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
		<h3 class="sub_tit">영화 정보 수정</h3>
		<form id="frm" name="frm" enctype="multipart/form-data">
			<div class="tbl_type_01">
				<table>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
					
						<tr>
							<th scope="row">영화제목</th><!--  -->
							<td>
								<input type="text" class="txt w200" id="MOVIE_NAME" name="MOVIE_NAME" value="${map.MOVIE_NAME}"/>
								<input type="hidden" id="MOVIE_NO" name="MOVIE_NO" value="${map.MOVIE_NO}" />
								<font color="blue"><span class="ibk">예) 영화제목(3D) </span></font>
								
							</td>
						</tr>
						
						<tr>
							<th scope="row">감독</th><!--  -->
							<td>
								<input type="text" class="txt w200" id="MOVIE_DIRECTOR" name="MOVIE_DIRECTOR" value="${map.MOVIE_DIRECTOR}"/>
								<font color="red"></font>
							</td>
						</tr>
						
						<tr>
							<th scope="row">배우</th><!--  -->
							<td>
								<input type="text" class="txt w200" id="MOVIE_ACTOR" name="MOVIE_ACTOR" value="${map.MOVIE_ACTOR}"/>
								<font color="red"></font>
							</td>
						</tr>
						
						<tr>
							<th scope="row">개봉일</th><!--  -->
							<td>
								<c:set var="TextValue" value="${map.MOVIE_OPENDATE}"/>
								<input type="date" class="txt w200" id="MOVIE_OPENDATE" name="MOVIE_OPENDATE" value="${fn:substring(TextValue,0,10)}" />
								<font color="red"></font>
							</td>
						</tr>
						
						<tr>
							<th scope="row">장르</th><!--  -->
							<td>
								<input type="text" class="txt w200" id="MOVIE_GENRE" name="MOVIE_GENRE" value="${map.MOVIE_GENRE}" />
								<font color="red"><span class="ibk"></span></font>
								
							</td>
						</tr>
						
						<tr>
							<th scope="row">러닝타임</th><!--  -->
							<td>
								<input type="text" class="txt w200" id="MOVIE_RUNTIME" name="MOVIE_RUNTIME" value="${map.MOVIE_RUNTIME}" />
								<font color="red"><span class="ibk"></span></font>
								
							</td>
						</tr>
						
						<tr>
							<th scope="row">타입</th><!--  -->
							<td>
								<select name="MOVIE_TYPE" class="slct w200" value="${map.MOVIE_TYPE}">
									<option value="일반" <c:if test="${map.MOVIE_TYPE == '일반'}"> selected</c:if>>2D</option>
									<option value="3D" <c:if test="${map.MOVIE_TYPE == '3D'}"> selected</c:if>>3D</option>
									<option value="4D" <c:if test="${map.MOVIE_TYPE == '4D'}"> selected</c:if>>4D</option>
									<option value="IMAX" <c:if test="${map.MOVIE_TYPE == 'IMAX'}"> selected</c:if>>IMAX</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<th scope="row">관람등급</th><!--  -->
							<td>
								<select name="MOVIE_AGE" class="slct w200" value="${map.MOVIE_AGE}">
									<option value="0" <c:if test="${map.MOVIE_TYPE == '전체'}"> selected</c:if>>전체</option>
									<option value="12" <c:if test="${map.MOVIE_TYPE == '12'}"> selected</c:if>>12</option>
									<option value="15" <c:if test="${map.MOVIE_TYPE == '15'}"> selected</c:if>>15</option>
									<option value="19" <c:if test="${map.MOVIE_TYPE == '19'}"> selected</c:if>>19</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<th scope="row">서브 타이틀</th>
							<td>
								<input type="text" class="txt w350" id="MOVIE_SUBTITLE" name="MOVIE_SUBTITLE" value="${map.MOVIE_SUBTITLE}"/>
								<font color="red"></font>
							</td>
						</tr> 
												
						<tr>
							<th scope="row">줄거리</th><!--  -->
							<td>
								<div class="textarea_grp">
									<textarea name="MOVIE_CONTENT">${map.MOVIE_CONTENT}</textarea>
								</div>
								<font color="red"></font>
							</td>
						</tr>
				
						<tr>
							<th scope="row">포스터</th>
							<td>
								<input type="hidden" id="poster" name="poster" value="${map.FILE_NO}">
								<a href="#this" id="name" name="name"><font color="red">${map.POSTER_ORGNAME}</font>&nbsp;&nbsp;&nbsp;</a>
								<font color="blue"><span class="ibk">파일이 이미 등록되어 있습니다. 새로 등록하시면 기존 파일이 삭제됩니다.</span></font><br/><br>
								<input type="file" class="txt" name="POSTER_ORGNAME" />
							</td>
						</tr>
					</tbody>
				</table>
<%-- 							
				<div id="fileDiv">
				<c:forEach var="row" items="${movieDetail}" varStatus="var">
				<table>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
						<tr>	
							<th scope="row">스틸컷</th>
							<td>
								<input type="hidden" id="IDX" name="IDX_${var.index}" value="${row.STILLCUT_NO}">
								<a href="#this" id="name" name="name"><font color="red">${row.STILLCUT_ORGNAME}</font>&nbsp;&nbsp;&nbsp;</a>
								<font color="blue"><span class="ibk">파일이 이미 등록되어 있습니다. 새로 등록하시면 기존 파일이 삭제됩니다.</span></font><br><br>
								
								<input type="file" class="txt" id="STILLCUT_ORGNAME_${var.index}" name="STILLCUT_ORGNAME_${var.index}" />
								<a href="#this" class="btn btnC_04 btnP_04" id="addFile"> <span>스틸컷 추가</span></a>
								<a href="this" class="btn btnC_04 btnP_04" id="delete" name="delete"><span>삭제</span></a>
								
								
							</td>
						</tr>	
					</tbody>
				</table>
				</c:forEach>		
				</div>			
 --%>			
 			</div>	
	
			<div class="btn_type_03">
				<a href="#this" class="btn btnC_04 btnP_04" id="write">
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
             
            $("#addFile").on("click", function(e){ //파일 추가 버튼
                e.preventDefault();
                fn_addFile();
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
            comSubmit.setUrl("<c:url value='movieModify.do' />");
            comSubmit.submit();
        }
         
        function fn_addFile(){
            var str = "<p><input type='file' name='STILLCUT_ORGNAME_"+(gfv_count++)+"'><a href='#this' class='btn btnC_04 btnP_02' name='delete'><span>삭제</span></a></p>";
            $("#fileDiv").append(str);
            $("a[name='delete']").on("click", function(e){ //삭제 버튼
                e.preventDefault();
                fn_deleteFile($(this));
            });
        }
         
        function fn_deleteFile(obj){
            obj.parent().remove();
        } 
        
    </script>
</body>
</html>
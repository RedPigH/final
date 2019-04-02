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
	function memberDelete() {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			location.href = 'memberDelete.do?MEMBER_NO=${map.MEMBER_NO}&currentPage=${currentPage}';
		} else { //취소
			return;
		}
	}
</script>

</head>

<body>

<div class="admin">
	<div class="logo">
	<h1><a href="<%=cp %>/admin/memberList.do">MovieCube Administrator - Member Detail </a></h1>
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
			<li><a href="<%=cp%>/admin/noticeList.do">공지사항</a></li>
			<li><a href="<%=cp%>/admin/faqList.do">FAQ</a></li>
			<li><a href="<%=cp%>/admin/qnaList.do">Q&amp;A</a></li>
			<li class="on"><a href="<%=cp%>/admin/memberList.do">회원정보</a></li>
		</ul>
	</div>
	
	<div class="admin_ct">
		<div class="movie_grp">
			<div class="movie_pic">
				<a href="#this" name="poster"> 
				<span class="detail">크게보기</span>
				<img src="<%=cp%>/resources/upload/member/${map.PROFILE_SAVNAME}" title="클릭하시면 원본크기로 보실 수 있습니다." style="cursor: pointer;" onclick="doImgPop('<%=cp%>/resources/upload/member/${map.PROFILE_SAVNAME}')" />
				</a>
			</div>
			
			<div class="movie_txt">
				<p class="tit"><font color="green"> ${map.MEMBER_RANK} 등급 회원</font></p>
				<dl>
					<dt>가입일</dt>
					<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:set var="TextValue" value="${map.MEMBER_REGDATE}"/>${fn:substring(TextValue,0,10)}</dd>
					
					<dt>아이디</dt>
					<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.MEMBER_ID}</dd>
					
					<dt>이름</dt>
					<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.MEMBER_NAME}</dd>
					
					<dt>나이</dt>
					<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.MEMBER_AGE}</dd>
					
					<dt>이메일</dt>
					<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.MEMBER_EMAIL}</dd>
					
					<dt>주소</dt>
					<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.MEMBER_ADDRESS1}&nbsp;&nbsp;${map.MEMBER_ADDRESS2}</dd>
<%-- 					
					<dt>등급</dt>
					<dd><strong class="iblock pt_red mr10 fz15">${map.MEMBER_RANK}</strong></dd>
	 --%>			
					<dt>포인트</dt>
					<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong class="iblock pt_blue mr10 fz15">${map.MEMBER_MILE}</strong></dd>
				</dl>
			</div>
		</div>
	
		
		<div class="movie_grp">
				<div class="movie_btn">
					<span class="btn btnC_02 btnF_01 mr10"></span> 
					<span></span>
						
					<a onClick="memberDelete()" class="btn btnC_01 btnF_04" style="padding-left: 10px;"> <span>삭제</span></a>
					
					<a href="<%=cp%>/admin/memberList.do?currentPage=${currentPage}" class="btn btnC_01 btnF_04" style="padding-left: 10px;"> <span>목록</span> </a>
				</div>
		</div>
	</div>
</div>
	
	
	
		
<%-- 
		<div class="movie_list">
			<h3 class="sub_tit"> Member Detail </h3>
			<div class="tbl_type_01">
				<table>
					<caption>Member 상세보기</caption>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">회원 번호</th>
							<td>${map.MEMBER_NO}</td>
						</tr>
						<tr>
							<th scope="row">회원 아이디</th>
							<td>${map.MEMBER_ID}</td>
						</tr>
						<tr>
							<th scope="row">회원 이름</th>
							<td>${map.MEMBER_NAME}</td>
						</tr>
						<tr>
							<th scope="row">회원 나이</th>
							<td><pre>${map.MEMBER_AGE}</pre></td>
						</tr>
						<tr>
							<th scope="row">회원 이메일</th>
							<td><pre>${map.MEMBER_EMAIL}</pre></td>
						</tr>
						<tr>
							<th scope="row">회원 주소</th>
							<td><pre>${map.MEMBER_ADDRESS1}&nbsp;&nbsp;${map.MEMBER_ADDRESS2}</pre></td>
						</tr>
						<tr>
							<th scope="row">가입 일자</th>
							<td><pre><c:set var="TextValue" value="${map.MEMBER_REGDATE}"/>${fn:substring(TextValue,0,10)}</pre></td>
						</tr>
						<tr>
							<th scope="row">회원 등급</th>
							<td><pre>${map.MEMBER_RANK}</pre></td>
						</tr>
						<tr>
							<th scope="row">회원 마일리지</th>
							<td><pre>${map.MEMBER_MILE}</pre></td>
						</tr>
 				</tbody>
				</table>
			</div>
		</div>
		<div class="btn_type_03">
			
 		
			<span class="btn btnC_04 btnP_04" style="padding-left: 10px;">
				<input type="button" onclick="memberDelete()" value="삭제" />
			</span>
			<a href="#none" style="padding-left: 10px;" class="btn btnC_04 btnP_04" onclick="location.href='<%=cp%>/admin/memberList.do?currentPage=${currentPage}' ">
				<span>목록</span>
			</a>
		</div>
	</div>
</div>
--%>		
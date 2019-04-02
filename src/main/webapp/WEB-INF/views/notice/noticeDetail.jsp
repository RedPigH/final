<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>NOTICE</title>
<script src="/moviecube/resources/js/admin_common.js"></script>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
</head>

<body class="animsition" style="opacity: 1; animation-duration: 1500ms;">

<%@ include file="../main/body_header.jspf" %> 
	
	<div class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">		
				</div>
			</div>
			
<<<<<<< HEAD
			<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
				<h4 class="mtext-109 cl2 p-b-30"> NOTICE Detail </h4>
				<div class="flex-w flex-t bor12 p-b-13">
					<div class="size-208" style="margin-top: 20px">
=======
			<div class="col-sm-10 col-lg-7 col-xl-7 m-lr-auto m-b-50">
				<h4 class="mtext-109 cl2 p-b-30"> 공지사항 </h4>
				<div class="flex-w flex-t bor12 p-b-13">
					<div class="size-198" style="margin-top: 20px">
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
						<span class="mtext-110 cl2" >글번호 </span>
					</div>
					<div class="size-209" style="margin-top: 20px">
						<span class="mtext-110 cl2"> ${map.NOTICE_NO} </span>
					</div>
				</div>
				
				<div class="flex-w flex-t bor12 p-b-13">
<<<<<<< HEAD
					<div class="size-208" style="margin-top: 20px">
=======
					<div class="size-198" style="margin-top: 20px">
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
						<span class="mtext-110 cl2" >제목 </span>
					</div>
					<div class="size-209" style="margin-top: 20px">
						<span class="mtext-110 cl2"> ${map.NOTICE_SUB} </span>
					</div>
				</div>
				
				<div class="flex-w flex-t bor12 p-t-15 p-b-30">
<<<<<<< HEAD
					<div class="size-208 w-full-ssm">
=======
					<div class="size-198 w-full-ssm">
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
						<span class="stext-110 cl2"> 내용 </span>
					 </div>
					<div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
						<p class="stext-111 cl6 p-t-2">
						${map.NOTICE_CONTENT}
						</p>
						
					</div>
				</div>

<<<<<<< HEAD
				<div class="flex-w flex-t p-t-27 p-b-33">
					
					
					<a href="#none" style="padding-left: 10px;"  class="flex-c-m stext-101 cl2 size-115 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer"
						onclick="location.href='noticeList.do?currentPage=${currentPage}' ">
						공지사항 목록 
					</a>
					
					<a href="#none" style="padding-left: 10px;"  class="flex-c-m stext-101 cl2 size-115 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer"
						onclick="location.href='main.do' ">
						홈페이지 메인으로 
					</a>
=======
				<div class="flex-w flex-t p-t-27 p-b-33" style="float: right;">
					
					
					<div class="flex-c-m stext-106 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-10" onclick="location.href='noticeList.do?currentPage=${currentPage}' ">
						목록으로 
					</div>
					
					<div class="flex-c-m stext-106 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-10" onclick="location.href='main.do' ">
						메인으로 
					</div>
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-menu-desktop">
			<div class="btn-back-to-top" id="myBtn">
				<span class="symbol-btn-back-to-top"> 
				<i class="zmdi zmdi-chevron-up"></i>
				</span>
			</div>
	</div>
	
	<%@ include file="../main/body_footer.jspf"%>
	
	<%@ include file="../member/loginForm.jspf"%>

	<%@ include file="../store/store_ProductModal.jspf"%>

	<%@ include file="../main/script.jspf"%>
	
</body>


<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../main/head.jspf"%>
<link rel="stylesheet" type="text/css" href="/resources/css/admin_import.css" />
<script src="/resources/js/jquery-1.10.2.min.js"></script>
<script src="/resources/js/admin_common.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/main/body_header.jspf"%>
<div class="admin_grp">
	<div class="admin_ct">
		<div class="movie_list">
			<h3 class="sub_tit">공지사항 상세보기 </h3>
			<div class="tbl_type_01" style="margin: 200px 200px 200px 200px">
				<table>
					<caption>번호,제목,글쓴이,날짜,조회를 나타내는 공지사항 표</caption>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">글번호</th>
							<td>${map.NOTICE_NO}</td>
						</tr>
						<tr>
							<th scope="row">글제목</th>
							<td>${map.NOTICE_SUB}</td>
						</tr>
						<tr>
							<th scope="row">작성일</th>
							<td><c:set var="TextValue" value="${map.NOTICE_REGDATE}"/>
								${fn:substring(TextValue,0,16)}
						</tr>
						<tr>
							<th scope="row">글내용</th>
							<td>${map.NOTICE_CONTENT}</pre></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="btn_type_03">
			<a href="#none" class="btn btnC_04 btnP_04" onclick="location.href='<%=cp%>/noticeList.do?currentPage=${currentPage}' ">
				<span>목록</span>
			</a>
		</div>
	</div>
</div>
	 --%>
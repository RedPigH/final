<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>FAQs</title>
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
			
			<div class="col-sm-10 col-lg-7 col-xl-7 m-lr-auto m-b-50">
<!-- 			<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm"> -->
				<h4 class="mtext-109 cl2 p-b-30" style="text-align: -webkit-center;"> FAQ 상세보기 </h4>
				
				<div class="filter-tope-group m-tb-10 m-lr-auto">
			   	    <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 "  onclick="refreshFAQpage()">전체 FAQ</button>
			        <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 " id="FAQ_TYPE1" value="영화예매" onClick="selectFaqType(1)">영화예매</button>
					<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 " id="FAQ_TYPE2" value="영화관" onClick="selectFaqType(2)">영화관</button>
					<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 " id="FAQ_TYPE3" value="상영관" onClick="selectFaqType(3)">상영관</button>
					<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 " id="FAQ_TYPE4" value="기타" onClick="selectFaqType(4)">기타</button>
				</div>
				
				
				<div class="flex-w flex-t bor12 p-b-13">
					<div class="size-208" style="margin-top: 20px">
						<span class="mtext-110 cl2" >분류 </span>
					</div>
					<div class="size-209" style="margin-top: 20px">
						<span class="mtext-110 cl2"> ${map.FAQ_TYPE} </span>
					</div>
				</div>
				
				<div class="flex-w flex-t bor12 p-b-13">
					<div class="size-208" style="margin-top: 20px">
						<span class="mtext-110 cl2" >질문 </span>
					</div>
					<div class="size-209" style="margin-top: 20px">
						<span class="mtext-110 cl2"> ${map.FAQ_SUB} </span>
					</div>
				</div>
				
				<div class="flex-w flex-t bor12 p-t-15 p-b-30">
					<div class="size-208 w-full-ssm">
						<span class="stext-110 cl2"> 내용 </span>
					 </div>
					<div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
						<p class="stext-111 cl6 p-t-2">
						${map.FAQ_CONTENT}
						</p>
						
					</div>
				</div>

				<div class="flex-w flex-t p-t-27 p-b-33" style="float: right;">
					
				
					<div class="flex-c-m stext-106 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-10" onclick="location.href='faqList.do?currentPage=${currentPage}' ">
						목록으로 
					</div>
					
					<div class="flex-c-m stext-106 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-10" onclick="location.href='main.do' ">
						메인으로 
					</div>
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
</html>

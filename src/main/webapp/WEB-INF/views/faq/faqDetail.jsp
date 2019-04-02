<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>FAQ</title>
<script src="/moviecube/resources/js/admin_common.js"></script>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
</head>

<body class="animsition" style="opacity: 1; animation-duration: 1500ms;">

<%@ include file="../main/body_header.jspf" %> 
	
	<div class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList.do">전체 FAQ</a>
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList1.do">영화 예매</a> 
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList2.do">영화관 </a>	
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList3.do">상영관 </a>	
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList5.do">기타 </a>		
				</div>
			</div>
			
			<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
<!-- 			<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm"> -->
				<h4 class="mtext-109 cl2 p-b-30"> FAQ Detail </h4>
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

				<div class="flex-w flex-t p-t-27 p-b-33">
					
					
					<a href="#none" style="padding-left: 10px;"  class="flex-c-m stext-101 cl2 size-115 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer"
						onclick="location.href='faqList.do?currentPage=${currentPage}' ">
						FAQ 목록 
					</a>
					
					<a href="#none" style="padding-left: 10px;"  class="flex-c-m stext-101 cl2 size-115 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer"
						onclick="location.href='main.do' ">
						홈페이지 메인으로  
					</a>
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

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>FAQ</title>
<script src="/moviecube/resources/js/admin_common.js"></script>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
</head>

<body class="animsition" style="opacity: 1; animation-duration: 1500ms;">

<%@ include file="../main/body_header.jspf" %> 
	
	<div class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList.do">전체 FAQ</a>
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList1.do">영화 예매</a> 
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList2.do">영화관 </a>	
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList3.do">상영관 </a>	
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList5.do">기타 </a>		
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
	
 --%>
<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>FAQ Detail</title>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<link rel="stylesheet" type="text/css" href="/moviecube/resources/css/admin_import.css" />
<script src="/moviecube/resources/js/jquery-1.10.2.min.js"></script>
<script src="/moviecube/resources/js/admin_common.js"></script>
</head>

<body class="animsition">
<%@ include file="/WEB-INF/views/main/body_header.jspf"%>
<div>
</div>
<div class="admin_grp" style="margin: 100px 200px 5px">
	<div class="admin_list">
		<ul>
			<li class="on"><a href="<%=cp%>/faqList.do">전체 FAQ</a></li>
			<li><a href="<%=cp%>/faqList1.do">영화예매 FAQ</a></li>
			<li><a href="<%=cp%>/faqList2.do">영화관 FAQ</a></li>
			<li><a href="<%=cp%>/faqList3.do">상영관 FAQ</a></li>
			<li><a href="<%=cp%>/faqList4.do">멤버쉽 FAQ</a></li>
			<li><a href="<%=cp%>/faqList5.do">기타 FAQ</a></li>
		</ul>
	</div>
	
	<div class="admin_ct">
		<div class="movie_list">
			<h3 class="sub_tit">FAQ 상세보기 </h3>
			<div class="tbl_type_01">
				<table>
					<caption>FAQ 상세보기</caption>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">글번호</th>
							<td>${map.FAQ_NO}</td>
						</tr>
						<tr>
							<th scope="row">타입</th>
							<td>${map.FAQ_TYPE}</td>
						</tr>
						<tr>
							<th scope="row">자주묻는질문</th>
							<td>${map.FAQ_SUB }</td>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td>${map.FAQ_CONTENT}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="btn_type_03">

			<a href="#none" style="padding-left: 10px;" class="btn btnC_04 btnP_04" onclick="location.href='<%=cp%>/faqList.do?currentPage=${currentPage}' ">
				<span>목록</span>
			</a>
		</div>
	</div>
</div> --%>
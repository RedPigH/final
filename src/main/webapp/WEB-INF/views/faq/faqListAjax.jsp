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
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList1.do">영화예매</a>
					<!-- 
					<a href="javascript:void(0); class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" onClick="selectFaqType()">영화예매
					<input type="hidden" id="FAQ_TYPE" name="FAQ_TYPE" value="영화 예매"></a>
					-->
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList2.do">영화관 </a>
					<!-- 
					<a href="javascript:void(0); class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" onClick="selectFaqType()">영화 예매
					<input type="hidden" id="FAQ_TYPE" name="FAQ_TYPE" value="영화관"></a>
					-->	
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList3.do">상영관 </a>	
					<!-- 
					<a href="javascript:void(0); class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" onClick="selectFaqType()">상영관
					<input type="hidden" id="FAQ_TYPE" name="FAQ_TYPE" value="상영관"></a>
					-->	
					<a class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" href="faqList5.do">기타 </a>
					<!-- 
					<a href="javascript:void(0); class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" onClick="selectFaqType()">기타
					<input type="hidden" id="FAQ_TYPE" name="FAQ_TYPE" value="기타"></a>
					-->			
				</div>
			</div>
			
			<div class="row" style="border-width:0px" >
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50"  id="faqList">
					<!-- <h3 class="stext-105 cl3">FAQ 목록</h3> -->
						<div class="wrap-table-shopping-cart" id="faqType">
							<table class="table-shopping-cart">
								<tbody>
								<tr class="table_head">
									<th class="column-1">글번호</th>
									<th class="column-2">분류</th>
									<th class="column-3">자주묻는질문</th>	
								</tr>
								
								<c:choose>
									<c:when test="${fn:length(faqList) > 0}">
            							<c:forEach items="${faqList}" var="row">
			
								<tr class="table_row" id="faqType">
									<td class="column-1">${row.FAQ_NO}</td>
									<td class="column-2">${row.FAQ_TYPE}</td>
									<td class="column-3"><a href="#this" name="FAQ_SUB">${row.FAQ_SUB}
									<input type="hidden" id="FAQ_NO" value="${row.FAQ_NO}"/></a></td>		
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
						
						<%-- <div class="paging">${pagingHtml}</div> --%>
					<form>
						<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
							<div class="size-198 respon6-next">
								<div class="rs1-select2 bor8 bg0">
							
								 <select  class="js-select2" name="searchNum">
									<option value="0">질문</option>
									<option value="1">내용</option>
								</select>
									<div class="dropDownSelect2"></div>
								</div>							
								
							</div>
							<input name="isSearch" class="stext-111 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5" type="text" placeholder="검색내용을 입력하세요">
							<span class="flex-c-m stext-102 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
								<input type="submit" value="검색" />
							</span>
						</div>
					</form>
						<div class="paging">${pagingHtml}</div>
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

<form id="commonForm" name="common"></form>

<script type="text/javascript">
	$(document).ready(function(){   
		$("a[name='FAQ_SUB']").on("click", function(e){ // 
        e.preventDefault();
        fn_openBoardDetail($(this));
       	});
    });
         
    function fn_openBoardDetail(obj){
        var comSubmit = new ComSubmit();
        comSubmit.setUrl("<c:url value='faqDetail.do'/>");
        comSubmit.addParam("FAQ_NO", obj.parent().find("#FAQ_NO").val());
        comSubmit.addParam("currentPage", "${currentPage}");
        comSubmit.submit();
    }
</script> 

</html>

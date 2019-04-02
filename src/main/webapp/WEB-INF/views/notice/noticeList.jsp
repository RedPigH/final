<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8" />
<<<<<<< HEAD
<title>FAQ</title>
=======
<title>공지사항</title>
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
<script src="/moviecube/resources/js/admin_common.js"></script>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
</head>

<body class="animsition" style="opacity: 1; animation-duration: 1500ms;">

<<<<<<< HEAD
<%@ include file="../main/body_header.jspf" %> 
	
	<div class="bg0 p-t-75 p-b-85">
		<div class="container">
		
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">	
				</div>
			</div>
			
			
			<div class="row" style="border-width:0px" >
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<h3 class="flex-c-m stext-101 cl0 size-112 bg1 bor14 hov-btn3 p-lr-15 trans-04 pointer">공지사항 </h3> 
						<div class="wrap-table-shopping-cart" style="margin: 10px">
							<table class="table-shopping-cart">
								<tbody>
								<tr class="table_head">
									<th class="column-1">글번호</th>
									<th class="column-2">제목</th>
									<th class="column-3">날짜</th>
									
								</tr>
								
								<c:choose>
									<c:when test="${fn:length(noticeList) > 0}">
            							<c:forEach items="${noticeList}" var="row">
			
								<tr class="table_row">
									<td class="column-1">${row.NOTICE_NO}</td>
									<td class="column-2"><a href="#this" name="NOTICE_SUB">${row.NOTICE_SUB}
									<input type="hidden" id="NOTICE_NO" value="${row.NOTICE_NO}"/></a></td>
									<td class="column-3"><c:set var="TextValue" value="${row.NOTICE_REGDATE}" />
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
						
						<%-- <div class="paging">${pagingHtml}</div> --%>
					<form>
						<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
							<div class="size-198 respon6-next">
								<div class="rs1-select2 bor8 bg0">
							
								 <select  class="js-select2" name="searchNum">
									<option value="0">제목</option>
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
=======
	<%@ include file="../main/body_header.jspf"%>


	<form class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="col-lg-10 col-xl-7 m-lr-auto m-t-50">
			<div class="flex-w flex-sb-m p-t-18 p-b-30 p-lr-50 p-lr-15-sm">
					<h4 class="mtext-109 cl2 p-b-30" style="padding-bottom: 0px">공지사항
					</h4>
				</div>
				</div>
				<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart" style="margin: 0 0 0 0;">
								<tbody>
									<tr class="table_head">
										<th class="column-1">글번호</th>
										<th class="column-2">제목</th>
										<th class="column-3">날짜</th>
									</tr>


									<c:choose>
										<c:when test="${fn:length(noticeList) > 0}">
											<c:forEach items="${noticeList}" var="row">

												<tr class="table_row" style="border-bottom: 1px dashed #edeaea;">
													<td class="column-1">${row.NOTICE_NO}</td>
													<td class="column-2"><a href="javascript:void(0);" name="NOTICE_SUB"
														style="color: #555;">${row.NOTICE_SUB} <input
															type="hidden" id="NOTICE_NO" value="${row.NOTICE_NO}" /></a></td>
													<td class="column-3"><c:set var="TextValue"
															value="${row.NOTICE_REGDATE}" />
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

						<div
							class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-l-40 p-lr-15-sm">
							<div class="flex-w flex-m m-r-20 m-tb-5">
								<div class="paging">${pagingHtml}</div>
							</div>

							<div class="flex-c-m stext-101 cl2 size-119 p-lr-15 trans-04 m-tb-10">
							<div class="rs1-select2 bor8 bg0" style="width: 100px">

									<select class="js-select2" name="searchNum">
										<option value="0">제목</option>
										<option value="1">내용</option>
									</select>
									<div class="dropDownSelect2"></div>
								</div>
								<input
									class="stext-104 cl2 plh4 size-117 bor4 p-lr-20 m-r-10 m-tb-5"
									type="text" name="isSearch" placeholder="제목,내용을 입력해주세요">
								<input type="submit" class="flex-c-m stext-106 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-10" value="검색">
									
							</div>
						</div>
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
					</div>
				</div>
			</div>
		</div>
<<<<<<< HEAD

	
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
    
            $("a[name='NOTICE_SUB']").on("click", function(e){ // 공지사항 제목 클릭
                e.preventDefault();
                fn_openBoardDetail($(this));
            });
        });
         
        function fn_openBoardDetail(obj){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='noticeDetail.do' />");
            comSubmit.addParam("NOTICE_NO", obj.parent().find("#NOTICE_NO").val());
            comSubmit.addParam("currentPage", "${currentPage}");
            comSubmit.submit();
        }
</script>  

</html>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>FAQ</title>
<%@ include file="/WEB-INF/views/main/head.jspf"%>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<link rel="stylesheet" type="text/css" href="/moviecube/resources/css/admin_import.css" />
<script src="/moviecube/resources/js/jquery-1.10.2.min.js"></script>
<script src="/moviecube/resources/js/admin_common.js"></script>

<script type="text/javascript">
	function selectFaqType(FAQ_TYPE){
		$.ajax({
			type: "POST",
			url: "<c:url value='/admin/selectFaqType.do'/>",
			dateType:"json",
			date:{param:FAQ_TYPE},
			success:function(data){
				$("#faqType").remove().end().append("");
				
				for(var idx = 0; idx < data.result.length; idx++){
					  $("#faqType").append("<tr><td>''"+data.result[idx].FAQ_NO+"'>"+data.result[idx].FAQ_TYPE+"</option>")
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				        alert("오류가 발생하였습니다.");
				  }                     
			});
				}

</script>

</head>

<body class="animsition">
<%@ include file="/WEB-INF/views/main/body_header.jspf"%>
<div>
</div>
<div class="admin_grp" style="margin: 100px 100px 5px">
	<div class="admin_list">
		<ul>
			<li><a href="javascript:void(0);" onclick="selectFaqType()" value="영화얘매">영화예매</a></li>
			<li><a onclick="selectFaqType()" value="영화관">영화관</a></li>
			<li>상영관</li>
			<li>멤버쉽</li>
			<li>기타</li>
		</ul>
	</div>
	
	<div class="admin_ct">
		<h3 class="sub_tit">전체 FAQ 안내</h3>
		<div class="tbl_type_02">
			<table>
				<caption>번호,제목,글쓴이,날짜,조회를 나타내는 공지사항 표</caption>
				<colgroup>
					<col style="width:10%;"/>
					<col style="width:80%;"/>
					<col style="width:10%;"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">자주묻는질문</th>
						<th scope="col">분류</th>
					</tr>
				</thead>
				<tbody>
					
				<c:choose>
					<c:when test="${fn:length(faqList) > 0}">
            			<c:forEach items="${faqList}" var="row">
						<tr class="faqType">
							<td>${row.FAQ_NO}</td>
							<td class="subject"><a href="#this" name="FAQ_SUB">${row.FAQ_SUB}
							<input type="hidden" id="FAQ_NO" value="${row.FAQ_NO}"/></a></td>
							<td>${row.FAQ_TYPE}</td>				
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
				
<!-- 	<div class="search_form">
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
		</div> -->
		
		<div class="paging">${pagingHtml}</div>
	</div>
</div>
<div class="admin_grp" style="margin: 50px 50px 50px">
	</div>

<%@ include file="/WEB-INF/views/main/script.jspf"%>
<%@ include file="/WEB-INF/views/main/body_footer.jspf"%>
<%@ include file="/WEB-INF/views/member/loginForm.jspf"%>

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

</html>		 --%>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/main/head.jspf"%>

<link rel="stylesheet" type="text/css" href="/moviecube/resources/css/admin_import.css" />
<script src="/moviecube/resources/js/jquery-1.10.2.min.js"></script>
<script src="/moviecube/resources/js/admin_common.js"></script>
<script src="/moviecube/resources/js/main.js"></script>

</head>
<body class="animsition">
	<%@ include file="/WEB-INF/views/main/body_header.jspf"%>
	
	<header class="header-v4">
	<div class="container-menu-desktop">

	<!-- Back to top -->
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top"> <i
			class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
	</div>
	</header>
	
<div class="admin_grp" style="margin: 0px 200px 50px 100px">
	<div class="admin_ct">
		<h3 class="sub_tit">공지사항</h3>
		<div class="tbl_type_02">
			<table>
				<caption>번호,제목,글쓴이,날짜,조회를 나타내는 공지사항 표</caption>
				<colgroup>
					<col style="width:15%;" />
					<col style="width:70%;" />
					<col style="width:15%;" />
				
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">날짜</th>
					</tr>
				</thead>
				<tbody>
					
				<c:choose>
					<c:when test="${fn:length(noticeList) > 0}">
            			<c:forEach items="${noticeList}" var="row">
						<tr>
							<td>${row.NOTICE_NO}</td>
							<td><a href="javascript:doDisplay();">${row.NOTICE_SUB}</a><br/><br/>
								<div id="myDIV">나나나</div>
							<td class="subject"><a href="#this" name="NOTICE_SUB">${row.NOTICE_SUB}
							<input type="hidden" id="NOTICE_NO" value="${row.NOTICE_NO}"/></a></td>
							<td class="menu">
									<a>${row.NOTICE_SUB}<input type="hidden" id="NOTICE_CONTENT" value="${row.NOTICE_CONTENT}"/></a>			 
           						 	<ul class="hide">${row.NOTICE_CONTENT}</ul>
							 </td>
							<td><c:set var="TextValue" value="${row.NOTICE_REGDATE}"/> ${fn:substring(TextValue,0,16)}</td>
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
		
		<div class="paging">${pagingHtml}</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/main/script.jspf"%>
<%@ include file="/WEB-INF/views/main/body_footer.jspf"%>
<%@ include file="/WEB-INF/views/member/loginForm.jspf"%>
=======
	</form>





	<div class="container-menu-desktop">
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top"> <i
				class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
	</div>

	<%@ include file="../main/body_footer.jspf"%>

	<%@ include file="../member/loginForm.jspf"%>

	<%@ include file="../store/store_ProductModal.jspf"%>

	<%@ include file="../main/script.jspf"%>
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9

</body>

<form id="commonForm" name="common"></form>

<script type="text/javascript">
<<<<<<< HEAD
        $(document).ready(function(){
    
            $("a[name='NOTICE_SUB']").on("click", function(e){ // 영화제목, 영화포스터 클릭
                e.preventDefault();
                fn_openBoardDetail($(this));
            });
        });
         
        function fn_openBoardDetail(obj){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='noticeDetail.do' />");
            comSubmit.addParam("NOTICE_NO", obj.parent().find("#NOTICE_NO").val());
            comSubmit.addParam("currentPage", "${currentPage}");
            comSubmit.submit();
        }
</script> 

</html> --%>
=======
	$(document).ready(function() {

		$("a[name='NOTICE_SUB']").on("click", function(e) { // 공지사항 제목 클릭
			e.preventDefault();
			fn_openBoardDetail($(this));
		});
	});

	function fn_openBoardDetail(obj) {
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='noticeDetail.do' />");
		comSubmit.addParam("NOTICE_NO", obj.parent().find("#NOTICE_NO").val());
		comSubmit.addParam("currentPage", "${currentPage}");
		comSubmit.submit();
	}
</script>

</html>
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9

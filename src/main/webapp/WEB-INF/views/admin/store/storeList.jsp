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
	<h1><a href="<%=cp %>/admin/storeList.do">MovieCube Administrator - Store List</a></h1>
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
			<li class="on"><a href="<%=cp%>/admin/storeList.do">STORE</a>
			<li><a href="<%=cp%>/admin/eventList.do">EVENT</a>
			<li><a href="<%=cp%>/admin/noticeList.do">공지사항</a></li>
			<li><a href="<%=cp%>/admin/faqList.do">FAQ</a></li>
			<li><a href="<%=cp%>/admin/qnaList.do">Q&amp;A</a></li>
			<li><a href="<%=cp%>/admin/memberList.do">회원정보</a></li>
		</ul>
	</div>
	<div class="admin_ct">
		<div class="movie_list">
			<h3 class="sub_tit">STORE</h3>
			<ul>
			<c:choose>
			<c:when test="${fn:length(storeList) > 0}">
            	<c:forEach items="${storeList}" var="row">
					
				<li><a href="#this" name="store" class="list"> 
				<img src="<%=cp%>/resources/upload/store/${row.IMAGE_SAVNAME}" alt="스토어이미지" /> 
				<input type="hidden" id="STORE_NO" value="${row.STORE_NO}">
				<input type="hidden" id="currentPage" value="${currentPage}">
				<span class="detail">상세보기</span>
						<div class="explan">
							<p>
								<strong>상품이름</strong> : ${row.STORE_ITEM}
							</p>
							<p>
								<strong>판매가격</strong> : ${row.STORE_PRICE}
							</p>
						</div>
				</a> 
				</li>
				</c:forEach>
				</c:when>
				<c:otherwise>
					조회된 결과가 없습니다.
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
		<%-- <c:if test="${session_member_grade == 1}"> --%>
			<div class="btn_type_03">
				<a href="#this" class="btn btnC_01 btnP_04" id="write">
					<span>상품 등록하기</span>
				</a>
			</div>
		<%-- </c:if> --%>
		
		<div class="search_form">
			<form>
				<div class="inner">
					<select class="slct w100" name="searchNum">
						<option value="0">상품이름</option>
					</select>
					<input class="txt w100" type="text" name="isSearch" />
					<span class="btn btnC_04 btnP_04">
						<input type="submit" value="검색" />
					</span>
				</div>
			</form>	
		</div>
		
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
             
            $("a[name='store']").on("click", function(e){ // 영화제목, 영화포스터 클릭
                e.preventDefault();
                fn_openBoardDetail($(this));
            });
        });
         
        function fn_openBoardWrite(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='storeWriteForm.do' />");
            comSubmit.submit();
        }
         
        function fn_openBoardDetail(obj){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='storeDetail.do' />");
            comSubmit.addParam("STORE_NO", obj.parent().find("#STORE_NO").val());
            comSubmit.addParam("currentPage", "${currentPage}");
            comSubmit.submit();
        }
        
    </script> 
</body>
</html>
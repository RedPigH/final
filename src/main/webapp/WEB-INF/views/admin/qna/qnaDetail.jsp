<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<% String cp = request.getContextPath(); %>
<%  pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");
%>
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
	function qnaDelete() {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			location.href = 'qnaDelete.do?QNA_NO=${map.QNA_NO}&currentPage=${currentPage}';
		} else { //취소
			return;
		}
	}
</script>

</head>

<body>

<div class="admin">
	<div class="logo">
	<h1><a href="<%=cp %>/admin/qnaList.do">MovieCube Administrator - Q&amp;A Detail </a></h1>
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
			<li class="on"><a href="<%=cp%>/admin/qnaList.do">Q&amp;A</a></li>
			<li><a href="<%=cp%>/admin/memberList.do">회원정보</a></li>
		</ul>
	</div>
	
	<div class="admin_ct">
		<div class="movie_list">
			<h3 class="sub_tit">Q&amp;A 상세보기 </h3>
			<div class="tbl_type_01">
				<table>
					<caption>Q&amp;A 상세보기</caption>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">글번호</th>
							<td>${map.QNA_NO}</td>
						</tr>
						<tr>
							<th scope="row">작성자</th>
							<td>${map.QNA_ID}</td>
						</tr>
						<tr>
							<th scope="row">작성시간</th>
							<td><c:set var="TextValue" value="${map.QNA_REGDATE}"/>
									${fn:substring(TextValue,0,19)}</td>
						</tr>
						<tr>
							<th scope="row">제목</th>
							<td>${map.QNA_SUB}</td>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td><pre>${fn:replace(map.QNA_CONTENT,cn,br)}</pre></td>
						</tr>
						
						<c:if test= "${map.QNA_FILE_NO > 0}">  
 						<tr>
							<th scope="row">첨부파일</th>
							<td colspan="3"><input type="hidden" id="QNA_FILE_NO" value="${map.QNA_FILE_NO }">
							<a href="#this" name="file">${map.QNA_ORGNAME }</a></td>
						</tr>
						<tr>
							<td>
							<img src="<%=cp%>/resources/upload/qna/${map.QNA_SAVNAME}"/></td>
						</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="btn_type_03">
			<a href="#this" id="update" class="btn btnC_04 btnP_04"><span>수정</span></a>
			<a href="#this" id="reply" class="btn btnC_04 btnP_04" style="padding-left: 10px;"><span>답변</span></a>
			<a href="#this" id="list" class="btn btnC_04 btnP_04" style="padding-left: 10px;"><span>목록</span></a>
			<a href="#this" id="delete" class="btn btnC_04 btnP_04" style="padding-left: 10px;"><span>삭제</span></a>
		</div>
	</div>
</div>

<form id="commonForm" name="common"></form>

<script type="text/javascript">

		$(document).ready(function() {
			$("#list").on("click", function(e) { /* 목록으로 */
				e.preventDefault();
				fn_openBoardList();
			});

			$("#update").on("click", function(e) { /* 저장하기  */
				e.preventDefault();
				fn_openBoardUpdate();
			});

			$("#reply").on("click", function(e) { /* 답변달기 */
				e.preventDefault();
				fn_openBoardReply();
			});

			$("a[name='file']").on("click", function(e) {/*파일 이름  */
				e.preventDefault();
			});
			
			$("#delete").on("click", function(e) { /* 답변달기 */
				e.preventDefault();
				fn_deleteBoard();
			});

		});

		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/admin/qnaList.do'/>");
			comSubmit.submit();
		}

		function fn_openBoardUpdate() {
			var qna_no = "${map.QNA_NO}";
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/admin/qnaModifyForm.do'/>")
			comSubmit.addParam("QNA_NO", qna_no);
			comSubmit.submit();
		}

		function fn_openBoardReply() {
			var qna_no = "${map.QNA_NO}";
			var qna_no1 = "${map.QNA_NO}";
			var qna_sub = "${map.QNA_SUB }";
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/admin/qnaReplyForm.do'/>");
			comSubmit.addParam("QNA_NOM", qna_no1);
     		comSubmit.addParam("QNA_NO", qna_no); 
			comSubmit.addParam("QNA_SUB", qna_sub);
			comSubmit.submit();
		}
		function fn_deleteBoard() {
			var comSubmit = new ComSubmit();
			var qna_no = "${map.QNA_NO}";
			comSubmit.setUrl("<c:url value='/admin/qnaDelete.do'/>");
			comSubmit.addParam("QNA_NO", qna_no);
			comSubmit.submit();
		}
</script>
</body>
</html>



<%--		
		<div class="btn_type_03">
 			
			<span class="btn btnC_04 btnP_04">
				<input type="button" onclick="location.href='qnaModifyForm.do?QNA_NO=${map.QNA_NO}'" value="수정" />
			</span>
			<span class="btn btnC_04 btnP_04">
				<input type="button" onclick="location.href='qnaReplyForm.do?QNA_NO=${map.QNA_NO}'" value="답변" />
			</span>
			<span class="btn btnC_04 btnP_04" style="padding-left: 10px;">
				<input type="button" onclick="qnaDelete()" value="삭제" />
			</span>
			<a href="#none" style="padding-left: 10px;" class="btn btnC_04 btnP_04" onclick="location.href='<%=cp%>/admin/qnaList.do?currentPage=${currentPage}' ">
				<span>목록</span>
			</a>
		</div>
	</div>
</div> --%>
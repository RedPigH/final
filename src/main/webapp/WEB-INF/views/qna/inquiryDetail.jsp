<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% String cp = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
</head>
<body>
	<table class="board_view">
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="35%" />
		</colgroup>
		<caption>QnA 상세♥</caption>
		<tbody>
			<tr>
				<th scope="row">글 번호</th>
				<td>${map.QNA_NO }</td>
			</tr>
			<tr>
				<th scope="row">작성자</th>
				<td>${map.QNA_ID }</td>
			</tr>
			<tr>
				<th scope="row">작성시간</th>
				<td>${map.QNA_REGDATE }</td>
			</tr>
			<tr>
				<th scope="row">제목</th>
				<td scope="3">${map.QNA_SUB }</td>
			</tr>
			<tr>
				<td colspan="4">${map.QNA_CONTENT }</td>
			</tr>
			<tr>
			
			<c:if test="${map.QNA_FILE_NO > 0 }"> 
 			<tr>
				<th scope="row">첨부파일</th>
				<td colspan="3"><input type="hidden" id="QNA_FILE_NO" value="${map.QNA_FILE_NO }">
				<a href="#this" name="file">${map.QNA_ORGNAME }</a>
				</td>
			</tr>
				<tr>
					<td>
						<img src="<%=cp%>/resources/upload/qna/${map.QNA_SAVNAME}"/>
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<br />

	<a href="#this" class="btn" id="list">목록으로</a>
	<a href="#this" class="btn" id="reply">답변달기</a>
	<a href="#this" class="btn" id="update">수정하기</a>


	<%@ include file="/WEB-INF/include/include-body.jspf"%>
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

		});

		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/qna/adminInquiryList.do'/>");
			comSubmit.submit();
		}

		function fn_openBoardUpdate() {
			var qna_no = "${map.QNA_NO}";
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/qna/adminInquiryModifyForm.do'/>")
			comSubmit.addParam("QNA_NO", qna_no);
			comSubmit.submit();
		}

		function fn_openBoardReply() {
			var qna_no = "${map.QNA_NO}";
			var qna_no1 = "${map.QNA_NO}";
			var qna_sub = "${map.QNA_SUB }";
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/qna/adminInquiryReplyForm.do'/>");
			comSubmit.addParam("QNA_NOM", qna_no1);
     		comSubmit.addParam("QNA_NO", qna_no); 
			comSubmit.addParam("QNA_SUB", qna_sub);
			comSubmit.submit();
		}
		/* 	function fn_deleteBoard() {
			var comSubmit = new ComSubmit();
			var qna_no = "${map.QNA_NO}";
			comSubmit.setUrl("<c:url value='/qna/adminInquiryDelete.do'/>");
			comSubmit.addParam("QNA_NO", qna_no);
			comSubmit.submit();
			// im babo */
	</script>
</body>
</html>
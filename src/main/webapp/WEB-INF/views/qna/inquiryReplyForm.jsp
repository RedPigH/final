<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%
	String cp = request.getContextPath();
%>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
</head>
<body>
	<form id="frm">
		<table class="board_view">
			<colgroup>
				<col width="15%">
				<col width="*" />
			</colgroup>
			<caption>답변 작성♥ ${map.QNA_NOM}</caption>
			<tbody>
				<tr>
					<th scope="row">제목</th>
					<td><input type="text" id="QNA_SUB" name="QNA_SUB"
						class="wdp_90" value="[답변]${map.QNA_SUB}"></td>
				</tr>
				<tr>
					<th scope="row">작성자</th>
					<td><input type="text" id="QNA_ID" name="QNA_ID"
						class="wdp_90"></td>
				</tr>
				<tr>
					<td colspan="2" class="view_text"><textarea rows="20"
							cols="100" title="내용" id="QNA_CONTENT" name="QNA_CONTENT"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<a href="#this" class="btn" id="write">작성하기</a> <a href="#this"
			class="btn" id="list">목록으로</a>

	</form>

	<%@ include file="/WEB-INF/include/include-body.jspf"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#list").on("click", function(e) {
				e.preventDefault();
				fn_openBoardList();
			});
		});

		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/qna/adminInquiryList.do'/>");
			comSubmit.submit();
		}

		$(document).ready(function() {
			$("#list").on("click", function(e) {/* 목록dm로 버튼 */
				e.preventDefault();
				fn_openBoardList();
			});

			$("#write").on("click", function(e) {
				e.preventDefault();
				fn_insertBoard();
			});
		});

		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/qna/adminInquiryList.do'/>");
			comSubmit.submit();
		}

		function fn_insertBoard() {
			var comSubmit = new ComSubmit("frm");
			var qna_no = "${map.QNA_NOM}";
			comSubmit.setUrl("<c:url value='/qna/adminInquiryReply.do'/>");
			comSubmit.addParam("REF", qna_no);
			alert(qna_no);
			comSubmit.submit();
		}
	</script>
</body>
</html>
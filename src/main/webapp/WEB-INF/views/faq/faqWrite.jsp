<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
</head>
<body>
	<form id="frm" action="/moviecube/faq/adminFaqWrite.do">
		<table class="board_view">
			<colgroup>
				<col width="15%">
				<col width="*" />
			</colgroup>
			<caption>FAQ 작성</caption>
			<tbody>


				<tr>
					<th><select id="FAQ_TYPE" name="FAQ_TYPE">
							<option value="">타입선택</option>
							<option value="영화관">영화관</option>
							<option value="영화예매">영화예매</option>
							<option value="멤버십">멤버십</option>
							<option value="기타">기타</option>
					</select></th>
					<th scope="row">제목</th>
					<td><input type="text" id="FAQ_SUB" name="FAQ_SUB"
						class="wdp_100"></input></td>
				</tr>

				<tr>
					<td colspan="2" class="view_text"><textarea rows="20"
							cols="100" title="내용" id="FAQ_CONTENT" name="FAQ_CONTENT"></textarea>
					</td>
				</tr>
			</tbody>
		</table>

		<input type="submit" value="작성하기" class="btn">
		<!-- <a href="#this" class="btn" id="write">작성하기</a> --> 
		<a href="#this" class="btn" id="list">목록으로</a>
	</form>

	<%@ include file="/WEB-INF/include/include-body.jspf"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#list").on("click", function(e) { //목록으로 버튼
				e.preventDefault();
				fn_openFaqList();
			});

			$("#write").on("click", function(e) { /* 작성하기 버튼 */
				e.preventDefault();
				fn_faqWriteBoard();
			});
		});

		function fn_openFaqList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/faq/adminFaqList.do'/>");
			comSubmit.submit();
		}

		function fn_faqWriteBoard() {
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/faq/adminFaqWrite.do'/>");
			comSubmit.submit();
		}
	</script>
</body>
</html>
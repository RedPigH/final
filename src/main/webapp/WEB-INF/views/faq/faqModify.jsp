<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
</head>
<body>
	<form id="frm">
		<table class="board_view">
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="35%" />
			</colgroup>
			<caption>FAQ 상세</caption>
			<tbody>
				<tr>
					<th scope="row">글 번호</th>
					<td>${map.FAQ_NO } <input type="hidden" id="FAQ_NO"
						name="FAQ_NO" value="${map.FAQ_NO }">
					</td>
				<tr>
					<th><select id="FAQ_TYPE" name="FAQ_TYPE">
							<option value="">타입선택</option>
							<option value="영화관">영화관</option>
							<option value="영화예매">영화예매</option>
							<option value="멤버십">멤버십</option>
							<option value="기타">기타</option>
					</select></th>
					<th scope="row">질문</th>
					<td colspan="3"><input type="text" id="FAQ_SUB" name="FAQ_SUB"
						class="wdp_90" value="${map.FAQ_SUB }" /></td>
				</tr>
				<tr>
					<td colspan="4" class="view_text"><textarea rows="20"
							cols="100" title="내용" id="FAQ_CONTENT" name="FAQ_CONTENT">${map.FAQ_CONTENT }</textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

		<a href="#this" class="btn" id="list">목록으로</a>
	<a href="#this" class="btn" id="update">저장하기</a>
	<a href="#this" class="btn" id="delete">삭제하기</a>

<%@ include file="/WEB-INF/include/include-body.jspf"%>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#list").on("click", function(e) { /* 목록으로 버튼 */
			e.preventDefault();
			fn_openBoardList();
		});
		
		$("#update").on("click", function(e) { //저장하기 버튼
			e.preventDefault();
			fn_updateBoard();
		});
		
		$("#delete").on("click", function(e) { //삭제하기 버튼
			e.preventDefault();
			fn_deleteBoard();
		});
	});

	function fn_openBoardList() {
		var comSubmit = new ComSubmit();
	comSubmit.setUrl("<c:url value='/faq/adminFaqList.do'/>");
	comSubmit.submit();
}

function fn_updateBoard(){
	var comSubmit = new ComSubmit("frm");
	comSubmit.setUrl("<c:url value='/faq/adminFaqModify.do'/>");
	
	comSubmit.submit();
}

function fn_deleteBoard(){
	var comSubmit = new ComSubmit();
	comSubmit.setUrl("<c:url value='/faq/adminFaqDelete.do'/>");
	comSubmit.addParam("FAQ_NO", $("#FAQ_NO").val());
	comSubmit.submit();
}
</script>
</body>
</html>
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
			<caption>게시글 상세</caption>
			<tbody>
				<tr>
					<th scope="row">글 번호</th>
					<td>${map.NOTICE_NO }
					<input type="hidden" id="NOTICE_NO" name="NOTICE_NO" value="${map.NOTICE_NO }">
					</td>
				<tr>
					<th scope="row">작성시간</th>
					<td>${map.NOTICE_REGDATE }</td>
				</tr>
				<tr>
					<th scope="row">제목</th>
					<td colspan="3"><input type="text" id="NOTCE_SUB"
						name="NOTICE_SUB" class="wdp_90" value="${map.NOTICE_SUB }" /></td>
				</tr>
				<tr>
					<td colspan="4" class="view_text"><textarea rows="20"
							cols="100" title="내용" id="NOTICE_CONTENT" name="NOTICE_CONTENT">${map.NOTICE_CONTENT }</textarea>
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
		comSubmit.setUrl("<c:url value='/notice/adminNoticeList.do'/>");
		comSubmit.submit();
	}
	
	function fn_updateBoard(){
		var comSubmit = new ComSubmit("frm");
		comSubmit.setUrl("<c:url value='/notice/adminNoticeModify.do'/>");
		comSubmit.submit();
	}
	
	function fn_deleteBoard(){
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='/notice/adminNoticeDelete.do'/>");
		comSubmit.addParam("NOTICE_NO", $("#NOTICE_NO").val());
		comSubmit.submit();
	}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet" type="text/css" href="/moviecube/css/ui.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="/moviecube/resources/js/jquery-3.2.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/common2.js'/>" charset="utf-8"></script>

<script>
	function delete_file() {
		$("#fileid").remove();
		/* $("#fileTD2").html("<input type='file' id='QNA_FILE_NO' class='QNA_FILE_NO' value='${map.QNA_FILE_NO }'>"); */
		$("#fileTD2")
				.html(
						"<input type='file' name ='file' id='QNA_FILE_NO' class='QNA_FILE_NO' value='${map.QNA_FILE_NO }'>");
	}
</script>
</head>
<body>
	<form id="frm" name="frm" enctype="multipart/form-data">
		<table class="board_view">
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="35%" />
			</colgroup>
			<caption>QNA 상세♥</caption>
			<tbody>
				<tr>
					<th scope="row">글 번호</th>
					<td>${map.QNA_NO }<input type="hidden" id="QNA_NO"
						name="QNA_NO" value="${map.QNA_NO }">
					</td>
				</tr>
				<tr>
					<th scope="row">작성자</th>
					<td>${map.QNA_ID }<input type="hidden" id="QNA_ID"
						name="QNA_ID" value="${map.QNA_ID }">
					</td>
				</tr>
				<tr>
					<th scope="row">작성시간</th>
					<td>${map.QNA_REGDATE }</td>
				</tr>
				<tr>
					<th scope="row">제목</th>
					<td colspan="3"><input type="text" id="QNA_SUB" name="QNA_SUB"
						class="wdp_90" value="${map.QNA_SUB }" /></td>
				</tr>
				<tr>
					<td colspan="4" class="view_text"><textarea rows="20"
							cols="100" title="내용" id="QNA_CONTENT" name="QNA_CONTENT">${map.QNA_CONTENT }</textarea>
					</td>
				</tr>
				<tr id="deleteTest">
					<th scope="row">첨부파일</th>
					<td id="file_TD"><c:choose>
							<c:when test="${map.QNA_FILE_NO > 0 }">
								<input type="button" onclick="delete_file();" id="fileid"
									class="fileclass" value="파일삭제">
							</c:when>
							<c:otherwise>
								<input type="file" name="file" id='QNA_FILE_NO'
									class='QNA_FILE_NO' value="파일수정">
							</c:otherwise>
						</c:choose> <!-- <input type="button" onclick="delete_file();" id="fileid" class="fileclass" value="파일삭제"> -->
					</td>
					<td colspan="3" id="fileTD2"><input type="hidden"
						id="QNA_FILE_NO" class="QNA_FILE_NO" value="${map.QNA_FILE_NO }">
						<a href="#this" name="file"> ${map.QNA_FILE_NO }
							${map.QNA_ORGNAME }</a></td>
				</tr>

				<tr>
					<!-- 여기서부터 추가 -->
			</tbody>
		</table>

	</form>

	<!-- 		<input type="file" name="file"> <br /> <br />  이거 추가시켜야함 원래대로 돌릴라면...-->

	<a href="#this" class="btn" id="list">목록으로</a>
	<a href="#this" class="btn" id="update">저장하기</a>
	<a href="#this" class="btn" id="delete">삭제하기</a>

	<%@ include file="/WEB-INF/include/include-body.jspf"%>
	<script type="text/javascript">
		var gfv_count = '${fn:length(list)+1}';
		$(document).ready(function() {
			$("#list").on("click", function(e) { /* 목록으로 버튼 */
				e.preventDefault();
				fn_openBoardList();
			});

			$("#update").on("click", function(e) { //저장하기 버튼
				e.preventDefault();
				fn_updateBoard();

			});

			$("#addFile").on("click", function(e) { //파일 추가버튼
				e.preventDefault();
				fn_addFile();
			});

			$("#delete").on("click", function(e) { //삭제하기 버튼
				e.preventDefault();
				fn_deleteBoard();
			});

		});

		function fn_openBoardList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/qna/adminInquiryList.do'/>");
			comSubmit.submit();
		}

		function fn_updateBoard() {
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/qna/adminInquiryModify.do'/>");

			var qna_file_no = "${map.QNA_FILE_NO}";
			var qna_orgname = "${map.QNA_ORGNAME}";

			comSubmit.addParam("QNA_FILE_NO", qna_file_no);
			comSubmit.addParam("QNA_ORGNAME", qna_orgname);

			comSubmit.submit();

		}

		function fn_deleteBoard() {
			var comSubmit = new ComSubmit();
			var qna_no = "${map.QNA_NO}";
			comSubmit.setUrl("<c:url value='/qna/adminInquiryDelete.do'/>");
			comSubmit.addParam("QNA_NO", qna_no);
			comSubmit.submit();
		}

		function fn_addFile() {
			var str = "<p>" + "<input type='file' id='file_" + (gfv_count)
					+ "' name='file_" + (gfv_count) + "'>"
					+ "<a href='#this' class='btn' id='delete_" + (gfv_count)
					+ "' name='delete_" + (gfv_count) + "'>삭제</a>" + "</p>";
			$("#fileDiv").append(str);
			$("#delete_" + (gfv_count++)).on("click", function(e) {/* //삭제버튼 */
				e.preventDefault();
				fn_deleteFile($(this));
			});

		}
		function fn_deleteFile(obj) {
			obj.parent().remove();
		}
	</script>
</body>
</html>
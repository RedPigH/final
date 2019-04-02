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
<script>
function delete_file() {
	$("#fileid").remove();
	/* $("#fileTD2").html("<input type='file' id='QNA_FILE_NO' class='QNA_FILE_NO' value='${map.QNA_FILE_NO }'>"); */
	$("#fileTD2").html("<input type='file' name ='file' id='QNA_FILE_NO' class='QNA_FILE_NO' value='${map.QNA_FILE_NO }'>");
}
</script>
</head>

<body>

<div class="admin">
	<div class="logo">
	<h1><a href="<%=cp %>/admin/qnaList.do">MovieCube Administrator - Q&amp;A Modify</a></h1>
	</div>
</div>

<div class="admin_grp">
	<div class="admin_list">
		<ul>
			<li><a href="<%=cp%>/admin/movieList.do">영화 정보</a></li>
			<li><a href="<%=cp%>/admin/cinemaList.do">영화관</a></li>
			<li><a href="<%=cp%>/admin/screenList.do">상영관</a></li>
			<li><a href="<%=cp%>/admin/insertSeatForm.do">영화 좌석</a></li>
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
		<h3 class="sub_tit">Q&amp;A 수정</h3>
		<form id="frm" name="frm" enctype="multipart/form-data">
			<div class="tbl_type_01">
				<table>
					<caption>Q&amp;A</caption>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					<tbody>
						
						<tr>
							<th scope="row">글번호</th>
							<td>${map.QNA_NO}
								<input type="hidden" id="QNA_NO" name="QNA_NO" value="${map.QNA_NO}"/>
								<font color="red"></font></td>
						</tr>
						
						<tr>
							<th scope="row">작성자</th>
							<td>
								<input type="text" class="txt w200" id="QNA_ID" name="QNA_ID" value="${map.QNA_ID}"/>
								<font color="red"></font>
							</td>
						</tr>
						
						<tr>
							<th scope="row">작성시간</th>
							<td><c:set var="TextValue" value="${map.QNA_REGDATE}"/>
									${fn:substring(TextValue,0,19)}</td>
						</tr>
						
						<tr>
							<th scope="row">제목</th>
							<td>
								<input type="text" class="txt w200" id="QNA_SUB" name="QNA_SUB" value="${map.QNA_SUB}"/>
								<font color="red"></font>
							</td>	
						<tr>
						
							<th scope="row">내용</th>
							<td>
								<div class="textarea_grp">
									<textarea name="QNA_CONTENT">${map.QNA_CONTENT}</textarea>
								</div>
								<font color="red"></font>
							</td>
						</tr>
<%-- 						
						<tr id="deleteTest">
					
							<th scope="row">첨부파일</th>
							<td id="file_TD">
							<c:choose>
								<c:when test="${map.QNA_FILE_NO > 0 }">
									<input type="button" onclick="delete_file();" id="fileid" class="fileclass" value="파일삭제">
								</c:when>
								<c:otherwise>
									<input type="file" name ="file" id='QNA_FILE_NO' class='QNA_FILE_NO' value="파일수정">
								</c:otherwise>
							</c:choose>
							</td>
							
							<td colspan="3" id="fileTD2">
								<input type="hidden" id="QNA_FILE_NO" class="QNA_FILE_NO" value="${map.QNA_FILE_NO }"> 
							<a href="#this" name="file"> ${map.QNA_FILE_NO } ${map.QNA_ORGNAME }</a></td>
						</tr>  
--%>						
					</tbody>
				</table>
			</div>
			
			<div class="btn_type_03">
				<a href="#this" class="btn btnC_04 btnP_04" id="write">
					<span>수정하기</span>
				</a>
			
				<a href="#this" class="btn btnC_04 btnP_04" id="list" style="padding-left: 10px;">
					<span>목록으로</span>
				</a>
			</div>
		</form>
	</div>
</div>

<form id="commonForm" name="common"></form>

<script type="text/javascript">

        var gfv_count = '${fn:length(list)+1}';

        $(document).ready(function(){
            $("#list").on("click", function(e){ //목록으로 버튼
                e.preventDefault();
                fn_openBoardList();
            });
             
            $("#write").on("click", function(e){ //수정하기 버튼
                e.preventDefault();
                fn_insertBoard();
            });
/*             
            $("#addFile").on("click", function(e) { //파일 추가버튼
				e.preventDefault();
				fn_addFile();
			});
 */        
 		});
         
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/admin/qnaList.do' />");
            comSubmit.submit();
        }
         
        function fn_insertBoard(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='/admin/qnaModify.do' />");
            
            var qna_file_no = "${map.QNA_FILE_NO}";
//			var qna_orgname = "${map.QNA_ORGNAME}";
			
            comSubmit.addParam("QNA_NO", "${map.QNA_NO}");
//          comSubmit.addParam("QNA_ORGNAME", qna_orgname);
            comSubmit.submit();
        }     
/*         
        function fn_addFile() {
			var str = "<p>" + "<input type='file' id='file_" + (gfv_count)
					+ "' name='file_" + (gfv_count) + "'>"
					+ "<a href='#this' class='btn' id='delete_" + (gfv_count)
					+ "' name='delete_" + (gfv_count) + "'>삭제</a>" + "</p>";
			$("#fileDiv").append(str);
			$("#delete_" + (gfv_count++)).on("click", function(e) {
				e.preventDefault();
				fn_deleteFile($(this));
			}); 
*/
		}
    </script>
</body>
</html>
			
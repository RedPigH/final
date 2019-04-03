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
<%-- <script src="<%= cp %>/resources/js/common.js"></script> --%>
<script src="<%= cp %>/resources/js/admin_common.js"></script>
</head>

<body>

<div class="admin">
	<div class="logo">
	<h1><a href="<%=cp %>/admin/qnaList.do">MovieCube Administrator - Q&amp;A Register</a></h1>
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
		<h3 class="sub_tit">Q&amp;A 답변 </h3>
		<form id="frm">
			<div class="tbl_type_01">
				<table>
					<caption>Q&amp;A</caption>
					<colgroup>
						<col style="width: 120px;" />
						<col />
					</colgroup>
					
					<tbody>
					<tr>
							<th scope="row">제목</th>
							<td><input type="text" class="txt w350" id="QNA_SUB" name="QNA_SUB" value="[답변]${map.QNA_SUB}" />
							<font color="red"></font>
						</tr>
						
						<tr>
							<th scope="row">작성자</th>
							<td>
								<input type="text" class="txt w200" id="QNA_ID" name="QNA_ID" value = "${sessionScope.userLoginInfo.MEMBER_ID}"/>
								<font color="red"></font>
							</td>
						</tr>
											
						<tr>
							<th scope="row">내용</th>
							<td>
								<div class="textarea_grp">
									<textarea name="QNA_CONTENT"></textarea>
								</div>
								<font color="red"></font>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_type_03">
				<a href="#this" class="btn btnC_04 btnP_04" id="write">
					<span>작성하기</span>
				</a>
				
				<a href="#this" class="btn btnC_04 btnP_04" style="padding-left: 10px;" id="list">
					<span>목록으로</span>
				</a>			
			</div>
		</form>		
	</div>
</div>

<form id="commonForm" name="common"></form>

<script type="text/javascript">
        var gfv_count = 1;
     
        $(document).ready(function(){
            $("#list").on("click", function(e){ //목록으로 버튼
                e.preventDefault();
                fn_openBoardList();
            });
             
            $("#write").on("click", function(e){ //작성하기 버튼
                e.preventDefault();
                fn_insertBoard();
            });
        });
         
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/admin/qnaList.do' />");
            comSubmit.submit();
        }
         
        function fn_insertBoard(){
            var comSubmit = new ComSubmit("frm");
            var qna_no = "${map.QNA_NOM}";
            comSubmit.setUrl("<c:url value='qnaReply.do' />");
            comSubmit.addParam("REF", qna_no);
            alert(qna_no);
            comSubmit.submit();
        }
    </script>
</body>
</html>
		
						
					
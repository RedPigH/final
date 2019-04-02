<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%
	String cp = request.getContextPath();
%>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
</head>
<body>
	<h2>QNA 목록♥</h2>
	<table class="board_list">
		<colgroup>
			<col width="10%" />
			<col width="*" />
			<col width="15%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">작성자</th>
				<th scope="col">제목</th>
				<th scope="col">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(Qnalist) > 0}">
					<c:forEach items="${Qnalist }" var="row">
						<tr>
							<td>${row.QNA_NO }</td>
							<td>${row.QNA_ID }</td>
							<td class="QNA_SUB">
							 <a href="#this" name="QNA_SUB">${row.QNA_SUB }</a>
							<input type="hidden" id="QNA_NO" value="${row.QNA_NO }">
							</td>
							<td>${row.QNA_REGDATE }</td>

						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<%-- <div id="PAGE_NAVI"></div>
   <input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX" />
   <br /> --%>
	<a href="#this" class="btn" id="write">글쓰기</a>${pagingHtml}

	<%@ include file="/WEB-INF/include/include-body.jspf"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#write").on("click", function(e) {/* 글쓰기 버튼 */
				e.preventDefault();
				fn_openBoardWrite();
			});

			$("a[name='QNA_SUB']").on("click", function(e) { /* 제목 */
				e.preventDefault();
				fn_openBoardDetail($(this));
			});
		});

		function fn_openBoardWrite() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/qna/adminInquiryWriteForm.do'/>");
			comSubmit.submit();
		}

		function fn_openBoardDetail(obj) {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/qna/adminInquiryDetail.do'/>");
			comSubmit.addParam("QNA_NO", obj.parent().find("#QNA_NO").val());
			comSubmit.submit();
		}
	<%--function fn_selectNoticeList(pageNo) {
         var comAjax = new ComAjax();
         comAjax.setUrl("<c:url value='/notice/selectNoticeList.do'/>");
         comAjax.setCallback("fn_selectNoticeCallback");
         comAjax.addParam("PAGE_INDEX", pageNo);
         comAjax.addParam("PAGE_ROW", 15);
         comAjax.ajax();
      }

      function fn_selectNoticeListCallback(data) {
         var total = data.TOTAL;
         var body = $("table>tbody");
         body.empty();
         if (total == 0) {
            var str ="<tr>" +
            "<td colspan='4'>조회된 결과가 없습니다.</td>" +
            "</tr>";
            body.append(str);
         } else {
            var params = {
               divId : "PAGE_NAVI",
               pageIndex : "PAGE_INDEX",
               totalCount : total,
               eventName : "fn_selectNoticeList"
            };
            gfn_renderPaging(params);

            var str = "";
            $.each(data.list,function(key,value){
                           str += "<tr>"
                                 + "<td>"
                                 + value.NOTICE_NO
                                 + "</td>"
                                 + "<td class='NOTICE_SUB'>"
                                 + "<a href='#this' name='NOTICE_SUB'>"
                                 + value.NOTICE_SUB
                                 + "</a>"
                                 + "<input type='hidden' name='NOTICE_SUB' value=" + value.NOTICE_NO + ">"
                                 + "</td>" + "<td>"
                                 + value.NOTICE_REGDATE + "</td>"
                                 + "</tr>";
                        });
            body.append(str);

            $("a[name='NOTICE_SUB']").on("click", function(e) {//제목
               e.preventDefault();
               fn_openNoticeDetail($(this));
            });
         }
      } --%>
		
	</script>
</body>
</html>
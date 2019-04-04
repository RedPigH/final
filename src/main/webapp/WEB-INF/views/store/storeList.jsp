<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
   <%@ include file="../main/head.jspf" %>
</head>
<script type="text/javascript">
	function storeModal(store_no, store_item, store_price, store_content, img_savname) {
		
		$("#h_modal_store_no").attr("value", store_no);
		
		$("#h_modal_price").attr("value", store_price);

		var item = "상품명 : " + store_item;
		$("#h_modal_store_item").text(item);
		
		var price = "가격 : " + store_price + "원";
		$("#h_modal_store_price").text(price);
		
		var content = "상품 설명 : " + store_content;
		$("#h_modal_store_content").html(content);
		
		var img_url = "/moviecube/resources/upload/store/"
			+ img_savname;
		$("#h_modal_img_savname").attr('src', img_url);
	}
</script>
<%@ include file="../main/body_header.jspf" %> 

<body class="animsition">
	
<header class="header-v4">
	<div class="container-menu-desktop">

	<!-- Back to top -->
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top"> <i
			class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
	</div>
		
</header>
	
	<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
		 <div class="flex-col-c p-b-10" style="margin-top: 40px">
         <h3 class="ltext-105 cl5">STORE LIST</h3>
      </div>
		
		
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<button
						class="mtext-109 cl2 p-b-30" style="padding-bottom: 0px;"
						data-filter="*">상품 목록</button>
				</div>
			</div>

			<div class="row isotope-grid">
				<c:choose>
				<c:when test="${fn:length(storelist) > 0 }">
					<c:forEach items="${storelist}" var="row">
						<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
							<!-- Block2 -->
							<div class="block2">
								<div class="block2-pic hov-img0" id="forstoreModal">
									<img
										src="/moviecube/resources/upload/store/${row.IMAGE_SAVNAME}"
										alt="상품 이미지" />

									<a href="#" id="gahyun"
									onclick="storeModal(
										'${row.STORE_NO }',
										'${row.STORE_ITEM }',
										'${row.STORE_PRICE }',
										'${row.STORE_CONTENT }',
										'${row.IMAGE_SAVNAME }'
									);"
										class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1">
										상세보기 </a>

								</div>

								<div class="block2-txt flex-w flex-t p-t-14">
									<div class="block2-txt-child1 flex-col-l ">
										<a href="resources/product-detail.html"
											class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
											${row.STORE_ITEM}</a> <span class="stext-105 cl3"
											> 가격 : ${row.STORE_PRICE}원</span>
									</div>

									<div class="block2-txt-child2 flex-r p-t-3 ${row.STORE_NO}">
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
				 <div class="col-sm-12 col-md-12 col-lg-12 p-b-35 isotope-item women">
            	<div class="size-196 p-t-35 p-b-35 flex-c-m m-tb-50" id="movieSelectNotice">
								<span class="mtext-110 cl2" style="font-family: MaplestoryLight; font-size: 25px">상품이 없습니다ㅠㅠ</span>
				</div>
				</div>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>

	<%@ include file="../main/body_footer.jspf"%>
	
	<%@ include file="../member/loginForm.jspf"%>

	<%@ include file="../store/store_ProductModal.jspf"%>

	<%@ include file="../main/script.jspf"%>
</body>
</html>
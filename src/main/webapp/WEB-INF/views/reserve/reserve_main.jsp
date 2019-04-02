<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../main/head.jspf"%>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" />
<script src="/moviecube/resources/vendor/jqueryui/jquery-ui.min.js"></script>
</head>
<body class="animsition">

	<%@ include file="../main/body_header.jspf"%>
	<%@ include file="../main/wishList.jspf"%>

	
	<!-- 영화 예매 -->
	<form class="bg0 p-t-75 p-b-85">

		<div class="container" style="margin-top: 100px">
			<div class="bread-crumb flex-w p-l-50 p-r-15 p-b-30 p-lr-0-lg">
				<a href="main.do" class="stext-109 cl8 hov-cl1 trans-04"> 메인으로 <i
					class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
				</a> <span class="stext-109 cl4"> 날짜/극장/영화/시간 </span>
			</div>

			<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">


						<div
							class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-50 p-lr-15-sm"
							style="border-top: 1px solid #e6e6e6;">

							<div class="flex-w flex-m m-r-20 m-tb-5">
								<h4 class="mtext-109 cl2 p-b-30" style="padding-bottom: 0px">날짜</h4>
							</div>
							<div class="wrapper">
								<input type="text" id="datepicker" placeholder="날짜를 선택하세요."
									autocomplete="off" style="font-family: MaplestoryLight" /> <i
									class="ion-calendar"></i>
							</div>
							<%@ include file="./datepicker.jspf"%>

						</div>


						<div
							class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-50 p-lr-15-sm"
							style="border-bottom: unset;">

							<div class="flex-w flex-m m-r-20 m-tb-5">
								<h4 class="mtext-109 cl2 p-b-30" style="padding-bottom: 0px">극장</h4>

							</div>
						</div>
						<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-50 p-lr-15-sm">

							<c:forEach var="i" begin="1" end="4" step="1">
								<div class="size-199 respon6-next" id="cinemaNo${i}">
									<div class="rs1-select2 bor8 bg0">
										<select class="js-select2" name="selectCinema"
											id="selectCinema" onchange="movieSelect();">
											<option value="" style="color: gray;">선 택</option>
											<c:forEach var="cinema" items="${cinemaList}">
												<option value="${cinema.CINEMA_NO}">${cinema.CINEMA_NAME}</option>
											</c:forEach>
										</select>
										<div class="dropDownSelect2"></div>
									</div>
								</div>
							</c:forEach>
						</div>

						<div style="border-bottom: 1px solid #e6e6e6;">
							<div
								class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-50 p-lr-15-sm"
								style="border-bottom: unset;">
								<h4 class="mtext-109 cl2 p-b-30" style="padding-bottom: 0px">영화</h4>
								<a href="#"
									class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1"
									style="position: unset;">영화 추가 </a>
							</div>

							<div>

								<c:forEach items="${movieList}" var="row">
									<div id="${row.MOVIE_NAME}" style="display: none;">
										<div
											class="wrap-table-shopping-cart table-shopping-cart table_row tr flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-50 p-lr-15-sm ${row.MOVIE_NO}">
											<div class="column-1">
												<div class="AddedMoviePoster">
													<img
														src="/moviecube/resources/upload/movie/poster/${row.POSTER_SAVNAME}">
												</div>
											</div>
											<div class="column-2"
<<<<<<< HEAD
												style="font-family: MaplestoryBold; font-size: 20px">${row.MOVIE_NAME}</div>
=======
												style="font-family: MaplestoryBold; text-align: -webkit-center; font-size: 20px; width:50%">${row.MOVIE_NAME}</div>
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
											<div class="column-3">
												<button
													onclick="remove_item(document.getElementById('AddedMovieList${row.MOVIE_NAME}')); movieSelect();"
													style="font-family: MaplestoryLight">삭제</button>
											</div>
										</div>
									</div>
								</c:forEach>


								<div id="field"></div>
							</div>
						</div>
					</div>
				</div>



				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div
						class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm"
						id="timeList">
						<h4 class="mtext-109 cl2 p-b-30">상영시간표</h4>


						<div class="flex-w flex-t bor12 p-t-15 p-b-15" id="Notice">
							<div class="size-196 p-t-35 p-b-35 flex-c-m"
								id="movieSelectNotice">
								<span class="mtext-110 cl2" style="font-family: MaplestoryLight">날짜, 극장, 영화를 선택해주세요.</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>



	<%@ include file="../main/body_footer.jspf"%>


	<!-- Back to top -->
	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top"> <i
			class="zmdi zmdi-chevron-up"></i> 
		</span>
	</div>

	<%@ include file="movieList_modal.jspf"%>

	<%@ include file="../main/script.jspf"%>
	
	<c:if test="${selectedMovieList != null}">
		<c:forEach var="idx" items="${selectedMovieList}">
			<script type="text/javascript">add_item('${idx}');</script> 
		</c:forEach>
		<script type="text/javascript">movieSelect();</script>
	</c:if>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>확인확인</title>
</head>
<body>
상영시간 번호 : ${time.TIME_NO } <br>
영화 번호 : ${time.MOVIE_NO } <br>
영화 이름 : ${time.MOVIE_NAME } <br>
영화관 이름 : ${time.CINEMA_NAME } <br>
상영관 번호 : ${time.SCREEN_NO } <br>
상영관 이름 : ${time.SCREEN_NAME } <br>
상영 날짜 : ${time.TIME_DATE } <br>
선택 좌석 : ${selectSeats } <br>
총 가격 : ${totalprice} <br>

<button onclick = "complete()">결제 </button>
</body>
<script type="text/javascript">
	function complete() {
		location.href = 'reserve_complete.do?TIME_NO=${time.TIME_NO}&SCREEN_NO=${time.SCREEN_NO}&selectSeats=${selectSeats}&TOTAL_PRICE=${totalprice}';
	} 
</script>
</html>
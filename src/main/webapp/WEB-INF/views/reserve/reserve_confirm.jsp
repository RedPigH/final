<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Ȯ��Ȯ��</title>
</head>
<body>
�󿵽ð� ��ȣ : ${time.TIME_NO } <br>
��ȭ ��ȣ : ${time.MOVIE_NO } <br>
��ȭ �̸� : ${time.MOVIE_NAME } <br>
��ȭ�� �̸� : ${time.CINEMA_NAME } <br>
�󿵰� ��ȣ : ${time.SCREEN_NO } <br>
�󿵰� �̸� : ${time.SCREEN_NAME } <br>
�� ��¥ : ${time.TIME_DATE } <br>
���� �¼� : ${selectSeats } <br>
�� ���� : ${totalprice} <br>

<button onclick = "complete()">���� </button>
</body>
<script type="text/javascript">
	function complete() {
		location.href = 'reserve_complete.do?TIME_NO=${time.TIME_NO}&SCREEN_NO=${time.SCREEN_NO}&selectSeats=${selectSeats}&TOTAL_PRICE=${totalprice}';
	} 
</script>
</html>
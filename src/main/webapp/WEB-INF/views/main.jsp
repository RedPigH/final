<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>

   <%@ include file="main/head.jspf" %>

</head>
<body class="animsition">


   <%@ include file="main/body_header.jspf" %>
   
   <%@ include file="main/wishList.jspf" %>
      
   <%@ include file="main/body_slider.jspf" %>

   <%@ include file="main/body_boxoffice.jspf" %>
      
   <%@ include file="main/body_moviecube.jspf" %>

   <%@ include file="main/body_footer.jspf" %>


      <!-- Back to top -->
      <div class="btn-back-to-top" id="myBtn">
         <span class="symbol-btn-back-to-top"> <i
            class="zmdi zmdi-chevron-up"></i>
         </span>
      </div>
      
      
   <%@ include file="member/loginForm.jspf" %>

   <%@ include file="main/body_ProductModal.jspf" %>

   <%@ include file="main/script.jspf" %>
   
</body>
</html>
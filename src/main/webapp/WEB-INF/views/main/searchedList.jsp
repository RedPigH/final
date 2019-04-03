<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>

   <%@ include file="head.jspf" %>

</head>
<body class="animsition">


   <%@ include file="body_header.jspf" %>
   
   <%@ include file="wishList.jspf" %>
      

      
   <!-- searchedList -->
<section class="bg0 p-t-75 p-b-85" style="margin-top: 60px;">
   <div class="container">
      <div class="flex-col-c p-b-10">
         <h3 class="ltext-105 cl5 p-b-52">SEARCH LIST</h3>
      </div>
      <div class="row isotope-grid">
         <!-- 여기부터 하나 시작  -->

         <c:choose>
            <c:when test="${fn:length(movieList) > 0 }">
               <c:forEach items="${movieList}" var="row">
                  <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
                     <!-- Block2 -->
                     <div class="block2">
                        <div class="block2-pic hov-img0" id="forMovieModal" style="border-radius: 10px">
                           <img
                              src="/moviecube/resources/upload/movie/poster/${row.POSTER_SAVNAME}"
                              alt="영화포스터" />
                           <!-- height 뺐음 -->


                           <a href="#" id="gahyun" onclick="toModal(${row.MOVIE_NO });" 
                           class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1">
                             	 상세보기 </a>

                        </div>

                        <div class="block2-txt flex-w flex-t p-t-14">
                           <div class="block2-txt-child1 flex-col-l ">
                              <a href="resources/product-detail.html"
                                 class="stext-105 cl3 hov-cl1 trans-04 js-name-b2 p-b-6">
                                 ${row.MOVIE_NAME}</a> <span class="stext-104 cl3"
                                 id="MOVIE_GENRE"> 장르 : ${row.MOVIE_GENRE} </span>
                           </div>

                           <div class="block2-txt-child2 flex-r p-t-3 ${row.MOVIE_NO}">


                              <c:choose>
                                 <c:when test="${WishList != null}">
                                    <c:set var="redHeart" value="false"/>
                                    <c:forEach var="idx" items="${WishList}">
                                       <c:if test="${idx.MOVIE_NO == row.MOVIE_NO}">
                                          <c:set var="redHeart" value="true" />
                                       </c:if>
                                    </c:forEach>
                                    <c:choose>
                                       <c:when test="${redHeart}">
                                          <a href="javascript:void(0);" id="${row.MOVIE_NO}"
                                             class="btn-addwish-b2 dis-block pos-relative js-addwish-b2 js-addedwish-b2"
                                             onclick="deleteWishList(${row.MOVIE_NO});"> <img
                                             class="icon-heart1 dis-block trans-04"
                                             src="resources/images/icons/icon-heart-01.png" alt="ICON">

                                             <img class="icon-heart2 dis-block trans-04 ab-t-l"
                                             src="resources/images/icons/icon-heart-02.png" alt="ICON">
                                          </a>
                                       </c:when>
                                       <c:otherwise>
                                          <a href="javascript:void(0);" id="${row.MOVIE_NO}"
                                             class="btn-addwish-b2 dis-block pos-relative js-addwish-b2"
                                             onclick="addWishList(${row.MOVIE_NO});"> <img
                                             class="icon-heart1 dis-block trans-04"
                                             src="resources/images/icons/icon-heart-01.png" alt="ICON">

                                             <img class="icon-heart2 dis-block trans-04 ab-t-l"
                                             src="resources/images/icons/icon-heart-02.png" alt="ICON">
                                          </a>
                                       </c:otherwise>
                                    </c:choose>
                                 </c:when>
                                 <c:otherwise>
                                    <a href="javascript:void(0);" class="btn-addwish-b2 dis-block pos-relative"
                                       onclick="loginAlert();"> <img
                                       class="icon-heart1 dis-block trans-04"
                                       src="resources/images/icons/icon-heart-01.png" alt="ICON">

                                       <img class="icon-heart2 dis-block trans-04 ab-t-l"
                                       src="resources/images/icons/icon-heart-02.png" alt="ICON">
                                    </a>
                                 </c:otherwise>
                              </c:choose>
                           </div>
                        </div>
                     </div>
                  </div>
               </c:forEach>
            </c:when>
            <c:otherwise>
            <div class="col-sm-12 col-md-12 col-lg-12 p-b-35 isotope-item women">
            	<div class="size-196 p-t-35 p-b-35 flex-c-m m-tb-50" id="movieSelectNotice">
								<span class="mtext-110 cl2" style="font-family: MaplestoryLight; font-size: 25px">검색 결과가 없습니다ㅠㅠ</span>
				</div>
				</div>
            </c:otherwise>
         </c:choose>


      </div>
   </div>
</section>

   <%@ include file="body_footer.jspf" %>


      <!-- Back to top -->
      <div class="btn-back-to-top" id="myBtn">
         <span class="symbol-btn-back-to-top"> <i
            class="zmdi zmdi-chevron-up"></i>
         </span>
      </div>
      
      
   <%@ include file="../member/loginForm.jspf" %>

   <%@ include file="body_ProductModal.jspf" %>

   <%@ include file="script.jspf" %>
   
</body>
</html>
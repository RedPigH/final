<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="ko">

<head>
<%@ include file="/WEB-INF/views/main/head.jspf"%>
<link rel="stylesheet" type="text/css"
   href="/moviecube/resources/css/member.css" />
<script type="text/javascript"
   src="/moviecube/resources/js/init.controls.js"></script>
</head>

<body class="animsition">
   <%@ include file="/WEB-INF/views/main/body_header.jspf"%>
   <%@ include file="/WEB-INF/views/main/wishList.jspf"%>
   <%@ include file="/WEB-INF/views/member/loginForm.jspf"%>

<c:if test="${empty sessionScope.userLoginInfo}">
	<script type="text/javascript">
		alert("로그인 해주세요.");
		window.location.replace("/moviecube/main.do");
	</script>
</c:if>


<c:if test="${not empty sessionScope.userLoginInfo}">
      <section class="bg0 p-t-140 p-b-116">
         <div class="container">
            <div class="flex-w flex-tr">
               <div class="size-217 w-full-md">
                  <div class="flex-w flex-sb-m p-t-18 p-b-30 p-lr-50 p-lr-15-sm">
                        <h4 class="mtext-109 cl2 p-b-30" style="padding-left: 150px; padding-bottom: 0px">마이페이지</h4>
                     </div>
                  <div class="row" style="margin: auto; justify-content: center;">
                     <div class="cols col1">
                        <!-- 개인정보 -->
                        <div id="myPageMainUser" class="mypage_myInfo">
                           <div class="h3_wrap mb35">
                              <h3>
                                 <img
                                    src="http://image2.megabox.co.kr/mop/home/mypage/main_title3.png"
                                    alt="개인정보">
                              </h3>
                              <a href="/moviecube/member/updateMemberForm.do" style="display: block;" class="flex-c-m stext-107 cl13 float-r size-301 bor21 p-lr-15 hov-tag2 trans-04">정보수정</a>
                           </div>

                           <ul>
                              <li><strong>이름</strong> <span>${sessionScope.userLoginInfo.MEMBER_NAME}님</span>
                              </li>
                              <li><strong>휴대폰</strong> <span>${sessionScope.userLoginInfo.MEMBER_PHONE}</span>
                              </li>
                              <li><strong>선호 영화관</strong> <span></span></li>
                              <!--           2014.09.19  페이스북 가입 및 연동 걷어내기 -->
                              <!--    <li> -->
                              <!--       <strong>페이스북 상태</strong> -->
                              <!--       <span> -->
                              <!--          <strong class="c_purple mr10">연동중</strong> -->
                              <!--          <button onclick="MyPageMain.disconnectFacebook()"></button> -->

                              <!--           2014.09.19  페이스북 가입 및 연동 걷어내기 -->
                              <!--          <button onclick="MyPageMain.connectFacebook()"></button> -->
                              <!--       </span> -->
                              <!--    </li> -->
                              <li><strong>SMS수신여부</strong> <span class="btn_sms">
                                    <button class="no active" title="거부 선택됨"
                                       onclick="MyPageMain.setSmsReceiveYn('N')" value="거부">거부</button>
                                    <!--         -->
                                    <button class="yes" title="수신 "
                                       onclick="MyPageMain.setSmsReceiveYn('Y')" value="수신">수신</button>

                              </span></li>
                           </ul>


                           <script type="text/javascript">
                              $('span.btn_sms').children('button').click(function() {
                              	$('span.btn_sms').children('button').removeClass('active');
                                	$('span.btn_sms').children('button').each(function() {
										$(this).attr("title", $(this).val());
                                    });
                                    $(this).addClass('active');
                                $(this).attr('title', $(this).val() + ' 선택됨');
                              });
                           </script>
                           <script>
                              
                           </script>
                        </div>


                        <!-- 나의 무비스토리 -->
                        <div id="myPageMainMovieStory" class="mypage_main_box"
                           style="height: 242px;">
                           <div class="h3_wrap p-l-20 p-r-20 m10">
                              <h3 style="height: 23px;">
                                 <img
                                    src="http://image2.megabox.co.kr/mop/home/mypage/main_title7.png"
                                    alt="나의 무비스토리">
                              </h3>
                           </div>

                           <ul class="mypage_main_moviestory m-t-30">
                              <li><a href="javascript:void(0)" class="js-show-cart"
                                 data-notify="0" title="보고싶어 보기"> <span><img
                                       src="http://image2.megabox.co.kr/mop/home/mypage/main_icon1.png"
                                       alt=""></span> <strong class="ml10">보고싶어</strong> <strong
                                    class="c_red pull-right">0</strong>
                              </a></li>
                              <li><a href="javascript:void(0)"
                                 onclick="showMenu('mypage-moviestory', 'seen')"
                                 title="본영화 보기"> <span><img
                                       src="http://image2.megabox.co.kr/mop/home/mypage/main_icon2.png"
                                       alt=""></span> <strong class="ml10">본영화</strong> <strong
                                    class="c_red pull-right">0</strong>
                              </a></li>
                           </ul>
                           <script>
                              
                           </script>
                        </div>
                     </div>



                     <div id="myPageMyBooking" class="cols col2">
                        <!-- 나의 예매내역 -->
                        <div class="mypage_main_box" style="height: 505px;">
                           <div class="h3_wrap pb38 bor22">
                              <h3>
                                 <img
                                    src="http://image2.megabox.co.kr/mop/home/mypage/main_title4.png"
                                    alt="최근 예매 내역">
                              </h3>
                           </div>

                           <ul class="type1">
                              <li class="no_data text-center pa30">조회된 내역이 없습니다</li>
                           </ul>
                        </div>
                        <script>
                           
                        </script>
                     </div>



                     <div class="cols col3">
                        <div id="myPageMyQuestion" class="mypage_main_box"
                           style="height: 505px;">
                           <div class="positionR">
                              <div class="h3_wrap pb38 bor22">
                                 <h3>
                                    <img
                                       src="http://image2.megabox.co.kr/mop/home/mypage/main_title8.png"
                                       alt="나의문의내역">
                                 </h3>
                              </div>

                              <ul class="type1">
                                 <li class="no_data text-center pa30">조회된 내역이 없습니다</li>
                              </ul>

                              <div class="m-t-205"></div>

                           </div>

                           <script>
                              
                           </script>
                        </div>
                     </div>



                  </div>
               </div>
            </div>
         </div>
      </section>
   </c:if>

   <%@ include file="/WEB-INF/views/main/script.jspf"%>

</body>
</html>
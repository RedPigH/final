package com.moviecube.member;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;
import com.moviecube.wishlist.WishListService;

@Controller
public class MemberController {
	
	  Logger log = Logger.getLogger(this.getClass());
	  
	  @Resource(name = "wishlistService")
		private WishListService wishlistService;
	  
	  @Resource(name="memberService")
	  private MemberServiceImpl memberService;
	  
	  //���Ǽ�
	  
	  @RequestMapping(value="/term.do")
	  public ModelAndView terms(CommandMap commandMap) throws Exception{
		  ModelAndView mv = new ModelAndView("/member/terms");
	  
		  return mv;
	  }
	  
	  //ȸ������ ��
	  
	  @RequestMapping(value="/member/joinForm.do")
	  public ModelAndView joinForm(CommandMap commandMap) throws Exception{
		  ModelAndView mv = new ModelAndView("/member/joinForm");
	  
		  return mv;
	  }
	  
	  //�ߺ�Ȯ��
	  @RequestMapping("/member/checkId.do")
	  @ResponseBody
	   public Map<String, Object> findUsedID(@RequestBody String id) throws Exception{
		  Map<String, Object> map = new HashMap<String, Object>();	
		  map.put("MEMBER_ID", id);
		  int count = 0;
		
		  count=memberService.findUsedID(map);
		  map.put("count", count);
		  
		  return map;
		  
	  }
	  
	  //ȸ������
	  @RequestMapping(value="/member/join.do")
	  public String join(CommandMap commandMap) throws Exception{
		  try {
	  memberService.insertMember(commandMap.getMap());
		  	  } catch(Exception e) {
		  	  }
		  
		  	return "redirect:/main.do";
	  }
	  
	  //�α��� ��
	  @RequestMapping(value="/member/loginForm.do")
	  public ModelAndView loginForm(CommandMap commandMap) throws Exception{
		  ModelAndView mv = new ModelAndView("/member/loginForm");
		  
		  return mv;
	  }
	  
	  //�α���
	  @RequestMapping(value="/member/login.do")
	  public ModelAndView login(CommandMap commandMap, HttpSession session, HttpServletRequest request) throws Exception{
		  ModelAndView mv = new ModelAndView();
		  
		  String referer = request.getHeader("Referer");
		  
		  Map <String, Object> user = new HashMap<String, Object>();
		  
		  user = memberService.checkUserIdAndPassword(commandMap.getMap());
		  		  
		  if(user!=null){
			  session.setAttribute("userLoginInfo", user);
				
				CommandMap map = new CommandMap();
				
				map.put("MEMBER_NO", user.get("MEMBER_NO"));
				
				List<Map<String, Object>> wish = wishlistService.selectWishList(map.getMap());
				
				mv.addObject("WishList", wish);
				mv.setViewName("redirect:" + referer);
				
			  return mv;
		  } 
		  
		  mv.setViewName("/member/loginError");
		  return mv;
	  }
	  
	    
	  //�α׾ƿ�
	  @RequestMapping(value="/member/logout.do")
	  public ModelAndView logout(HttpSession session) {
		  ModelAndView mv = new ModelAndView("redirect:/main.do");
		  session.setAttribute("userLoginInfo", null);
		  session.setAttribute("WishList", null);
		  return mv;
	  }
	  
	  //���̵� ��й�ȣ ã�� ��
	  @RequestMapping(value="/member/findForm.do")
	  public ModelAndView findForm(CommandMap commandMap) throws Exception{
		  ModelAndView mv = new ModelAndView("/member/findIdAndPassword");
		  
		  return mv;
	  }
	  
<<<<<<< HEAD
	  @RequestMapping(value="/member/find.do")
	  @ResponseBody
	  public ModelAndView findId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  ModelAndView mv = new ModelAndView();
		  
		  CommandMap map = new CommandMap();
		  
		  map.put("MEMBER_NAME", request.getParameter("MEMBER_NAME"));
		  map.put("MEMBER_AGE", request.getParameter("MEMBER_AGE"));
		  map.put("MEMBER_PHONE", request.getParameter("MEMBER_PHONE"));
		  
		  System.out.println(map.getMap());
		  
		  Map<String, Object> findid = memberService.findId(map.getMap());
		  
		  mv.setViewName("jsonView");
		  mv.addObject("findid", findid);
		  
=======
	
	  //���̵� ��й�ȣ ã��
	  
	  @RequestMapping(value="/member/find.do")
	  public ModelAndView findId(CommandMap commandMap) throws Exception{
		  ModelAndView mv = new ModelAndView();
		  
		  String id = memberService.findId(commandMap.getMap());
		  mv.addObject("id", id);
		  
		  mv.setViewName("/member/findId");
>>>>>>> 2f24f14e2eb38df9eac54d8e4cdb671a356b4268
		  return mv;
	  }
	 
	  @RequestMapping(value="/member/find1.do")
<<<<<<< HEAD
	  @ResponseBody
	  public ModelAndView findPw(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  
		  ModelAndView mv = new ModelAndView();
		  
		  CommandMap map = new CommandMap();
		  
		  map.put("MEMBER_ID", request.getParameter("MEMBER_ID"));
		  map.put("MEMBER_NAME", request.getParameter("MEMBER_NAME"));
		  map.put("MEMBER_PHONE", request.getParameter("MEMBER_PHONE"));
		  
		  System.out.println(map.getMap());
		  
		  Map<String, Object> findpw = memberService.findPasswd(map.getMap());
		  
		  System.out.println(findpw);
		  
		  mv.setViewName("jsonView");
		  mv.addObject("findpw", findpw);
		  
		  mv.addObject("findpw2", findpw.get("MEMBER_PASSWD1"));
		  
=======
	  public ModelAndView findPw(CommandMap commandMap) throws Exception{
		  ModelAndView mv = new ModelAndView();
		  
		  String pw = memberService.findPasswd(commandMap.getMap());
		  mv.addObject("pw", pw);
		  
		  mv.setViewName("/member/findPw");
>>>>>>> 2f24f14e2eb38df9eac54d8e4cdb671a356b4268
		  return mv;
	  }
	  
	
	  //ȸ�� ���� ����
	  
	  @RequestMapping(value="/member/updateMember.do")
	  public ModelAndView updateMember(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception{
		  ModelAndView mv = new ModelAndView();
		  Map <String, Object> user = new HashMap<String, Object>();
		  Map <String, Object> infoUpdate = new HashMap<String, Object>();
		  
		  user = memberService.checkUserIdAndPassword(commandMap.getMap());
		  
		  if(user != null) {
			  memberService.updateMember(commandMap.getMap());
			  
			  infoUpdate = memberService.checkUserIdAndPassword(commandMap.getMap());
			  session.setAttribute("userLoginInfo", infoUpdate);
			  mv.setViewName("redirect:/member/myPage.do");
			  return mv;
		  }
		  
		  mv.setViewName("/member/passwordError");
		  return mv;
	  }
	  
	  @RequestMapping(value="/member/updatePassword.do")
	  public ModelAndView updateMember(CommandMap commandMap) throws Exception{
		  ModelAndView mv = new ModelAndView();
		  Map <String, Object> pass = new HashMap<String, Object>();
		  
		  pass = memberService.checkUserIdAndPassword(commandMap.getMap());
		  
		  if(pass!=null) {
		 	memberService.updatePass(commandMap.getMap());
		 	mv.setViewName("redirect:/member/updateMemberForm.do");
		 	
		 	return mv;
		  }
		  
		  mv.setViewName("/member/passwordError");
		  return mv;
	  }
	  
	  @RequestMapping(value="/member/deleteMember.do")
	  public ModelAndView deleteMember(CommandMap commandMap, HttpSession session) throws Exception{
		  ModelAndView mv = new ModelAndView("/member/deleteSuccess");
		  
		  memberService.deleteMember(commandMap.getMap());
		  session.setAttribute("userLoginInfo", null);
		  session.setAttribute("WishList", null);
		  
		  return mv;
	  }
	  
	  @RequestMapping(value = "/member/profileEnter.do")
		public ModelAndView insertMyPage(CommandMap commandMap, HttpServletRequest request) throws Exception {
			ModelAndView mv = new ModelAndView("redirect:/member/updateMemberForm.do");
			
			String MEMBER_NO = ((String)commandMap.get("MEMBER_NO"));
			System.out.println(" 슬라이더 추가 값 체크 ================" + commandMap.get("MEMBER_NO"));

			
			commandMap.getMap().put("MEMBER_NO", MEMBER_NO);
			memberService.insertMyPage(commandMap.getMap(), request);

			return mv;
		}
	  
	  @RequestMapping(value = "/member/profileUpdate.do")
		public ModelAndView modifyInquiry(CommandMap commandMap, HttpServletRequest request) throws Exception {
			ModelAndView mv = new ModelAndView("redirect:/member/updateMemberForm.do");
			
			String MEMBER_NO = ((String)commandMap.get("MEMBER_NO"));
			System.out.println(" 슬라이더 추가 값 체크 ================" + commandMap.get("MEMBER_NO"));

			commandMap.getMap().put("MEMBER_NO", MEMBER_NO);
			
			memberService.updateProfile(commandMap.getMap(), request);

			return mv;
		}
}

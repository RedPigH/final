package com.moviecube.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;
import com.moviecube.qna.QnaService;
import com.moviecube.reserve.ReserveService;

@Controller
public class MypageController {

	@Resource(name = "reserveService")
	private ReserveService reserveService;
	
	@Resource(name = "qnaService")
	private QnaService qnaService;

	@Resource(name = "memberService")
	private MemberService memberService;

	Logger log = Logger.getLogger(this.getClass());

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/member/myPage.do")
	public ModelAndView findForm(CommandMap commandMap, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/member/myPage");

		/* 멤버의 예매내역 */		
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("userLoginInfo");
		commandMap.put("MEMBER_NO", user.get("MEMBER_NO"));
		List<Map<String, Object>> resList = reserveService.MyReservation(commandMap.getMap());
		
		Map<String, Object> tempMap = memberService.selectMemberFile(commandMap.getMap());
		Map<String, Object> tempMap2 = (Map<String, Object>) tempMap.get("map");
		
		mv.addObject("profile_savname", tempMap2.get("PROFILE_SAVNAME"));

		/* 멤버의 문의내역 */
		commandMap.put("QNA_ID", user.get("MEMBER_NAME"));
		List<Map<String, Object>> qnaList = qnaService.selectQnaListByMember(commandMap.getMap());
		
		List<Map<String, Object>> allResList = reserveService.MyReservation(commandMap.getMap());
		
		mv.addObject("res_count", allResList.size());
		mv.addObject("qnaList", qnaList);
		mv.addObject("resList", resList);

		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/member/updateMemberForm.do")
	public ModelAndView updateMemberForm(CommandMap commandMap, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/member/updateMember");
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("userLoginInfo");
		commandMap.put("MEMBER_NO", user.get("MEMBER_NO"));
		
		Map<String,Object> map = memberService.selectMemberFile(commandMap.getMap());
		
		mv.addObject("map", map.get("map"));

		return mv;
	}
}

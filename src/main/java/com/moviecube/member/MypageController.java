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
import com.moviecube.reserve.ReserveService;

@Controller
public class MypageController {

	@Resource(name = "reserveService")
	private ReserveService reserveService;
	
	@Resource(name = "memberService")
	private MemberService memberService;

	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/member/myPage.do")
	public ModelAndView findForm(CommandMap commandMap, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/member/myPage");

		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("userLoginInfo");
		
		commandMap.put("MEMBER_NO", user.get("MEMBER_NO"));

		List<Map<String, Object>> ResList = reserveService.MyReservation(commandMap.getMap());

		mv.addObject("ResList", ResList);

		return mv;
	}

	@RequestMapping(value = "/member/updateMemberForm.do")
	public ModelAndView updateMemberForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/member/updateMember");
		
		Map<String,Object> map = memberService.selectMemberFile(commandMap.getMap());
		
		mv.addObject("map", map.get("map"));

		return mv;
	}
	
}

package com.moviecube.event;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;
import com.moviecube.common.Paging;

@Controller
public class EventController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "eventService")
	private EventService eventService;

	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockpaging = 10;
	private String pagingHtml;
	private Paging paging;

	/*
	 * @RequestMapping(value = "/eventList.do") public ModelAndView
	 * eventList(Map<String, Object> commandMap, HttpServletRequest request) throws
	 * Exception { ModelAndView mv = new ModelAndView();
	 * 
	 * List<Map<String, Object>> eventList =
	 * eventService.selectEventList(commandMap);
	 * 
	 * if (request.getParameter("currentPage") == null ||
	 * request.getParameter("currentPage").trim().isEmpty() ||
	 * request.getParameter("currentPage").equals("0")) { currentPage = 1; } else {
	 * currentPage = Integer.parseInt(request.getParameter("currentPage")); }
	 * 
	 * totalCount = eventList.size();
	 * 
	 * paging = new Paging(currentPage, totalCount, blockCount, blockpaging,
	 * "eventList"); pagingHtml = paging.getPagingHtml().toString();
	 * 
	 * int lastCount = totalCount;
	 * 
	 * if (paging.getEndCount() < totalCount) { lastCount = paging.getEndCount() +
	 * 1; }
	 * 
	 * eventList = eventList.subList(paging.getStartCount(), lastCount);
	 * 
	 * mv.addObject("eventList", eventList); mv.addObject("list", eventList);
	 * mv.addObject("currentPage", currentPage); mv.addObject("pagingHtml",
	 * pagingHtml); mv.addObject("totalCount", totalCount);
	 * mv.setViewName("/admin/event/eventList");
	 * 
	 * return mv; }
	 */
	
	@RequestMapping(value = "/eventList.do")
	public ModelAndView eventList(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/event/eventList");

		List<Map<String, Object>> eventList = eventService.selectEventList(commandMap);
		mv.addObject("eventList", eventList);

		return mv;
	}

	@RequestMapping(value = "userEventList.do")

	public ModelAndView userEventList(Map<String, Object> commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/event/userEventList");

		String event_no = request.getParameter("event_no");

		List<Map<String, Object>> eventList = eventService.selectEventList(commandMap);
		mv.addObject("event_no", event_no);

		mv.addObject("eventList", eventList);

		System.out.println("�삙怡몃뒗夷⑹씠�뼃" + commandMap.get("event_no"));

		List<Map<String, Object>> mceventList = eventService.McEventList(commandMap);// moviecubeeventList
		List<Map<String, Object>> meventList = eventService.MovieEventList(commandMap);// movieEventList
		List<Map<String, Object>> alleventList = eventService.AllEventList(commandMap);// alliance(�젣�쑕)eventList

		mv.addObject("mceventList", mceventList);
		mv.addObject("meventList", meventList);
		mv.addObject("alleventList", alleventList);

		return mv;
	}

	@RequestMapping(value = "/eventWriteForm.do")
	public ModelAndView eventWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/event/eventWriteForm");

		return mv;
	}

	@RequestMapping(value = "/eventWrite.do")
	public ModelAndView eventWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/eventList.do");
		eventService.insertEvent(commandMap.getMap(), request);

		return mv;
	}

	@RequestMapping(value = "/eventDetail.do")
	public ModelAndView eventDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/event/eventDetail");

		Map<String, Object> map = eventService.selectEventDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("eventDetail", map.get("eventDetail"));
		System.out.println("占쎌굺�〓㈇�꽏占쎌맕占쎈뻷:" + commandMap.get("EVENT_NO"));

		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/eventDetail2.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView eventDetail2(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/event/eventDetail");
		String event_no = request.getParameter("event_no");

		commandMap.put("EVENT_NO", event_no);

		Map<String, Object> map = eventService.selectEventDetail(commandMap.getMap());
		List<Map<String, Object>> file_list = (List<Map<String, Object>>) map.get("eventDetail");

		Map<String, Object> map2 = (Map<String, Object>) map.get("map");

		mv.addObject("map", map.get("map"));

		String open_date = map2.get("EVENT_OPENDATE").toString().substring(0, 10);
		String end_date = map2.get("EVENT_CLOSEDATE").toString().substring(0, 10);
		String event_url = "";
		if (map2.get("EVENT_URL") != null) {
			event_url = map2.get("EVENT_URL").toString();
		}
		mv.addObject("file_list", file_list);

		mv.addObject("event_url", event_url);
		mv.addObject("open_date", open_date);
		mv.addObject("end_date", end_date);

		System.out.println("�삙怡몄Ł" + event_url);
		mv.setViewName("jsonView");
		return mv;
	}

	@RequestMapping(value = "/eventModifyForm.do")
	public ModelAndView movieModifyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/event/eventModify");
		System.out.println("占쎌겫占쎌넅 占쎈땾占쎌젟 占쎈쨲1 揶쏉옙 占쎌넇占쎌뵥 =============: " + commandMap.getMap());
		Map<String, Object> map = eventService.selectEventDetail(commandMap.getMap());

		mv.addObject("map", map.get("map"));
		mv.addObject("eventDetail", map.get("eventDetail"));
		mv.addObject("fileList", map.get("fileList"));

		return mv;
	}

	@RequestMapping(value = "/eventModify.do")
	public ModelAndView modifyEvent(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/eventList.do");

		/*
		 * String EVENT_NO = (String)commandMap.get("EVENT_NO");
		 * commandMap.getMap().put("EVENT_NO", EVENT_NO);
		 */
		/*
		 * int EVENT_NO = Integer.parseInt((String)commandMap.get("EVENT_NO"));
		 * commandMap.getMap().put("EVENT_NO", EVENT_NO);
		 */
		eventService.modifyEvent(commandMap.getMap(), request);
		System.out.println("占쎌굺�〓㈇�꽏占쎌맕占쎈뻷占쎌삒:" + commandMap.get("EVENT_NO"));
		System.out.println("占쎌굺�〓챶�뮉筌욊퉮�뵠占쎈튊占쎈��:" + commandMap.getMap());
		mv.addObject("EVENT_NO", commandMap.get("EVENT_NO"));

		return mv;
	}

	@RequestMapping(value = "/eventDelete.do")
	public ModelAndView eventDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/eventList.do");

		eventService.deleteEvent(commandMap.getMap(), request);

		return mv;
	}

}

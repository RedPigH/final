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

	
	@RequestMapping(value = "/admin/eventList.do")
	public ModelAndView eventList(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/event/eventList");

		List<Map<String, Object>> eventList = eventService.selectEventList(commandMap);
		mv.addObject("eventList", eventList);

		return mv;
	}

	@RequestMapping(value = "/userEventList.do")

	public ModelAndView userEventList(Map<String, Object> commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/event/userEventList");

		String event_no = request.getParameter("event_no");

		List<Map<String, Object>> eventList = eventService.selectEventList(commandMap);
		mv.addObject("event_no", event_no);

		mv.addObject("eventList", eventList);

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

		mv.setViewName("jsonView");
		return mv;
	}

	@RequestMapping(value = "/eventModifyForm.do")
	public ModelAndView movieModifyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/event/eventModify");

		Map<String, Object> map = eventService.selectEventDetail(commandMap.getMap());

		mv.addObject("map", map.get("map"));
		mv.addObject("eventDetail", map.get("eventDetail"));
		mv.addObject("fileList", map.get("fileList"));

		return mv;
	}

	@RequestMapping(value = "/eventModify.do")
	public ModelAndView modifyEvent(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/eventList.do");

		eventService.modifyEvent(commandMap.getMap(), request);
	
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

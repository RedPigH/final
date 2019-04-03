package com.moviecube.screen;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;
import com.moviecube.common.Paging;
import com.moviecube.seat.SeatService;
import com.moviecube.cinema.CinemaService;

@RequestMapping(value = "/admin")
@Controller
public class ScreenController {

	@Resource(name = "screenService")
	private ScreenService screenService;
	
	@Resource(name = "cinemaService")
	private CinemaService cinemaService;
	
	@Resource(name = "seatService")
	private SeatService seatService;
	
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockpaging = 5;
	private String pagingHtml;
	private Paging paging;
	private String screen_no;

	@RequestMapping(value = "/screenList.do")
	public ModelAndView screenList(CommandMap commandMap, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView();
		
		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Map<String, Object>> screenList = null;
		String isSearch  = null;
		int searchNum = 0;
		
		isSearch = request.getParameter("isSearch");
		
		CommandMap smap = new CommandMap();
		
		if(isSearch != null){
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			
			if(searchNum == 0){
				smap.put("CINEMA_NAME", isSearch);
				screenList = screenService.screenSearch(smap.getMap());
			}else if(searchNum == 1){
				smap.put("SCREEN_TYPE", isSearch);
				screenList = screenService.screenSearch(smap.getMap());
			}else{
				smap.put("SCREEN_NAME", isSearch);
				screenList = screenService.screenSearch(smap.getMap());
			}
			
			totalCount = screenList.size();
			paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "memberList", searchNum, isSearch);
			pagingHtml = paging.getPagingHtml().toString();
			
			int lastCount = totalCount;
			
			if(paging.getEndCount() < totalCount){
				lastCount = paging.getEndCount() + 1;
			}
			
			screenList = screenList.subList(paging.getStartCount(), lastCount);
			mv.addObject("currentPage", currentPage);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("screenList", screenList);
			mv.setViewName("/admin/screen/screenList");
			return mv;
		}
		
		screenList = screenService.selectScreenList(commandMap.getMap());
		
		totalCount = screenList.size();
		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "screenList");
		pagingHtml = paging.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if(paging.getEndCount() < totalCount){
			lastCount = paging.getEndCount() + 1;
		}
		
		screenList = screenList.subList(paging.getStartCount(), lastCount);
		
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("screenList", screenList);
		mv.setViewName("/admin/screen/screenList");
		return mv;
	}
	
	@RequestMapping(value = "/screenDetail.do")
	public ModelAndView screenDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if(commandMap.containsKey("SCREEN_NO")) {
			screen_no = (String) commandMap.get("SCREEN_NO");
		}else {
			commandMap.put("SCREEN_NO", screen_no);
		}
		Map<String, Object> map = screenService.screenDetail(commandMap.getMap());
		
		List<Map<String, Object>> seatList = seatService.selectScreenSeat(map);
		
		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		totalCount = seatList.size();
		
		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "screenDetail");
		pagingHtml = paging.getPagingHtml().toString();
		
		int lastCount = totalCount;
	
		if (paging.getEndCount() < totalCount) {
			lastCount = paging.getEndCount() + 1;
		}

		seatList = seatList.subList(paging.getStartCount(), lastCount);
		
		mv.addObject("seatList", seatList);
		mv.addObject("list", seatList);
		mv.addObject("SCREEN_NO", commandMap.get("SCREEN_NO"));
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("totalCount", totalCount);
		mv.setViewName("/admin/screen/screenDetail");
		mv.addObject("map", map);

		return mv;
	}

	@RequestMapping(value = "/screenWriteForm.do")
	public ModelAndView screenWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/screen/screenWrite");
		
		List<Map<String, Object>> cinemalist = cinemaService.selectCinemaList(commandMap.getMap());
		
		mv.addObject("cinemalist", cinemalist);

		return mv;
	}

	@RequestMapping(value = "/screenWrite.do")
	public ModelAndView screenWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/screenList.do");

		screenService.insertScreen(commandMap.getMap());

		return mv;
	}

	@RequestMapping(value = "/screenModifyForm.do")
	public ModelAndView screenUpdateForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/screen/screenModify");

		Map<String, Object> map = screenService.screenDetail(commandMap.getMap());

		mv.addObject("map", map);

		return mv;
	}

	@RequestMapping(value = "/screenModify.do")
	public ModelAndView screenUpdate(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/screenDetail.do");

		screenService.updateScreen(commandMap.getMap());

		mv.addObject("SCREEN_NO", commandMap.get("SCREEN_NO"));

		return mv;
	}

	@RequestMapping(value = "/screenDelete.do")
	public ModelAndView screenDelete(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/screenList.do");

		screenService.deleteScreen(commandMap.getMap());
		mv.addObject("currentPage", commandMap.get("currentPage"));
		return mv;
	}

	@RequestMapping(value = "/deleteSeat.do")
	public ModelAndView deleteSeat(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/screenDetail.do");
		
		seatService.deleteSeat(commandMap.getMap());
		mv.addObject("currentPage", commandMap.get("currentPage"));
		return mv;
	}
}
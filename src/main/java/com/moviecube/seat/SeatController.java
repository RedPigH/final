package com.moviecube.seat;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;
import com.moviecube.common.Paging;
import com.moviecube.screen.ScreenService;

@RequestMapping(value = "/admin")
@Controller
public class SeatController {
	
	@Resource(name = "seatService")
	private SeatService seatService;
	
	@Resource(name = "screenService")
	private ScreenService screenService;
	
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockpaging = 10;
	private String pagingHtml;
	private Paging paging;
	
	
	@RequestMapping(value = "/seatList.do")
	public ModelAndView selectSeatList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Map<String, Object>> seatList = null;
		String isSearch  = null;
		int searchNum = 0;
		
		isSearch = request.getParameter("isSearch");
		
		if(isSearch != null){
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			
			if(searchNum == 0){
				seatList = seatService.seatSearch0(isSearch);
			}
			
			totalCount = seatList.size();
			paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "seatList", searchNum, isSearch);
			pagingHtml = paging.getPagingHtml().toString();
			
			int lastCount = totalCount;
			
			if(paging.getEndCount() < totalCount){
				lastCount = paging.getEndCount() + 1;
			}
			
			seatList = seatList.subList(paging.getStartCount(), lastCount);
			mv.addObject("currentPage", currentPage);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("seatList", seatList);
			mv.setViewName("/admin/seat/seatList");
			return mv;
		
		}
			
		seatList = seatService.selectSeatList(commandMap.getMap());
		
		totalCount = seatList.size();
		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "seatList");
		pagingHtml = paging.getPagingHtml().toString();
			
		int lastCount = totalCount;
			
		if(paging.getEndCount() < totalCount){
			lastCount = paging.getEndCount() + 1;
		}
			
		seatList = seatList.subList(paging.getStartCount(), lastCount);
			
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("seatList", seatList);
		mv.setViewName("/admin/seat/seatList");
		return mv;
	}
	
	@RequestMapping(value = "/insertSeatForm.do")
	public ModelAndView insertSeatForm(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/admin/seat/seatWrite");
		
		List<Map<String, Object>> screenList = screenService.selectScreenList(commandMap.getMap());
		mv.addObject("screenList", screenList);
		
		return mv;
	}
	
	@RequestMapping(value = "/insertSeat.do")
	public ModelAndView insertSeat(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/insertSeatForm.do");
		
		commandMap.put("SCREEN_NO", request.getParameter("selectScreen"));
		
		// 행,열 값을 받아옴
		int row = Integer.parseInt(request.getParameter("row"));
		int col = Integer.parseInt(request.getParameter("col"));
		
		// 받아온 행열 값을 통하여 좌석 생성 행(A~H) 열(10~15) 정도로 예상중
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= col; j++) {
				commandMap.put("SEAT_ROW", i);
				commandMap.put("SEAT_COL", j);
				
				seatService.insertSeat(commandMap.getMap());
				
				commandMap.remove("SEAT_ROW");
				commandMap.remove("SEAT_COL");
			}	
		}	
		return mv;
	}
	
	/*
	 * @RequestMapping(value = "/deleteSeat.do") public ModelAndView
	 * deleteSeat(CommandMap commandMap) throws Exception{ ModelAndView mv = new
	 * ModelAndView("redirect:/admin/seatList.do");
	 * 
	 * seatService.deleteSeat(commandMap.getMap()); mv.addObject("currentPage",
	 * commandMap.get("currentPage")); return mv; }
	 */
	
	/*
	 * @RequestMapping(value = "/TimeSeatList.do") public ModelAndView
	 * TimeSeatList(CommandMap commandMap) throws Exception{ ModelAndView mv = new
	 * ModelAndView("/timeSeatList");
	 * 
	 * List<Map<String, Object>> list =
	 * seatService.selectTimeSeat(commandMap.getMap()); mv.addObject("timeSeatList",
	 * list);
	 * 
	 * return mv; }
	 */

}

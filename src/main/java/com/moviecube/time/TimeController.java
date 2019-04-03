package com.moviecube.time;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;
import com.moviecube.movie.MovieService;
import com.moviecube.screen.ScreenService;
import com.moviecube.seat.SeatService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.moviecube.cinema.CinemaService;
import com.moviecube.common.Paging;

@RequestMapping(value = "/admin")
@Controller
public class TimeController {

	@Resource(name = "timeService")
	private TimeService timeService;

	@Resource(name = "seatService")
	private SeatService seatService;

	@Resource(name = "screenService")
	private ScreenService screenService;

	@Resource(name = "movieService")
	private MovieService movieService;

	@Resource(name = "cinemaService")
	private CinemaService cinemaService;

	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockpaging = 5;
	private String pagingHtml;
	private Paging paging;

	@RequestMapping(value = "/timeList.do")
	public ModelAndView timeList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Map<String, Object>> timeList = null;
		String isSearch  = null;
		int searchNum = 0;
				
		isSearch = request.getParameter("isSearch");
		
		CommandMap smap = new CommandMap();
		
		if(isSearch != null){
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			
			if(searchNum == 0){
				smap.put("MOVIE_NAME", isSearch);
				timeList = timeService.timeSearch(smap.getMap());
			}else if(searchNum == 1){
				smap.put("CINEMA_NAME", isSearch);
				timeList = timeService.timeSearch(smap.getMap());
			}else if(searchNum == 2){
				smap.put("SCREEN_NAME", isSearch);
				timeList = timeService.timeSearch(smap.getMap());
			}
		
			totalCount = timeList.size();
			paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "timeList", searchNum, isSearch);
			pagingHtml = paging.getPagingHtml().toString();
			
			int lastCount = totalCount;
			
			if(paging.getEndCount() < totalCount){
				lastCount = paging.getEndCount() + 1;
			}
			
			timeList = timeList.subList(paging.getStartCount(), lastCount);
			mv.addObject("currentPage", currentPage);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("timeList", timeList);
			mv.setViewName("/admin/time/timeList");
			return mv;
		}
		
		timeList = timeService.selectTimeList(commandMap.getMap());
		
		totalCount = timeList.size();
		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "timeList");
		pagingHtml = paging.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if(paging.getEndCount() < totalCount){
			lastCount = paging.getEndCount() + 1;
		}
		
		timeList = timeList.subList(paging.getStartCount(), lastCount);
		
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("timeList", timeList);
		mv.setViewName("/admin/time/timeList");
		return mv;
		
	}

	@RequestMapping(value = "/timeDetail.do")
	public ModelAndView timeSelectOne(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/time/timeDetail");

		Map<String, Object> map = timeService.timeDetail(commandMap.getMap());
		mv.addObject("map", map);
		mv.addObject("currentPage", commandMap.get("currentPage"));

		return mv;
	}

	@RequestMapping(value = "/timeWriteForm.do")
	public ModelAndView timeWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/time/timeWrite");

		List<Map<String, Object>> movieList = movieService.selectMovieList(commandMap.getMap());
		List<Map<String, Object>> screenList = screenService.selectScreenList(commandMap.getMap());
		List<Map<String, Object>> cinemaList = cinemaService.selectCinemaList(commandMap.getMap());

		mv.addObject("movieList", movieList);
		mv.addObject("screenList", screenList);
		mv.addObject("cinemaList", cinemaList);

		return mv;
	}

	@RequestMapping(value = "/timeWrite.do")
	public ModelAndView timeWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/timeList.do");

		commandMap.put("MOVIE_NO", request.getParameter("selectMovie"));
		commandMap.put("CINEMA_NO", request.getParameter("selectCinema"));
		commandMap.put("SCREEN_NO", request.getParameter("selectScreen"));

		// 상영 시간표 생성
		timeService.insertTime(commandMap.getMap());

		// 생성된 시간표에 대한 좌석 생성
		CommandMap screenSeatmap = new CommandMap();
		CommandMap timeSeatmap = new CommandMap();

		screenSeatmap.put("SCREEN_NO", commandMap.get("SCREEN_NO"));

		List<Map<String, Object>> seatlist = seatService.selectScreenSeat(screenSeatmap.getMap());

		for (int i = 0; i < seatlist.size(); i++) {
			timeSeatmap.put("SEAT_NO", seatlist.get(i).get("SEAT_NO"));
			seatService.insertTimeSeat(timeSeatmap.getMap());
		}

		return mv;
	}

	/*
	 * @RequestMapping("/selectscreenList.do")
	 * 
	 * @ResponseBody public ResponseEntity<List<Map<String, Object>>>
	 * selectscreenList(String cinema_no) throws Exception {
	 * ResponseEntity<List<Map<String, Object>>> entity = null; CommandMap smap =
	 * new CommandMap(); smap.put("CINEMA_NO", cinema_no); try { List<Map<String,
	 * Object>> screenlist = cinemaService.selectCinemaScreen(smap.getMap()); entity
	 * = new ResponseEntity<List<Map<String, Object>>>(screenlist, HttpStatus.OK); }
	 * catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return entity; }
	 */
	@RequestMapping(value = "/timeModifyForm.do")
	public ModelAndView timeUpdateForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/time/timeModify");

		Map<String, Object> map = timeService.timeDetail(commandMap.getMap());
		List<Map<String, Object>> movieList = movieService.selectMovieList(commandMap.getMap());
		List<Map<String, Object>> screenList = screenService.selectScreenList(commandMap.getMap());
		List<Map<String, Object>> cinemaList = cinemaService.selectCinemaList(commandMap.getMap());

		mv.addObject("movieList", movieList);
		mv.addObject("screenList", screenList);
		mv.addObject("cinemaList", cinemaList);
		mv.addObject("map", map);

		return mv;
	}

	@RequestMapping(value = "/timeModify.do")
	public ModelAndView timeUpdate(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/timeDetail");

		timeService.updateTime(commandMap.getMap());

		mv.addObject("TIME_NO", commandMap.get("TIME_NO"));

		return mv;
	}

	@RequestMapping(value = "/timeDelete.do")
	public ModelAndView timeDelete(CommandMap commandMap) throws Exception {

		ModelAndView mv = new ModelAndView("redirect:/admin/timeList.do");

		timeService.deleteTime(commandMap.getMap());
		mv.addObject("currentPage", commandMap.get("currentPage"));

		return mv;
	}

	@RequestMapping(value = "/ScreenSelect.do")
	@ResponseBody
	public ModelAndView selectAjaxScreen(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView();

		CommandMap map = new CommandMap();

		map.put("MOVIE_NO", request.getParameter("MOVIE_NO"));
		map.put("CINEMA_NO", request.getParameter("CINEMA_NO"));

		List<Map<String, Object>> screenList = screenService.selectCinemaScreen(map.getMap());
		
		mv.setViewName("jsonView");
		mv.addObject("result", screenList);
		
		return mv;
	}
}

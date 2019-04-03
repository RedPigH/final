package com.moviecube.reserve;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.cinema.CinemaService;
import com.moviecube.common.CommandMap;
import com.moviecube.member.MemberService;
import com.moviecube.movie.MovieService;
import com.moviecube.seat.SeatService;
import com.moviecube.time.TimeService;

@Controller
public class ReserveController {
	@Resource(name = "reserveService")
	private ReserveService reserveService;
	
	@Resource(name = "timeService")
	private TimeService timeService;
	
	@Resource(name = "seatService")
	private SeatService seatService;
	
	@Resource(name = "cinemaService")
	private CinemaService cinemaService;
	
	@Resource(name = "movieService")
	private MovieService movieService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	

	@RequestMapping(value = "/reserve_selectSeat.do")
	   public ModelAndView reserve_seat(CommandMap commandMap, HttpServletRequest request) throws Exception {
	      ModelAndView mv = new ModelAndView("reserve/reserve_selectSeat");
	      
	      CommandMap timeSeatMap = new CommandMap();
	      CommandMap screenMap = new CommandMap();
	      CommandMap timeMap = new CommandMap();
	      
	      //나중에 Step1 완료해서 Map으로 변경함  -국
	      timeSeatMap.put("TIME_NO", commandMap.get("time_no"));
	      screenMap.put("SCREEN_NO", commandMap.get("screen_no"));
	      timeMap.put("TIME_NO", commandMap.get("time_no"));
	      
	      List<Map<String, Object>> unableSeatList = seatService.unableTimeSeat(timeSeatMap.getMap());
	      Map<String, Object> seatnum = seatService.ScreenSeatNum(screenMap.getMap());
	      Map<String, Object> time = timeService.timeDetail(timeMap.getMap());
	      
	      int row = Integer.parseInt(seatnum.get("ROW_NUM").toString());
	      int col = Integer.parseInt(seatnum.get("COL_NUM").toString());
	      String seats = "";
	      String unableseats = "";
	      
	      for(int i = 0; i < row; i++) {
	         
	         for(int j = 0; j < col; j++) {
	            seats +="a";
	         }
	         if(i == row-1) continue;
	         else seats += ",";
	      }
	      
	      for(int i = 0; i < unableSeatList.size(); i++) {
	    	  unableseats += unableSeatList.get(i).get("SEAT_ROW").toString() + "_" + unableSeatList.get(i).get("SEAT_COL").toString();
	    	  if(i == unableSeatList.size() -1) continue;
	    	  else unableseats += ",";
	      }

	      //시간별 좌석 리스트
	      mv.addObject("unableseats", unableseats);
	      //좌석 행 열 String
	      mv.addObject("seats", seats);
	      //상영 정보
	      mv.addObject("time", time);
	      
	      return mv;
	   }
	
	@RequestMapping(value = "/reserve.do")
	public ModelAndView reserveMain(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("reserve/reserve_main");
		
		
		List<Map<String, Object>> alltimeList = timeService.selectAllTimeList(commandMap.getMap());
		
		List<Map<String, Object>> cinemaList =  cinemaService.selectCinemaList(commandMap.getMap());
		
		List<Map<String, Object>> movieList = movieService.dupMovieList(commandMap.getMap());
		
		if(request.getParameter("CheckedBoxList") != null) {
			String[] selectedMovieList = request.getParameter("CheckedBoxList").split(",");
			
			mv.addObject("selectedMovieList", selectedMovieList);
		}
		
	    mv.addObject("movieList", movieList);
		mv.addObject("alltimeList", alltimeList);
		mv.addObject("cinemaList", cinemaList);
		
		return mv;
	}
	
	@RequestMapping(value = "/reserve/movieSelect.do")
	public ModelAndView movieSelect(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("reserve/reserve_main");
		
		String selectedDate = request.getParameter("selectedDate");
		String[] var = request.getParameterValues("cinemaNo");
		String[] var2 = request.getParameterValues("movieName");
		
		CommandMap map = new CommandMap();
		
		map.put("TIME_DATE", selectedDate);
		
		for(int i = 0; i < var.length ; i++) {
			map.put("CINEMA_NO" + i, var[i]);			
		}
		
		for(int i = 0; i < var2.length ; i++) {
			map.put("MOVIE_NAME" + i, var2[i]);			
		}
	
		List<Map<String, Object>> optionTimeList = timeService.optionTimeList(map.getMap());
		
		mv.setViewName("jsonView");
		mv.addObject("optionTimeList", optionTimeList);
		
		return mv;
	}
	
	@RequestMapping(value = "/reserve_confirm.do")
	public ModelAndView reserveConfirm(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("reserve/reserve_confirm");
		
		CommandMap map = new CommandMap();
		
		map.put("TIME_NO", commandMap.get("TIME_NO"));
		
		Map<String,Object> timemap = timeService.timeDetail(map.getMap());
		
		String timeDate = timemap.get("TIME_DATE").toString();
		timeDate = timeDate.substring(0, 10);
	
		
		mv.setViewName("jsonView");
		mv.addObject("timeDate", timeDate);
		mv.addObject("selectSeats",commandMap.get("SELECT_SEATS"));
		mv.addObject("totalprice", commandMap.get("TOTAL_PRICE"));
		mv.addObject("time", timemap);
		
		return mv;
	}
	
	@RequestMapping(value = "/reserve_complete.do")
	public ModelAndView reserveComplete(CommandMap commandMap, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:main.do");
		
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("userLoginInfo");
		
		CommandMap reserveMap = new CommandMap();
		CommandMap seatMap = new CommandMap();
		CommandMap updateMap = new CommandMap();
		CommandMap userMap = new CommandMap();
		
		//insert reservation
		reserveMap.put("TIME_NO", commandMap.get("TIME_NO"));
		reserveMap.put("TOTAL_PRICE", commandMap.get("TOTAL_PRICE"));
		
		//need reserveMap add "MEMBER_NO" get session value
		reserveMap.put("MEMBER_NO", user.get("MEMBER_NO"));
		
		reserveService.insertReservation(reserveMap.getMap());
		
		//res_seat insert and time_seat status update
		seatMap.put("SCREEN_NO", commandMap.get("SCREEN_NO"));
		updateMap.put("TIME_NO", commandMap.get("TIME_NO"));
		updateMap.put("STATUS", 1);	// reserve unable 
		
		String[] selectSeats = commandMap.get("selectSeats").toString().split(",");
		
		for(String s : selectSeats) {
			String[] temp = s.split("-");
			seatMap.put("SEAT_ROW", temp[0]);
			seatMap.put("SEAT_COL", temp[1]);
			
			Map<String, Object> SeatNoMap = seatService.selectSeatNo(seatMap.getMap());
			
			//res_seat insert
			seatService.insertResSeat(SeatNoMap);
			
			//time_seat update
			updateMap.put("SEAT_NO", SeatNoMap.get("SEAT_NO"));
			seatService.updateSeatStatus(updateMap.getMap());
			
			seatMap.remove("SEAT_ROW");
			seatMap.remove("SEAT_COL");
			updateMap.remove("SEAT_NO");
		}
		
		//update Mile 
		int totalprice = Integer.parseInt(commandMap.get("TOTAL_PRICE").toString());
				
		userMap.put("MEMBER_NO", user.get("MEMBER_NO"));
		userMap.put("MEMBER_MILE", totalprice/10);
		
		memberService.updateMile(userMap.getMap()); 
		
		//update grade
		Map<String, Object> MemberInfoMap = memberService.selectOneMember(userMap.getMap());
		
		if(MemberInfoMap.get("MEMBER_RANK").equals("일반") && Integer.parseInt(MemberInfoMap.get("MEMBER_MILE").toString()) >= 10000) {
			userMap.put("MEMBER_RANK", "우수");

			memberService.updateRank(userMap.getMap());
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/reserve_delete.do")
	public ModelAndView deleteReservation(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/member/myPage.do");
		String res_no = request.getParameter("res_no");
		commandMap.put("RES_NO", res_no);
		
		reserveService.deleteReservation(commandMap.getMap());
		
		return mv;
	}
}

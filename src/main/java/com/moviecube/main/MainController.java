package com.moviecube.main;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.moviecube.common.CommandMap;
import com.moviecube.movie.MovieService;
import com.moviecube.wishlist.WishListService;

@Controller
@EnableWebMvc
public class MainController {

	@Resource(name = "movieService")
	private MovieService movieService;

	@Resource(name = "wishlistService")
	private WishListService wishlistService;
	
	@RequestMapping(value = "/main.do")
	public ModelAndView openBoardList(CommandMap commandMap, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/main");

		if(session.getAttribute("userLoginInfo") != null){
				
			@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) session.getAttribute("userLoginInfo");
			
			CommandMap map = new CommandMap();
			
			map.put("MEMBER_NO", user.get("MEMBER_NO"));
			
			List<Map<String, Object>> wish = wishlistService.selectWishList(map.getMap());
			
			session.setAttribute("WishList", wish);
			
			mv.addObject("WishList", wish);
		  }

		
		List<Map<String, Object>> list = movieService.selectMovieList(commandMap.getMap());
		
		List<Map<String, Object>> HotList = movieService.HotMovieList(commandMap.getMap()); 
		
		List<Map<String, Object>> LatelyList = movieService.LatelyMovieList(commandMap.getMap());
		
		List<Map<String, Object>> ExpectedList = movieService.ExpectedMovieList(commandMap.getMap());
		
		List<Map<String, Object>> GradeMovieList = movieService.GradeMovieList(commandMap.getMap());
		
		List<Map<String, Object>> CommentMovieList = movieService.CommentMovieList(commandMap.getMap());
		
		
		mv.addObject("list", list);
		mv.addObject("HotList", HotList);
		mv.addObject("LatelyList", LatelyList);
		mv.addObject("ExpectedList", ExpectedList);
		mv.addObject("GradeMovieList", GradeMovieList);
		mv.addObject("CommentMovieList", CommentMovieList);
		
		mv.addObject(mv);
		
		return mv;
	}
	
	@RequestMapping(value = "/movieSearch.do")
	public ModelAndView movieList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("main/searchedList");
		
		CommandMap searchKeyword = new CommandMap();
		searchKeyword.put("searchKeyword", commandMap.get("searchKeyword"));

		List<Map<String, Object>> movieList = movieService.MainMovieSearch(searchKeyword.getMap());
		mv.addObject("movieList", movieList);

		return mv;
	}

}
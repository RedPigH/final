package com.moviecube.movie;

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

@Controller
public class MovieController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "movieService")
	private MovieService movieService;

	@RequestMapping(value = "/movieList.do")
	public ModelAndView movieList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("movieList");

		List<Map<String, Object>> movieList = movieService.selectMovieList(commandMap.getMap());
		mv.addObject("movieList", movieList);

		return mv;
	}

	@RequestMapping(value = "/movieWriteForm.do")
	public ModelAndView movieWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("movieWriteForm");

		return mv;
	}

	@RequestMapping(value = "/movieWrite.do")
	public ModelAndView movieWrtie(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/movieList");

		movieService.insertMovie(commandMap.getMap(), request);

		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/movieDetail.do")
	public ModelAndView movieDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/movieDetail");

		Map<String, Object> map = movieService.selectMovieDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("movieDetail", map.get("movieDetail"));

		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/movieDetail2.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView movieDetail2(CommandMap commandMap, HttpServletRequest request) throws Exception { // 모달을 위한 메서드
		ModelAndView mv = new ModelAndView("redirect:/main");

		String movie_no = request.getParameter("movie_no");
		commandMap.put("MOVIE_NO", movie_no);

		Map<String, Object> map = movieService.selectMovieDetail(commandMap.getMap());
		Map<String, Object> map2 = (Map<String, Object>) map.get("map");

		String openDate = map2.get("MOVIE_OPENDATE").toString();
		openDate = openDate.substring(0, 10);
		
		Map<String, Object> map3 = movieService.selectCommentCount(commandMap.getMap()); // 코멘트 카운트 가져올려고
		mv.addObject("comment_count", map3.get("COMMENT_CNT"));
		/*
		map3.clear();
		map3 = movieService.selectHotMovie(commandMap.getMap());
		map.put("RANKING", map3.get("RNUM"));
		*/

		mv.addObject("map", map.get("map"));
		
		//String content = (String)map2.get("MOVIE_CONTENT");
		//content.replaceAll("<\n\r>", "")

		mv.addObject("openDate", openDate);
		mv.setViewName("jsonView");

		return mv;
	}

	@RequestMapping(value = "/movieModify.do")
	public ModelAndView movieModify(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/movieDetail");

		movieService.modifyMovie(commandMap.getMap(), request);

		mv.addObject("MOVIE_NO", commandMap.get("MOVIE_NO"));

		return mv;
	}

	@RequestMapping(value = "/movieDelete.do")
	public ModelAndView movieDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/movieList");

		movieService.deleteMovie(commandMap.getMap(), request);

		return mv;
	}

	@RequestMapping(value = "/stillcut.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView stillCut(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/main");

		String movieNo = request.getParameter("movie_no");

		commandMap.put("MOVIE_NO", movieNo);

		List<Map<String, Object>> stillcut_list = movieService.selectStillCutList(commandMap.getMap());

		mv.setViewName("jsonView");
		mv.addObject("stillcut_list", stillcut_list);

		return mv;
	}

	@RequestMapping(value = "/movieComment.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView commentList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/main");

		String movie_no = request.getParameter("movie_no");

		commandMap.put("MOVIE_NO", movie_no);
		List<Map<String, Object>> comment_list = movieService.selectCommentList(commandMap.getMap());

		mv.setViewName("jsonView");
		mv.addObject("comment_list", comment_list);

		return mv;
	}

	@RequestMapping(value = "/commentPaging.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView commentPaingList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/main");

		String movie_no = request.getParameter("movie_no");
		String commentHtml = "";

		int currentPage; // 현재페이지
		int totalCount = 0; // 전체 게시물 수
		int totalPage; // 전체 페이지 수
		int blockCount = 5; // 한 페이지의 게시물의 수
		int blockPage = 5; // 한 화면에 보여줄 페이지 수
		int startCount; // 한 페이지에서 보여줄 게시글의 시작 번호
		int endCount; // 한 페이지에서 보여줄 게시글의 끝 번호
		int startPage; // 시작 페이지
		int endPage; // 마지막 페이지

		currentPage = Integer.parseInt(request.getParameter("currentPage")); // 클릭한 페이지

		startPage = (int) ((currentPage - 1) / blockPage) * blockPage + 1;
		endPage = startPage + blockPage - 1;

		commandMap.put("MOVIE_NO", movie_no);

		totalCount = Integer
				.parseInt((movieService.selectCommentCount(commandMap.getMap())).get("COMMENT_CNT").toString());
		totalPage = (int) Math.ceil((double) totalCount / blockCount); // 전체페이지 수 = 전체게시글의 수 / 한 화면에 보여줄 게시글의 수

		if (currentPage > blockPage)
			commentHtml += "<a id='prevPage' onclick='get_reviews(" + (startPage - 1) + ")'> 이전 </a> &nbsp;";

		if (totalCount != 0) {
			String selected = "";

			for (int i = startPage; i <= endPage; i++) {
				if (i > totalPage) {
					break;
				}

				if (i == currentPage) {
					selected = "<Strong>" + i + "<Strong>";
				} else {
					selected = " " + i;
				} // if문 끝

				commentHtml += "<a id='page" + i + "' onclick='get_reviews(" + i + ")' >" + selected + "</a> &nbsp;";
			} // for문 끝
		} // if문 끝

		if (totalPage - startPage >= blockPage) {
			commentHtml += "<a id='prevPage' onclick='get_reviews(" + (endPage + 1) + ")'> 다음 </a> &nbsp;";
		}

		startCount = ((currentPage - 1) * blockCount) + 1; // 만약 현재 선택한 페이지가 2페이지면 1 (2-1) * 5 + 1 = 6
		endCount = startCount + blockCount - 1; // 6 + 5 - 1 = 10 그래서 6~10번까지 5개 띄워줄 수 있도록 설정.

		commandMap.put("START_COUNT", startCount);
		commandMap.put("END_COUNT", endCount);

		List<Map<String, Object>> comment_paging_list = movieService.selectCommentPaingList(commandMap.getMap());

		// 시작 페이지와 마지막 페이지 값 구하기.
		startPage = (int) ((currentPage - 1) / blockPage) * blockPage + 1; // 현재페이지가 6페이지라면 시작페지이지는 6부터 10까지 만들어주기 위한
																			// 변수설정.
		endPage = startPage + blockPage - 1;

		mv.addObject("commentHtml", commentHtml);
		mv.addObject("totalCount", totalCount);

		mv.setViewName("jsonView");
		mv.addObject("comment_paging_list", comment_paging_list);

		return mv;
	}

	@RequestMapping(value = "/writeComment.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView writeComment(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/main");

		String member_id = request.getParameter("member_id");
		String cmt_content = request.getParameter("cmt_content");
		String cmt_like = request.getParameter("cmt_like");
		String movie_no = request.getParameter("movie_no");
		boolean id_check = true; // true면 쓸 수 있음

		CommandMap map = new CommandMap();

		map.put("CMT_ID", member_id);
		map.put("CMT_CONTENT", cmt_content);
		map.put("CMT_LIKE", cmt_like);
		map.put("MOVIE_NO", movie_no);

		// 리뷰 이미 썼으면 다시 못쓰게
		List<Map<String, Object>> comment_list = movieService.selectCommentList(map.getMap());
		for (int i = 0; i < comment_list.size(); i++) {
			if ((comment_list.get(i).get("CMT_ID")).equals(member_id)) {
				id_check = false;
				break;
			}
		}

		if (id_check)
			movieService.insertComment(map.getMap());

		/* 여기서부터 영화 평점 업데이트 */
		Map<String, Object> ReviewMap = movieService.CommentLikeInfo(map.getMap());

		int all_like = Integer.parseInt(ReviewMap.get("ALL_LIKE").toString());
		int cnt = Integer.parseInt(ReviewMap.get("CNT").toString());

		double grade = all_like / (double)cnt;

		CommandMap Grademap = new CommandMap();

		Grademap.put("MOVIE_NO", movie_no);
		Grademap.put("MOVIE_GRADE", String.format("%.1f", grade));

		movieService.modifyGrade(Grademap.getMap());

		mv.addObject("id_check", id_check);
		mv.addObject("cnt", cnt);
		
		mv.setViewName("jsonView");

		return mv;
	}

	@RequestMapping(value = "/deleteComment.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView deleteComment(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/main");

		String cmt_no = request.getParameter("cmt_no");
		commandMap.put("CMT_NO", cmt_no);
		movieService.deleteComment(commandMap.getMap());

		/* 영화 평점 업데이트 */
		String movie_no = request.getParameter("movie_no");
		commandMap.put("MOVIE_NO", movie_no);

		Map<String, Object> ReviewMap = movieService.CommentLikeInfo(commandMap.getMap());
		CommandMap Grademap = new CommandMap();

		if (ReviewMap.get("ALL_LIKE") != null) {
			int all_like = Integer.parseInt(ReviewMap.get("ALL_LIKE").toString());

			int cnt = Integer.parseInt(ReviewMap.get("CNT").toString());

			double grade = (double) all_like / cnt;

			Grademap.put("MOVIE_NO", movie_no);
			Grademap.put("MOVIE_GRADE", String.format("%.1f", grade));
		} else {

			Grademap.put("MOVIE_NO", movie_no);
			Grademap.put("MOVIE_GRADE", 0);
		}

		movieService.modifyGrade(Grademap.getMap());

		mv.setViewName("jsonView");

		return mv;
	}

}

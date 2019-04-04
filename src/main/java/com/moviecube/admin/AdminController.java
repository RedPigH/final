package com.moviecube.admin;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;
import com.moviecube.movie.MovieService;
import com.moviecube.notice.NoticeService;
import com.moviecube.qna.QnaService;
import com.moviecube.common.Paging;
import com.moviecube.faq.FaqService;
import com.moviecube.store.StoreService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource(name = "movieService")
	private MovieService movieService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@Resource(name = "faqService")
	private FaqService faqService;
	
	@Resource(name = "adminService")
	private AdminService adminService;
	
	@Resource(name = "qnaService")
	private QnaService qnaService;
	
	@Resource(name = "storeService")
	private StoreService storeService;
	
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 5;
	private int blockpaging = 5;
	private String pagingHtml;
	private Paging paging;

	
	@RequestMapping(value="/movieList.do")
	public ModelAndView movieList(CommandMap commandMap, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView();
		
		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Map<String, Object>> movieList = null;
		String isSearch  = null;
		int searchNum = 0;
		
		isSearch = request.getParameter("isSearch");
		
		CommandMap smap = new CommandMap();
		
		if(isSearch != null){
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			
			if(searchNum == 0){
				smap.put("MOVIE_NAME", isSearch);
				movieList = movieService.movieSearch(smap.getMap());
			}else if(searchNum == 1){
				smap.put("MOVIE_DIRECTOR", isSearch);
				movieList = movieService.movieSearch(smap.getMap());
			}else if(searchNum == 2){
				smap.put("MOVIE_ACTOR", isSearch);
				movieList = movieService.movieSearch(smap.getMap());
			}
			
			totalCount = movieList.size();
			paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "movieList", searchNum, isSearch);
			pagingHtml = paging.getPagingHtml().toString();
			
			int lastCount = totalCount;
			
			if(paging.getEndCount() < totalCount){
				lastCount = paging.getEndCount() + 1;
			}
			
			movieList = movieList.subList(paging.getStartCount(), lastCount);
			mv.addObject("currentPage", currentPage);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("movieList", movieList);
			mv.setViewName("/admin/movie/movieList");
			return mv;
		}
		
		movieList = movieService.selectMovieList(commandMap.getMap());
		
		totalCount = movieList.size();
		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "movieList");
		pagingHtml = paging.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if(paging.getEndCount() < totalCount){
			lastCount = paging.getEndCount() + 1;
		}
		
		movieList = movieList.subList(paging.getStartCount(), lastCount);
		
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("movieList", movieList);
		mv.setViewName("/admin/movie/movieList");
		return mv;	
	}
	
	@RequestMapping(value="/movieWriteForm.do")
	public ModelAndView movieWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/movie/movieWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/movieWrite.do")
	public ModelAndView movieWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/movieList.do");
		
		String content2 = (String)commandMap.get("MOVIE_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		commandMap.put("MOVIE_CONTENT", content);
	
		movieService.insertMovie(commandMap.getMap(), request);
		
		return mv;
	}
	
	@RequestMapping(value="/movieWriteForm2.do")
	public ModelAndView movieWriteForm2(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/movie/movieWrite2");
		
		return mv;
	}
	
	@RequestMapping(value="/movieWrite2.do")
	public ModelAndView movieWrtie2(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/movieDetail.do");
		
		int MOVIE_NO = Integer.parseInt((String)commandMap.get("MOVIE_NO"));
		commandMap.getMap().put("MOVIE_NO", MOVIE_NO);
		movieService.insertMovie2(commandMap.getMap(), request);
		
		mv.addObject("MOVIE_NO", commandMap.get("MOVIE_NO"));
		
		return mv;
	}

	@RequestMapping(value="/movieWriteForm3.do")
	public ModelAndView movieWriteForm3(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/movie/movieWrite3");
		
		return mv;
	}
	
	@RequestMapping(value="/movieWrite3.do")
	public ModelAndView movieWrtie3(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/movieDetail.do");
		
		String MOVIE_NO = ((String)commandMap.get("MOVIE_NO"));
		
		commandMap.getMap().put("MOVIE_NO", MOVIE_NO);
		movieService.insertMovie3(commandMap.getMap(), request);
		
		mv.addObject("MOVIE_NO", commandMap.get("MOVIE_NO"));
		
		return mv;
	}

	@RequestMapping(value="/movieDetail.do")
	public ModelAndView movieDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/movie/movieDetail");
		
		Map<String,Object> map = movieService.selectMovieDetail(commandMap.getMap());
		
		mv.addObject("map", map.get("map"));
		mv.addObject("map2", map.get("map2"));
		mv.addObject("currentPage", commandMap.get("currentPage"));
		mv.addObject("movieDetail", map.get("movieDetail"));
	
		mv.addObject("MOVIE_NO", commandMap.get("MOVIE_NO"));
		
		return mv;
	}
	
	@RequestMapping(value="/movieModifyForm.do")
	public ModelAndView movieModifyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/movie/movieModify");

		Map<String, Object> map = movieService.selectMovieDetail(commandMap.getMap());

		mv.addObject("map", map.get("map"));
		mv.addObject("movieDetail", map.get("movieDetail"));
		mv.addObject("fileList", map.get("fileList"));
		
		return mv;
	}
	
	@RequestMapping(value="/movieModify.do")
	public ModelAndView movieModify(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/movieDetail.do");
		
		String content2 = (String)commandMap.get("MOVIE_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		commandMap.put("MOVIE_CONTENT", content);
		
		movieService.modifyMovie(commandMap.getMap(), request);

		mv.addObject("MOVIE_NO", commandMap.get("MOVIE_NO"));


		return mv;
	}

	@RequestMapping(value="/movieModifyForm2.do")
	public ModelAndView movieModifyForm2(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/movie/movieModify2");
		
		Map<String, Object> map = movieService.selectMovieDetail(commandMap.getMap());

		mv.addObject("map", map.get("map"));
		mv.addObject("movieDetail", map.get("movieDetail"));
		mv.addObject("fileList", map.get("fileList"));
		
		return mv;
	}
	
	@RequestMapping(value="/movieModify2.do")
	public ModelAndView movieModify2(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/movieDetail.do");
		
		int MOVIE_NO = Integer.parseInt((String)commandMap.get("MOVIE_NO"));
		commandMap.getMap().put("MOVIE_NO", MOVIE_NO);
		
		movieService.modifyMovie2(commandMap.getMap(), request);
		
		mv.addObject("MOVIE_NO", commandMap.get("MOVIE_NO"));
		
		return mv;
	}


	@RequestMapping(value="/movieModifyForm3.do")
	public ModelAndView movieModifyForm3(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/movie/movieModify3");
		
		Map<String, Object> map = movieService.selectMovieDetail(commandMap.getMap());
	
		mv.addObject("map", map.get("map"));
		mv.addObject("map2", map.get("map2"));
		mv.addObject("movieDetail", map.get("movieDetail"));
		mv.addObject("fileList", map.get("fileList"));
		
		return mv;
	}
	
	@RequestMapping(value="/movieModify3.do")
	public ModelAndView movieModify3(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/movieDetail.do");
		String MOVIE_NO = ((String)commandMap.get("MOVIE_NO"));
//		int MOVIE_NO = Integer.parseInt((String)commandMap.get("MOVIE_NO"));
		commandMap.getMap().put("MOVIE_NO", MOVIE_NO);
		
		movieService.modifyMovie3(commandMap.getMap(), request);
		
		mv.addObject("MOVIE_NO", commandMap.get("MOVIE_NO"));
		
		return mv;
	}

	
	
	@RequestMapping(value="/movieDelete.do")
	public ModelAndView movieDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("redirect:/admin/movieList.do");
		
		movieService.deleteMovie(commandMap.getMap(), request);
		
		mv.addObject("MOVIE_NO", commandMap.get("MOVIE_NO"));
		
		return mv;
	}
	
	@RequestMapping(value = "noticeList.do")
	public ModelAndView noticeList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Map<String, Object>> noticeList = null;
		String isSearch  = null;
		int searchNum = 0;
		
		isSearch = request.getParameter("isSearch");
		
		CommandMap smap = new CommandMap();
		
		if(isSearch != null){
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			
			if(searchNum == 0){
				smap.put("NOTICE_SUB", isSearch);
				noticeList = noticeService.noticeSearch(smap.getMap());
			}else if(searchNum == 1){
				smap.put("NOTICE_CONTENT", isSearch);
				noticeList = noticeService.noticeSearch(smap.getMap());
			}
			
			totalCount = noticeList.size();
			paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "noticeList", searchNum, isSearch);
			pagingHtml = paging.getPagingHtml().toString();
			
			int lastCount = totalCount;
			
			if(paging.getEndCount() < totalCount){
				lastCount = paging.getEndCount() + 1;
			}
			
			noticeList = noticeList.subList(paging.getStartCount(), lastCount);
			mv.addObject("currentPage", currentPage);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("noticeList", noticeList);
			mv.setViewName("/admin/notice/noticeList");
			return mv;
		}
		
		noticeList = noticeService.selectBoardList(commandMap.getMap());
		
		totalCount = noticeList.size();
		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "noticeList");
		pagingHtml = paging.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if(paging.getEndCount() < totalCount){
			lastCount = paging.getEndCount() + 1;
		}
		
		noticeList = noticeList.subList(paging.getStartCount(), lastCount);
		
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("noticeList", noticeList);
		mv.setViewName("/admin/notice/noticeList");
		return mv;
	}
		
	@RequestMapping(value = "/noticeWriteForm.do")
	public ModelAndView noticeWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/notice/noticeWrite");

		return mv;

	}
	
	@RequestMapping(value = "/noticeWrite.do")
	public ModelAndView noticeWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/noticeList.do");
		
		String content2 = (String)commandMap.get("NOTICE_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		commandMap.put("NOTICE_CONTENT", content);
		
		noticeService.insertBoard(commandMap.getMap());

		return mv;

	}
	
	@RequestMapping(value = "/noticeDetail.do")
	public ModelAndView noticeDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/notice/noticeDetail");
		
		Map<String, Object> map = noticeService.selectBoardDetail(commandMap.getMap());
		
		
		mv.addObject("map", map);
		mv.addObject("currentPage", commandMap.get("currentPage"));
		return mv;
	}

	@RequestMapping(value = "/noticeModifyForm.do")
	public ModelAndView noticeModifyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/notice/noticeModify");
		
		Map<String, Object> map = noticeService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map);

		return mv;

	}

	@RequestMapping(value = "/noticeModify.do")
	public ModelAndView noticeModify(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/noticeDetail.do");
		
		String content2 = (String)commandMap.get("NOTICE_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		commandMap.put("NOTICE_CONTENT", content);
		
		noticeService.updateBoard(commandMap.getMap());

		mv.addObject("NOTICE_NO", commandMap.get("NOTICE_NO"));
		return mv;
	}

	@RequestMapping(value = "/noticeDelete.do")
	public ModelAndView noticeDelete(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/noticeList.do");
		
		noticeService.deleteBoard(commandMap.getMap());
		mv.addObject("currentPage", commandMap.get("currentPage"));

		return mv;
	}
	
	@RequestMapping(value = "/faqList.do")
	public ModelAndView faqList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Map<String, Object>> faqList = null;
		String isSearch  = null;
		int searchNum = 0;
		
		isSearch = request.getParameter("isSearch");
		
		CommandMap smap = new CommandMap();
		
		if(isSearch != null){
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			
			if(searchNum == 0){
				smap.put("FAQ_SUB", isSearch);
				faqList = faqService.faqSearch(smap.getMap());
			}else if(searchNum == 1){
				smap.put("FAQ_CONTENT", isSearch);
				faqList = faqService.faqSearch(smap.getMap());
			}
			
			totalCount = faqList.size();
			paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "faqList", searchNum, isSearch);
			pagingHtml = paging.getPagingHtml().toString();
			
			int lastCount = totalCount;
			
			if(paging.getEndCount() < totalCount){
				lastCount = paging.getEndCount() + 1;
			}
			
			faqList = faqList.subList(paging.getStartCount(), lastCount);
			mv.addObject("currentPage", currentPage);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("faqList", faqList);
			mv.setViewName("/admin/faq/faqList");
			return mv;
		}
		
		faqList = faqService.selectFaqList(commandMap.getMap());
		
		totalCount = faqList.size();
		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "faqList");
		pagingHtml = paging.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if(paging.getEndCount() < totalCount){
			lastCount = paging.getEndCount() + 1;
		}
		
		faqList = faqList.subList(paging.getStartCount(), lastCount);
		
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("faqList", faqList);
		mv.setViewName("/admin/faq/faqList");
		return mv;
	}		

	@RequestMapping(value = "/faqWriteForm.do")
	public ModelAndView faqWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/faq/faqWrite");

		return mv;
	}

	@RequestMapping(value = "/faqWrite.do")
	public ModelAndView faqWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/faqList.do");
		String faq_type = request.getParameter("FAQ_TYPE");
		String faq_sub = request.getParameter("FAQ_SUB");
	
		String content2 = (String)commandMap.get("FAQ_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		commandMap.put("FAQ_CONTENT", content);
	
		commandMap.put("FAQ_TYPE", faq_type);
		commandMap.put("FAQ_SUB", faq_sub);
		

		faqService.insertFaq(commandMap.getMap());

		return mv;
	}

	@RequestMapping(value = "/faqDetail.do")
	public ModelAndView faqDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/faq/faqDetail");

		Map<String, Object> map = faqService.selectFaqDetail(commandMap.getMap());
		mv.addObject("map", map);
		mv.addObject("currentPage", commandMap.get("currentPage"));
		
		return mv;
	}

	@RequestMapping(value = "/faqModifyForm.do")
	public ModelAndView faqModifyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/faq/faqModify");

		Map<String, Object> map = faqService.selectFaqDetail(commandMap.getMap());
		mv.addObject("map", map);

		return mv;
	}

	@RequestMapping(value = "/faqModify.do")
	public ModelAndView faqModify(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/faqDetail.do");
		
		String content2 = (String)commandMap.get("FAQ_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		
		commandMap.put("FAQ_CONTENT", content);
		
		faqService.updateFaq(commandMap.getMap());
		mv.addObject("FAQ_NO", commandMap.get("FAQ_NO"));

		return mv;
	}

	@RequestMapping(value = "/faqDelete.do")
	public ModelAndView faqDelete(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/faqList.do");

		faqService.deleteFaq(commandMap.getMap());
		mv.addObject("currentPage", commandMap.get("currentPage"));
		return mv;
	}
	
	@RequestMapping(value = "/memberList.do")
	public ModelAndView memberList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Map<String, Object>> memberList = null;
		String isSearch  = null;
		int searchNum = 0;
		
		isSearch = request.getParameter("isSearch");
		
		CommandMap smap = new CommandMap();
		
		if(isSearch != null){
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			
			if(searchNum == 0){
				smap.put("MEMBER_ID", isSearch);
				memberList = adminService.memberSearch(smap.getMap());
			}else if(searchNum == 1){
				smap.put("MEMBER_NAME", isSearch);
				memberList = adminService.memberSearch(smap.getMap());
			}else if(searchNum == 2){
				smap.put("MEMBER_RANK", isSearch);
				memberList = adminService.memberSearch(smap.getMap());
			}
			
			totalCount = memberList.size();
			paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "memberList", searchNum, isSearch);
			pagingHtml = paging.getPagingHtml().toString();
			
			int lastCount = totalCount;
			
			if(paging.getEndCount() < totalCount){
				lastCount = paging.getEndCount() + 1;
			}
			
			memberList = memberList.subList(paging.getStartCount(), lastCount);
			mv.addObject("currentPage", currentPage);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("memberList", memberList);
			mv.setViewName("/admin/member/memberList");
			return mv;
		}
		
		memberList = adminService.selectMemberList(commandMap.getMap());
		
		totalCount = memberList.size();
		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "memberList");
		pagingHtml = paging.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if(paging.getEndCount() < totalCount){
			lastCount = paging.getEndCount() + 1;
		}
		
		memberList = memberList.subList(paging.getStartCount(), lastCount);
		
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("memberList", memberList);
		mv.setViewName("/admin/member/memberList");
		return mv;
	}
	
	@RequestMapping(value="/memberDetail.do")
	public ModelAndView memberDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/member/memberDetail");
		
		Map<String,Object> map = adminService.selectMemberDetail(commandMap.getMap());
		
		mv.addObject("map", map.get("map"));
		mv.addObject("currentPage", commandMap.get("currentPage"));
		mv.addObject("MEMBER_NO", commandMap.get("MEMBER_NO"));
		
		return mv;
	}
	@RequestMapping(value = "/memberDelete.do")
	public ModelAndView memberDelete(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/memberList.do");
		
		CommandMap map = new CommandMap();
		
		map.put("MEMBER_NO", commandMap.get("MEMBER_NO"));
		adminService.memberDelete(map.getMap());
		
		mv.addObject("currentPage", commandMap.get("currentPage"));
		return mv;
	}
	
	@RequestMapping(value = "/qnaList.do")
	public ModelAndView qnaList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		List<Map<String, Object>> qnaList = qnaService.selectQnaList(commandMap.getMap());
		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		totalCount = qnaList.size();

		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "qnaList");
		pagingHtml = paging.getPagingHtml().toString();

		int lastCount = totalCount;

		if (paging.getEndCount() < totalCount) {
			lastCount = paging.getEndCount() + 1;
		}

		qnaList = qnaList.subList(paging.getStartCount(), lastCount);

		mv.addObject("qnaList", qnaList);
		mv.addObject("list", qnaList);
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("totalCount", totalCount);
		mv.setViewName("/admin/qna/qnaList");
		return mv;
	}

	@RequestMapping(value = "/qnaWriteForm.do")
	public ModelAndView qnaWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/qna/qnaWrite");

		return mv;

	}

	@RequestMapping(value = "/qnaWrite.do")
	public ModelAndView qnaWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/qnaList.do");
		
		String content2 = (String)commandMap.get("QNA_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		
		commandMap.put("QNA_CONTENT", content);
		
		qnaService.insertQna(commandMap.getMap(), request);
		return mv;

	}

	@RequestMapping(value = "/qnaDetail.do")
	public ModelAndView qnaDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/qna/qnaDetail");

		Map<String, Object> cmap = qnaService.checkQnaFile(commandMap.getMap());

		String temp = String.valueOf(cmap.get("CNT"));
		int count = Integer.parseInt(temp);

		if (count == 0) {
			Map<String, Object> map = qnaService.selectQnaDetail1(commandMap.getMap());
			mv.addObject("map", map);
		} else {
			Map<String, Object> map = qnaService.selectQnaDetail2(commandMap.getMap());
			mv.addObject("map", map);
		}

		return mv;
	}

	@RequestMapping(value = "qnaModifyForm.do")
	public ModelAndView qnaModifyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/qna/qnaModify");

		Map<String, Object> cmap = qnaService.checkQnaFile(commandMap.getMap());

		String temp = String.valueOf(cmap.get("CNT"));
		int count = Integer.parseInt(temp);

		if (count == 0) {
			Map<String, Object> map = qnaService.selectQnaDetail1(commandMap.getMap());
			mv.addObject("map", map);
		} else {
			Map<String, Object> map = qnaService.selectQnaDetail2(commandMap.getMap());
			mv.addObject("map", map);
		}
		
		return mv;

	}

	@RequestMapping(value = "/qnaModify.do")
	public ModelAndView qnaModify(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/qnaList.do");
		
		String content2 = (String)commandMap.get("QNA_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		commandMap.put("QNA_CONTENT", content);
		
		qnaService.updateQna(commandMap.getMap(), request);

		return mv;
	}


	@RequestMapping(value = "/qnaDelete.do")
	public ModelAndView qndDelete(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/qnaList.do");
		qnaService.deleteQna(commandMap.getMap());

		return mv;
	}

	@RequestMapping(value = "/qnaReplyForm.do")
	public ModelAndView qnaReplyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/qna/qnaReply");

		Map<String, Object> map = new HashMap();

		map.put("QNA_NOM", commandMap.get("QNA_NOM"));
		map.put("QNA_SUB", commandMap.get("QNA_SUB"));
		mv.addObject("map", map);

		return mv;

	}

	@RequestMapping(value = "/qnaReply.do")
	public ModelAndView qnaReply(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/qnaList.do");
		
		Map<String, Object> map = new HashMap();
		CommandMap updatemap = new CommandMap();
		
		updatemap.put("QNA_NO", commandMap.get("REF"));
		
		String content2 = (String)commandMap.get("QNA_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		commandMap.put("QNA_CONTENT", content);
		
		map.put("REF", commandMap.get("REF"));
		commandMap.put("RE_STEP", 1);
		commandMap.put("RE_LEVEL", 1);
		qnaService.replyQna(commandMap.getMap());
		qnaService.updateQnaStatus(updatemap.getMap());
		
		return mv;
	}

	@RequestMapping(value="/storeList.do")
	public ModelAndView storeList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Map<String, Object>> storeList = null;
		String isSearch  = null;
		int searchNum = 0;
		
		isSearch = request.getParameter("isSearch");
		
		if(isSearch != null){
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			
			if(searchNum == 0){
				storeList = storeService.storeSearch0(isSearch);			
			}
			
			totalCount = storeList.size();
			paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "storeList", searchNum, isSearch);
			pagingHtml = paging.getPagingHtml().toString();
			
			int lastCount = totalCount;
			
			if(paging.getEndCount() < totalCount){
				lastCount = paging.getEndCount() + 1;
			}
			
			storeList = storeList.subList(paging.getStartCount(), lastCount);
			mv.addObject("currentPage", currentPage);
			mv.addObject("pagingHtml", pagingHtml);
			mv.addObject("storeList", storeList);
			mv.setViewName("/admin/store/storeList");
			return mv;
		}
		
		storeList = storeService.selectStoreList(commandMap.getMap());
		
		totalCount = storeList.size();
		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "storeList");
		pagingHtml = paging.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if(paging.getEndCount() < totalCount){
			lastCount = paging.getEndCount() + 1;
		}
		
		storeList = storeList.subList(paging.getStartCount(), lastCount);
		
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("storeList", storeList);
		mv.setViewName("/admin/store/storeList");
		return mv;
	}
	
	@RequestMapping(value="/storeWriteForm.do")
	public ModelAndView storeWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/store/storeWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/storeWrite.do")
	public ModelAndView storeWrtie(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/storeList.do");
		
		String content2 = (String)commandMap.get("STORE_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		commandMap.put("STORE_CONTENT", content);
		
//		int STORE_NO = Integer.parseInt((String)commandMap.get("STORE_NO"));
//		commandMap.getMap().put("STORE_NO", STORE_NO);
		storeService.insertStore(commandMap.getMap(), request);
//		mv.addObject("STORE_NO", commandMap.get("STORE_NO"));
		
		return mv;
	}
	
	@RequestMapping(value="/storeDetail.do")
	public ModelAndView storeDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/store/storeDetail");
		
		Map<String,Object> map = storeService.selectStoreDetail(commandMap.getMap());
		
		mv.addObject("map", map.get("map"));
		mv.addObject("currentPage", commandMap.get("currentPage"));
		mv.addObject("storeDetail", map.get("storeDetail"));
		mv.addObject("STORE_NO", commandMap.get("STORE_NO"));
		
		return mv;
	}
	
	@RequestMapping(value="/storeModifyForm.do")
	public ModelAndView storeModifyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/store/storeModify");
		
		Map<String, Object> map = storeService.selectStoreDetail(commandMap.getMap());

		mv.addObject("map", map.get("map"));
		mv.addObject("storeDetail", map.get("storeDetail"));
		mv.addObject("fileList", map.get("fileList"));
		
		return mv;
	}
	
	@RequestMapping(value="/storeModify.do")
	public ModelAndView storeModify(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/storeDetail.do");
		
		String content2 = (String)commandMap.get("STORE_CONTENT");
		String content = content2.replaceAll("\r\n", "<br>");
		commandMap.put("STORE_CONTENT", content);
		
		storeService.modifyStore(commandMap.getMap(), request);
		
		mv.addObject("STORE_NO", commandMap.get("STORE_NO"));

		return mv;
	}
	
	@RequestMapping(value="/storeDelete.do")
	public ModelAndView storeDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("redirect:/admin/storeList.do");
		
		storeService.deleteStore(commandMap.getMap(), request);
		
		mv.addObject("STORE_NO", commandMap.get("STORE_NO"));
		
		return mv;
	}
}
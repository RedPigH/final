package com.moviecube.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;
import com.moviecube.common.Paging;

@Controller
public class QnaController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "qnaService")
	private QnaService qnaService;

	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockpaging = 10;
	private String pagingHtml;
	private Paging paging;

	/* "/qna/inquiryList" */
	@RequestMapping(value = "/qnaList.do")
	public ModelAndView inquiryList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		List<Map<String, Object>> Qnalist = qnaService.selectQnaList(commandMap.getMap());
		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		totalCount = Qnalist.size();

		paging = new Paging(currentPage, totalCount, blockCount, blockpaging, "qnalist");
		pagingHtml = paging.getPagingHtml().toString();

		int lastCount = totalCount;
		
		if (paging.getEndCount() < totalCount) {
			lastCount = paging.getEndCount() + 1;
		}

		Qnalist = Qnalist.subList(paging.getStartCount(), lastCount);

		mv.addObject("Qnalist", Qnalist);
		mv.addObject("list", Qnalist);
		mv.addObject("currentPage", currentPage);
		mv.addObject("pagingHtml", pagingHtml);
		mv.addObject("totalCount", totalCount);
		mv.setViewName("/qna/qnaList");
		return mv;
	}
	

	@RequestMapping(value = "/qnaWriteForm.do")
	public ModelAndView writeInquiryForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("qna/qnaWrite");

		return mv;
	}

	@RequestMapping(value = "/qnaWrite.do")
	public ModelAndView writeInquiry(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/main.do");
		qnaService.insertQna(commandMap.getMap(), request);
		return mv;

	}
	
	@RequestMapping(value = "/qnaDetail.do")
	public ModelAndView inquiryDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/qna/qnaDetail");
		
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
	
	@RequestMapping(value = "/qnaAdminDetail.do")
	public ModelAndView inquiryAdminDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/qna/qnaDetail");
		
		String qna_no = request.getParameter("qna_no");
		commandMap.put("QNA_NO", qna_no);
		Map<String, Object> cmap = qnaService.checkQnaFile(commandMap.getMap());
		
		String temp = String.valueOf(cmap.get("CNT"));
		int count = Integer.parseInt(temp);
		
		if (count == 0) {
			mv.addObject("qna_savname", null);
		} else {
			Map<String, Object> map = qnaService.selectQnaDetail2(commandMap.getMap());
			mv.addObject("qna_savname", map.get("QNA_SAVNAME"));
		}
		
		Map<String, Object> adminMap = qnaService.selectQnaAdminDetail(commandMap.getMap());
		
		
		mv.addObject("adminMap", adminMap);
		mv.setViewName("jsonView");
		
		return mv;
	}

	@RequestMapping(value = "/qnaModifyForm.do")
	public ModelAndView modifyInquiryForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/qna/qnaModify");

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
	public ModelAndView modifyInquiry(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/qnaList.do");
		
		qnaService.updateQna(commandMap.getMap(), request);

		return mv;
	}


	@RequestMapping(value = "/qnaDelete.do")
	public ModelAndView deleteInquiry(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/qnaList.do");
		qnaService.deleteQna(commandMap.getMap());

		return mv;
	}

	@RequestMapping(value = "/qnaReplyForm.do")
	public ModelAndView replyInquiryForm(CommandMap commandMap) throws Exception {
		Map<String, Object> map1 = qnaService.selectQnaDetail1(commandMap.getMap());
		ModelAndView mv = new ModelAndView("qna/qnaReplyForm");

		Map<String, Object> map = new HashMap();
		
		map.put("QNA_NOM", commandMap.get("QNA_NOM"));
		map.put("QNA_SUB", commandMap.get("QNA_SUB"));
		mv.addObject("map", map);
		//mv.addObject("map", map1);

		return mv;

	}

	@RequestMapping(value = "/qnaReply.do")
	public ModelAndView replyInquiry(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/qnaList.do");

		Set keyset = commandMap.keySet();
		
		Map<String, Object> map = new HashMap();
		map.put("REF", commandMap.get("REF"));
		
		commandMap.put("RE_STEP", 1);
		commandMap.put("RE_LEVEL", 1);
		qnaService.replyQna(commandMap.getMap());
		return mv;
	}
}

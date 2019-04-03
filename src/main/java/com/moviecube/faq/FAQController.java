package com.moviecube.faq;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;
import com.moviecube.common.Paging;

@Controller
public class FAQController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "faqService")
	private FaqService faqService;

	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 7;
	private int blockpaging = 10;
	private String pagingHtml;
	private Paging paging;

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
			mv.setViewName("/faq/faqList");
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
		mv.setViewName("/faq/faqList");
		return mv;
	}		
	
	@RequestMapping(value = "/faqWriteForm.do")
	public ModelAndView faqWriteForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/faq/faqWrite");

		return mv;
	}

	@RequestMapping(value = "/faqWrite.do")
	public ModelAndView faqWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/faqList.do");
		String faq_type = request.getParameter("FAQ_TYPE");
		String faq_sub = request.getParameter("FAQ_SUB");
		String faq_content = request.getParameter("FAQ_CONTENT");

		commandMap.put("FAQ_TYPE", faq_type);
		commandMap.put("FAQ_SUB", faq_sub);
		commandMap.put("FAQ_CONTENT", faq_content);

		faqService.insertFaq(commandMap.getMap());

		return mv;
	}

	@RequestMapping(value = "/faqDetail.do")
	public ModelAndView faqDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/faq/faqDetail");
		
		String faq_no = request.getParameter("faq_no");
		commandMap.put("FAQ_NO", faq_no);
		
		Map<String, Object> map = faqService.selectFaqDetail(commandMap.getMap());
		mv.addObject("map", map);

		return mv;
	}

	@RequestMapping(value = "/faqModifyForm.do")
	public ModelAndView faqModifyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/faq/faqModify");

		Map<String, Object> map = faqService.selectFaqDetail(commandMap.getMap());
		mv.addObject("map", map);

		return mv;
	}

	@RequestMapping(value = "/faqModify.do")
	public ModelAndView faqModify(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/faqList.do");

		// mv.addObject("FAQ_NO", commandMap.get("FAQ_NO"));
		faqService.updateFaq(commandMap.getMap());

		return mv;
	}

	@RequestMapping(value = "/faqDelete.do")
	public ModelAndView faqDelete(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/faqList.do");

		faqService.deleteFaq(commandMap.getMap());

		return mv;
	}
	
	@RequestMapping(value = "/selectFaqType.do", method = RequestMethod.POST)
	public ModelAndView selectFaqType(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/faq/faqList");
		
		
		String FAQ_TYPE = request.getParameter("FAQ_TYPE");

		commandMap.put("FAQ_TYPE", FAQ_TYPE);
		
		List<Map<String, Object>> faqList = faqService.selectFaqType(commandMap.getMap());
		
		mv.addObject("faqList", faqList);
		mv.setViewName("jsonView");

		return mv;
		

	}
}
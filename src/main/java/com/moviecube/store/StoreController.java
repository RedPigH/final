package com.moviecube.store;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moviecube.common.CommandMap;

@Controller
public class StoreController {
	@Resource(name = "storeService")
	private StoreService storeService;

	@RequestMapping(value = "/storeList.do")
	public ModelAndView storeList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("store/storeList");
		
		List<Map<String, Object>> storelist = storeService.selectStoreList(commandMap.getMap());
		
		mv.addObject("storelist", storelist);

		return mv;
	}
	
	@RequestMapping(value = "/insertItem.do")
	public ModelAndView insertItem(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("store/storeList");
		
		commandMap.put("STORE_NO", request.getParameter("STORE_NO"));
		commandMap.put("MEMBER_NO", request.getParameter("MEMBER_NO"));
		commandMap.put("TOTAL", request.getParameter("TOTAL"));
		commandMap.put("AMOUNT", request.getParameter("AMOUNT"));
		
		storeService.insertItem(commandMap.getMap());
		
		mv.setViewName("jsonView");
		
		return mv;
	}

}
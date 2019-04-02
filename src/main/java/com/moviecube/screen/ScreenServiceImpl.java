package com.moviecube.screen;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("screenService")
public class ScreenServiceImpl implements ScreenService {

	@Resource(name = "screenDAO")
	private ScreenDAO screenDAO;

	@Override
	public List<Map<String, Object>> selectScreenList(Map<String, Object> map) throws Exception {

		return screenDAO.selectScreenList(map);
	}
	
	@Override
	public List<Map<String, Object>> selectCinemaScreen(Map<String, Object> map) throws Exception{
		
		return screenDAO.selectCinemaScreen(map);
	}

	@Override
	public Map<String, Object> screenDetail(Map<String, Object> map) throws Exception {

		return screenDAO.screenDetail(map);
	}

	@Override
	public void insertScreen(Map<String, Object> map) throws Exception {

		screenDAO.insertScreen(map);
	}

	@Override
	public void updateScreen(Map<String, Object> map) throws Exception {

		screenDAO.updateScreen(map);
	}

	@Override
	public void deleteScreen(Map<String, Object> map) throws Exception {

		screenDAO.deleteScreen(map);
	}
	
	@Override
	public List<Map<String, Object>> screenSearch(Map<String, Object> map) throws Exception {
		return screenDAO.screenSearch(map);
	}

}

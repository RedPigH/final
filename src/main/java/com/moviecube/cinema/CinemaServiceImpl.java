package com.moviecube.cinema;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("cinemaService")
public class CinemaServiceImpl implements CinemaService {

	@Resource(name = "cinemaDAO")
	private CinemaDAO cinemaDAO;

	@Override
	public List<Map<String, Object>> selectCinemaList(Map<String, Object> map) throws Exception {
		return cinemaDAO.selectCinemaList(map);
	}
	
	@Override
	public List<Map<String, Object>> selectCinemaScreen(Map<String, Object> map ) throws Exception{
		return cinemaDAO.selectCinemaScreen(map);
	}

	@Override
	public void insertCinema(Map<String, Object> map) throws Exception {
		cinemaDAO.insertCinema(map);
	}

	@Override
	public void updateCinema(Map<String, Object> map) throws Exception {
		cinemaDAO.updateCinema(map);
	}

	@Override
	public void deleteCinema(Map<String, Object> map) throws Exception {
		cinemaDAO.deleteCinema(map);
	}

	@Override
	public Map<String, Object> cinemaDetail(Map<String, Object> map) throws Exception {
		return cinemaDAO.cinemaDetail(map);
	}

	@Override
	public Map<String, Object> selectOneCinema(Map<String, Object> map) throws Exception {
		return cinemaDAO.selectOneCinema(map);	
	}
	
	@Override
	public List<Map<String, Object>> cinemaSearch(Map<String, Object> map) throws Exception {
		return cinemaDAO.cinemaSearch(map);
	}

}

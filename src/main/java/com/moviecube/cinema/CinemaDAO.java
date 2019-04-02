package com.moviecube.cinema;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moviecube.dao.AbstractDAO;

@Repository("cinemaDAO")
public class CinemaDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectCinemaList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("cinema.selectCinemaList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectCinemaScreen(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("cinema.selectCinemaScreen", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> cinemaDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("cinema.selectOneCinema", map);
	}

	public void insertCinema(Map<String, Object> map) throws Exception {
		insert("cinema.insertCinema", map);
	}

	public void updateCinema(Map<String, Object> map) throws Exception {
		update("cinema.updateCinema", map);
	}

	public void deleteCinema(Map<String, Object> map) throws Exception {
		delete("cinema.deleteCinema", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOneCinema(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("cinema.selectOneCinema", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> cinemaSearch(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("cinema.cinemaSearch", map);
	}
}

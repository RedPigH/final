package com.moviecube.reserve;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ReserveService {

	List<Map<String, Object>> selectCinemaList(Map<String, Object> map) throws Exception;

	Map<String, Object> selectOneCinema(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> selectMovieList(Map<String, Object> map) throws Exception;
	
	List<Map<String ,Object>> MyReservation(Map<String, Object> map) throws Exception;

	Map<String, Object> selectOneMovie(Map<String, Object> map) throws Exception;
	
	Map<String, Object> ResTime(Map<String, Object> map) throws Exception;

	void insertReservation(Map<String, Object> map) throws Exception;

	void deleteReservation(Map<String, Object> map) throws Exception;
}

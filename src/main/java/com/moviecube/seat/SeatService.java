package com.moviecube.seat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SeatService {

	List<Map<String, Object>> selectSeatList(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> selectScreenSeat(Map<String, Object> map) throws Exception;

	Map<String, Object> ScreenSeatNum(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> selectTimeSeat(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> unableTimeSeat(Map<String, Object> map) throws Exception;
	
	Map<String, Object> selectSeat(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> selectResSeat(Map<String, Object> map) throws Exception;
	
	Map<String, Object> selectSeatNo(Map<String, Object> map) throws Exception;

	void insertSeat(Map<String, Object> map) throws Exception;
	
	//Time Controller insert시 좌석도 함께 생성
	void insertTimeSeat(Map<String, Object> map) throws Exception;
	
	void insertResSeat(Map<String, Object> map) throws Exception;

	void updateSeatStatus(Map<String, Object> map) throws Exception;
	
	void ResDeleteStatus(Map<String, Object> map) throws Exception;

	void deleteSeat(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> seatSearch0(String map) throws Exception;

}

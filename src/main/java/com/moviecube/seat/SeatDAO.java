package com.moviecube.seat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.moviecube.dao.AbstractDAO;

@Repository("seatDAO")
public class SeatDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectSeatList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("seat.selectSeatList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectScreenSeat(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("seat.selectScreenSeat", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> ScreenSeatNum(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("seat.ScreenSeatNum", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTimeSeat(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("seat.selectTimeSeat", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> unableTimeSeat(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("time_seat.unableTimeSeat", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectSeat(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("seat.selectSeat", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectSeatNo(Map<String, Object>map) throws Exception{
		return (Map<String, Object>) selectOne("seat.selectSeatNo", map);
	}

	public void insertSeat(Map<String, Object> map) throws Exception {
		insert("seat.insertSeat", map);
	}

	public void insertTimeSeat(Map<String, Object> map) throws Exception {
		insert("seat.insertTimeSeat", map);
	}
	
	public void insertResSeat(Map<String, Object> map) throws Exception {
		insert("seat.insertResSeat", map);
	}

	public void updateSeatStatus(Map<String, Object> map) throws Exception {
		update("seat.updateSeatStatus", map);
	}

	public void deleteSeat(Map<String, Object> map) throws Exception {
		delete("seat.deleteSeat", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> seatSearch0(String isSearch) {
		return (List<Map<String, Object>>)selectList("seat.seatSearch0", "%"+isSearch+"%");
	}

}

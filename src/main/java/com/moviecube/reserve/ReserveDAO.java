package com.moviecube.reserve;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moviecube.dao.AbstractDAO;

@Repository("reserveDAO")
public class ReserveDAO extends AbstractDAO {

	public void insertReserve(Map<String, Object> map) throws Exception {

		insert("reservation.insertReservation", map);
	}
	
	public void deleteReserve(Map<String, Object> map) throws Exception{
		
		delete("reservation.deleteReservation", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> MyReservation(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("reservation.MyReservation", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> MyAllReservation(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("reservation.MyAllReservation", map);
	}

}

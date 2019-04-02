package com.moviecube.seat;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service("seatService")
public class SeatServiceImpl implements SeatService {
	
	@Resource(name = "seatDAO")
	private SeatDAO seatDAO;

	@Override
	public List<Map<String, Object>> selectSeatList(Map<String, Object> map) throws Exception {
		return seatDAO.selectSeatList(map);
	}
	
	@Override
	public List<Map<String, Object>> selectScreenSeat(Map<String, Object> map) throws Exception{
		return seatDAO.selectScreenSeat(map);
	}

	@Override
	public Map<String, Object> ScreenSeatNum(Map<String, Object> map) throws Exception {
		return seatDAO.ScreenSeatNum(map);
	}
	
	@Override
	public List<Map<String, Object>> selectTimeSeat(Map<String, Object> map) throws Exception{
		return seatDAO.selectTimeSeat(map);
	}
	
	@Override
	public List<Map<String, Object>> unableTimeSeat(Map<String, Object> map) throws Exception{
		return seatDAO.unableTimeSeat(map);
	}
	
	@Override
	public Map<String, Object> selectSeat(Map<String,Object> map) throws Exception{
		return seatDAO.selectSeat(map);
	}
	
	@Override
	public Map<String, Object> selectSeatNo(Map<String, Object> map) throws Exception{
		return seatDAO.selectSeatNo(map);
	}

	@Override
	public void insertSeat(Map<String, Object> map) throws Exception {
		seatDAO.insertSeat(map);
	}

	@Override
	public void insertTimeSeat(Map<String, Object> map) throws Exception {
		seatDAO.insertTimeSeat(map);
	}
	
	@Override
	public void insertResSeat(Map<String, Object> map) throws Exception{
		seatDAO.insertResSeat(map);
	}

	@Override
	public void updateSeatStatus(Map<String, Object> map) throws Exception {
		seatDAO.updateSeatStatus(map);
	}

	@Override
	public void deleteSeat(Map<String, Object> map) throws Exception {
		seatDAO.deleteSeat(map);
	}
	
	@Override
	public List<Map<String, Object>> seatSearch0(String map) throws Exception {
		return seatDAO.seatSearch0(map);
	}
}

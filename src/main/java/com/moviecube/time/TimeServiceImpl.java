package com.moviecube.time;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("timeService")
public class TimeServiceImpl implements TimeService {

	@Resource(name = "timeDAO")
	private TimeDAO timeDAO;

	@Override
	public List<Map<String, Object>> selectTimeList(Map<String, Object> map) throws Exception {

		return timeDAO.selectTimeList(map);
	}

	@Override
	public List<Map<String, Object>> optionTimeList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return timeDAO.optionTimeList(map);
	}
	
	@Override
	public List<Map<String, Object>> selectAllTimeList(Map<String, Object> map) throws Exception{
		return timeDAO.selectAllTimeList(map);
	}

	@Override
	public Map<String, Object> timeDetail(Map<String, Object> map) throws Exception {

		return timeDAO.selectOneTime(map);
	}

	@Override
	public void insertTime(Map<String, Object> map) throws Exception {

		timeDAO.insertTime(map);
	}

	@Override
	public void updateTime(Map<String, Object> map) throws Exception {

		timeDAO.updateTime(map);
	}

	@Override
	public void deleteTime(Map<String, Object> map) throws Exception {

		timeDAO.deleteTime(map);
	}

	@Override
	public List<Map<String, Object>> timeSearch(Map<String, Object> map) throws Exception {
		return timeDAO.timeSearch(map);
	}
}

package com.moviecube.time;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moviecube.dao.AbstractDAO;

@Repository("timeDAO")
public class TimeDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTimeList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("time.selectTimeList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> optionTimeList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("time.optionTimeList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectAllTimeList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("time.selectAllTimeList", map);
	}

	/*
	 * @SuppressWarnings("unchecked") public List<Map<String, Object>>
	 * optionTimeList(Map<String, Object> map) throws Exception { return
	 * (List<Map<String, Object>>) selectList("time.optionTimeList", map); }
	 */

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOneTime(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("time.selectOneTime", map);
	}

	public void insertTime(Map<String, Object> map) throws Exception {
		insert("time.insertTime", map);
	}

	public void updateTime(Map<String, Object> map) throws Exception {
		update("time.updateTime", map);
	}

	public void deleteTime(Map<String, Object> map) throws Exception {
		delete("time.deleteTime", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> timeSearch(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("time.timeSearch", map);
	}
}

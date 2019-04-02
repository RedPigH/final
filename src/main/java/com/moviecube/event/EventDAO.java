package com.moviecube.event;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moviecube.common.AbstractDAO;

@Repository("eventDAO")
public class EventDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectEventList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("event.selectEventList", map);

	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMovieCubeEventList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("event.selectMovieCubeEventList", map);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMovieEventList(Map<String, Object> map) throws Exception {
		return (List<Map<String,Object>>) selectList("event.selectMovieEventList", map);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectAllianceEventList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("event.selectAllianceEventList", map);
	}

	public void insertEvent(Map<String, Object> map) throws Exception {
		insert("event.insertEvent", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectEventDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("event.selectEventDetail", map);

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectEventFileDetail(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("event.selectEventFileDetail", map);
	}

	public void insertFile(Map<String, Object> filelist) throws Exception {
		insert("event.insertFile", filelist);
	}

	public void modifyEvent(Map<String, Object> map) throws Exception {
		update("event.modifyEvent", map);

	}

	public void updateFileList(Map<String, Object> map) throws Exception {
		update("event.updateFileList", map);

	}

	public void modifyFile(Map<String, Object> map) {
		update("event.modifyFile", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectEventDetail2(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("event.selectEventDetail2", map);
	}

	public void deleteEvent(Map<String, Object> map) throws Exception {
		update("event.deleteEvent", map);
	}

	public void deleteFileList(Map<String, Object> map) throws Exception {
		update("event.deleteFileList", map);
	}


}

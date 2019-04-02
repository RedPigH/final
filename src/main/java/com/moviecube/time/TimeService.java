package com.moviecube.time;

import java.util.List;
import java.util.Map;

public interface TimeService {

	List<Map<String, Object>> selectTimeList(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> optionTimeList(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> selectAllTimeList(Map<String, Object> map) throws Exception;

	Map<String, Object> timeDetail(Map<String, Object> map) throws Exception;

	void insertTime(Map<String, Object> map) throws Exception;

	void updateTime(Map<String, Object> map) throws Exception;

	void deleteTime(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> timeSearch(Map<String, Object> map) throws Exception;

}

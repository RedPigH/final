package com.moviecube.store;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface StoreService {
	
	List<Map<String, Object>> selectStoreList(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> selectMyItem(Map<String, Object> map) throws Exception;
	
	void insertStore(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	void insertItem(Map<String, Object> map) throws Exception;
	
	Map<String, Object> selectStoreDetail(Map<String,Object> map) throws Exception;
	
	public void modifyStore(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	public void deleteStore(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	List<Map<String, Object>> storeSearch0(String map) throws Exception;
	
}

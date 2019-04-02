package com.moviecube.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

	List<Map<String, Object>> selectMemberList(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> memberSearch(Map<String, Object> map) throws Exception;

	void memberDelete(Map<String, Object> map) throws Exception;

	Map<String,Object> selectMemberDetail(Map<String,Object> map) throws Exception;

}


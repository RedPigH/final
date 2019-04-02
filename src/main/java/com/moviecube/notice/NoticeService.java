package com.moviecube.notice;

import java.util.List;
import java.util.Map;

import com.moviecube.common.CommandMap;

public interface NoticeService {

	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;

	void insertBoard(Map<String, Object> map) throws Exception;

	Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception;

	void updateBoard(Map<String, Object> map) throws Exception;

	void deleteBoard(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> noticeSearch(Map<String, Object> map) throws Exception;

}

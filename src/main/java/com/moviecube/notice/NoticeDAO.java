package com.moviecube.notice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moviecube.dao.AbstractDAO;

@Repository("noticeDAO")
public class NoticeDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {

		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("notice.selectBoardList", map);
	};

	public void insertBoard(Map<String, Object> map) throws Exception {
		insert("notice.insertBoard", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("notice.selectBoardDetail", map);
	}

	public void updateBoard(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		update("notice.updateBoard", map);
	}

	public void deleteBoard(Map<String, Object> map) throws Exception {
		delete("notice.deleteBoard", map);
		// TODO Auto-generated method stub

	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> noticeSearch(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("notice.noticeSearch", map);
	}
}

package com.moviecube.notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.moviecube.common.CommandMap;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = noticeDAO.selectBoardDetail(map);
		return resultMap;
	}

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "noticeDAO")
	private NoticeDAO noticeDAO;

	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.selectBoardList(map);

	}

	@Override
	public void insertBoard(Map<String, Object> map) throws Exception {
		noticeDAO.insertBoard(map);
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBoard(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.updateBoard(map);

	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.deleteBoard(map);

	}
	
	@Override
	public List<Map<String, Object>> noticeSearch(Map<String, Object> map) throws Exception {
		return noticeDAO.noticeSearch(map);
	}

}

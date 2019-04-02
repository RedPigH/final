package com.moviecube.qna;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	@Resource(name = "qnaFileUtils")
	private QnaFileUtils fileUtils;

	@Resource(name = "qnaDAO")
	private QnaDAO qnaDAO;

	@Override
	public Map<String, Object> selectQnaDetail1(Map<String, Object> map) throws Exception {
		return qnaDAO.selectQnaDetail1(map); 

	}

	@Override
	public Map<String, Object> selectQnaDetail2(Map<String, Object> map) throws Exception {
		return qnaDAO.selectQnaDetail2(map);

	}

	Logger log = Logger.getLogger(this.getClass());

	@Override
	public List<Map<String, Object>> selectQnaList(Map<String, Object> map) throws Exception {
		return qnaDAO.selectQnaList(map);
	}

	@Override
	public void insertQna(Map<String, Object> map, HttpServletRequest request) throws Exception {

		qnaDAO.insertQna(map);

		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
		
		System.out.println("파일 길이 : " + list.size());
		if (list.size() > 0)
			for (int i = 0, size = list.size(); i < size; i++) {
				qnaDAO.insertFile(list.get(i));
			}

	}

	// TODO Auto-generated method stub

	@Override
	public void updateQna(Map<String, Object> map, HttpServletRequest request) throws Exception {
		int size = 0;
		qnaDAO.updateQna(map);

		if (map.get("QNA_FILE_NO") != null) { // null값이 아니라는건 수정할 파일이 있다는 소리거나 수정하지 않더라도 원래 있던 파일을 그대로 가져온다는 의미임.
			qnaDAO.deleteQnaFile(map);// qna_file_no가 null이라는건 수정하면서 파일삭제를 눌렀다는 것을 의미함.

			List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(map, request);

			if (list.size() > 0) {
				Map<String, Object> tempMap = null;
				tempMap = list.get(0);
				
				size = list.size();

				for (int i = 0; i < size; i++) {
					tempMap = list.get(i);

					if (tempMap.get("IS_NEW").equals("Y")) {
						qnaDAO.insertFile2(tempMap);
					} else {
						qnaDAO.updateQnaFile(tempMap);
					}
				} // for문 끝
			}
		}
	}

	@Override
	public void deleteQna(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		qnaDAO.deleteQna(map);

	}

	@Override
	public void replyQna(Map<String, Object> map, HttpServletRequest request) throws Exception {
		qnaDAO.replyQna(map, request);

	}

	@Override
	public void replyQna(Map<String, Object> map) throws Exception {
		qnaDAO.replyQna(map, null);
		// TODO Auto-generated method stub

	}

	@Override
	public void updateQna(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Map<String, Object>> selectQnaFileList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.selectQnaFileList(map);
	}

	@Override
	public Map<String, Object> checkQnaFile(Map<String, Object> map) throws Exception {
		Map<String, Object> result = qnaDAO.checkQnaFile(map);

		if (result == null) {
			result.put("CNT", 0);
			return result;
		}

		return result;
	}

}

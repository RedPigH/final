package com.moviecube.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.moviecube.event.EventFileUtils;

@Service("eventService")
public class EventServiceImpl implements EventService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "eventDAO")
	private EventDAO EventDAO;

	@Resource(name = "eventFileUtils")
	private EventFileUtils fileUtils;

	@Override
	public List<Map<String, Object>> selectEventList(Map<String, Object> map) throws Exception {
		return EventDAO.selectEventList(map);
	}

	@Override
	public List<Map<String, Object>> McEventList(Map<String, Object> map) throws Exception {
		return EventDAO.selectMovieCubeEventList(map);
	}

	@Override
	public List<Map<String, Object>> MovieEventList(Map<String, Object> map) throws Exception {
		return EventDAO.selectMovieEventList(map);
	}

	@Override
	public List<Map<String, Object>> AllEventList(Map<String, Object> map) throws Exception {
		return EventDAO.selectAllianceEventList(map);
	}

	@Override
	public void insertEvent(Map<String, Object> map, HttpServletRequest request) throws Exception {
		EventDAO.insertEvent(map);

		List<Map<String, Object>> fileList = fileUtils.parseInsertFileInfo(map, request);

		for (int i = 0, size = fileList.size(); i < size; i++) {
			EventDAO.insertFile(fileList.get(i));
			/*
			 * List<Map<String, Object>> Filelist = fileUtils.parseInsertFileInfo(map,
			 * request); EventDAO.insertFile(Filelist.get(0));
			 * 
			 * MultipartHttpServletRequest multipartHttpServletRequest =
			 * (MultipartHttpServletRequest) request;
			 * 
			 * // HttpServletRequest �옄泥대줈�뒗 Multipart�삎�떇. �뜲�씠�꽣 議곗옉�븯�뒗�뜲 �뼱�젮���씠 �엳湲�
			 * �븣臾몄뿉 // MultipartHttpServletRequest �삎�떇�쑝濡� �삎 蹂��솚�븳�떎
			 * 
			 * Iterator<String> iterator = multipartHttpServletRequest.getFileNames(); //
			 * �씠�꽣�젅�씠�꽣瑜� �씠�슜�븯�뿬 request�뿉 �쟾�넚�맂 紐⑤뱺 name�쓣 �씠�슜�븳�떎 => Map�뿉 �엳�뒗
			 * �뜲�씠�꽣瑜� while臾몄쓣 �씠�슜�븯�뿬 �닚李⑥쟻�쑝濡� �젒洹쇳븿
			 * 
			 * MultipartFile multipartFile = null; while (iterator.hasNext()) {
			 * multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			 * 
			 * // MultipartFile媛앹껜�뿉 request�뿉�꽌 �뙆�씪 媛앹껜瑜� 媛��졇�삩�떎 //
			 * multipartHttpServletRequest�쓽 getFile() 硫붿꽌�뱶�뒗 request�뿉 ���옣�맂 �뙆�씪�쓽
			 * name�쓣 �씤�옄濡� 諛쏅뒗�떎. // �씠 name�쓣 Iterator瑜� �넻�빐�꽌 媛��졇�삤�뒗�뜲 洹멸쾬�씠
			 * Iterator.next() 硫붿꽌�뱶�씠�떎 if (multipartFile.isEmpty() == false) { // �떎�젣
			 * �뙆�씪 �젙蹂닿� �엳�뒗吏� 寃��궗�븳�썑�뿉 �븘�옒�쓽 硫붿꽌�뱶瑜� �넻�빐 �뙆�씪�쓽 �젙蹂대��
			 * 異쒕젰�븳�떎(log.debug)
			 * 
			 * log.debug("------------- file start -------------"); log.debug("name : " +
			 * multipartFile.getName()); log.debug("filename : " +
			 * multipartFile.getOriginalFilename()); log.debug("size : " +
			 * multipartFile.getSize());
			 * log.debug("-------------- file end --------------\n"); }
			 */
		}

	}

	@Override
	public Map<String, Object> selectEventDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = EventDAO.selectEventDetail(map);
		resultMap.put("map", tempMap);

		List<Map<String, Object>> eventDetail = EventDAO.selectEventFileDetail(map);
		resultMap.put("eventDetail", eventDetail);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectEventDetail2(Map<String, Object> map) throws Exception {
		return EventDAO.selectEventDetail2(map);
	}

	@Override
	public void modifyEvent(Map<String, Object> map, HttpServletRequest request) throws Exception {
		EventDAO.modifyEvent(map);
		EventDAO.updateFileList(map);

		List<Map<String, Object>> fileList = fileUtils.parseUpdateFileInfo(map, request);

		for (Map<String, Object> fmap : fileList) {
			System.out.println("�빆�꽩�븳�뀋�빆�꽩�븯 : " + fmap);
		}

		Map<String, Object> tempMap = null;

		for (int i = 0, size = fileList.size(); i < size; i++) {
			tempMap = fileList.get(i);

			if (tempMap.get("IS_NEW").equals("Y")) {
				EventDAO.insertFile(tempMap);
			} else {
				EventDAO.modifyFile(tempMap);
				/*
				 * tempMap = fileList.get(i); if (i == 0) {
				 * 
				 * if (tempMap.get("IS_NEW").equals("Y")) { EventDAO.insertFile(tempMap); } else
				 * { EventDAO.modifyFile(tempMap);
				 */
			}
		}
	}

	/*
	 * @Override public Map<String, Object> checkEventFile(Map<String, Object> map)
	 * throws Exception { Map<String, Object> result = EventDAO.checkEventFile(map);
	 * 
	 * if (result == null) { result.put("CNT", 0); return result; }
	 * 
	 * return result; }
	 */
	@Override
	public void deleteEvent(Map<String, Object> map, HttpServletRequest request) throws Exception {
		EventDAO.deleteEvent(map);

	}

}

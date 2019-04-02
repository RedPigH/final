package com.moviecube.event;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.moviecube.common.CommonUtils;

@Component("eventFileUtils")
public class EventFileUtils {
	private static final String filePath = "C:\\moviecubefinal\\moviecubefinal\\src\\main\\webapp\\resources\\upload\\event\\"; // Event �씠誘몄�
																											// �뙆�씪 ���옣 �쐞移�

	public List<Map<String, Object>> parseInsertFileInfo(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> Filelist = new ArrayList<Map<String, Object>>(); // �겢�씪�씠�뼵�듃�뿉�꽌 �쟾�넚�맂 �뙆�씪 �젙蹂대�� �떞�븘�꽌 諛섑솚�쓣 �빐二쇰뒗
																					// List (�떎以묓뙆�씪�쟾�넚)
		Map<String, Object> FilelistMap = null;

		int EVENT_NO = (int) map.get("EVENT_NO"); // EventServiceImpl �쁺�뿭�뿉�꽌 �쟾�떖�빐以� map�뿉�꽌 �떊洹쒓쾶�떆湲��쓽 踰덊샇瑜� 諛쏆븘�삩�떎

		File file = new File(filePath); // �뙆�씪�쓣 ���옣�븷 寃쎈줈�뿉 �빐�떦�뤃�뜑媛� �뾾�쑝硫� �뤃�뜑瑜� �깮�꽦�븳�떎
		if (file.exists() == false) {
			file.mkdirs();
		}

		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			System.out.println("test ============================== " + multipartFile.getName());

			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32�옄由ъ쓽 �옖�뜡�븳 �뙆�씪�씠由� �깮�꽦�븯怨� �썝蹂명뙆�씪�쓽
																						// �솗�옣�옄瑜� 遺숈뿬以��떎

				file = new File(filePath + storedFileName); // �꽌踰꾩뿉 �떎�젣 �뙆�씪�쓣 ���옣�븯�뒗 遺�遺�
				multipartFile.transferTo(file); // ���옣�맂 寃쎈줈�뿉 �뙆�씪�쓣 �깮�꽦�븳�떎.
				// �쐞�뿉�꽌 留뚮뱺 �젙蹂대�� Filelist�뿉 異붽��븳�떎.

				FilelistMap = new HashMap<String, Object>();
				FilelistMap.put("EVENT_NO", EVENT_NO);
				FilelistMap.put("EVENT_ORGNAME", originalFileName);
				FilelistMap.put("EVENT_SAVNAME", storedFileName);
				Filelist.add(FilelistMap);
			}

		}

		return Filelist;

	}

	public List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> Filelist = new ArrayList<Map<String, Object>>();
		Map<String, Object> FilelistMap = null;

		int EVENT_NO = Integer.parseInt((String)map.get("EVENT_NO"));
		
		String requestName = null;
		String idx = null;

		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			System.out.println("test ============================== " + multipartFile.getName());

			if (multipartFile.isEmpty() == false) {
				/*
				 * if (multipartFile.isEmpty() == false &&
				 * multipartFile.getName().equals("EVENT_ORGNAME")) {
				 */
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension;

				multipartFile.transferTo(new File(filePath + storedFileName));

				FilelistMap = new HashMap<String, Object>();
				FilelistMap.put("IS_NEW", "Y");
				FilelistMap.put("EVENT_NO", EVENT_NO);
				FilelistMap.put("EVENT_ORGNAME", originalFileName);
				FilelistMap.put("EVENT_SAVNAME", storedFileName);
				Filelist.add(FilelistMap);
			} else {
				requestName = multipartFile.getName();
				idx = "IDX_" + requestName.substring(requestName.indexOf("_") + 1);

				FilelistMap = new HashMap<String, Object>();
				FilelistMap.put("IS_NEW", "N");
				FilelistMap.put("FILE_NO", map.get(idx));
				Filelist.add(FilelistMap);
			}
		}

		return Filelist;
	}
	
	public void fileDelete(Map<String, Object> map, String filePath, String media) throws Exception{
	String EVENT_SAVNAME = null;
	File file = null;
	
	file = new File(filePath + EVENT_SAVNAME);
	file.delete();

}
}

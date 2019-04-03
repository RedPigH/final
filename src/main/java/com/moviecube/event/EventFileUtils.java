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
	private static final String filePath = "C:\\princessHyesoo\\src\\main\\webapp\\resources\\upload\\event\\"; // Event 占쎌뵠沃섎챷占�
																											// 占쎈솁占쎌뵬 占쏙옙占쎌삢 占쎌맄燁삼옙

	public List<Map<String, Object>> parseInsertFileInfo(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> Filelist = new ArrayList<Map<String, Object>>(); // 占쎄깻占쎌뵬占쎌뵠占쎈섧占쎈뱜占쎈퓠占쎄퐣 占쎌읈占쎈꽊占쎈쭆 占쎈솁占쎌뵬 占쎌젟癰귣�占쏙옙 占쎈뼖占쎈툡占쎄퐣 獄쏆꼹�넎占쎌뱽 占쎈퉸雅뚯눖�뮉
																					// List (占쎈뼄餓λ쵑�솁占쎌뵬占쎌읈占쎈꽊)
		Map<String, Object> FilelistMap = null;

		int EVENT_NO = (int) map.get("EVENT_NO"); // EventServiceImpl 占쎌겫占쎈열占쎈퓠占쎄퐣 占쎌읈占쎈뼎占쎈퉸餓ο옙 map占쎈퓠占쎄퐣 占쎈뻿域뱀뮄苡띰옙�뻻疫뀐옙占쎌벥 甕곕뜇�깈�몴占� 獄쏆룇釉섓옙�궔占쎈뼄

		File file = new File(filePath); 
		if (file.exists() == false) {
			file.mkdirs();
		}

		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			System.out.println("test ============================== " + multipartFile.getName());

			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32占쎌쁽�뵳�딆벥 占쎌삏占쎈쑁占쎈립 占쎈솁占쎌뵬占쎌뵠�뵳占� 占쎄문占쎄쉐占쎈릭�⑨옙 占쎌뜚癰귣챸�솁占쎌뵬占쎌벥
																						// 占쎌넇占쎌삢占쎌쁽�몴占� �겫�늿肉т빳占쏙옙�뼄

				file = new File(filePath + storedFileName); // 占쎄퐣甕곌쑴肉� 占쎈뼄占쎌젫 占쎈솁占쎌뵬占쎌뱽 占쏙옙占쎌삢占쎈릭占쎈뮉 �겫占썽겫占�
				multipartFile.transferTo(file); // 占쏙옙占쎌삢占쎈쭆 野껋럥以덌옙肉� 占쎈솁占쎌뵬占쎌뱽 占쎄문占쎄쉐占쎈립占쎈뼄.
				// 占쎌맄占쎈퓠占쎄퐣 筌띾슢諭� 占쎌젟癰귣�占쏙옙 Filelist占쎈퓠 �빊遺쏙옙占쎈립占쎈뼄.

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

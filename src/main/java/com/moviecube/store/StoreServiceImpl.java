package com.moviecube.store;

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

import com.moviecube.store.StoreDAO;

@Service("storeService")
public class StoreServiceImpl implements StoreService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="storeDAO")
	private StoreDAO storeDAO;

	@Resource(name="storeFileUtils")
	private StoreFileUtils fileUtils;
	
	@Override
	public List<Map<String, Object>> selectStoreList(Map<String, Object> map) throws Exception {
		return storeDAO.selectStoreList(map);
	}
	
	@Override
	public List<Map<String, Object>> selectMyItem(Map<String, Object> map) throws Exception {
		return storeDAO.selectMyItem(map);
	}

	@Override
	public void insertStore(Map<String, Object> map, HttpServletRequest request) throws Exception {
		storeDAO.insertStore(map);
		
		List<Map<String,Object>> fileList = fileUtils.parseInsertFileInfo(map, request);
	 	storeDAO.insertFile(fileList.get(0)); 	
	}
	
	@Override
	public void insertItem(Map<String, Object> map) throws Exception {
		storeDAO.insertItem(map);
	}

	@Override
	public Map<String, Object> selectStoreDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = storeDAO.selectStoreDetail(map);
		resultMap.put("map", tempMap);
		
		List<Map<String, Object>> storeDetail = storeDAO.selectStoreFileDetail(map);
		resultMap.put("storeDetail", storeDetail);
		return resultMap;
	}

	@Override
	public void modifyStore(Map<String, Object> map, HttpServletRequest request) throws Exception {
		storeDAO.modifyStore(map);
		
		storeDAO.updateFileList(map);
		
		List<Map<String,Object>> fileList = fileUtils.parseUpdateFileInfo(map, request);
		Map<String, Object> tempMap = null;
			
		for(int i=0, size=fileList.size(); i<size; i++){
			tempMap = fileList.get(i);
			if (i == 0) {
				
				if(tempMap.get("IS_NEW").equals("Y")) { 
					storeDAO.insertFile(tempMap);					
				}
				else {
					storeDAO.modifyFile(tempMap);
				}
			}
		}
	}

	@Override
	public void deleteStore(Map<String, Object> map, HttpServletRequest request) throws Exception {
		storeDAO.deleteStore(map);	
		storeDAO.updateFileList(map);
	}
	
	@Override
	public List<Map<String, Object>> storeSearch0(String map) throws Exception {
		return storeDAO.storeSearch0(map);
	}
}

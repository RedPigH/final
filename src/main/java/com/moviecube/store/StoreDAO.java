package com.moviecube.store;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moviecube.common.AbstractDAO;

@Repository("storeDAO")
public class StoreDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectStoreList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("store.selectStoreList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMyItem(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("store.selectMyItem", map);
	}

	public void insertStore(Map<String, Object> map) throws Exception {
		insert("store.insertStore", map);
	}

	public void insertItem(Map<String, Object> map) throws Exception {
		insert("store.insertItem", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectStoreDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("store.selectStoreDetail", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectStoreFileDetail(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("store.selectStoreFileDetail", map);
	}

	public void modifyStore(Map<String, Object> map) throws Exception {
		update("store.modifyStore", map);
	}

	public void deleteStore(Map<String, Object> map) throws Exception {
		update("store.deleteStore", map);
	}

	public void insertFile(Map<String, Object> filelist) throws Exception {
		insert("store.insertFile", filelist);
	}

	public void updateFileList(Map<String, Object> map) throws Exception {
		update("store.updateFileList", map);
	}

	public void modifyFile(Map<String, Object> map) {
		update("store.modifyFile", map);
	}

	public void deleteFile(Map<String, Object> map) {
		delete("store.deleteFile", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> storeSearch0(String isSearch) {
		return (List<Map<String, Object>>) selectList("store.storeSearch0", "%" + isSearch + "%");
	}
}
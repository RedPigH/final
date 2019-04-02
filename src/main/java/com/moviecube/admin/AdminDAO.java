package com.moviecube.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moviecube.common.AbstractDAO;


@Repository("AdminDAO")
public class AdminDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMemberList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("admin.selectMemberList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> memberSearch(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>)selectList("admin.memberSearch", map);
	}

	public void memberDelete(Map<String, Object> map) {
		delete("admin.memberDelete", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMemberDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("admin.selectMemberDetail", map);
	}
}

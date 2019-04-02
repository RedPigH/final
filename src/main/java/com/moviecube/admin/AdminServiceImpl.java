package com.moviecube.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.moviecube.admin.AdminDAO;
import com.moviecube.member.MemberDAO;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="AdminDAO")
	private AdminDAO adminDAO;
	
	@Resource(name = "memberDAO")
	  private MemberDAO memberDAO;
	
	@Override
	public List<Map<String, Object>> selectMemberList(Map<String, Object> map) throws Exception {
		return adminDAO.selectMemberList(map);
	}

	@Override
	public List<Map<String, Object>> memberSearch(Map<String, Object> map) throws Exception {
		return adminDAO.memberSearch(map);
	}

	@Override
	public void memberDelete(Map<String, Object> map) throws Exception {
		adminDAO.memberDelete(map);
		
	}

	@Override
	public Map<String, Object> selectMemberDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = adminDAO.selectMemberDetail(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
}

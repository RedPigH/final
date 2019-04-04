package com.moviecube.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
	
	 void insertMember(Map<String, Object> map)throws Exception; 
	 
	 int findUsedID(Map<String, Object> map) throws Exception;
	 
	 Map<String, Object> checkId(Map<String, Object> map) throws Exception;
	
	 Map<String, Object> checkUserIdAndPassword(Map<String, Object> map)throws Exception;
	 
	 Map<String, Object> selectOneMember(Map<String, Object> map) throws Exception;
	 
	 Map<String, Object> findId(Map<String, Object> map) throws Exception;
	 
	 Map<String, Object> findPasswd(Map<String, Object> map) throws Exception;
	 
	 void updateMile(Map<String, Object> map) throws Exception;
	 
	 void updateRank(Map<String, Object> map) throws Exception;
	 
	 void updateMember(Map<String, Object> map) throws Exception;
	 
	 void updatePass(Map<String, Object> map) throws Exception;
	 
	 void deleteMember(Map<String, Object> map) throws Exception;

	 void insertMyPage(Map<String, Object> map, HttpServletRequest request) throws Exception;
	 
	 Map<String,Object> selectMemberFile(Map<String,Object> map) throws Exception;
	 
	 void updateProfile(Map<String, Object> map, HttpServletRequest request) throws Exception;
	 
	 void deleteProfile(Map<String, Object> map, HttpServletRequest request) throws Exception;
}

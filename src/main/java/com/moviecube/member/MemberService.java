package com.moviecube.member;

import java.util.Map;

public interface MemberService {
	
	 void insertMember(Map<String, Object> map)throws Exception; 
	 
	 int findUsedID(Map<String, Object> map) throws Exception;
	
	 Map<String, Object> checkUserIdAndPassword(Map<String, Object> map)throws Exception;
	 
	 Map<String, Object> selectOneMember(Map<String, Object> map) throws Exception;
	 
	 String findId(Map<String, Object> map) throws Exception;
	 
	 String findPasswd(Map<String, Object> map) throws Exception;
	 
	 void updateMile(Map<String, Object> map) throws Exception;
	 
	 void updateRank(Map<String, Object> map) throws Exception;
<<<<<<< HEAD
	 
	 void updateMember(Map<String, Object> map) throws Exception;
	 
	 void updatePass(Map<String, Object> map) throws Exception;
	 
	 void deleteMember(Map<String, Object> map) throws Exception;
=======
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
}

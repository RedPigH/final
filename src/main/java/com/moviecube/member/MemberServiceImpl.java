package com.moviecube.member;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	  @Resource(name = "memberDAO")
	  private MemberDAO memberDAO;
	  
	  @Override
	  public void insertMember(Map<String, Object> map) throws Exception{
	  
	  memberDAO.insertMember(map);
	  }
	  
	  @Override
	  public int findUsedID(Map<String, Object> map) throws Exception{
		  return memberDAO.findUsedID(map);
	  }
	  
	  @Override
	  public Map<String, Object> checkUserIdAndPassword(Map<String, Object> map) throws Exception{
		  return memberDAO.checkUserIdAndPassword(map);
	  }
	  
	  @Override
	  public Map<String, Object> selectOneMember(Map<String, Object> map) throws Exception{
		  return memberDAO.selectOneMember(map);
	  }
	  
	  @Override
	  public String findId(Map<String, Object> map) throws Exception{
		  return memberDAO.findId(map);
	  }
	  
	  @Override
	  public String findPasswd(Map<String, Object> map) throws Exception{
		  return memberDAO.findPasswd(map);
	  }
	  
	  @Override
	  public void updateMile(Map<String, Object> map) throws Exception{
		  memberDAO.updateMile(map);
	  }
	  
	  @Override
	  public void updateRank(Map<String, Object> map ) throws Exception{
		  memberDAO.updateRank(map);
	  }
<<<<<<< HEAD
	  
	  @Override
	  public void updateMember(Map<String, Object> map) throws Exception{
		  memberDAO.updateMember(map);
	  }
	  
	  @Override
	  public void updatePass(Map<String, Object> map) throws Exception{
		  memberDAO.updatePass(map);
	  }
	  
	  @Override
	  public void deleteMember(Map<String, Object> map) throws Exception{
		  memberDAO.deleteMember(map);
	  }
=======
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9
}

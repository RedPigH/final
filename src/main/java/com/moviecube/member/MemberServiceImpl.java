package com.moviecube.member;

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

import com.moviecube.member.memberFileUtils;


@Service("memberService")
public class MemberServiceImpl implements MemberService {
	  Logger log = Logger.getLogger(this.getClass());
	
	  @Resource(name = "memberDAO")
	  private MemberDAO memberDAO;
	  
	  @Resource(name="memberFileUtils")
	  private memberFileUtils fileUtils;
	  
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
	  public Map<String, Object> findId(Map<String, Object> map) throws Exception{
		  return memberDAO.findId(map);
	  }
	  
	  @Override
	  public Map<String, Object> findPasswd(Map<String, Object> map) throws Exception{
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
	  
	  @Override
      public void insertMyPage(Map<String, Object> map, HttpServletRequest request) throws Exception {
         
         List<Map<String,Object>> fileList = fileUtils.parseInsertFileInfo(map, request);
            memberDAO.insertFile(fileList.get(0));    
     }
     
     @Override
      public Map<String, Object> selectMemberFile(Map<String, Object> map) throws Exception {
         Map<String, Object> resultMap = new HashMap<String,Object>();
         
         Map<String, Object> tempMap = memberDAO.selectMemberFile(map);   
         resultMap.put("map", tempMap);
         
         return resultMap;
      }
     
     @Override
      public void updateProfile(Map<String, Object> map, HttpServletRequest request) throws Exception {
        
            memberDAO.deleteFile(map);// qna_file_no가 null이라는건 수정하면서 파일삭제를 눌렀다는 것을 의미함.

            List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(map, request);

            if (list.size() > 0) {
               Map<String, Object> tempMap = null;
               tempMap = list.get(0);
               
                  if (tempMap.get("IS_NEW").equals("Y")) {
                     memberDAO.insertFile(tempMap);
                  } else {
                     memberDAO.updateProfile(tempMap);
                  }
            }
     }
}

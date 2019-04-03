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

import com.moviecube.movie.MovieFileUtils;


@Service("memberService")
public class MemberServiceImpl implements MemberService {
	  Logger log = Logger.getLogger(this.getClass());
	
	  @Resource(name = "memberDAO")
	  private MemberDAO memberDAO;
	  
	  @Resource(name="memberFileUtils") // @Conponent �뼱�끂�뀒�씠�뀡�쓣 �씠�슜�븯�뿬 �벑濡앺븳 媛앹껜瑜� @Resource �뼱�끂�뀒�씠�뀡�쓣 �씠�슜�븯�뿬 媛앹껜瑜� �꽑�뼵 �븳�떎
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
			memberDAO.insertMyPage(map);
			
			List<Map<String,Object>> fileList = fileUtils.parseInsertFileInfo(map, request);
				memberDAO.insertFile(fileList.get(0)); 	

			
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request; 
			
		    Iterator<String> iterator = multipartHttpServletRequest.getFileNames(); 
		    MultipartFile multipartFile = null;
		    while(iterator.hasNext()){ 
		        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
		        
		        if(multipartFile.isEmpty() == false){ 
		            log.debug("------------- file start -------------");
		            log.debug("name : "+multipartFile.getName());
		            log.debug("filename : "+multipartFile.getOriginalFilename());
		            log.debug("size : "+multipartFile.getSize());
		            log.debug("-------------- file end --------------\n");
		        }
		    }
		}

	@Override
	public Map<String, Object> selectMemberFile(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProfile(Map<String, Object> map, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
	}
}

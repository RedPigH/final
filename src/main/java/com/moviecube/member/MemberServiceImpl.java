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
	  
	  @Resource(name="memberFileUtils") // @Conponent 어노테이션을 이용하여 등록한 객체를 @Resource 어노테이션을 이용하여 객체를 선언 한다
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
			MemberDAO.insertMyPage(map);
			
			List<Map<String,Object>> fileList = fileUtils.parseInsertFileInfo(map, request);
				MemberDAO.insertFile(fileList.get(0)); 	
//	        for(int i=1, size=fileList.size(); i<size; i++){
//	      	MovieDAO.insertFile2(fileList.get(i));
//	          }
			
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request; 
			// HttpServletRequest 자체로는 Multipart형식 데이터 조작하는데 어려움이 있기 때문에 MultipartHttpServletRequest 형식으로 형 변환한다
		    Iterator<String> iterator = multipartHttpServletRequest.getFileNames(); 
		    // 이터레이터를 이용하여 request에 전송된 모든 name을 이용한다 => Map에 있는 데이터를 while문을 이용하여 순차적으로 접근함
		    MultipartFile multipartFile = null;
		    while(iterator.hasNext()){ // hasNext() 메서드는 Iterator내에 그 다음 데이터의 존재 유무를 알려주고 , Next()메서드는 Interator내의 데이터를 가져온후, 커서를 다음위치로 이동시킨다
		        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
		        // MultipartFile객체에 request에서 파일 객체를 가져온다
		        // multipartHttpServletRequest의 getFile() 메서드는 request에 저장된 파일의 name을 인자로 받는다.
		        // 이 name을 Iterator를 통해서 가져오는데 그것이 Iterator.next() 메서드이다
		        if(multipartFile.isEmpty() == false){ // 실제 파일 정보가 있는지 검사한후에 아래의 메서드를 통해 파일의 정보를 출력한다(log.debug)
		            log.debug("------------- file start -------------");
		            log.debug("name : "+multipartFile.getName());
		            log.debug("filename : "+multipartFile.getOriginalFilename());
		            log.debug("size : "+multipartFile.getSize());
		            log.debug("-------------- file end --------------\n");
		        }
		    }
		}
}

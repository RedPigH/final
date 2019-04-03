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
			memberDAO.insertMyPage(map);
			
			List<Map<String,Object>> fileList = fileUtils.parseInsertFileInfo(map, request);
				memberDAO.insertFile(fileList.get(0)); 	
//	        for(int i=1, size=fileList.size(); i<size; i++){
//	      	MovieDAO.insertFile2(fileList.get(i));
//	          }
			
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request; 
			// HttpServletRequest �옄泥대줈�뒗 Multipart�삎�떇 �뜲�씠�꽣 議곗옉�븯�뒗�뜲 �뼱�젮���씠 �엳湲� �븣臾몄뿉 MultipartHttpServletRequest �삎�떇�쑝濡� �삎 蹂��솚�븳�떎
		    Iterator<String> iterator = multipartHttpServletRequest.getFileNames(); 
		    // �씠�꽣�젅�씠�꽣瑜� �씠�슜�븯�뿬 request�뿉 �쟾�넚�맂 紐⑤뱺 name�쓣 �씠�슜�븳�떎 => Map�뿉 �엳�뒗 �뜲�씠�꽣瑜� while臾몄쓣 �씠�슜�븯�뿬 �닚李⑥쟻�쑝濡� �젒洹쇳븿
		    MultipartFile multipartFile = null;
		    while(iterator.hasNext()){ // hasNext() 硫붿꽌�뱶�뒗 Iterator�궡�뿉 洹� �떎�쓬 �뜲�씠�꽣�쓽 議댁옱 �쑀臾대�� �븣�젮二쇨퀬 , Next()硫붿꽌�뱶�뒗 Interator�궡�쓽 �뜲�씠�꽣瑜� 媛��졇�삩�썑, 而ㅼ꽌瑜� �떎�쓬�쐞移섎줈 �씠�룞�떆�궓�떎
		        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
		        // MultipartFile媛앹껜�뿉 request�뿉�꽌 �뙆�씪 媛앹껜瑜� 媛��졇�삩�떎
		        // multipartHttpServletRequest�쓽 getFile() 硫붿꽌�뱶�뒗 request�뿉 ���옣�맂 �뙆�씪�쓽 name�쓣 �씤�옄濡� 諛쏅뒗�떎.
		        // �씠 name�쓣 Iterator瑜� �넻�빐�꽌 媛��졇�삤�뒗�뜲 洹멸쾬�씠 Iterator.next() 硫붿꽌�뱶�씠�떎
		        if(multipartFile.isEmpty() == false){ // �떎�젣 �뙆�씪 �젙蹂닿� �엳�뒗吏� 寃��궗�븳�썑�뿉 �븘�옒�쓽 硫붿꽌�뱶瑜� �넻�빐 �뙆�씪�쓽 �젙蹂대�� 異쒕젰�븳�떎(log.debug)
		            log.debug("------------- file start -------------");
		            log.debug("name : "+multipartFile.getName());
		            log.debug("filename : "+multipartFile.getOriginalFilename());
		            log.debug("size : "+multipartFile.getSize());
		            log.debug("-------------- file end --------------\n");
		        }
		    }
		}
}

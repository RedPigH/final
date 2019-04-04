package com.moviecube.member;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.moviecube.common.CommonUtils;
 
@Component("memberFileUtils") // 이 객체의 관리를 스프링이 담당하도록 함
public class memberFileUtils {
	private static final String filePath = "D:\\wow\\src\\main\\webapp\\resources\\upload\\mypage\\"; // POSTER 파일의 저장위치
    
    /*영화 포스터 파일 Insert*/ 
    public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
           
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>(); // 클라이언트에서 전송된 파일 정보를 담아서 반환을 해주는 List (다중파일전송)
        Map<String, Object> fileListMap = null;
               
        String MEMBER_NO = map.get("MEMBER_NO").toString();  // MovieServiceImpl 영역에서 전달해준 map에서 신규게시글의 번호를 받아온다
         
        File file = new File(filePath); // 파일을 저장할 경로에 해당폴더가 없으면 폴더를 생성한다
        if(file.exists() == false){
            file.mkdirs();
        }
         
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            System.out.println("test ============================== " + multipartFile.getName());

         	if(multipartFile.isEmpty() == false) {  // 파일의 정보를 받아서 새로우은 이름으로 변경하는 로직

            	originalFileName = multipartFile.getOriginalFilename();
            	originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
           		storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32자리의 랜덤한 파일이름 생성하고 원본파일의 확장자를 붙여준다
                
           		file = new File(filePath + storedFileName); // 서버에 실제 파일을 저장하는 부분
           		multipartFile.transferTo(file); // 지정된 경로에 파일을 생성한다
           		// 위에서 만든 정보를 Filelist에 추가한다 
           		fileListMap = new HashMap<String,Object>();
           		fileListMap.put("MEMBER_NO", MEMBER_NO);
           		fileListMap.put("PROFILE_ORGNAME", originalFileName);
           		fileListMap.put("PROFILE_SAVNAME", storedFileName);
           		fileList.add(fileListMap);
            } 
        }
        return fileList;
    }
    
    public List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception{
	    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    System.out.println("출력해봐라 : " + iterator.hasNext());
	     
	    MultipartFile multipartFile = null;
	    String originalFileName = null;
	    String originalFileExtension = null;
	    String storedFileName = null;
	     
	    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	    Map<String, Object> listMap = null;
	     
	    String MEMBER_NO = (String)map.get("MEMBER_NO");
	     
	     
	    while(iterator.hasNext()){ // 업데이트 할 파일이 들어올 경우. 
	        multipartFile = multipartHttpServletRequest.getFile(iterator.next()); 
	        if(multipartFile.isEmpty() == false){
	            originalFileName = multipartFile.getOriginalFilename();
	            originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	            storedFileName = CommonUtils.getRandomString() + originalFileExtension;
	             
	            multipartFile.transferTo(new File(filePath + storedFileName));
	             
	            listMap = new HashMap<String,Object>();
	            listMap.put("IS_NEW", "Y");
	            listMap.put("MEMBER_NO", MEMBER_NO);
	            listMap.put("PROFILE_ORGNAME", originalFileName);
	            listMap.put("PROFILE_SAVNAME", storedFileName);
	            list.add(listMap);
	        }
	    }
	    
	    return list;
	}
}
package com.moviecube.movie;

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
 
@Component("movieFileUtils") // 이 객체의 관리를 스프링이 담당하도록 함
public class MovieFileUtils {
	private static final String filePath = "C:\\java\\maven\\MovieCube2\\src\\main\\webapp\\resources\\upload\\movie\\poster\\"; // POSTER 파일의 저장위치
    private static final String filePath2 = "C:\\java\\maven\\MovieCube2\\src\\main\\webapp\\resources\\upload\\movie\\stillcut\\"; // STILLCUT 파일의 저장위치
    private static final String filePath3 = "C:\\java\\maven\\MovieCube2\\src\\main\\webapp\\resources\\upload\\movie\\slider\\"; // SLIDER 파일의 저장위치
    
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
               
        String MOVIE_NO = map.get("MOVIE_NO").toString();  // MovieServiceImpl 영역에서 전달해준 map에서 신규게시글의 번호를 받아온다
         
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
           		fileListMap.put("MOVIE_NO", MOVIE_NO);
           		fileListMap.put("POSTER_ORGNAME", originalFileName);
           		fileListMap.put("POSTER_SAVNAME", storedFileName);
           		fileList.add(fileListMap);
            } 
        }
        return fileList;
    }
    
    /*영화 스틸컷 파일 수정*/ 
    public List<Map<String,Object>> parseInsertFileInfo2(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
           
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<Map<String,Object>> fileList2 = new ArrayList<Map<String,Object>>(); // 클라이언트에서 전송된 파일 정보를 담아서 반환을 해주는 List (다중파일전송)
        Map<String, Object> fileListMap2 = null;
               
        int MOVIE_NO = (Integer)map.get("MOVIE_NO");  // MovieServiceImpl 영역에서 전달해준 map에서 신규게시글의 번호를 받아온다
         
        File file = new File(filePath); // 파일을 저장할 경로에 해당폴더가 없으면 폴더를 생성한다
        if(file.exists() == false){
            file.mkdirs();
        }
         
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            System.out.println("test ============================== " + multipartFile.getName());
            
           	if(multipartFile.isEmpty() == false) { // 파일의 정보를 받아서 새로우은 이름으로 변경하는 로직
         
           		originalFileName = multipartFile.getOriginalFilename();
           		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            	storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32자리의 랜덤한 파일이름 생성하고 원본파일의 확장자를 붙여준다
                
           		file = new File(filePath2 + storedFileName); // 서버에 실제 파일을 저장하는 부분
           		multipartFile.transferTo(file); // 지정된 경로에 파일을 생성한다
           		// 위에서 만든 정보를 Filelist에 추가한다 
           		fileListMap2 = new HashMap<String,Object>();
           		fileListMap2.put("MOVIE_NO", MOVIE_NO);
           		fileListMap2.put("STILLCUT_ORGNAME", originalFileName);
          		fileListMap2.put("STILLCUT_SAVNAME", storedFileName);
           		fileList2.add(fileListMap2);

            }
        }
        return fileList2;
    }
    
    /*영화 포스터 파일 수정*/ 
    public List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        // 클라이언트에서 전송된 파일 정보를 담아서 반환을 해주는 List (다중파일전송)
        List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>(); 
        Map<String, Object> fileListMap = null;
         
        String MOVIE_NO = (String)map.get("MOVIE_NO");
        String requestName = null;
        String idx = null;
        
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            System.out.println("test ============================== " + multipartFile.getName());
            
            if(multipartFile.isEmpty() == false) { 

         		originalFileName = multipartFile.getOriginalFilename();
           		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
           		storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32자리의 랜덤한 파일이름 생성하고 원본파일의 확장자를 붙여준다
                
           		multipartFile.transferTo(new File(filePath + storedFileName));
           		
           		fileListMap = new HashMap<String,Object>();
           		fileListMap.put("IS_NEW", "Y");
           		fileListMap.put("MOVIE_NO", MOVIE_NO);
           		fileListMap.put("POSTER_ORGNAME", originalFileName);
           		fileListMap.put("POSTER_SAVNAME", storedFileName);
           		fileList.add(fileListMap);
            } 
            
            else {
            	requestName = multipartFile.getName();
            	idx = "poster";
            	if(map.containsKey(idx) == true && map.get(idx) != null) {

            	fileListMap = new HashMap<String,Object>();
            	fileListMap.put("IS_NEW", "N");
                fileListMap.put("FILE_NO", map.get(idx));
                fileList.add(fileListMap);
            	}
            }
        }
        return fileList;
    }
    
    /*영화 스틸컷 파일 수정*/ 
    public List<Map<String, Object>> parseUpdateFileInfo2(Map<String, Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        // 클라이언트에서 전송된 파일 정보를 담아서 반환을 해주는 List (다중파일전송)
        List<Map<String,Object>> fileList2 = new ArrayList<Map<String,Object>>(); 
        Map<String, Object> fileListMap2 = null;
         
        int MOVIE_NO = (Integer)map.get("MOVIE_NO");
        String requestName = null;
        String idx2 = null;
        
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            System.out.println("test ============================== " + multipartFile.getName());
            
            if(multipartFile.isEmpty() == false){ 

         		originalFileName = multipartFile.getOriginalFilename();
           		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
           		storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32자리의 랜덤한 파일이름 생성하고 원본파일의 확장자를 붙여준다
                
           		multipartFile.transferTo(new File(filePath2 + storedFileName));
           		
           		fileListMap2 = new HashMap<String,Object>();
           		fileListMap2.put("IS_NEW", "Y");
           		fileListMap2.put("MOVIE_NO", MOVIE_NO);
           		fileListMap2.put("STILLCUT_ORGNAME", originalFileName);
           		fileListMap2.put("STILLCUT_SAVNAME", storedFileName);
           		fileList2.add(fileListMap2);
            } 
            else {
        		requestName = multipartFile.getName();
            	idx2 = "IDX_"+requestName.substring(requestName.indexOf("_")+1);
            	
            	fileListMap2 = new HashMap<String,Object>();
            	fileListMap2.put("IS_NEW", "N");
                fileListMap2.put("STILLCUT_NO", map.get(idx2));
                fileList2.add(fileListMap2);
            }
        }
        return fileList2;
    }
    
    public void fileDelete(Map<String, Object> map, String filePath, String media) throws Exception{
        String POSTER_SAVNAME = null;
        String STILLCUT_SAVNAME = null;
        File file = null;
        
        file = new File(filePath + POSTER_SAVNAME);
        file.delete();
        
        file = new File(filePath2 + STILLCUT_SAVNAME);
        file.delete();
            
    }
    
    /*영화 슬라이드 파일 Insert*/    
    public List<Map<String,Object>> parseInsertFileInfo3(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
           
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<Map<String,Object>> fileList3 = new ArrayList<Map<String,Object>>(); // 클라이언트에서 전송된 파일 정보를 담아서 반환을 해주는 List (다중파일전송)
        Map<String, Object> fileListMap3 = null;
               
        String MOVIE_NO = (String)map.get("MOVIE_NO");
         
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
                
           		file = new File(filePath3 + storedFileName); // 서버에 실제 파일을 저장하는 부분
           		multipartFile.transferTo(file); // 지정된 경로에 파일을 생성한다
           		// 위에서 만든 정보를 Filelist에 추가한다 
           		fileListMap3 = new HashMap<String,Object>();
           		fileListMap3.put("MOVIE_NO", MOVIE_NO);
           		fileListMap3.put("SLIDER_ORGNAME", originalFileName);
           		fileListMap3.put("SLIDER_SAVNAME", storedFileName);
           		fileList3.add(fileListMap3);
            } 
        }
        return fileList3;
    }
    
    /*영화 슬라이드 파일 수정*/ 
    public List<Map<String, Object>> parseUpdateFileInfo3(Map<String, Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        // 클라이언트에서 전송된 파일 정보를 담아서 반환을 해주는 List (다중파일전송)
        List<Map<String,Object>> fileList3 = new ArrayList<Map<String,Object>>(); 
        Map<String, Object> fileListMap3 = null;
         
        String MOVIE_NO = (String)map.get("MOVIE_NO");
        String requestName = null;
        String idx = null;
        
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            System.out.println("test ============================== " + multipartFile.getName());
            
            if(multipartFile.isEmpty() == false) { 

         		originalFileName = multipartFile.getOriginalFilename();
           		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
           		storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32자리의 랜덤한 파일이름 생성하고 원본파일의 확장자를 붙여준다
                
           		multipartFile.transferTo(new File(filePath3 + storedFileName));
           		
           		fileListMap3 = new HashMap<String,Object>();
           		fileListMap3.put("IS_NEW", "Y");
           		fileListMap3.put("MOVIE_NO", MOVIE_NO);
           		fileListMap3.put("SLIDER_ORGNAME", originalFileName);
           		fileListMap3.put("SLIDER_SAVNAME", storedFileName);
           		fileList3.add(fileListMap3);
            } 
            
            else {
            	requestName = multipartFile.getName();
            	idx = "slider";
            	if(map.containsKey(idx) == true && map.get(idx) != null) {

            	fileListMap3 = new HashMap<String,Object>();
            	fileListMap3.put("IS_NEW", "N");
                fileListMap3.put("SLIDER_NO", map.get(idx));
                fileList3.add(fileListMap3);
            	}
            }
        }
        return fileList3;
    }
}
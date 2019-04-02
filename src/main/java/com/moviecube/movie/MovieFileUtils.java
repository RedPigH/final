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
 
@Component("movieFileUtils") // �씠 媛앹껜�쓽 愿�由щ�� �뒪�봽留곸씠 �떞�떦�븯�룄濡� �븿
public class MovieFileUtils {
	private static final String filePath = "C:\\Users\\user1\\Documents\\MovieCube\\src\\main\\webapp\\resources\\upload\\movie\\poster\\"; // POSTER �뙆�씪�쓽 ���옣�쐞移�
    private static final String filePath2 = "C:\\Users\\user1\\Documents\\MovieCube\\src\\main\\webapp\\resources\\upload\\movie\\stillcut\\"; // STILLCUT �뙆�씪�쓽 ���옣�쐞移�
    private static final String filePath3 = "C:\\Users\\user1\\Documents\\MovieCube\\src\\main\\webapp\\resources\\upload\\movie\\slider\\"; // SLIDER �뙆�씪�쓽 ���옣�쐞移�
    
    /*�쁺�솕 �룷�뒪�꽣 �뙆�씪 Insert*/ 
    public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
           
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>(); // �겢�씪�씠�뼵�듃�뿉�꽌 �쟾�넚�맂 �뙆�씪 �젙蹂대�� �떞�븘�꽌 諛섑솚�쓣 �빐二쇰뒗 List (�떎以묓뙆�씪�쟾�넚)
        Map<String, Object> fileListMap = null;
               
        String MOVIE_NO = map.get("MOVIE_NO").toString();  // MovieServiceImpl �쁺�뿭�뿉�꽌 �쟾�떖�빐以� map�뿉�꽌 �떊洹쒓쾶�떆湲��쓽 踰덊샇瑜� 諛쏆븘�삩�떎
         
        File file = new File(filePath); // �뙆�씪�쓣 ���옣�븷 寃쎈줈�뿉 �빐�떦�뤃�뜑媛� �뾾�쑝硫� �뤃�뜑瑜� �깮�꽦�븳�떎
        if(file.exists() == false){
            file.mkdirs();
        }
         
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            System.out.println("test ============================== " + multipartFile.getName());

         	if(multipartFile.isEmpty() == false) {  // �뙆�씪�쓽 �젙蹂대�� 諛쏆븘�꽌 �깉濡쒖슦�� �씠由꾩쑝濡� 蹂�寃쏀븯�뒗 濡쒖쭅

            	originalFileName = multipartFile.getOriginalFilename();
            	originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
           		storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32�옄由ъ쓽 �옖�뜡�븳 �뙆�씪�씠由� �깮�꽦�븯怨� �썝蹂명뙆�씪�쓽 �솗�옣�옄瑜� 遺숈뿬以��떎
                
           		file = new File(filePath + storedFileName); // �꽌踰꾩뿉 �떎�젣 �뙆�씪�쓣 ���옣�븯�뒗 遺�遺�
           		multipartFile.transferTo(file); // 吏��젙�맂 寃쎈줈�뿉 �뙆�씪�쓣 �깮�꽦�븳�떎
           		// �쐞�뿉�꽌 留뚮뱺 �젙蹂대�� Filelist�뿉 異붽��븳�떎 
           		fileListMap = new HashMap<String,Object>();
           		fileListMap.put("MOVIE_NO", MOVIE_NO);
           		fileListMap.put("POSTER_ORGNAME", originalFileName);
           		fileListMap.put("POSTER_SAVNAME", storedFileName);
           		fileList.add(fileListMap);
            } 
        }
        return fileList;
    }
    
    /*�쁺�솕 �뒪�떥而� �뙆�씪 �닔�젙*/ 
    public List<Map<String,Object>> parseInsertFileInfo2(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
           
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<Map<String,Object>> fileList2 = new ArrayList<Map<String,Object>>(); // �겢�씪�씠�뼵�듃�뿉�꽌 �쟾�넚�맂 �뙆�씪 �젙蹂대�� �떞�븘�꽌 諛섑솚�쓣 �빐二쇰뒗 List (�떎以묓뙆�씪�쟾�넚)
        Map<String, Object> fileListMap2 = null;
               
        int MOVIE_NO = (Integer)map.get("MOVIE_NO");  // MovieServiceImpl �쁺�뿭�뿉�꽌 �쟾�떖�빐以� map�뿉�꽌 �떊洹쒓쾶�떆湲��쓽 踰덊샇瑜� 諛쏆븘�삩�떎
         
        File file = new File(filePath); // �뙆�씪�쓣 ���옣�븷 寃쎈줈�뿉 �빐�떦�뤃�뜑媛� �뾾�쑝硫� �뤃�뜑瑜� �깮�꽦�븳�떎
        if(file.exists() == false){
            file.mkdirs();
        }
         
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            System.out.println("test ============================== " + multipartFile.getName());
            
           	if(multipartFile.isEmpty() == false) { // �뙆�씪�쓽 �젙蹂대�� 諛쏆븘�꽌 �깉濡쒖슦�� �씠由꾩쑝濡� 蹂�寃쏀븯�뒗 濡쒖쭅
         
           		originalFileName = multipartFile.getOriginalFilename();
           		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            	storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32�옄由ъ쓽 �옖�뜡�븳 �뙆�씪�씠由� �깮�꽦�븯怨� �썝蹂명뙆�씪�쓽 �솗�옣�옄瑜� 遺숈뿬以��떎
                
           		file = new File(filePath2 + storedFileName); // �꽌踰꾩뿉 �떎�젣 �뙆�씪�쓣 ���옣�븯�뒗 遺�遺�
           		multipartFile.transferTo(file); // 吏��젙�맂 寃쎈줈�뿉 �뙆�씪�쓣 �깮�꽦�븳�떎
           		// �쐞�뿉�꽌 留뚮뱺 �젙蹂대�� Filelist�뿉 異붽��븳�떎 
           		fileListMap2 = new HashMap<String,Object>();
           		fileListMap2.put("MOVIE_NO", MOVIE_NO);
           		fileListMap2.put("STILLCUT_ORGNAME", originalFileName);
          		fileListMap2.put("STILLCUT_SAVNAME", storedFileName);
           		fileList2.add(fileListMap2);

            }
        }
        return fileList2;
    }
    
    /*�쁺�솕 �룷�뒪�꽣 �뙆�씪 �닔�젙*/ 
    public List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        // �겢�씪�씠�뼵�듃�뿉�꽌 �쟾�넚�맂 �뙆�씪 �젙蹂대�� �떞�븘�꽌 諛섑솚�쓣 �빐二쇰뒗 List (�떎以묓뙆�씪�쟾�넚)
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
           		storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32�옄由ъ쓽 �옖�뜡�븳 �뙆�씪�씠由� �깮�꽦�븯怨� �썝蹂명뙆�씪�쓽 �솗�옣�옄瑜� 遺숈뿬以��떎
                
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
    
    /*�쁺�솕 �뒪�떥而� �뙆�씪 �닔�젙*/ 
    public List<Map<String, Object>> parseUpdateFileInfo2(Map<String, Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        // �겢�씪�씠�뼵�듃�뿉�꽌 �쟾�넚�맂 �뙆�씪 �젙蹂대�� �떞�븘�꽌 諛섑솚�쓣 �빐二쇰뒗 List (�떎以묓뙆�씪�쟾�넚)
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
           		storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32�옄由ъ쓽 �옖�뜡�븳 �뙆�씪�씠由� �깮�꽦�븯怨� �썝蹂명뙆�씪�쓽 �솗�옣�옄瑜� 遺숈뿬以��떎
                
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
    
    /*�쁺�솕 �뒳�씪�씠�뱶 �뙆�씪 Insert*/    
    public List<Map<String,Object>> parseInsertFileInfo3(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
           
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<Map<String,Object>> fileList3 = new ArrayList<Map<String,Object>>(); // �겢�씪�씠�뼵�듃�뿉�꽌 �쟾�넚�맂 �뙆�씪 �젙蹂대�� �떞�븘�꽌 諛섑솚�쓣 �빐二쇰뒗 List (�떎以묓뙆�씪�쟾�넚)
        Map<String, Object> fileListMap3 = null;
               
        String MOVIE_NO = (String)map.get("MOVIE_NO");
         
        File file = new File(filePath); // �뙆�씪�쓣 ���옣�븷 寃쎈줈�뿉 �빐�떦�뤃�뜑媛� �뾾�쑝硫� �뤃�뜑瑜� �깮�꽦�븳�떎
        if(file.exists() == false){
            file.mkdirs();
        }
         
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            System.out.println("test ============================== " + multipartFile.getName());

         	if(multipartFile.isEmpty() == false) {  // �뙆�씪�쓽 �젙蹂대�� 諛쏆븘�꽌 �깉濡쒖슦�� �씠由꾩쑝濡� 蹂�寃쏀븯�뒗 濡쒖쭅

            	originalFileName = multipartFile.getOriginalFilename();
            	originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
           		storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32�옄由ъ쓽 �옖�뜡�븳 �뙆�씪�씠由� �깮�꽦�븯怨� �썝蹂명뙆�씪�쓽 �솗�옣�옄瑜� 遺숈뿬以��떎
                
           		file = new File(filePath3 + storedFileName); // �꽌踰꾩뿉 �떎�젣 �뙆�씪�쓣 ���옣�븯�뒗 遺�遺�
           		multipartFile.transferTo(file); // 吏��젙�맂 寃쎈줈�뿉 �뙆�씪�쓣 �깮�꽦�븳�떎
           		// �쐞�뿉�꽌 留뚮뱺 �젙蹂대�� Filelist�뿉 異붽��븳�떎 
           		fileListMap3 = new HashMap<String,Object>();
           		fileListMap3.put("MOVIE_NO", MOVIE_NO);
           		fileListMap3.put("SLIDER_ORGNAME", originalFileName);
           		fileListMap3.put("SLIDER_SAVNAME", storedFileName);
           		fileList3.add(fileListMap3);
            } 
        }
        return fileList3;
    }
    
    /*�쁺�솕 �뒳�씪�씠�뱶 �뙆�씪 �닔�젙*/ 
    public List<Map<String, Object>> parseUpdateFileInfo3(Map<String, Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        // �겢�씪�씠�뼵�듃�뿉�꽌 �쟾�넚�맂 �뙆�씪 �젙蹂대�� �떞�븘�꽌 諛섑솚�쓣 �빐二쇰뒗 List (�떎以묓뙆�씪�쟾�넚)
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
           		storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32�옄由ъ쓽 �옖�뜡�븳 �뙆�씪�씠由� �깮�꽦�븯怨� �썝蹂명뙆�씪�쓽 �솗�옣�옄瑜� 遺숈뿬以��떎
                
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
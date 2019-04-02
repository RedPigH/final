package com.moviecube.movie;

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

import com.moviecube.movie.MovieDAO;

@Service("movieService")
public class MovieServiceImpl implements MovieService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="movieDAO")
	private MovieDAO MovieDAO;
	
	@Resource(name="movieFileUtils") // @Conponent 어노테이션을 이용하여 등록한 객체를 @Resource 어노테이션을 이용하여 객체를 선언 한다
	private MovieFileUtils fileUtils;
	
	@Override
	public List<Map<String, Object>> selectMovieList(Map<String, Object> map) throws Exception {
		return MovieDAO.selectMovieList(map);
	}
	
	@Override
	public List<Map<String, Object>> HotMovieList(Map<String, Object> map) throws Exception{
		return MovieDAO.HotMovieList(map);
	}
	
	@Override
	public List<Map<String, Object>> LatelyMovieList(Map<String, Object> map) throws Exception{
		return MovieDAO.LatelyMovieList(map);
	}
	
	@Override
	public List<Map<String, Object>> ExpectedMovieList(Map<String, Object> map) throws Exception{
		return MovieDAO.ExpectedMovieList(map);
	}
	
	@Override
	public List<Map<String, Object>> GradeMovieList(Map<String, Object> map) throws Exception {
		return MovieDAO.GradeMovieList(map);
	}
	
	@Override
	public List<Map<String, Object>> CommentMovieList(Map<String, Object> map) throws Exception{
		return MovieDAO.CommentMovieList(map);
	}
	
	@Override
	public List<Map<String, Object>> dupMovieList(Map<String, Object> map) throws Exception{
		return MovieDAO.dupMovieList(map);
	}

	@Override
	public void insertMovie(Map<String, Object> map, HttpServletRequest request) throws Exception {
		MovieDAO.insertMovie(map);
		
		List<Map<String,Object>> fileList = fileUtils.parseInsertFileInfo(map, request);
		 	MovieDAO.insertFile(fileList.get(0)); 	
//          for(int i=1, size=fileList.size(); i<size; i++){
//      	MovieDAO.insertFile2(fileList.get(i));
//          }
		
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
	
	@Override
	public Map<String, Object> CommentLikeInfo(Map<String, Object> map) throws Exception{
		return MovieDAO.CommentLikeInfo(map);
	}
	
	@Override
	public void insertMovie2(Map<String, Object> map, HttpServletRequest request) throws Exception {
		List<Map<String,Object>> fileList2 = fileUtils.parseInsertFileInfo2(map, request);
	 		
			for(int i=0, size=fileList2.size(); i<size; i++){
				MovieDAO.insertFile2(fileList2.get(i));
			}
	}

	@Override
	public void insertMovie3(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		List<Map<String,Object>> fileList3 = fileUtils.parseInsertFileInfo3(map, request);
	 	MovieDAO.insertFile3(fileList3.get(0)); 
	}

	
	@Override
	public void insertComment(Map<String, Object> map) throws Exception {
		MovieDAO.insertComment(map);
		
	}

	
	@Override
	public Map<String, Object> selectMovieDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		
		Map<String, Object> tempMap = MovieDAO.selectMovieDetail(map);	
		resultMap.put("map", tempMap);
		
		Map<String, Object> tempMap2 = MovieDAO.selectSliderFileDetail(map);		
		resultMap.put("map2", tempMap2);

		List<Map<String, Object>> movieDetail = MovieDAO.selectMovieFileDetail(map);
		resultMap.put("movieDetail", movieDetail);

		return resultMap;
	}

	@Override
	public void modifyMovie(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		MovieDAO.modifyMovie(map);
		
		MovieDAO.updateFileList(map);
		
		List<Map<String,Object>> fileList = fileUtils.parseUpdateFileInfo(map, request);
		Map<String, Object> tempMap = null;
		
		for(int i=0, size=fileList.size(); i<size; i++){
			tempMap = fileList.get(i);
			if (i == 0) {
				
				if(tempMap.get("IS_NEW").equals("Y")) { 
					MovieDAO.insertFile(tempMap);	
				}
				else {
					MovieDAO.modifyFile(tempMap);
				}
			}
		}
	}	
	
	@Override
	public void modifyMovie2(Map<String, Object> map, HttpServletRequest request) throws Exception {
		MovieDAO.updateFileList2(map);
		
		List<Map<String,Object>> fileList2 = fileUtils.parseUpdateFileInfo2(map, request);
		Map<String, Object> tempMap = null;
		
		for(int i=0, size=fileList2.size(); i<size; i++){
			tempMap = fileList2.get(i);
			
			if(tempMap.get("IS_NEW").equals("Y")) {
				MovieDAO.insertFile2(tempMap);
			}
			else {
				MovieDAO.modifyFile2(tempMap);	
			}
		}
	}

	@Override
	public void modifyMovie3(Map<String, Object> map, HttpServletRequest request) throws Exception {
				
		MovieDAO.updateFileList3(map);
		
		List<Map<String,Object>> fileList3 = fileUtils.parseUpdateFileInfo3(map, request);
		Map<String, Object> tempMap = null;
		
		for(int i=0, size=fileList3.size(); i<size; i++){
			tempMap = fileList3.get(i);
			if (i == 0) {
				
				if(tempMap.get("IS_NEW").equals("Y")) { 
					MovieDAO.insertFile3(tempMap);	
				}
				else {
					MovieDAO.modifyFile3(tempMap);
				}
			}
		}
	}	

	public void modifyGrade(Map<String, Object> map) throws Exception{
		MovieDAO.modifyGrade(map);
	}
	
	@Override
	public void deleteMovie(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		MovieDAO.deleteMovie(map);
	}

	@Override
	public List<Map<String, Object>> selectStillCutList(Map<String, Object> map) throws Exception {
		return MovieDAO.selectStillCutList(map);
	}

	@Override
	public List<Map<String, Object>> selectCommentList(Map<String, Object> map) throws Exception {
		return MovieDAO.selectCommentList(map);
	}
	
	@Override
	public Map<String, Object> selectCommentCount(Map<String, Object> map) throws Exception {
		return MovieDAO.selectCommentCount(map);
	}
	
	@Override
	public List<Map<String, Object>> selectCommentPaingList(Map<String, Object> map) throws Exception {
		return MovieDAO.selectCommentPagingList(map);
	}
	
	@Override
	public void deleteComment(Map<String, Object> map) throws Exception {
		MovieDAO.deleteComment(map);
	}

	@Override
	public List<Map<String, Object>> movieSearch(Map<String, Object> map) throws Exception {
		return MovieDAO.movieSearch(map);
	}

	@Override
	public List<Map<String, Object>> MainMovieSearch(Map<String, Object> map) throws Exception {
		
		return MovieDAO.MainMovieSearch(map);
	}
	
}

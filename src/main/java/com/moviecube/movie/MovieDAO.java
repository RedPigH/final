package com.moviecube.movie;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moviecube.common.AbstractDAO;

@Repository("movieDAO")
public class MovieDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMovieList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.selectMovieList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> HotMovieList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.HotMovieList", map);
	}
<<<<<<< HEAD
=======
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectHotMovie(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("movie.selectHotMovie", map);
	}
	
>>>>>>> ebdbccbfdff85f23768ebe1f80a42883fd1861e9

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> LatelyMovieList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.LatelyMovieList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> ExpectedMovieList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.ExpectedMovieList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> GradeMovieList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.GradeMovieList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> CommentMovieList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.CommentMovieList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectStillCutList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.selectStillCutList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectCommentList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.selectCommentList", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> CommentLikeInfo(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("movie.CommentLikeInfo", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectCommentPagingList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.selectCommentPagingList", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectCommentCount(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("movie.selectCommentCount", map);
	}

	public void insertMovie(Map<String, Object> map) throws Exception {
		insert("movie.insertMovie", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> dupMovieList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.DupMovieList", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMovieDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("movie.selectMovieDetail", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMovieFileDetail(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("movie.selectMovieFileDetail", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectSliderFileDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("movie.selectSliderFileDetail", map);
	}

	public void modifyMovie(Map<String, Object> map) throws Exception {
		update("movie.modifyMovie", map);
	}

	public void deleteMovie(Map<String, Object> map) throws Exception {
		update("movie.deleteMovie", map);
	}

	public void insertFile(Map<String, Object> filelist) throws Exception {
		insert("movie.insertFile", filelist);
	}

	public void insertFile2(Map<String, Object> map) throws Exception {
		insert("movie.insertFile2", map);
	}

	public void insertFile3(Map<String, Object> map) throws Exception {
		insert("movie.insertFile3", map);
	}
	
	public void updateFileList(Map<String, Object> map) throws Exception {
		update("movie.updateFileList", map);
	}

	public void updateFileList2(Map<String, Object> map) {
		update("movie.updateFileList2", map);
	}

	public void updateFileList3(Map<String, Object> map) throws Exception {
		update("movie.updateFileList3", map);
	}
	
	public void modifyFile(Map<String, Object> map) {
		update("movie.modifyFile", map);
	}

	public void modifyFile2(Map<String, Object> map) {
		update("movie.modifyFile2", map);
	}

	public void modifyFile3(Map<String, Object> map) {
		update("movie.modifyFile3", map);
	}
	
	public void modifyGrade(Map<String, Object> map) {
		update("movie.modifyGrade", map);
	}

	public void deleteFile(Map<String, Object> map) {
		delete("movie.deleteFile", map);
	}

	public void deleteFile2(Map<String, Object> map) {
		delete("movie.deleteFile2", map);
	}

	public void insertComment(Map<String, Object> map) {
		insert("movie.insertComment", map);
	}

	public void deleteComment(Map<String, Object> map) {
		delete("movie.deleteComment", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> movieSearch(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("movie.movieSearch", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> MainMovieSearch(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("movie.MainMovieSearch", map);
	}

}
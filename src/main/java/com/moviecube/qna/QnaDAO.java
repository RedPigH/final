package com.moviecube.qna;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.moviecube.dao.AbstractDAO;

@Repository("qnaDAO")
public class QnaDAO extends AbstractDAO {

   @SuppressWarnings("unchecked")
   public List<Map<String, Object>> selectQnaList(Map<String, Object> map) throws Exception {

      // TODO Auto-generated method stub
      return (List<Map<String, Object>>) selectList("qna.selectQnaList", map);
   };
   
   @SuppressWarnings("unchecked")
   public List<Map<String, Object>> selectQnaListByMember(Map<String, Object> map) throws Exception {
      return (List<Map<String, Object>>) selectList("qna.selectQnaListByMember", map);
   };

   @SuppressWarnings("unchecked")
   public List<Map<String, Object>> selectQnaFileList(Map<String, Object> map) throws Exception {

      return (List<Map<String, Object>>) selectList("qna.selectQnaFileList", map);
   }

   public void insertQna(Map<String, Object> map) throws Exception {
      insert("qna.insertQna", map);
   }

   @SuppressWarnings("unchecked")
   public Map<String, Object> selectQnaDetail1(Map<String, Object> map) throws Exception {
      return (Map<String, Object>) selectOne("qna.selectQnaDetail1", map);
   }

   @SuppressWarnings("unchecked")
   public Map<String, Object> selectQnaDetail2(Map<String, Object> map) throws Exception {
      return (Map<String, Object>) selectOne("qna.selectQnaDetail2", map);
   }

   public void updateQna(Map<String, Object> map) throws Exception { // 여기서 파일삭제까지 같이 할 예정
      // TODO Auto-generated method stub
      update("qna.updateQna", map);
   }

   public void deleteQna(Map<String, Object> map) throws Exception {
      delete("qna.deleteQna", map);
      // TODO Auto-generated method stub

   }

   public void insertFile(Map<String, Object> map) throws Exception {
      insert("qna.insertQnaFile", map);
   }

   public void insertFile2(Map<String, Object> map) throws Exception {
      insert("qna.insertQnaFile2", map);
   }

   public void replyQna(Map<String, Object> map, HttpServletRequest request) throws Exception {
      insert("qna.replyQna", map);
   }

   public void deleteQnaFile(Map<String, Object> map) {
      delete("qna.deleteQnaFile", map);
      // TODO Auto-generated method stub
   }

   public void updateQnaFile(Map<String, Object> map) {
      update("qna.updateQnaFile", map);
   }

   @SuppressWarnings("unchecked")
   public Map<String, Object> checkQnaFile(Map<String, Object> map) throws Exception {
      return (Map<String, Object>) selectOne("qna.checkQnaFile", map);
   }
}
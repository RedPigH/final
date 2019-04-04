package com.moviecube.member;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moviecube.dao.AbstractDAO;

@Repository("memberDAO")
public class MemberDAO extends AbstractDAO {

	/* protected Log log = LogFactory.getLog(MemberDAO.class); */

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void insertMember(Map<String, Object> map) throws Exception {
		insert("member.insertMember", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> checkId(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.checkId", map);
	}

	public int findUsedID(Map<String, Object> map) throws Exception {
		return (Integer) selectOne("member.findUsedID", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> checkUserIdAndPassword(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.findUserIdAndPassword", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOneMember(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.selectOneMember", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> findId(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.findId", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> findPasswd(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.findPasswd", map);
	}

	public void updateMile(Map<String, Object> map) throws Exception {
		update("member.updateMile", map);
	}

	public void updateRank(Map<String, Object> map) throws Exception {
		update("member.updateRank", map);
	}

	public void updateMember(Map<String, Object> map) throws Exception {
		update("member.informUpdate", map);
	}

	public void updatePass(Map<String, Object> map) throws Exception {
		update("member.passwdUpdate", map);
	}

	public void deleteMember(Map<String, Object> map) throws Exception {
		delete("member.deleteMember", map);
	}

	public void insertFile(Map<String, Object> map) throws Exception {
		insert("member.insertFile", map);
	}

	@SuppressWarnings("unchecked") 
	public Map<String, Object> selectMemberFile(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.selectP", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMemberFile1(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.selectPF", map);
	}
	
	public void deleteFile(Map<String, Object> map) throws Exception{
		  delete("member.deleteFile", map);
	}

	public void updateProfile(Map<String, Object> map) throws Exception { // 여기서 파일삭제까지 같이 할 예정
		update("member.updateProfile", map);
	}
}

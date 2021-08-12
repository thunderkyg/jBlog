package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입
	public int insertJoin(UserVo userVo) {
		System.out.println("[UserDao.insertUser]");
		
		int count = sqlSession.insert("user.insertUser", userVo);
		System.out.println(count);
		
		return count;
	}
	
	//유저 아이디 확인
	public UserVo checkId(String id) {
		System.out.println("[UserDao.checkId]");
		
		UserVo userVo = new UserVo(id);
		
		return sqlSession.selectOne("user.checkId", userVo);
	}
	
	//유저 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserDao.getUser]");
		
		return sqlSession.selectOne("user.getUser", userVo);
		
	}
}

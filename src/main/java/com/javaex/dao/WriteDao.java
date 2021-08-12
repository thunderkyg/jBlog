package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.WriteVo;

@Repository
public class WriteDao {
	
	@Autowired
	SqlSession sqlSession;
	
	//write
	public int writePost(WriteVo writeVo) {
		System.out.println("[WriteDao.writePost]");
		
		return sqlSession.insert("write.insertPost", writeVo);
		
	}
	
	//getOnePost
	public WriteVo getOnePost(int no) {
		System.out.println("[WriteDao.getOnePost]");
		
		return sqlSession.selectOne("write.getOnePost", no);
		
	}
	

}

package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertBlog(UserVo userVo) {
		System.out.println("[BlogDao.InsertBlog()]");

		Map<String, Object> blogMap = new HashMap<String, Object>();
		//아이디
		blogMap.put("id", userVo.getId());
		//블로그이름
		String blogTitle = userVo.getUserName() + "의 블로그입니다.";
		blogMap.put("blogTitle", blogTitle);
		System.out.println(blogMap);
		
		int count = sqlSession.insert("blog.insertBlog", blogMap);
		
		return count;
	}
	
	public BlogVo selectUser(String id) {
		System.out.println("BlogDao.SelectUser()");
		
		return sqlSession.selectOne("blog.selectBlog", id);
	}
	
	public int blogUpdate (BlogVo blogVo) {
		System.out.println("BlogDao.blogUpdate()");
		
		return sqlSession.update("blog.updateBlog", blogVo);
	}

	
	
	
}

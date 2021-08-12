package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("[UserService.join()]");
		
		int count = userDao.insertJoin(userVo);
		
		System.out.println("Blog용: " + userVo);
		blogDao.insertBlog(userVo);
		
		return count;
	}
	
	//아이디 중복 확인
	public boolean checkId(String id) {
		System.out.println("[UserService.checkId()]");
		
		UserVo userVo = userDao.checkId(id);
		
		if (userVo == null) {
			//사용 가능한 아이디
			return true;
		} else {
			//사용 불가능한 아이디
			return false;
		}
	}
	
	//로그인
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser()]");
		
		return userDao.getUser(userVo);
	}
	
	

}

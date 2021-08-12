package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.WriteDao;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.WriteVo;

@Service
public class WriteService {
	
	@Autowired
	WriteDao writeDao;
	@Autowired
	CategoryDao categoryDao;
	
	public List<CategoryVo> getList(){
		System.out.println("[WriteService.getList()]");
		
		List<CategoryVo> list = categoryDao.getList();
		System.out.println(list);
		
		return list;
	}
	
	//writePost
	public int writePost(WriteVo writeVo) {
		System.out.println("[WriteService.writePost()]");
		
		return writeDao.writePost(writeVo);
	}

}

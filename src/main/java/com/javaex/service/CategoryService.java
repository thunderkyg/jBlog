package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	public CategoryVo insertCategory(CategoryVo categoryVo) {
		System.out.println("[CategoryService.insertCategory()]");
		System.out.println(categoryVo);
		
		int count = categoryDao.insertCategory(categoryVo);
		
		int no = categoryVo.getCateNo();
		System.out.println(categoryVo);
		
		return categoryDao.selectOneCategory(no);
	}
	
	public List<CategoryVo> getList(){
		System.out.println("[CategoryService.getList()]");
		
		return categoryDao.getList();
	}
	
	public boolean deleteCategory(int cateNo) {
		System.out.println("[CategoryService.deleteCategory()]");
		
		boolean check;
		
		int count = categoryDao.checkPostDelete(cateNo);
		
		if (count != 0) {
			check = false;
		} else {
			categoryDao.deleteCategory(cateNo);
			check = true;
		}
		
		return check;
	}

}

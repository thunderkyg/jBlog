package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;
import com.javaex.vo.WriteVo;

@Repository
public class CategoryDao {

	@Autowired
	SqlSession sqlSession;
	
	public int insertCategory(CategoryVo categoryVo) {
		System.out.println("CategoryDao.InsertCategory()");
		
		return sqlSession.insert("category.insertCategory", categoryVo);
	}
	
	public List<WriteVo> selectCategoryPost(int no) {
		System.out.println("CategoryDao.selectCategoryPost()");
		
		List<WriteVo> writeList = sqlSession.selectList("category.selectCategoryPost", no);
		System.out.println(writeList);

		return writeList;
	}
	
	public CategoryVo selectOneCategory(int no) {
		System.out.println("CategoryDao.selectOneCategory()");
		
		CategoryVo categoryVo = sqlSession.selectOne("category.selectOneCategory", no);
		System.out.println(categoryVo);

		return categoryVo;
	}
	
	public List<CategoryVo> getList() {
		System.out.println("CategoryDao.getList()");
		
		List<CategoryVo> list = sqlSession.selectList("category.getList");
		
		System.out.println(list);
		
		return list;
	}
	
	public int deleteCategory(int cateNo) {
		
		return sqlSession.delete("category.deleteCategory", cateNo);
	}
	
	public int checkPostDelete(int cateNo) {
		System.out.println("CategoryDao.checkPostDelete()");
		
		return sqlSession.selectOne("category.checkPostDelete", cateNo);
	}
	
	public int maxCateNo() {
		System.out.println("CategoryDao.maxCateNo()");
		
		return sqlSession.selectOne("category.maxCateNo");
	}
	
	public int maxPostNo(int no) {
		System.out.println("CategoryDao.maxPostNo()");
		
		return sqlSession.selectOne("category.maxPostNo", no);
	}
}

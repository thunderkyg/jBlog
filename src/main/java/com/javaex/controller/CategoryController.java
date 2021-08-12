package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	//CategoryForm
	@RequestMapping(value = "/{id}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminCategoryForm(@PathVariable ("id") String id) {
		System.out.println("[BlogController.adminBasic()]");
		
		return "blog/admin/blog-admin-cate";
	}
	
	//List
	@ResponseBody
	@RequestMapping(value = "/admin/categoryList", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CategoryVo> categoryList() {
		System.out.println("[BlogController.categoryList()]");
		
		List<CategoryVo> categoryList = categoryService.getList();
		
		System.out.println(categoryList);
		
		return categoryList;
	}
	
	//CategoryInsert
	@ResponseBody
	@RequestMapping(value = "/admin/categoryInsert", method = { RequestMethod.GET, RequestMethod.POST })
	public CategoryVo categoryInsert(@RequestParam ("cateName") String cateName,
								 @RequestParam ("description") String description,
								 HttpSession session) {
		System.out.println("[BlogController.categoryInsert()]");
		
		//아이디 받아오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String id = authUser.getId();
		
		CategoryVo categoryVo = new CategoryVo(id, cateName, description);
		
		System.out.println(categoryVo);
		
		CategoryVo updatedUser = categoryService.insertCategory(categoryVo);
		System.out.println(updatedUser);
		
		return updatedUser;
	}
	
	//CategoryDelete
	@ResponseBody
	@RequestMapping(value = "/admin/categoryDelete", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean categoryDelete(@RequestParam ("cateNo") int cateNo) {
		System.out.println("[BlogController.categoryDelete()]");
		System.out.println("cateNo: " + cateNo);
		
		boolean check = categoryService.deleteCategory(cateNo);
		System.out.println(check);

		return check;
	}
	
	
	

}

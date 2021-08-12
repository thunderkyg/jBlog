package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;
import com.javaex.vo.WriteVo;

@Controller

public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	// Main Blog
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String mainBlog(@PathVariable ("id") String id, Model model, HttpSession session) {
		System.out.println("[BlogController.MainBlog()]");
				
		BlogVo blogVo = blogService.selectUser(id);
		
		if (blogVo != null) {
			session.setAttribute("blogVo", blogVo);
			System.out.println(blogVo);
			
			List<CategoryVo> categoryList = blogService.getCategoryList();
			List<WriteVo> writeList = blogService.getDefaultCategory();
			WriteVo writeVo = blogService.getDefaultPost();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("writeList", writeList);
			model.addAttribute("selectedPost", writeVo);
			
			return "blog/blog-main";
		} else {
			return "user/loginForm";
		}
	}
	
	//Category의 글
	@RequestMapping(value = "/{id}/category/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String category(@PathVariable ("id") String id,
						   @PathVariable ("no") int no,
						   Model model, HttpSession session) {
		System.out.println("[BlogController.Category()]");
		
		model.addAttribute("no", no);
		
		WriteVo writeVo = blogService.getTopPost(no);
		model.addAttribute("selectedPost", writeVo);
		
		List<CategoryVo> categoryList = blogService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		
		List<WriteVo> writeList = blogService.selectCategoryPost(no);
		model.addAttribute("writeList", writeList);
		
		return "blog/blog-main";
	}
	
	//Post의 글
	@RequestMapping(value = "/{id}/post/{no}/{postNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public String post(@PathVariable ("id") String id,
					   @PathVariable ("no") int no,
					   @PathVariable ("postNo") int postNo,
					   Model model) {
		System.out.println("[BlogController.Post()]");
		
		//카테고리
		List<CategoryVo> categoryList = blogService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		
		WriteVo writeVo = blogService.getOnePost(postNo);
		model.addAttribute("selectedPost", writeVo);
		
		List<WriteVo> writeList = blogService.selectCategoryPost(no);
		model.addAttribute("writeList", writeList);
		
		return "blog/blog-main";
	}
	
	
	//BasicForm
	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminBasicForm(@PathVariable ("id") String id) {
		System.out.println("[BlogController.adminBasicForm()]");
		
		return "blog/admin/blog-admin-basic";
	}
	
	//BasicUpdate
	@RequestMapping(value = "/{id}/admin/basicUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public String basicUpdate(@RequestParam ("file") MultipartFile file,
							  @RequestParam ("blogTitle") String blogTitle,
							  HttpSession session){
		System.out.println("[BlogController.basicUpdate()]");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String id = authUser.getId();
		
		Map<String, Object> blogInsertMap = new HashMap<>();
		blogInsertMap.put("file", file);
		blogInsertMap.put("blogTitle", blogTitle);
		blogInsertMap.put("id", id);
		System.out.println(blogInsertMap);
		
		blogService.blogUpdate(blogInsertMap);
		
		return "redirect:/" + id;
	}
	
}

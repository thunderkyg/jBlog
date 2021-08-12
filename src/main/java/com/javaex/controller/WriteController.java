package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.WriteService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.WriteVo;

@Controller
public class WriteController {
	
	@Autowired
	WriteService writeService;
	
	
	//WriteForm
	@RequestMapping(value = "/{id}/admin/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminWriteForm(@PathVariable ("id") String id, Model model) {
		System.out.println("[WriteController.adminWrite()]");
		
		List<CategoryVo> categoryList = writeService.getList();
		model.addAttribute("categoryList", categoryList);
		
		return "blog/admin/blog-admin-write";
	}
	
	//Write
	@RequestMapping(value = "/{id}/admin/insert", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminWrite(@PathVariable ("id") String id,
							 @ModelAttribute WriteVo writeVo) {
		System.out.println("WriteController.adminWrite()");

		writeService.writePost(writeVo);
		System.out.println(writeVo);
		
		return "blog/admin/blog-admin-write";
		}
	

}

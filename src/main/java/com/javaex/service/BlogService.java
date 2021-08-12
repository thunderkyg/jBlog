package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.WriteDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.WriteVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	WriteDao writeDao;

	public BlogVo selectUser(String id) {
		System.out.println("BlogService.SelectUser()");

		return blogDao.selectUser(id);
	}

	//사진
	public int blogUpdate(Map<String, Object> blogInsertMap) {
		System.out.println("BlogService.blogUpdate()");

		MultipartFile file = (MultipartFile) blogInsertMap.get("file");
		String id = (String) blogInsertMap.get("id");
		String blogTitle= (String) blogInsertMap.get("blogTitle");

		String saveDir = "C:\\javaStudy\\upload";

		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName:" + exName);
		
		// 저장파일이름(관리 떄문에 겹치지 않는 새이름 부여)
		String logoFile = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName:" + logoFile);

		// 파일패스
		String filePath = saveDir + "\\" + logoFile;
		System.out.println("filePath:" + filePath);
		
		// 파일 서버하드디스크에 저장
		try {

			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		BlogVo blogVo = new BlogVo(id, blogTitle, logoFile);
		
		return blogDao.blogUpdate(blogVo);
	}
	
	//카테고리
	public List<CategoryVo> getCategoryList(){
		System.out.println("[BlogService.getList()]");
		
		List<CategoryVo> list = categoryDao.getList();
		System.out.println(list);
		
		return list;
	}
	
	//Get Default Category
	public List<WriteVo> getDefaultCategory() {
		System.out.println("[DefaultCategory]");
		
		int maxCate = categoryDao.maxCateNo();
		List<WriteVo> writeList = categoryDao.selectCategoryPost(maxCate);
		
		return writeList;
	}
	
	//Get Default Post
	public WriteVo getDefaultPost() {
		System.out.println("[DefaultPost]");
		
		int maxCate = categoryDao.maxCateNo();
		int maxPost = categoryDao.maxPostNo(maxCate);
		WriteVo writeVo = writeDao.getOnePost(maxPost);
		
		return writeVo;
	}
	
	//Get Top Post
	public WriteVo getTopPost(int no) {
		System.out.println("getTopPost");
		
		int topPost = categoryDao.maxPostNo(no);
		WriteVo writeVo = writeDao.getOnePost(topPost);
		
		
		return writeVo;
	}
	
	//카데고리 포스트
	public List<WriteVo> selectCategoryPost(int no) {
		System.out.println("[BlogService.getCategoryPost()]");
		
		List<WriteVo> writeList = categoryDao.selectCategoryPost(no);
		
		return writeList;
	}
	
	//포스트 가져오기
	public WriteVo getOnePost(int no) {
		System.out.println("[BlogService.getOnePost()]");
		
		WriteVo writeVo = writeDao.getOnePost(no);
		
		return writeVo;
	}

}

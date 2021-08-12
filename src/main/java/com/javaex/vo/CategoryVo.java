package com.javaex.vo;

public class CategoryVo {
	
	//Field
	private int rn;
	private int postNo;
	private int cateNo;
	private String id;
	private String cateName;
	private String description;
	private String regDate;

	
	//Constructor
	public CategoryVo() {
		
	}
	
	public CategoryVo(String id, String cateName, String description) {
		this.id = id;
		this.cateName = cateName;
		this.description = description;
	}

	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
	}

	public CategoryVo(int rn, int postNo, int cateNo, String id, String cateName, String description, String regDate) {
		this.rn = rn;
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
	}

	//Method
	
	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	//Method - ordinary
	@Override
	public String toString() {
		return "CategoryVo [rn=" + rn + ", postNo=" + postNo + ", cateNo=" + cateNo + ", id=" + id + ", cateName="
				+ cateName + ", description=" + description + ", regDate=" + regDate + "]";
	}
	

	
	

}

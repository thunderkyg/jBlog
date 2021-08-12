package com.javaex.vo;

public class BlogVo {
	
	//Field
	private String id;
	private String blogTitle;
	private String logoFile;

	//Constructor
	public BlogVo() {
	}
	
	public BlogVo(String id, String blogTitle, String logoFile) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}

	//Method G/S
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	//Method Ordinary
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + "]";
	}
}

package com.javaex.vo;

public class UserVo {

	// Field

	private int userNo;
	private String id;
	private String userName;
	private String password;
	private String joinDate;

	// Constructor
	public UserVo() {

	}
	
	public UserVo(String id) {
		this.id = id;
	}

	public UserVo(int userNo, String id, String userName) {
		this.userNo = userNo;
		this.id = id;
		this.userName = userName;
	}

	public UserVo(String id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public UserVo(int userNo, String id, String userName, String password, String joinDate) {
		this.userNo = userNo;
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.joinDate = joinDate;
	}

	// Method - G/S
	
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	// Method - Ordinary
	
	@Override
	public String toString() {
		return "UserVo [userNo=" + userNo + ", id=" + id + ", userName=" + userName + ", password=" + password
				+ ", joinDate=" + joinDate + "]";
	}
	
	
}

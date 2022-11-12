package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;



import lombok.Data;


@Data
public class User implements Serializable {

	private Long id;

	private String userName;

	private String passWord;



	public User() {
		super();
	}
	public User(String userName, String passWord) {
		super();
		this.id = new Date().getTime();
		this.userName = userName;
		this.passWord = passWord;
	}
	public User(String message) {
		this.userName=message;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}

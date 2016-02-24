package com.main.bean;

import javax.persistence.*;

@Entity
public class MemberVO{

	@Id
	String userId;
	
	@Column(length=200)
    String pwd;
	
	@Column(length=50)
    String name;
    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

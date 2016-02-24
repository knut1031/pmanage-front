package com.main.bean;

import javax.persistence.*;

@Entity
public class UserLoginHistoryVO{

	@Id
	private String userId;
	
	@Id
	private int seq;
	
	@Column(length=1)
	private String loginGubun;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getLoginGubun() {
		return loginGubun;
	}

	public void setLoginGubun(String loginGubun) {
		this.loginGubun = loginGubun;
	}

}

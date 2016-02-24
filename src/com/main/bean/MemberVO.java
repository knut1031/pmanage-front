package com.main.bean;

import javax.persistence.*;


@Entity
public class MemberVO{

	@Id
	private String userId;
	
	@Column(length=200)
	private String pwd;
	
	@Column(length=50)
	private String name;
	
	private UserLoginHistoryVO userLoginHistoryVo;
	
	/*
	 * @OneToMany 속성
	 *  FetchType.LAZY - 연관을 맺고 있는 Entity 들을 요청하는 순간 가져오는 설정
	 *  FetchType.EAGER - 미리 연관을 맺고 있는 Entity들까지 모두 가져오는 설정
	 *  
	 *  mappedBy - 관계가 양방향 일때 관계의 주인이 되는 쪽에서 어떤 속성으로 접근할건지 설정
	 */
	//@OneToMany(fetch=FetchType.LAZY, mappedBy="userLoginHistoryVo")
	//private List<UserLoginHistoryVO> userLoginHistoryVo;
	
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

	public UserLoginHistoryVO getUserLoginHistoryVo() {
		return userLoginHistoryVo;
	}

	public void setUserLoginHistoryVo(UserLoginHistoryVO userLoginHistoryVo) {
		this.userLoginHistoryVo = userLoginHistoryVo;
	}

	
}

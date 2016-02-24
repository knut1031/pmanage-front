package com.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.bean.MemberVO;

@Service
public interface MemberService {
	
	List<MemberVO> findAllMembers();
	
	MemberVO memberEdit(String userId, String pwd, String name);
	
	MemberVO memberAdd(String userId, String pwd, String name);
	
	void memberDelete(String userId);
}

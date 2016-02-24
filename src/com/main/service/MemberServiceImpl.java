package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.bean.MemberVO;
import com.main.dao.MemberDao;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	public List<MemberVO> findAllMembers() {
		return memberDao.findAllMembers();
	}
	
	/*
	 * @Transactional 어노테이션은 interface 를 구현한 클래스에서 사용이 가능.
	 * 인터페이스 구현하거나 servlet-context에 <annotation-driven proxy-target-class="true"/> 선언 
	 */
	@Transactional
	public MemberVO memberEdit(String userId, String pwd, String name) {
		return memberDao.memberEdit(userId, pwd, name);
	}
	
	@Transactional
	public MemberVO memberAdd(String userId, String pwd, String name) {
		return memberDao.memberAdd(userId, pwd, name);
	}
	
	@Transactional
	public void memberDelete(String userId) {
		memberDao.memberDelete(userId);
	}
	
}

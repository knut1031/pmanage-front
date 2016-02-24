package com.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.bean.MemberVO;
import com.main.bean.UserLoginHistoryVO;
import com.main.dao.MemberDao;
import com.pmanage.framework.data.DataSet;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Transactional(readOnly = true)
	public List<MemberVO> findAllMembers(Class cls, Map map) {
		return memberDao.findAllMembers(cls, map);
	}
	
	@Transactional(readOnly = true)
	public List<MemberVO> findAllMembersCriteria(Class cls, Map map) {
		return memberDao.findAllMembersCriteria(cls, map);
	}
	
	@Transactional(readOnly = true)
	public DataSet findAllMembersNative(Map map) {
		return memberDao.findAllMembersNative(map);
	}
	
	@Transactional(readOnly = true)
	public List<UserLoginHistoryVO> memberHistoryJoin(Class cls, Map map) {
		return memberDao.memberHistoryJoin(cls, map);
	}
	
	/*
	 * @Transactional 어노테이션은 interface 를 구현한 클래스에서 사용이 가능.
	 * 인터페이스 구현하거나 servlet-context에 <annotation-driven proxy-target-class="true"/> 선언 
	 */
	@Transactional(readOnly = false, rollbackFor=Exception.class)
	public int memberEdit(String userId, String pwd, String name) throws Exception {
		return memberDao.memberEdit(userId, pwd, name);
	}
	
	@Transactional(readOnly = false, rollbackFor=Exception.class)
	public void memberAdd(String userId, String pwd, String name) {
		memberDao.memberAdd(userId, pwd, name);
	}
	
	@Transactional(readOnly = false, rollbackFor=Exception.class)
	public void memberDelete(String userId) {
		memberDao.memberDelete(userId);
	}
	
}

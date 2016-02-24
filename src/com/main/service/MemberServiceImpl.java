package com.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	 * 
	 * propagation = Propagation.REQUIRES : (default)부모트렌젝션에서 실행. 없을경우 새로운 트랜잭션 생성
	 * propagation = Propagation.REQUIRES_NEW : 부모트렌젝션과 상관없이 무조건 새로운 트렌젝션 생성
	 */
	@Transactional(readOnly = false
						,propagation = Propagation.REQUIRES_NEW
						,rollbackFor={Exception.class,Exception.class}
						, timeout = -1)
	public int memberEdit(Map map) throws Exception {
		int result = memberDao.memberEdit(map);
			
		return result;
	}
	
	public int memberEditBegin(Map map) {
		int result = memberDao.memberEditBegin(map);
			
		return result;
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

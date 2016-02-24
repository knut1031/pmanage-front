package com.main.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.bean.MemberVO;
import com.main.bean.UserLoginHistoryVO;
import com.pmanage.framework.data.DataSet;
import com.pmanage.framework.persist.SqlMap;

@Repository("memberDao")
public class MemberDaoImpl  extends SqlMap implements MemberDao  {
	
	
	/*
	 * querySelect - HQL 에 SELECT 과 ALLIAS 명시
	 * queryList - HQL 에 SELECT 생략. 1 테이블 단순 조회
	 * queryNative - Native SQL
	 */
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// 조인 조회
	@Override
	public List<UserLoginHistoryVO> memberHistoryJoin(Class cls, Map map) {
		List<UserLoginHistoryVO> result = (List<UserLoginHistoryVO>) querySelect(cls, "pmanage/Member.hql.sql", "selectMemberJoinList", map);
		return result;
	}
	
	// 단순 조회
	@Override
	public List<MemberVO> findAllMembers(Class cls, Map map) {
		List<MemberVO> result = (List<MemberVO>) queryList(cls, "pmanage/Member.hql.sql", "selectMemberList", map);
		return result;
	}
	
	// NativeSQL
	//@Override
	public DataSet findAllMembersNative(Map map) {
		DataSet result = queryNative("pmanage/Member.hql.sql", "selectMemberListNative", map);
		return result;
	}
	
	// createCriteria : 객체를 이용해 조회 (hibernate 의 DB 호환을 높히기 위한 API) 
	@Override
	public List<MemberVO> findAllMembersCriteria(Class cls, Map map) {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<MemberVO> result = (List<MemberVO>) session.createCriteria(cls)
				.setMaxResults(50)	// limit 제한
				.add(Restrictions.eq("userId","jhchoi"))	// 조건
				.list();
		return result;
	}
	
	
	// 키값 조회
	public MemberVO get(Session s ,String userId) {
		return (MemberVO) s.get(MemberVO.class, userId);
	}

	@Override
	public int memberEdit(String userId, String pwd, String name)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void memberAdd(String userId, String pwd, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberDelete(String userId) {
		// TODO Auto-generated method stub
		
	}
	

}

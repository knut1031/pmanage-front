package com.main.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	 * - select 방법 -
	 * 1. querySelect - HQL 에 SELECT 과 ALLIAS 명시
	 * 2. queryList - HQL 에 SELECT 생략. 1 테이블 단순 조회
	 * 3. queryListNative - Native SQL
	 * 4. createCriteria - Hibernate API
	 * 
	 * - Update 방법 -
	 * 1. queryUpdate - HQL 문 사용하여 update
	 * 2. queryUpdateNative - Native SQL
	 * 3. sessionFactory update
	 * 4. hql update - transaction 강제 처리.
	 * 
	 */
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// 조인 조회
	@Override
	public List<UserLoginHistoryVO> memberHistoryJoin(Class cls, Map map) {
		Session session = sessionFactory.getCurrentSession();
		List<UserLoginHistoryVO> result = (List<UserLoginHistoryVO>) querySelect(cls, session, "pmanage/Member.hql.sql", "selectMemberJoinList", map);
		return result;
	}
	
	// 단순 조회
	@Override
	public List<MemberVO> findAllMembers(Class cls, Map map) {
		Session session = sessionFactory.getCurrentSession();
		List<MemberVO> result = (List<MemberVO>) queryList(cls, session, "pmanage/Member.hql.sql", "selectMemberList", map);
		return result;
	}
	
	// NativeSQL
	@Override
	public DataSet findAllMembersNative(Map map) {
		Session session = sessionFactory.getCurrentSession();
		DataSet result = queryListNative(session, "pmanage/Member.hql.sql", "selectMemberListNative", map);
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

	// 수정
	@Override
	public int memberEdit(Map map) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		
		// hql update
		int result = queryUpdate(session, "pmanage/Member.hql.sql", "updateMember", map);
		
		// sql update
		int result2 = queryUpdateNative(session, "pmanage/Member.hql.sql", "updateMemberNative", map);
		
		// 트렌젝션 테스트
		if(result+result2 < 2){
			throw new Exception();
		}
		
		// sessionFactory update
		MemberVO memberVo = get(session, (String)map.get("P4"));
		memberVo.setName((String)map.get("P3"));
		session.update(memberVo);
		
		return result+result2;
	}
	
	// 수정
	// hql update - transaction 강제 처리.
	@Override
	public int memberEditBegin(Map map){
		
		Session s = null;
		Transaction transaction = null;
		int result3 = 0;
		
		try{
			 s = sessionFactory.openSession();
			 transaction = s.beginTransaction();
			 transaction.setTimeout(30);
			 
			// hql update
			result3 = queryUpdate(s, "pmanage/Member.hql.sql", "updateMemberBegin", map);
			
			transaction.commit();
		}catch(Exception e){
			if (transaction != null) transaction.rollback();
		}finally{
			if (s != null) s.close();
		}
		
		return result3;
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

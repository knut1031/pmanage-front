package com.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.main.bean.MemberVO;

@Repository("memberDao")
public class MemberDaoImpl  implements MemberDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	// 조회
	public List<MemberVO> findAllMembers() {
		Session session = sessionFactory.openSession();
		List<MemberVO> result = (List<MemberVO>) session.createCriteria(MemberVO.class).list();
		
		return result;
	}
	
	// 키값 조회
	public MemberVO get(String userId) {
		Session session = sessionFactory.openSession();
		
		return (MemberVO) session.get(MemberVO.class, userId);
	}
	
	// 수정
	// @Transactional AOP 사용.
	// @Transactional AOP 미사용 주석처리.
	public MemberVO memberEdit(String userId, String pwd, String name){
		/*
		 * 기존 코드는 sessionFactory.openSession() 을 사용하지만
		 * Transaction annotation을 사용하는 경우 sessionFactory의 getCurrentSession 을 호출한다.
		 * 
		 * Transaction annotation 을 사용하지 않을 경우 아래 코드를 실행하는 것과 같다.
		 * 
		 * MemberVO memberVo = null; 
		 * Session s = null;
		 * Transaction transaction = null;
		 * 
		 * try{
		 * 		s = sessionFactory.openSession();
		 *		transaction = s.beginTransaction();
		 *		transaction.setTimeout(dbTimeout);
		 *
		 *		memberVo = get(userId);
		 *		memberVo.setPwd(pwd);		
		 *		memberVo.setName(name);
		 *	
		 *		s.update(memberVo);
		 * 	}catch(Exception e){
		 * 		transaction.rollback();
		 *	}finally{
		 *		s.close();
		 *	}
		 *
		 * */
		Session session = sessionFactory.getCurrentSession();
		
		MemberVO memberVo = get(userId);
		memberVo.setPwd(pwd);		
		memberVo.setName(name);
		
		session.update(memberVo);
		/*
		 * transaction.commit();
		 * 
		 * 메서드 종료시에 자동 commit이 이루어지기 때문에 
		 * getCurrentSession 을 통해서 transaction 이 적용된 세션을 얻어내야 한다.
		 */
		
		return memberVo;
	}
	
	// 추가
	public MemberVO memberAdd(String userId, String pwd, String name){
		MemberVO memberVo = new MemberVO();
		Session session = sessionFactory.getCurrentSession();
		
		memberVo.setUserId(userId);		
		memberVo.setPwd(pwd);
		memberVo.setName(name);
		
		session.save(memberVo);
		
		return memberVo;
	}
	
	// 삭제
	public void memberDelete(String userId){
		Session session = sessionFactory.getCurrentSession();
		MemberVO memberVo = get(userId);
		
		session.delete(memberVo);
	}

}

package com.main.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.main.bean.MemberVO;

/*
Interface 를 만들어 쓰면 좋은 이유 ?
- 모든 DAO에서 중복되거나 반복되는 코드를 상당량 줄일 수 있습니다.
- 테스트도 그만큼 줄일 수 있습니다.
- 개발이 좀 더 빨라집니다.
- 비슷한 기능을 하는 메서드 이름을 통일할 수 있습니다.
 */
@Repository
public interface MemberDao {

	List<MemberVO> findAllMembers();
	
	MemberVO memberEdit(String userId, String pwd, String name);
	
	MemberVO memberAdd(String userId, String pwd, String name);
	
	void memberDelete(String userId);
}

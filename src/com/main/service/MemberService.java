package com.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.main.bean.MemberVO;
import com.main.bean.UserLoginHistoryVO;
import com.pmanage.framework.data.DataSet;

@Service
public interface MemberService {
	
	List<MemberVO> findAllMembers(Class cls, Map map);
	
	List<MemberVO> findAllMembersCriteria(Class cls, Map map);
	
	DataSet findAllMembersNative(Map map);
	
	List<UserLoginHistoryVO> memberHistoryJoin(Class cls, Map map);
	
	int memberEdit(Map map) throws Exception;
	
	int memberEditBegin(Map map);
	
	void memberAdd(String userId, String pwd, String name);
	
	void memberDelete(String userId);
}

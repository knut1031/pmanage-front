package com.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.main.bean.MemberVO;
import com.main.bean.UserLoginHistoryVO;
import com.main.service.MemberService;
import com.pmanage.framework.data.DataSet;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = { "/" , "/member" }, method = RequestMethod.GET)  
	public String helloWorld(Model model ,HttpServletRequest request, HttpServletResponse response) {
		
		// 추가
		//memberService.memberAdd("sjpark","1111","박성진");
				
		// 삭제
		//memberService.memberDelete("dhpark");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("P0", "sjpark");
		map.put("P1", "jhchoi");
		map.put("P2", "zmsnxm");
		map.put("P3", "수정됐슴55");
		map.put("P4", "jhwang");
		map.put("P5", "jhchoi");
		
		// 수정
		// transation annotation 사용
		int resultEdit = 0;
		try{
			resultEdit = memberService.memberEdit(map);
		}catch(Exception e){
			e.printStackTrace();
			resultEdit = -1;
		}
		
		// transaction commit rollback 사용
		resultEdit = memberService.memberEditBegin(map);
		
		map = new HashMap<String, String>();
		map.put("P0", "jhchoi");
		map.put("P1", "jhchoi");
		map.put("P2", "jhchoi");
		
		// 조회
		List<MemberVO> members = memberService.findAllMembers(MemberVO.class,map);
		// API 조회
		List<MemberVO> membersCriteria = memberService.findAllMembersCriteria(MemberVO.class,map);
		// Native 조회
		DataSet membersNative = memberService.findAllMembersNative(map);
		// 조인 조회
		List<UserLoginHistoryVO> memberHistoryJoin =  memberService.memberHistoryJoin(UserLoginHistoryVO.class, map);
		
		
		
		request.setAttribute("members",members);
		request.setAttribute("membersCriteria",membersCriteria);
		request.setAttribute("membersNative",membersNative);
		request.setAttribute("memberHistoryJoin",memberHistoryJoin);
		
		return "member";
	}
	
}

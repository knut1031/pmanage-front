package com.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.main.bean.MemberVO;
import com.main.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = { "/" , "/member" }, method = RequestMethod.GET)  
	public String helloWorld(Model model ,HttpServletRequest request, HttpServletResponse response) {
		
		// 수정
		//memberService.memberEdit("dhpark","1111","박동");
		
		// 추가
		//memberService.memberAdd("sjpark","1111","박성진");
				
		// 삭제
		//memberService.memberDelete("dhpark");
		
		// 조회
		List<MemberVO> members = memberService.findAllMembers();
		request.setAttribute("members",members);
		
		return "member";
	}
	
}

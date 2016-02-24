package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.main.service.HomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	// @Autowired 해당 변수타입과  context 내에 설정된 이름이 일치하는 bean 을 찾아 인스턴스 변수에 주입해준다.
	// 의존성 주입 위해선 bean 에 getter, setter, 생성자가 필요한데 어노테이션 하면 없어도 가능하다.
	@Autowired
	HomeService homeService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String helloWorld(Model model) {
		model.addAttribute("message", homeService.getHome());
		
		return "home";
	}
	
}

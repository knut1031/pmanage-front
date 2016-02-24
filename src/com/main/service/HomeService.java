package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
	
	@Autowired
	private HomeDao homeDao;
	
	public String getHome(){
		return homeDao.getHome();
	}
}

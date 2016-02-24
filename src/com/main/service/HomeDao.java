package com.main.service;

import org.springframework.stereotype.Repository;

@Repository
public class HomeDao {
	public String getHome(){
		return "dao리턴값";
	}
}

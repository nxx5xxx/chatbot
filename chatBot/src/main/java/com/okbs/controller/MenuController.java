package com.okbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okbs.service.MenuService;

@Controller
public class MenuController {
	@Autowired
	MenuService menuService;
	
	@GetMapping("/send.do")
	@ResponseBody
	public String send() {
		return "데이터값";
	}
}

package com.okbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okbs.service.MenuService;

@Controller
public class MenuController {
	@Autowired
	MenuService menuService;
	
//	@GetMapping("/send.do")
//	@ResponseBody
//	public String send() {
//		return "데이터값";
//	}
	
	@GetMapping("/send.do")
	@ResponseBody
	public String send(@RequestParam("title") String title) {
		String data = "";
		try {
			data = menuService.selectMenu(title).getDesc();
		} catch (Exception e) {
			data = "해당하는 내역이 없습니다. 도움말을 입력해보세요!";
		}
		
		return data;
	}
}

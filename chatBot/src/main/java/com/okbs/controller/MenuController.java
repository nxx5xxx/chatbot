package com.okbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okbs.service.MenuService;
import com.okbs.service.OpinionService;
import com.okbs.service.TransService;

@Controller
public class MenuController {
	@Autowired
	MenuService menuService;
	@Autowired
	OpinionService opinionService;
	
	@Autowired
	TransService transService;
	
//	@GetMapping("/send.do")
//	@ResponseBody
//	public String send() {
//		return "데이터값";
//	}
	
	@GetMapping("/send.do")
	@ResponseBody
	public String send(@RequestParam("title") String title) {
		String data = "";
		//번역하기
		if(title.endsWith("번역해줘")) {
			//System.out.println("번역시작");
			data = transService.translate(title);
		}else {
			try {
				//메뉴선택하기 db에 해당하는 메뉴가 있을경우 정보전달
				data = menuService.selectMenu(title).getDesc();
				//System.out.println(data);
			} catch (Exception e) {
				//해당메뉴가 없어 예외가 날 경우 제안하기
				data = opinionService.getOpinions(title);
				if(data.length()>0) {
					data = "찾으시는 메뉴가 " + data + " 이 중에 있나요? <br> 원하시는 메뉴를 선택해보세요";
				}else {
					data = "해당하는 내역이 없습니다. '도움말'을 입력해보세요!";
				}
				//System.out.println("준비중입니다");
			}
		}

		
		return data;
	}
}

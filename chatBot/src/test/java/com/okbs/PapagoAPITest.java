package com.okbs;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.okbs.api.PapagoTransAPI;

class PapagoAPITest {
//	@Autowired
//	PapagoTransAPI api;
	
	String txt;
	@Test
	@DisplayName("파파고번역테스트")
	void test() {
		PapagoTransAPI api = new PapagoTransAPI();
		try {
			//api.setTxt(URLEncoder.encode("테스트코드입니다","UTF-8"));
			txt = URLEncoder.encode("테스트코드입니다","UTF-8");
		}catch(Exception e) {
			System.out.println("인코딩 실패" + e);
		}
		
		Map<String,String> reqHeaders = new HashMap<>();
		reqHeaders.put("X-Naver-Client-Id", "OrehdcLJZ1ti19puEojM");
		reqHeaders.put("X-Naver-Client-Secret", "puXWjHq49t");
		
		
		String responseBody = api.post(reqHeaders, txt);
		
		System.out.println(responseBody);
	}
	
	@Test
	@DisplayName("파파고번역테스트 - 언어변환")
	void test2() {
		PapagoTransAPI api = new PapagoTransAPI();
		try {
			//api.setTxt(URLEncoder.encode("테스트코드입니다","UTF-8"));
			txt = URLEncoder.encode("테스트코드입니다","UTF-8");
		}catch(Exception e) {
			System.out.println("인코딩 실패" + e);
		}
		
		Map<String,String> reqHeaders = new HashMap<>();
		reqHeaders.put("X-Naver-Client-Id", "OrehdcLJZ1ti19puEojM");
		reqHeaders.put("X-Naver-Client-Secret", "puXWjHq49t");
		
		api.setAfterTransLang("ja");
		String responseBody = api.post(reqHeaders, txt);
		
		System.out.println(responseBody);
	}

}

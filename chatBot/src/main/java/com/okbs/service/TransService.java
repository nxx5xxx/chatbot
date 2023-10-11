package com.okbs.service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.okbs.api.PapagoTransAPI;

@Service
public class TransService {

	public String translate(String txt) {
		PapagoTransAPI api = new PapagoTransAPI();
		//번역해줄 해당언어 설정
		if(txt.endsWith("영어로 번역해줘")) {
			api.setAfterTransLang("en");
		}else if(txt.endsWith("일본어로 번역해줘")) {
			api.setAfterTransLang("ja");
		}else if(txt.endsWith("중국어로 번역해줘")) {
			api.setAfterTransLang("zh-CN");
		}else if(txt.endsWith("프랑스어로 번역해줘")) {
			api.setAfterTransLang("fr");
		}
		
		//~로 번역해줘 또는 번역해줘 제거줘야함
		if(txt.endsWith("로 번역해줘")) {
			txt = txt.substring(0,txt.lastIndexOf("로 번역해줘"));
			try {
				txt = txt.substring(0,txt.lastIndexOf(" "));
			} catch (Exception e) {
				return "띄어쓰기에 유의해주세요";
			}
			
		}else {
			try {
				txt = txt.substring(0,txt.lastIndexOf(" 번역해줘"));
			} catch (Exception e) {
				return "띄어쓰기에 유의해주세요";
			}
		}
		
		//번역해야 할 언어 입력감지
		if(txt.matches("[가-힣 ]*")) {
			api.setTransLang("ko");
			if(api.getAfterTransLang().equals("ko")) {	
				api.setAfterTransLang("en");
			}
		}else if(txt.matches("[ぁ-ゔァ-ヴー々〆〤]*")) {
			api.setTransLang("ja");
		}else if(txt.matches("[一-龥]*")) {
			api.setTransLang("zh-CN");
		}else if(txt.matches("[a-zA-Z]*")) {
			api.setTransLang("en");
		}
		
		try {
			txt = URLEncoder.encode(txt,"UTF-8");
		}catch(Exception e) {
			System.out.println("translate 메소드 인코딩 실패" + e);
		}
		
		Map<String,String> reqHeaders = new HashMap<>();
		reqHeaders.put("X-Naver-Client-Id", "OrehdcLJZ1ti19puEojM");
		reqHeaders.put("X-Naver-Client-Secret", "puXWjHq49t");
		
		
		String responseBody = api.post(reqHeaders, txt);
		//번역한거 출력
		try {
			responseBody = responseBody.substring(responseBody.indexOf("\"translatedText\":\"")+18 
					, responseBody.lastIndexOf("\",\"engineType\"") );	
		} catch (Exception e) {
			responseBody = "해당 번역은 제공하지 않습니다."; 
		}
		
		
		return responseBody;
	}
	
	
}

package com.okbs.apiTest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class test2 {
	static String transLang = "ko";
	static String afterTransLang = "en";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		String txt ="";
		//번역할것 한글에서 영어
		try {
			//api.setTxt(URLEncoder.encode("테스트코드입니다","UTF-8"));
			txt = URLEncoder.encode("테스트코드입니다","UTF-8");
		}catch(Exception e) {
			System.out.println("인코딩 실패" + e);
		}
		
		Map<String,String> reqHeaders = new HashMap<>();
		reqHeaders.put("X-Naver-Client-Id", "OrehdcLJZ1ti19puEojM");
		reqHeaders.put("X-Naver-Client-Secret", "puXWjHq49t");
		
		String responseBody = post(apiURL, reqHeaders, txt);
		
		System.out.println(responseBody);
		
		//junit테스트코드에 넣을값들

	}
	//post로 보내서 받아오기
	public static String post(String apiURL, Map<String, String> reqHeaders, String txt) {
		System.out.println("post메서드 실행");
		HttpURLConnection conn = connect(apiURL);
		String txtData = "source="+transLang+"&target="+afterTransLang+"&text="+txt;
		try {
			conn.setRequestMethod("POST");
			//이부분 왜 for문으로 처리하는지모르겠다 
			// reqHeaders에 넣은 Map에는 내 네이버 클라이언트 id와 키코드밖에 없을텐데
			for(Map.Entry<String, String> header : reqHeaders.entrySet()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}
			//결국 아래 걸로 대체해도 되는거였다
			/*
			 * conn.setRequestProperty("X-Naver-Client-Id", "OrehdcLJZ1ti19puEojM");
			 * conn.setRequestProperty("X-Naver-Client-Secret", "puXWjHq49t");
			 */
			
			//setDoOutput 는 응답을 받겠다는 옵션
			conn.setDoOutput(true);
			try (DataOutputStream wr =new DataOutputStream(conn.getOutputStream())){
				wr.write(txtData.getBytes());
				wr.flush();
				
			} catch (Exception e) {
				System.out.println("DataOutputStream부분 에러 : "+e);
			}
			
			int resCode = conn.getResponseCode();
			//정상응답일경우
			if(resCode == HttpURLConnection.HTTP_OK) {
				return readBody(conn.getInputStream());
			}else {
				//응답 에러일경우
				return readBody(conn.getErrorStream());
			}
			
		} catch (Exception e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
			//System.out.println("post메소드 에러 : "+e);
		} finally {
			conn.disconnect();
		}
		
	}
	
	
	//api url연결메소드
	private static HttpURLConnection connect(String apiURL) {
		try {
			URL url = new URL(apiURL);
			return (HttpURLConnection)url.openConnection();
		} catch (Exception e) {
			System.out.println("connect 메소드에러 : "+e);
		}
		return null;
		
	}

	
	//body부분 읽어오는메서드
	private static String readBody(InputStream body) {
		//인풋스트림은 1byte씩인식해서 한글을 인식못하므로
		//여기서 인풋스트림 리더가 사용되는것 인풋스트림 리더는 char단위로 전송이 가능하다
		InputStreamReader streamReader = new InputStreamReader(body);
		
		//트라이부분에()넣는거 처음봤다
		try (BufferedReader lineReader = new BufferedReader(streamReader)){
			StringBuilder responseBody = new StringBuilder();
			String line;
			while((line=lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			return responseBody.toString();
			
		} catch (Exception e) {
			System.out.println("readBody 부분 에러 : "+e);
		}
		return null;
	}
}

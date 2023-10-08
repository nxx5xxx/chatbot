package com.okbs.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PapagoTransAPI {
	//재사용 할수있도록 키값 한번에 넣기
	
	final static String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	//번역할것 한글에서 영어
	String transLang = "en";
	String afterTransLang = "ko";

	//post로 보내서 받아오기
	public String post(Map<String, String> reqHeaders, String txt) {
		//System.out.println("post메서드 실행");
		HttpURLConnection conn = connect(apiURL);
		String txtData = "source="+transLang+"&target="+afterTransLang+"&text="+txt;
		try {
			conn.setRequestMethod("POST");
			//이부분 왜 for문으로 처리하는지모르겠다 
			// reqHeaders에 넣은 Map에는 내 네이버 클라이언트 id와 키코드밖에 없을텐데
			for(Map.Entry<String, String> header : reqHeaders.entrySet()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}
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
		//	throw new RuntimeException("API 요청과 응답 실패", e);
			System.out.println("post메소드 에러 : "+e);
		} finally {
			conn.disconnect();
		}
		return null;
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

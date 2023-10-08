package com.okbs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.okbs.collections.MenuDocument;
import com.okbs.collections.OpinionDocument;
import com.okbs.repository.MenuRepository;
import com.okbs.repository.OpinionRepository;

@SpringBootTest
class DBCollectionInsert {
	//Opinions에 데이터 추가하는 junit
	@Autowired
	OpinionRepository opinionRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	
	@Test
	@DisplayName("menu 콜렉션 추가")
	void menuInsert() {
		MenuDocument menuDocument = new MenuDocument();
		menuDocument.setTitles("도움말");
		menuDocument.setDesc("날씨 , 번역 , 메뉴3 등이 있습니다");
	}
	@Test
	@DisplayName("menu 콜렉션 추가2")
	void menuInsert2() {
		MenuDocument menuDocument = new MenuDocument();
		menuDocument.setTitles("날씨");
		menuDocument.setDesc("'궁금한지역 날씨정보'를 입력해보세요.(예: 제주도 날씨정보 , 김포한강11로 날씨정보)");
	}
	@Test
	@DisplayName("menu 콜렉션 추가3")
	void menuInsert3() {
		MenuDocument menuDocument = new MenuDocument();
		menuDocument.setTitles("번역");
		menuDocument.setDesc("'(문장) 번역해줘' 또는 '(문장) (결과언어)로 번역해줘' 를 입력해보세요<br>"
				+ "제공하는기능 : <br> 한>영어 , 한>일본어 , 한>중국어 , 한>프랑스 , <br>"
				+ "	영>한 , 영>일 , 영>프 , 영>중 <br>"
				+ "	일>영 ,일>한 ,일>중 , <br>"
				+ "	중>영 , 중>일 , 중>한 <br>");
	}
	
	@Test
	@DisplayName("Opinions 콜렉션 추가")
	void test() {
		OpinionDocument opinionDocument = new OpinionDocument();
		opinionDocument.setOpinions("도움말 , 날씨 , 번역 , 메뉴3");
		opinionRepository.insert(opinionDocument);
	}

}

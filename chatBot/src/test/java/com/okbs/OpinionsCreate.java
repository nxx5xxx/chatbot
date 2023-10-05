package com.okbs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.okbs.collections.OpinionDocument;
import com.okbs.repository.OpinionRepository;

@SpringBootTest
class OpinionsCreate {
	//Opinions에 데이터 추가하는 junit
	@Autowired
	OpinionRepository opinionRepository;
	
	@Test
	@DisplayName("Opinions 콜렉션 추가")
	void test() {
		OpinionDocument opinionDocument = new OpinionDocument();
		opinionDocument.setOpinions("도움말 , 메뉴1 , 메뉴2 , 메뉴3");
		opinionRepository.insert(opinionDocument);
	}

}

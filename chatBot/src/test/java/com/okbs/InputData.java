package com.okbs;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.okbs.collections.InputDocument;
import com.okbs.repository.InputRepository;

@SpringBootTest
class InputData {
	
	@Autowired
	InputRepository inputRepository;
	
	@Test
	@DisplayName("InputData")
	void test() {
		List<InputDocument> list = inputRepository.findAll();
//		System.out.println(inputRepository.findAll());
		for(InputDocument data : list) {
			System.out.println(data);
		}
	}

}

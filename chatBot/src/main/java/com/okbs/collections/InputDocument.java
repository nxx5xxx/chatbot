package com.okbs.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document("input")
@Getter @Setter @ToString
public class InputDocument {
	@Id
	private String _id;
	private String collections;
}

package com.okbs.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "menu")
@Getter @Setter @ToString
public class MenuDocument {
	@Id
	private String _id;
	private String titles;
	private String desc;
}

package com.okbs.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document("opinion")
@Getter @Setter @ToString
public class OpinionDocument {
	@Id
	private String _id;
	private String opinions;
}

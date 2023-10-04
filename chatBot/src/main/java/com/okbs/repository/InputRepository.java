package com.okbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.okbs.collections.InputDocument;

public interface InputRepository extends MongoRepository<InputDocument, String>{
	InputDocument findByCollections(String collections);
}

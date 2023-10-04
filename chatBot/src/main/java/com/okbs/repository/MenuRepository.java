package com.okbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.okbs.collections.MenuDocument;

public interface MenuRepository extends MongoRepository<MenuDocument, String>{
	MenuDocument findByTitles(String title);
}

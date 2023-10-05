package com.okbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.okbs.collections.OpinionDocument;

public interface OpinionRepository extends MongoRepository<OpinionDocument, String>{

}

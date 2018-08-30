package com.nikhilgupta.spring.repository;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.nikhilgupta.Book.Book;

import reactor.core.publisher.Mono;

@Repository
public interface CouchBaseRepository extends ReactiveCouchbaseRepository<Book, Integer> {

	public Mono<Book> findByName(String bookName);
}

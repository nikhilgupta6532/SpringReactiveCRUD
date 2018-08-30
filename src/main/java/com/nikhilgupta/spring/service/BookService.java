package com.nikhilgupta.spring.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import com.couchbase.client.deps.com.fasterxml.jackson.core.sym.Name;
import com.nikhilgupta.Book.Book;
import com.nikhilgupta.spring.repository.CouchBaseRepository;
import com.nikhilgupta.spring.wrapper.ChaptersRepository;
import com.nikhilgupta.spring.wrapper.NameRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

	@Autowired
	CouchBaseRepository repository;
	
	public Mono<Book> saveBookDetails(Mono<Book> book) {
		return book.flatMap(repository::save);
//		return book.flatMap(new Function<Book, Mono<Book>>() {
//
//			@Override
//			public Mono<Book> apply(Book book) {
//				return repository.save(book);
//			}
//		});
	}

	public Flux<Book> getAllBooks() {
		return repository.findAll();
	}
	
	public Mono<Book> getBook(int id) {
		System.out.println("Service class "+id);
		return repository.findById(id);
	}
	
	public Mono<Book> updateBookName(int id, Mono<NameRepository> newName) {
		System.out.println("Service "+id+" "+newName);
		Mono<Book> book = repository.findById(id);
		
		return newName.flatMap(nameRepo->{
			String name = nameRepo.getName();
			return book.flatMap(b -> {
				b.setName(name);
				return repository.save(b);
			});
		});
	}

	public Mono<Void> deleteBook(int bookId) {
		return repository.deleteById(bookId);
	}

	public Mono<Book> updateBookByName(String bookName, Mono<ChaptersRepository> chapRepo) {
		Mono<Book> book = repository.findByName(bookName);
		return chapRepo.flatMap(chapter->{
			String[] chapters = chapter.getChapters();
			return book.flatMap(b -> {
				b.setChapters(chapters);
				return repository.save(b);
			});
		});
	}
}

package com.nikhilgupta.spring.component;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nikhilgupta.Book.Book;
import com.nikhilgupta.spring.service.BookService;
import com.nikhilgupta.spring.wrapper.ChaptersRepository;
import com.nikhilgupta.spring.wrapper.NameRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {

	@Autowired
	BookService service;
	
	public Mono<ServerResponse> saveBookDetails(ServerRequest request){
		return ServerResponse.ok()
				.body(service.saveBookDetails(request.bodyToMono(Book.class)),Book.class);
	}
	
	public Mono<ServerResponse> getAllBooks(ServerRequest request) {
		Flux<Book> fluxBook = service.getAllBooks();
		return ServerResponse.ok()
				.body(fluxBook,Book.class);
	}
	
	public Mono<ServerResponse> getBook(ServerRequest request) {
		int bookId = Integer.valueOf(request.pathVariable("id"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Book> monoBook = service.getBook(bookId);
//		return fluxBook
//				.then(book -> ServerResponse.ok().body(fromObject(book)))
//				.otherwiseIfEmpty(notFound);
		return ServerResponse.ok()
				.body(monoBook,Book.class);
		
	}
	
	public Mono<ServerResponse> updateBookName(ServerRequest request) {
		int bookId = Integer.valueOf(request.pathVariable("id"));
		Mono<NameRepository> bodyToMono = request.bodyToMono(NameRepository.class);
		Mono<Book> updateName = service.updateBookName(bookId,bodyToMono);
		return ServerResponse.ok()
				.body(updateName,Book.class);
	}
	
	public Mono<ServerResponse> deleteBook(ServerRequest request) {
		int bookId = Integer.valueOf(request.pathVariable("id"));
		Mono<Void> deleteBook = service.deleteBook(bookId);
		return ServerResponse.ok()
				.body(deleteBook,Void.class);
	}
	
	public Mono<ServerResponse> updateBookByName(ServerRequest request) {
		String bookName = String.valueOf(request.pathVariable("name"));
		Mono<ChaptersRepository> chapRepo = request.bodyToMono(ChaptersRepository.class);
		Mono<Book>updateBookName = service.updateBookByName(bookName,chapRepo);
		return ServerResponse.ok()
				.body(updateBookName,Book.class);
	}
}

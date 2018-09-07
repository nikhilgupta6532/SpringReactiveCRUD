package com.nikhilgupta.spring.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.ReturnedType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Configuration
public class BookRouter {
	
	@Bean
	public RouterFunction<ServerResponse> routePath(BookHandler bookHandler) {
		return RouterFunctions.route(RequestPredicates.POST("/addBook"), bookHandler::saveBookDetails)
				.andRoute(RequestPredicates.GET("/getBooks"), bookHandler::getAllBooks)
				.andRoute(RequestPredicates.GET("/getBook/{id}"), bookHandler::getBook)
				.andRoute(RequestPredicates.POST("/updateName/{id}"), bookHandler::updateBookName)
				.andRoute(RequestPredicates.DELETE("/deleteBook/{id}"), bookHandler::deleteBook)
				.andRoute(RequestPredicates.POST("/updateBookByName/{name}"), bookHandler::updateBookByName)
				.andRoute(RequestPredicates.POST("/v2/narratives/{id}/versions"), bookHandler::addNarrativeVersions)
				;
	}

}

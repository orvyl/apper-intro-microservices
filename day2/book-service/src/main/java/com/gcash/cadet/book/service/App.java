package com.gcash.cadet.book.service;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner runner(IdGenerator idGenerator, BookService bookService) {
		return args -> {
			System.out.println("Hello, World!");
			System.out.println(idGenerator.getNext());
			System.out.println("++=++=");

			Book registeredBook = bookService.registerBook("ISBN123", "The SPring bOot Magic", "John Doe", 2016);
			System.out.println("The id of the new book is: " + registeredBook.getId());
		};
	}

	@Data
	public static class Book {
		private String id;
		private String isbn;
		private String title;
		private String author;
		private int publishedYear;
	}

	@Service
	public static class BookService {

		private final Map<String, Book> books = new HashMap<>();

		private final IdGenerator idGenerator;

		public BookService(IdGenerator idGenerator) {
			this.idGenerator = idGenerator;
		}

		public Book registerBook(String isbn, String title, String author, int publishedYear) {
			Book book = new Book();
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setAuthor(author);
			book.setPublishedYear(publishedYear);
			book.setId(idGenerator.getNext());

			books.put(isbn, book);

			return book;
		}
	}

	@Primary
	@Service
	public static class NotCoolIdGenerator implements IdGenerator {
		public String getNext() {
			return UUID.randomUUID().toString();
		}
	}

	@Service
	public static class CoolIdGenerator implements IdGenerator {
		public String getNext() {
			return "<3 <3 <3";
		}
	}

	public static interface IdGenerator {
		String getNext();
	}

}

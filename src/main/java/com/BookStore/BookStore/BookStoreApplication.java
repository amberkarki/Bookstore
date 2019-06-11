package com.BookStore.BookStore;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import com.BookStore.BookStore.domain.Book;
import com.BookStore.BookStore.domain.BookRepository;
import com.BookStore.BookStore.domain.Category;
import com.BookStore.BookStore.domain.CategoryRepository;
import com.BookStore.BookStore.domain.User;
import com.BookStore.BookStore.domain.UserRepository;

@SpringBootApplication
public class BookStoreApplication extends SpringBootServletInitializer{
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BookStoreApplication.class);
	}
	@Bean
	public CommandLineRunner bookrepo(BookRepository bkr, CategoryRepository ctr, UserRepository usr) {
		
		return (args) -> {
			//log.info("saving hard-coded book objects");
			
			ctr.save(new Category("IT"));
			ctr.save(new Category("Programming"));
			ctr.save(new Category("Romance"));
			ctr.save(new Category("Science"));
			bkr.save(new Book("Mybook","Myauthor","Mydate","MyISBN","MyPrice", ctr.findByName("Science").get(0)));
			bkr.save(new Book("HisBook","HisAuthor","HisDate","HisISBN","HisPrice", ctr.findByName("Programming").get(0)));
			bkr.save(new Book("Zbook","Zauthor","Zdate","zISBN","Zprice", ctr.findByName("Romance").get(0)));
			bkr.save(new Book("Abook","Aauthor","Adate","aISBN","Aprice", ctr.findByName("Science").get(0)));
			
			// Create users: admin/admin user/user
						User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
						User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
						usr.save(user1);
						usr.save(user2);
			
			log.info("getting all books");
			for (Book book : bkr.findAll()) {
				log.info(book.toString());
			}

		};
	}
}


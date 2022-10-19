package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("Demo Publisher", "111 Demo St", "Demoville", "DE", "12345");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        authorRepository.save(eric);

        Book ddd = new Book("Domain Driven Design", "123123");
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        authorRepository.save(rod);

        Book noEJB = new Book("J2EE Development without EJB", "321321");
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println(String.format("Number of Books: %s", bookRepository.count()));

        System.out.println(String.format("Number of Publishers: %s", publisherRepository.count()));

        publisher.getBooks().add(ddd);
        publisher.getBooks().add(noEJB);
        publisherRepository.save(publisher);
        System.out.println(String.format("Publisher Number of Books: %s", publisher.getBooks().size()));
    }
}

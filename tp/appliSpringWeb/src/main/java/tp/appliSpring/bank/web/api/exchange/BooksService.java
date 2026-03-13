package tp.appliSpring.bank.web.api.exchange;

import org.springframework.http.ResponseEntity;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/rest/api-bank/v1")
public interface BooksService {
    @GetExchange("/books")
    //@Retryable( maxRetries = 3 , delay = 100, multiplier = 2, maxDelay = 1000  )
    //@ConcurrencyLimit(10)
    List<Book> getBooks();

    @GetExchange("/books/{id}")
    Book getBook(@PathVariable long id);

    @PostExchange("/books")
    Book saveBook(@RequestBody Book book);

    @DeleteExchange("/books/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable long id);
}


/*
@Retryable( maxRetries = 3 , delay = 100, multiplier = 2, maxDelay = 1000  )
@ConcurrencyLimit(10)
coorespondent à des annotations récentes de la famille "ResilientMethods"
ces annotations sont interprétées si @EnableResilientMethods est placée sur une classe de configuration

@Configuration
@EnableResilientMethods
public class ResilienceConfig {
    // Additional configuration if needed
}
 */
package tp.appliSpring.bank.web.api.exchange;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

public class BooksBasicClientApp {
    public static void main(String[] args) {
        RestClient restClient = RestClient.builder().baseUrl("http://localhost:8181/appliSpring/").build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        //avec HttpServiceProxyFactory et interface comportant @HttpExchange
        //sorte de RPC automatique  java/spring <--> java/spring au dessus de REST/HTTP
        //un peu comme JAX-RS2
        HttpServiceProxyFactory httpServiceProxyFactory=HttpServiceProxyFactory.builderFor(adapter).build();
        BooksService booksService = httpServiceProxyFactory.createClient(BooksService.class);

        List<Book> books = booksService.getBooks();
        System.out.println("books="+books);

        //NB: on peut facilement via @Configuration/@Bean configurer un "BooksServiceClient" Bean
        // à injecter ailleurs dans l'application cliente spring ayant accès aux interfaces "BooksService" et aux classes de données associées
    }
}

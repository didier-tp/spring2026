package tp.appliSpring.bank.web.api.exchange;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController //@Component de type controller d'api rest
@RequestMapping(value="/rest/api-bank/v1", headers="Accept=application/json" )
public class BooksServiceRestImpl implements BooksService{
    private long lastId=0;
    private Map<Long,Book> mapBooks = new HashMap<>();

    BooksServiceRestImpl(){
        this.saveBook(new Book(1L,"b1"));
        this.saveBook(new Book(2L,"b2"));
    }

    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(mapBooks.values());
    }

    @Override
    public Book getBook(long id) {
        return mapBooks.get(id);
    }

    @Override
    public Book saveBook(Book book) {
        book.setId(++lastId);
        mapBooks.put(book.getId(),book);
        return  book;
    }

    @Override
    public ResponseEntity<Void> deleteBook(long id) {
        mapBooks.remove(id);
        return ResponseEntity.noContent().build();
    }
}

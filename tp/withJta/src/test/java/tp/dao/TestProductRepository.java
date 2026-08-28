package tp.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import tp.dao.main.ProductRepository;
import tp.entity.main.Product;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class TestProductRepository {

    private final ProductRepository productRepository;

    @Test
    public void testProduct(){
        productRepository.deleteAll();
        productRepository.save(new Product(null,"stylo bille" , 1.2));
        productRepository.save(new Product(null,"trousse" , 3.2));
        Product p = new Product(null,"gomme",1.9);
        productRepository.save(p);
        List<Product> products = productRepository.findAll();
        log.info("products:"+products.toString());
        int nb = products.size();
        assertTrue(nb >=3);
    }
}

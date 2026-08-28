package tp.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import tp.dao.main.CustomerRepository;
import tp.entity.main.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class TestCustomerRepository {

    private final CustomerRepository customerRepository;

    @Test
    public void testCustomer(){
        customerRepository.deleteAll();
        customerRepository.save(new Customer(null,"jean.bon@aaa.fr"));
        customerRepository.save(new Customer(null,"alex.therieur"));
        Customer c = new Customer(null,"toto@xyz.com");
        customerRepository.save(c);
        List<Customer> customers = customerRepository.findAll();
        int nb = customers.size();
        log.info("customers:"+customers.toString());
        assertTrue(nb >=3);
    }
}

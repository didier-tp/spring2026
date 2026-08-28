package tp.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import tp.dao.main.CustomerRepository;
import tp.dao.main.OrderRepository;
import tp.dao.main.ProductRepository;
import tp.entity.main.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class TestOrderRepository {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Test
    public void testOrders(){

        Order o1 = new Order(1L);
        orderRepository.save(o1); //first save to generate order_id
        o1.addOrderLine(1,5);
        o1.addOrderLine(2 , 6);
        orderRepository.save(o1);//second save/update to store/attach orderLine

        Order o2 = new Order(2L);
        orderRepository.save(o2); //first save to generate order_id
        o2.addOrderLine(1,3);
        o2.addOrderLine(2 , 4);
        orderRepository.save(o2);//second save/update to store/attach orderLine

        List<Order> orders = orderRepository.findAll();
        int nb = orders.size();
        assertTrue(nb >=2);
        log.info("orders="+orders);
    }
}

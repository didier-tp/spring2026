package tp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.dao.main.CustomerRepository;
import tp.dao.main.OrderRepository;
import tp.dao.main.ProductRepository;
import tp.dao.purchase.PurchaseRepository;
import tp.entity.main.Order;
import tp.entity.main.Product;
import tp.entity.purchase.Purchase;
import tp.model.ProductSelection;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    private final OrderRepository orderRepository;
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    public long savePurchaseOrder(long customerId, List<ProductSelection> productSelections) {
        //this method will be executed within global JTA transaction (with two phases commit / XA jdbc drivers)
        //temporary SQL statements will be either all commited , either all rollbacked .

        //1 store order while computing global amount/price (in database 1)
        double globalPrice = 0;
        Order o = new Order(customerId);
        orderRepository.save(o); //first save/insert to generate order_id
        for(ProductSelection selection : productSelections) {
            Product prod = productRepository.findById(selection.getProductId()).get();
            o.addOrderLine(prod.getId(), selection.getQuantity());
            double linePrice = selection.getQuantity() * prod.getPrice();
            globalPrice+=linePrice;
        }
        orderRepository.save(o);//second save/update to store/attach orderLine

        //2 store purchase with computed price (in database 2)
        Purchase p = new Purchase(null,customerId,globalPrice,o.getId());
        purchaseRepository.save(p);

        /*
        //3 check valid customerId to visualize global JTA transaction (rollbacked if customerId does not exists)
        if(!customerRepository.existsById(customerId))
            throw new RuntimeException("invalid customerId , purchaseOrder transaction will be rollbacked");
        */

        //NB: this transaction may be automatically rollbacked if the computed amount of order and purchase
        //is greater than the maximum value of column amount of table purchase (ex: amount float(53) not null CHECK (amount <= 1000))

        return p.getId();
    }
}

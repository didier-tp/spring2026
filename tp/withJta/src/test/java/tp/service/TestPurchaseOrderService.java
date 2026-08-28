package tp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import tp.dao.main.CustomerRepository;
import tp.dao.main.OrderRepository;
import tp.dao.main.ProductRepository;
import tp.dao.purchase.PurchaseRepository;
import tp.entity.main.Customer;
import tp.entity.main.Product;
import tp.model.ProductSelection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class TestPurchaseOrderService {

    private final PurchaseOrderService purchaseOrderService;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final OrderRepository orderRepository;


    private static long idProdA,idProdB,idCustomerY;

    private void reinitDataSet(double priceProdB){
        productRepository.deleteAll();
        idProdA = productRepository.save(new Product(null,"stylo bille" , 1.2)).getId();
        idProdB = productRepository.save(new Product(null,"trousse" , priceProdB)).getId();
        customerRepository.deleteAll();
        idCustomerY = customerRepository.save(new Customer(null,"jean.bon@aaa.fr")).getId();
        customerRepository.save(new Customer(null,"alex.therieur"));
    }

    @Test
    public void testSavePurchaseOrderWithAcceptedAmount(){
        this.reinitDataSet(35.78);
        orderRepository.deleteAll();
        purchaseRepository.deleteAll();
        List<ProductSelection> productSelections = new ArrayList<>();
        productSelections.add(new ProductSelection(idProdA,1));
        productSelections.add(new ProductSelection(idProdB,3));
        long purchaseId = purchaseOrderService.savePurchaseOrder(idCustomerY,productSelections);
        log.info("purchaseId="+purchaseId);
    }

    @Test
    public void testRejectSavePurchaseOrderWithInvalidAmount(){
        this.reinitDataSet(350);
        List<ProductSelection> productSelections = new ArrayList<>();
        productSelections.add(new ProductSelection(idProdA,1));
        productSelections.add(new ProductSelection(idProdB,3)); //3*350 = 1050 = plus que le maximum autorisé de 1000 sur la valeur de la colonne amount de la table purchase de la base de données purchase_db
        Exception exception = assertThrows(RuntimeException.class, () -> {
            purchaseOrderService.savePurchaseOrder(idCustomerY ,productSelections);
        });
        log.info("expected exception : " + exception.getMessage());
    }
}

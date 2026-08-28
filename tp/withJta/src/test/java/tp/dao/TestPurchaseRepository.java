package tp.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import tp.dao.purchase.PurchaseRepository;
import tp.entity.purchase.Purchase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class TestPurchaseRepository {

    private final PurchaseRepository purchaseRepository;

    @Test
    public void testPurchases(){
        purchaseRepository.deleteAll();
        purchaseRepository.save(new Purchase(null, 1,120.0,1));
        purchaseRepository.save(new Purchase(null,  2 , 157.8,2));
        purchaseRepository.save(new Purchase(null,1,267.9,3));
        List<Purchase> purchases = purchaseRepository.findAll();
        int nb = purchases.size();
        assertTrue(nb ==3);
        log.info("purchases:"+purchases.toString());
    }
}

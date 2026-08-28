package tp.service;

import tp.model.ProductSelection;

import java.util.List;

public interface PurchaseOrderService {
    //very simple implementation (just for testing JTA transaction accross several databases ).
    public long savePurchaseOrder(long customerId, List<ProductSelection> productSelections); //return purchase_id
}

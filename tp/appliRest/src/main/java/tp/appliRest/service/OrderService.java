package tp.appliRest.service;

import tp.appliRest.model.Customer;
import tp.appliRest.model.Order;

import java.util.List;

public interface OrderService {
    public Order searchById(String id);
    public List<Order> getAllOrdersForCustomer(String customerId);
}

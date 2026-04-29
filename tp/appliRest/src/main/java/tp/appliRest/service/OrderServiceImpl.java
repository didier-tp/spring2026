package tp.appliRest.service;

import org.springframework.stereotype.Service;
import tp.appliRest.model.Customer;
import tp.appliRest.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    private Map<String, Order> mapOrder = new HashMap<>();

    public OrderServiceImpl(){
        mapOrder.put("1",new Order("1",12.5));
        mapOrder.put("2",new Order("2",8.5));
        mapOrder.put("3",new Order("3",2.5));
        mapOrder.put("4",new Order("4",4.5));
    }

    @Override
    public Order searchById(String id) {
        return mapOrder.get(id);
    }

    @Override
    public List<Order> getAllOrdersForCustomer(String customerId) {
        switch(customerId){
            case "1":  return List.of(mapOrder.get("1") , mapOrder.get("3"));
            case "2": return List.of(mapOrder.get("2") , mapOrder.get("4"));
            default: return List.of();
        }
    }
}

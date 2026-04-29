package tp.appliRest.service;

import org.springframework.stereotype.Service;
import tp.appliRest.model.Customer;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<String,Customer> mapCustomer = new HashMap<>();

    public CustomerServiceImpl(){
        mapCustomer.put("1",new Customer("1","toto","company1"));
        mapCustomer.put("2",new Customer("2","titi","company2"));
    }

    @Override
    public Customer searchById(String id) {
        return mapCustomer.get(id);
    }
}

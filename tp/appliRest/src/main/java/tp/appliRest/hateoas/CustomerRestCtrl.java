package tp.appliRest.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.appliRest.model.Customer;
import tp.appliRest.model.Order;
import tp.appliRest.service.CustomerService;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import tp.appliRest.service.OrderService;

import java.util.List;

@RestController
@RequestMapping(value = "/my-rest-api/v1/customers")
public class CustomerRestCtrl {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;


    private Customer getCustomerById(@PathVariable String id) {
        Customer customer = customerService.searchById(id);
        return customer;
    }


    private ResponseEntity<Customer> getCustomerByIdV2(@PathVariable String id) {
        Customer customer = customerService.searchById(id);
        if(customer==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Customer>> getCustomerRepModelById(@PathVariable String id) {
        Customer customer = this.getCustomerById(id);
        if(customer==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        Link selfLink = WebMvcLinkBuilder.linkTo(CustomerRestCtrl.class).slash(customer.getId()).withSelfRel();
        Link ordersLink = WebMvcLinkBuilder.linkTo(CustomerRestCtrl.class).slash(customer.getId()).slash("/orders").withRel("orders");
        EntityModel<Customer> customerRepModel = EntityModel.of(customer,selfLink,ordersLink);
        return ResponseEntity.ok(customerRepModel);
    }


    @GetMapping(value = "/{customerId}/orders")
      public CollectionModel<Order> getOrdersForCustomer(@PathVariable final String customerId){
        List<Order> orders = orderService.getAllOrdersForCustomer(customerId);

        //return orders;
        Link customerlink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerRestCtrl.class)
                .getCustomerRepModelById(customerId)).withRel("customer");
        CollectionModel<Order> ordersRepModel = CollectionModel.of(orders, customerlink);
        return ordersRepModel;
    }
}

/*
old version without wrapper:
@GetMapping("/{id}")
    private Customer getCustomerById(@PathVariable String id) {
        Customer customer = customerService.searchById(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(CustomerRestCtrl.class).slash(customer.getId()).withSelfRel();
        customer.removeLinks();
        customer.add(selfLink);
        Link ordersLink = WebMvcLinkBuilder.linkTo(CustomerRestCtrl.class).slash(customer.getId()).slash("/orders").withRel("orders");
        customer.add(ordersLink);
        return customer;
    }
 */

/*
old version without ResponseEntity<T> :
 @GetMapping("/{id}")
    public EntityModel<Customer> getCustomerRepModelById(@PathVariable String id) {
        Customer customer = this.getCustomerById(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(CustomerRestCtrl.class).slash(customer.getId()).withSelfRel();
        Link ordersLink = WebMvcLinkBuilder.linkTo(CustomerRestCtrl.class).slash(customer.getId()).slash("/orders").withRel("orders");
        EntityModel<Customer> customerRepModel = EntityModel.of(customer,selfLink,ordersLink);
        return customerRepModel;
    }
 */

/*
Version complexe avec liste comportant des liens:

 @GetMapping(value = "/{customerId}/orders")
public CollectionModel<EntityModel<Order>> getOrdersForCustomer(@PathVariable final String customerId){

    List<Order> orders = orderService.getAllOrdersForCustomer(customerId);

    List<EntityModel<Order>> orderRepModelList = new ArrayList<>();
    for (final Order order : orders) {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderRestCtrl.class)
                .getOrderRepModelById(order.getId())).withSelfRel();
        orderRepModelList.add(EntityModel.of(order,selfLink));
    }
    //return orders;
    Link customerlink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerRestCtrl.class)
            .getCustomerRepModelById(customerId)).withRel("customer");
    CollectionModel<EntityModel<Order>> ordersRepModel = CollectionModel.of(orderRepModelList, customerlink);
    //CollectionModel<Order> ordersRepModel = CollectionModel.of(orders, customerlink);
    return ordersRepModel;
}
 */
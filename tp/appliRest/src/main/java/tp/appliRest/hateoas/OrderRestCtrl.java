package tp.appliRest.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.appliRest.model.Order;
import tp.appliRest.service.OrderService;

@RestController
@RequestMapping(value = "/my-rest-api/v1/orders")
public class OrderRestCtrl {
      @Autowired
    private OrderService orderService;

    //@GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        Order order = orderService.searchById(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(OrderRestCtrl.class).slash(order.getId()).withSelfRel();
        return order;
    }

    @GetMapping("/{id}")
    public EntityModel<Order> getOrderRepModelById(@PathVariable String id) {
        Order order = this.getOrderById(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(OrderRestCtrl.class).slash(order.getId()).withSelfRel();
        return EntityModel.of(order,selfLink);
    }


}

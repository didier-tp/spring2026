package tp.appliRest.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {
    private String id;
    private double price;
    private LocalDate date=LocalDate.now();

    public Order(String id, double price) {
        this.id = id;
        this.price = price;
    }
}

/*
old version without wrapper:
public class Order extends RepresentationModel<Order> {
...
}
 */

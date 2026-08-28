package tp.entity.main;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_line")
@Getter
@Setter
@NoArgsConstructor
public class OrderLine {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(
                    name = "orderId",
                    column = @Column(name = "order_id")),
            @AttributeOverride(
                    name = "productId",
                    column = @Column(name = "product_id"))
    })
    private OrderLinePk orderLinePk;


    @ManyToOne
    @JoinColumn(name="order_id" , insertable = false, updatable = false)
    private Order order;

    private Integer quantity;

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLinePk=" + orderLinePk +
                ", quantity=" + quantity +
                '}';
    }

    public OrderLine(OrderLinePk orderLinePk, Order order, Integer quantity) {
        this.orderLinePk = orderLinePk;
        this.order = order;
        this.quantity = quantity;
    }
}
